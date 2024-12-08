package br.edu.imepac.services.impl;

import br.edu.imepac.dao.AtendenteDAO;
import br.edu.imepac.dto.CadastroConsultaDTO;
import br.edu.imepac.dto.CadastroPacienteDTO;
import br.edu.imepac.dto.ConsultaResponseDTO;
import br.edu.imepac.dto.PacienteResponseDTO;
import br.edu.imepac.entities.Consulta;
import br.edu.imepac.entities.Paciente;
import br.edu.imepac.services.AtendenteService;

import java.sql.SQLException;
import java.util.List;


public class AtendenteServiceImpl implements AtendenteService {

    //implementação da interface
    private final AtendenteDAO atendenteDAO;

    public AtendenteServiceImpl(AtendenteDAO atendenteDAO) {
        this.atendenteDAO = atendenteDAO;
    }

    //TESTAR
    //CONSULTA
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
    public ConsultaResponseDTO atualizarConsulta(CadastroConsultaDTO consulta) throws SQLException {
        if (consulta == null || consulta .paciente() == null || consulta .sintomas() == null) {
            throw new IllegalArgumentException("Dados incompletos para atualizar a consulta.");
        }
        Consulta newConsulta = toEntity(consulta);
        atendenteDAO.updateConsulta(
                newConsulta.getSintomas(),
                newConsulta.iseRetorno(),
                newConsulta.isEstaAtiva(),
                newConsulta.getPaciente().getId(),
                newConsulta.getProntuario().getId(),
                newConsulta.getConvenio().getId(),
                newConsulta.getId()
        );
        return toResponse(newConsulta);
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

    //Paciente
    //TESTAR
    @Override
    public PacienteResponseDTO cadastrarPaciente(CadastroPacienteDTO paciente) throws SQLException {
        if (paciente == null || paciente.cpf() == null || paciente.nome() == null || paciente.contato() == null || paciente.dataNascimento() == null ){
            throw new RuntimeException("Dados requeridos incompletos");
        }
        Paciente newPaciente = toEntityPaciente(paciente);
        newPaciente = atendenteDAO.getPacienteByCpf(newPaciente.getCpf());
        if (newPaciente.getCpf() != null){
            throw new RuntimeException("Paciente com este cpf já existe");
        }
        atendenteDAO.cadastrarPaciente(newPaciente);
        return toResponsePaciente(newPaciente);
    }

    @Override
    public PacienteResponseDTO buscarPacienteByCpf(String cpf) throws SQLException {
        Paciente paciente = atendenteDAO.getPacienteByCpf(cpf);
        if (paciente == null) {
            throw new RuntimeException("Paciente com o cpf "+cpf+" não existe");
        }
        return toResponsePaciente(paciente);
    }

    @Override
    public PacienteResponseDTO atualizarPaciente(CadastroPacienteDTO paciente) throws SQLException {
        if (paciente == null || paciente.cpf() == null){
            throw new RuntimeException("Dados requeridos incompletos");
        }
        Paciente newPaciente = toEntityPaciente(paciente);
        atendenteDAO.updatePaciente(
                newPaciente.getNome(),
                newPaciente.getIdade(),
                newPaciente.getSexo(),
                newPaciente.getCpf(),
                newPaciente.getRua(),
                newPaciente.getNumero(),
                newPaciente.getComplemento(),
                newPaciente.getBairro(),
                newPaciente.getCidade(),
                newPaciente.getEstado(),
                newPaciente.getContato(),
                newPaciente.getEmail(),
                newPaciente.getDataNascimento(),
                newPaciente.getId()
        );
        return toResponsePaciente(newPaciente);
    }
    @Override
    public void removerPaciente(String cpf) throws SQLException {
        Paciente paciente = atendenteDAO.getPacienteByCpf(cpf);
        if (paciente == null){
            throw new SQLException("Paciente com cpf "+cpf+" não encontrado");
        }
        atendenteDAO.deletePaciente(cpf);
        System.out.println("Paciente com o cpf: "+cpf+ " removido com sucesso");
    }

    @Override
    public List<PacienteResponseDTO> listarPacientes() throws SQLException {
        return atendenteDAO.listAllPacientes()
                .stream()
                .map(this::toResponsePaciente)
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

    private Paciente toEntityPaciente(CadastroPacienteDTO dto){
        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setSexo(dto.sexo());
        paciente.setIdade(dto.idade());
        paciente.setBairro(dto.bairro());
        paciente.setCidade(dto.cidade());
        paciente.setEstado(dto.estado());
        paciente.setRua(dto.rua());
        paciente.setNumero(dto.numero());
        paciente.setComplemento(dto.complemento());
        paciente.setCpf(dto.cpf());
        paciente.setContato(dto.contato());
        paciente.setEmail(dto.email());
        paciente.setDataNascimento(dto.dataNascimento());
        return paciente;
    }

    private PacienteResponseDTO toResponsePaciente(Paciente paciente){
        return new PacienteResponseDTO(
                paciente.getId(), paciente.getNome(), paciente.getIdade(),
                paciente.getSexo(), paciente.getCpf(), paciente.getRua(),
                paciente.getNumero(), paciente.getComplemento(), paciente.getBairro(),
                paciente.getCidade(), paciente.getEstado(), paciente.getContato(),
                paciente.getEmail(), paciente.getDataNascimento()
        );
    }
}
