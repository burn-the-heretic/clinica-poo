package br.edu.imepac.services.impl;

import br.edu.imepac.dao.AdminDAO;
import br.edu.imepac.dao.MedicoDAO;
import br.edu.imepac.dto.ConsultaResponseDTO;
import br.edu.imepac.dto.ProntuarioRequestDTO;
import br.edu.imepac.dto.ProntuarioResponseDTO;
import br.edu.imepac.entities.Consulta;
import br.edu.imepac.entities.Funcionario;
import br.edu.imepac.entities.Prontuario;
import br.edu.imepac.services.MedicoService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MedicoServiceImpl implements MedicoService {

    private final MedicoDAO medicoDAO;
    private final AdminDAO adminDAO;

    public MedicoServiceImpl(MedicoDAO medicoDAO, AdminDAO adminDAO) {
        this.medicoDAO = medicoDAO;
        this.adminDAO = adminDAO;
    }

    @Override
    public ProntuarioResponseDTO cadastrarProntuario(ProntuarioRequestDTO prontuario) throws SQLException {
        Prontuario newProntuario = toEntity(prontuario);
        medicoDAO.cadastrarPronturario(newProntuario);
        return toResponse(newProntuario);
    }

    @Override
    public ProntuarioResponseDTO buscarProntuario(int id) throws SQLException {
        Prontuario prontuario = medicoDAO.getProntuarioById(id);
        if (prontuario == null) {
            throw new SQLException("Prontuario com id"+id+" encontrado");
        }
        return toResponse(prontuario);
    }

    @Override
    public ProntuarioResponseDTO atualizarProntuario(ProntuarioRequestDTO prontuario) throws SQLException {
        Prontuario newProntuario = toEntity(prontuario);
        medicoDAO.updateProntuario(
                newProntuario.getReceituario(),
                newProntuario.getExames(),
                newProntuario.getObservacao() ,
                newProntuario.getId()
        );
        return  toResponse(newProntuario);
    }

    @Override
    public void removerProntuarios(int id) throws SQLException {
           Prontuario prontuario = medicoDAO.getProntuarioById(id);
           if (prontuario == null) {
               throw new SQLException("Prontuario com id"+id+" encontrado");
           }
           medicoDAO.deleteProntuario(id);
    }

    @Override
    public List<ConsultaResponseDTO> listMyConsultas(int id) throws SQLException {
        Funcionario funcionario = adminDAO.getFuncionarioById(id);
        List<Consulta> consultas = medicoDAO.listMyConsultas(funcionario.getId());
        return consultas.stream()
                .map(this::toResponseConsulta)
                .collect(Collectors.toList());
    }

    private Prontuario toEntity(ProntuarioRequestDTO dto){
        Prontuario prontuario= new Prontuario();
        prontuario.setExames(dto.exame());
        prontuario.setObservacao(dto.observacao());
        prontuario.setReceituario(dto.receituario());
        return prontuario;
    }

    private ProntuarioResponseDTO toResponse(Prontuario prontuario){
        return new ProntuarioResponseDTO(
                prontuario.getId(),
                prontuario.getReceituario(),
                prontuario.getExames(),
                prontuario.getObservacao()
        );
    }

    private ConsultaResponseDTO toResponseConsulta(Consulta consulta){
        return new ConsultaResponseDTO(consulta.getDataHorario(),consulta.getSintomas(), consulta.iseRetorno(),
                consulta.isEstaAtiva(),consulta.getPaciente().getId(),consulta.getProntuario().getId(),
                consulta.getConvenio().getId());
    }
}
