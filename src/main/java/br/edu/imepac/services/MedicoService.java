package br.edu.imepac.services;

import br.edu.imepac.dto.ConsultaResponseDTO;
import br.edu.imepac.dto.ProntuarioRequestDTO;
import br.edu.imepac.dto.ProntuarioResponseDTO;
import java.sql.SQLException;
import java.util.List;

public interface MedicoService {

    ProntuarioResponseDTO cadastrarProntuario(ProntuarioRequestDTO prontuario) throws SQLException;
    ProntuarioResponseDTO buscarProntuario(int id) throws SQLException;
    ProntuarioResponseDTO atualizarProntuario(ProntuarioRequestDTO prontuario) throws SQLException;
    void removerProntuarios(int id) throws SQLException;
    List<ConsultaResponseDTO> listMyConsultas(int id) throws SQLException;
}
