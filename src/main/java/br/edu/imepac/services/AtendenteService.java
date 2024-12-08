package br.edu.imepac.services;

import br.edu.imepac.dto.CadastroConsultaDTO;
import br.edu.imepac.dto.CadastroPacienteDTO;
import br.edu.imepac.dto.ConsultaResponseDTO;
import br.edu.imepac.dto.PacienteResponseDTO;
import br.edu.imepac.entities.Consulta;

import java.sql.SQLException;
import java.util.List;

public interface AtendenteService {
    //consulta
    ConsultaResponseDTO cadastrarConsulta(CadastroConsultaDTO consulta) throws SQLException;
    ConsultaResponseDTO buscarConsulta(int id) throws SQLException;
    ConsultaResponseDTO atualizarConsulta(CadastroConsultaDTO  consulta) throws SQLException;
    void removerConsulta(int id) throws SQLException;
    List<ConsultaResponseDTO> listarConsultas() throws SQLException;


    //paciente
    PacienteResponseDTO cadastrarPaciente(CadastroPacienteDTO paciente) throws SQLException;
    PacienteResponseDTO buscarPacienteByCpf(String cpf) throws SQLException;
    PacienteResponseDTO atualizarPaciente(CadastroPacienteDTO paciente) throws SQLException;
    void removerPaciente(String cpf) throws SQLException;
    List<PacienteResponseDTO> listarPacientes() throws SQLException;
}
