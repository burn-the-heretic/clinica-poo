package br.edu.imepac.dao;

import br.edu.imepac.entities.*;
import br.edu.imepac.enums.Tipo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface AdminDAO {

    int updateEspecialidade(String nome, String descricao, final int id) throws SQLException;
    List<Especialidade> listAllEspecialidades() throws SQLException;
    int deleteEspecialidadeById(final int id)throws SQLException;
    Especialidade getEspecialidadeById(final int id)throws SQLException;
    int cadastrarEspecialidade(Especialidade especialidade)throws SQLException;
    int updateConvenio(String nome, String descricao, final int id)throws SQLException;
    List<Convenio> listAllConvenios()throws SQLException;
    int deleteConvenioById(final int id)throws SQLException;
    Convenio getConvenioById(final int id)throws SQLException;
    int cadastrarConvenio(Convenio convenio)throws SQLException;


    //ADMIN - FUNCIONARIO
    int cadastrarFuncionario(Funcionario funcionario)throws SQLException;
    Funcionario getFuncionarioById(final int id)throws SQLException;
    int deleteFuncionarioById(final int id)throws SQLException;
    List<Funcionario> listAllFuncionarios()throws SQLException;
    int updateFuncionario(String usuario , int idade , char sexo , String cpf , String rua ,
                          String numero , String complemento, String bairro , String cidade
            , String estado , String contato , String email, int senha , LocalDateTime dataNnascimento , Tipo tipo, final int whereId)throws SQLException;


    //MEDICO
    List<Consulta> listMyConsultas(final int medicoId) throws SQLException;


    int cadastrarProntuario(final Prontuario prontuario) throws SQLException;
    Prontuario getProntuarioById(final int id) throws SQLException;
    List<Prontuario> listAllProntuarios() throws SQLException;
    int deleteProntuario(final int id) throws SQLException;
    int updateProntuario(final String receituario,
                         final String exames,
                         final String observacao,
                         final int whereId)  throws SQLException;

    //ATENDENTe
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
