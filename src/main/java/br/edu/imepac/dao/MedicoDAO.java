package br.edu.imepac.dao;

import br.edu.imepac.entities.Consulta;
import br.edu.imepac.entities.Paciente;
import br.edu.imepac.entities.Prontuario;

import java.sql.SQLException;
import java.util.List;

public interface MedicoDAO {
    List<Consulta> listMyConsultas(final int medicoId) throws SQLException;


    int cadastrarPronturario(final Prontuario prontuario) throws SQLException;
    Prontuario getProntuarioById(final int id) throws SQLException;
    List<Prontuario> listAllProntuarios() throws SQLException;
    int deleteProntuario(final int id) throws SQLException;
    int updateProntuario(final String receituario,
                         final String exames,
                         final String observacao,
                         final int whereId)  throws SQLException;
}
