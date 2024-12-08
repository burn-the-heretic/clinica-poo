package br.edu.imepac.dao;

import br.edu.imepac.entities.Consulta;
import br.edu.imepac.entities.Paciente;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface AtendenteDAO {
    void cadastrarConsulta(final Consulta consulta) throws SQLException;
    Consulta getConsultaById(final int id) throws SQLException;
    List<Consulta> listAllConsultas() throws SQLException;
    void deleteConsulta(final int id) throws SQLException;
    int updateConsulta(final String sintomas ,
                       final boolean eRetorno,
                       final boolean estaAtiva,
                       final int pacienteId,
                       final int prontuarioId,
                       final int convenioId,
                       final int whereId)  throws SQLException;

    int cadastrarPaciente(final Paciente paciente) throws SQLException;
    Paciente getPacienteByCpf(final String cpf) throws SQLException;
    List<Paciente> listAllPacientes() throws SQLException;
    int deletePaciente(final String cpf) throws SQLException;
    int updatePaciente(String nome,
                       int idade,
                       char sexo,
                       String cpf,
                       String rua,
                       String numero,
                       String complemento,
                       String bairro,
                       String cidade,
                       String estado,
                       String contato,
                       String email,
                       LocalDateTime data_nascimento,
                       final int whereId) throws SQLException;
}
