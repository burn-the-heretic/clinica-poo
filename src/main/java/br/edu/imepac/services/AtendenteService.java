package br.edu.imepac.services;

import br.edu.imepac.dto.CadastroConsultaDTO;
import br.edu.imepac.dto.ConsultaResponseDTO;
import br.edu.imepac.entities.Consulta;

import java.sql.SQLException;
import java.util.List;

public interface AtendenteService {
    ConsultaResponseDTO cadastrarConsulta(CadastroConsultaDTO consulta) throws SQLException;
    ConsultaResponseDTO buscarConsulta(int id) throws SQLException;
    ConsultaResponseDTO atualizarConsulta(CadastroConsultaDTO  consulta);
    void removerConsulta(int id) throws SQLException;
    List<ConsultaResponseDTO> listarConsultas() throws SQLException;
}
