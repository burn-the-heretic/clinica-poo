package br.edu.imepac.services.impl;

import br.edu.imepac.dao.AtendenteDAO;
import br.edu.imepac.dao.Impl.AtendenteDAOImpl;
import br.edu.imepac.dto.CadastroConsultaDTO;
import br.edu.imepac.dto.ConsultaResponseDTO;
import br.edu.imepac.entities.Consulta;
import br.edu.imepac.services.AtendenteService;

import java.sql.SQLException;
import java.util.List;


public class AtendenteServiceImpl implements AtendenteService {

    //implementação da
    private final AtendenteDAO atendenteDAO;

    public AtendenteServiceImpl(AtendenteDAO atendenteDAO) {
        this.atendenteDAO = atendenteDAO;
    }

    @Override
    public ConsultaResponseDTO cadastrarConsulta(CadastroConsultaDTO consulta) throws SQLException {
        if (consulta == null || consulta.paciente() == null || consulta.sintomas() == null) {
            throw new IllegalArgumentException("Dados incompletos para cadastro da consulta.");
        }
        Consulta newConsulta = toEntity(consulta);
        atendenteDAO.cadastrarConsulta(newConsulta);
        return toResponse(newConsulta);
    }

    @Override
    public ConsultaResponseDTO buscarConsulta(int id) throws SQLException {
        Consulta consulta = atendenteDAO.getConsultaById(id);
        if (consulta == null){
            throw new SQLException("Consulta com o id "+id+" não existe");
        }
        return toResponse(consulta);
    }

    @Override
    public ConsultaResponseDTO atualizarConsulta(CadastroConsultaDTO consulta) {
        return null;
    }

    @Override
    public void removerConsulta(int id) throws SQLException {
        Consulta consulta = atendenteDAO.getConsultaById(id);
        if (consulta == null){
            throw new SQLException("Consulta com id "+id+" não encontrada");
        }
        atendenteDAO.deleteConsulta(id);
        System.out.println("Consulta removida com id "+id);
    }

    @Override
    public List<ConsultaResponseDTO> listarConsultas() throws SQLException {
        return atendenteDAO.listAllConsultas()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private Consulta toEntity(CadastroConsultaDTO dto){
        Consulta consulta = new Consulta();
        consulta.setDataHorario(dto.dataHorario());
        consulta.setSintomas(dto.sintomas());
        consulta.seteRetorno(dto.eRetorno());
        consulta.setEstaAtiva(dto.estaAtiva());
        consulta.setProntuario(dto.prontuario());
        consulta.setPaciente(dto.paciente());
        consulta.setConvenio(dto.convenio());
        return consulta;
    }

    private ConsultaResponseDTO toResponse(Consulta consulta){
        return new ConsultaResponseDTO(consulta.getDataHorario(),consulta.getSintomas(), consulta.iseRetorno(),
                consulta.isEstaAtiva(),consulta.getPaciente().getId(),consulta.getProntuario().getId(),
                consulta.getConvenio().getId());
    }
}
