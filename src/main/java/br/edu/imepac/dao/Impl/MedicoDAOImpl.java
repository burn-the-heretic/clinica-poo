package br.edu.imepac.dao.Impl;

import br.edu.imepac.config.ConnectionFactory;
import br.edu.imepac.config.DbConfig;
import br.edu.imepac.dao.MedicoDAO;
import br.edu.imepac.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAOImpl implements MedicoDAO {

    private String getConsulta = "SELECT *FROM Consulta where funcionario_id= ?";
    private PreparedStatement pstGetConsultasById;

    private String insertNewProntuario = "insert into Prontuario(receituario , exames , observacao) values(? , ? , ?)";
    private PreparedStatement pstinsert;

    private String selectAllProntuarios = "SELECT *FROM Prontuario";
    private PreparedStatement pstselectAll;

    private String deleteProntuario = "DELETE FROM Prontuario where id = ?";
    private PreparedStatement pstdelete;

    private String updateProntuario = "UPDATE Prontuario SET receituario = ? set exames = ? set observacao where id = ?";
    private PreparedStatement pstupdate;

    private String getProntuarioById = "SELECT *FROM Prontuario where id = ?";
    private PreparedStatement pstGetProntuarioById;

    public MedicoDAOImpl(final Connection connection) throws SQLException {
        pstGetConsultasById = connection.prepareStatement(getConsulta);
        pstinsert = connection.prepareStatement(insertNewProntuario);
        pstselectAll = connection.prepareStatement(selectAllProntuarios);
        pstdelete = connection.prepareStatement(deleteProntuario);
        pstupdate = connection.prepareStatement(updateProntuario);
        pstGetProntuarioById = connection.prepareStatement(getProntuarioById);
    }

    private void createConnection() {
        try {
            ConnectionFactory.getConnection(DbConfig.ip , DbConfig.porta,
                    DbConfig.nomeBanco, DbConfig.usuario,DbConfig.senha);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Consulta> listMyConsultas(int medicoId) {
        Connection con = null;
        List<Consulta> localConsulta = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection(DbConfig.ip, DbConfig.porta,
                    DbConfig.nomeBanco, DbConfig.usuario, DbConfig.senha);
            pstGetConsultasById.clearParameters();
            pstGetConsultasById.setInt(1, medicoId);
            ResultSet resultSet = pstGetConsultasById.executeQuery();
            while (resultSet.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(resultSet.getInt("id"));
                consulta.setDataHorario(resultSet.getTimestamp("data_horario").toLocalDateTime());
                consulta.setSintomas(resultSet.getString("sintomas"));
                consulta.seteRetorno(resultSet.getBoolean("e_retorno"));
                consulta.setEstaAtiva(resultSet.getBoolean("esta_ativa"));
                consulta.setConvenio((Convenio) resultSet.getObject("convenio_id"));
                consulta.setPaciente((Paciente) resultSet.getObject("paciente_id"));
                consulta.setProntuario((Prontuario) resultSet.getObject("prontuario_id"));
                consulta.setFuncionario((Funcionario) resultSet.getObject("funcionario_id"));
                localConsulta.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas do médico: " + e.getMessage());
            throw new RuntimeException("Erro ao tentar listar consultas no banco de dados.", e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão após listar consultas: " + e.getMessage());
            }
        }
        return localConsulta;
    }


    @Override
    public int cadastrarPronturario(Prontuario prontuario) throws SQLException {
        createConnection();
        pstinsert.clearParameters();
        pstinsert.setString(1 , prontuario.getReceituario());
        pstinsert.setString(2 , prontuario.getExames());
        pstinsert.setString(3 , prontuario.getObservacao());
        pstinsert.executeQuery();
        return pstinsert.executeUpdate();
    }

    @Override
    public Prontuario getProntuarioById(int id) throws SQLException {
        createConnection();
        pstGetProntuarioById.clearParameters();
        Prontuario prontuario = new Prontuario();
        pstGetProntuarioById.setInt(1 , id);
        ResultSet resultSet = pstGetProntuarioById.executeQuery();
        while (resultSet.next()) {
            prontuario.setId(resultSet.getInt("id"));
            prontuario.setReceituario(resultSet.getString("receituario"));
            prontuario.setExames(resultSet.getString("exames"));
            prontuario.setObservacao(resultSet.getString("observacao"));
        }
        return prontuario;
    }

    @Override
    public List<Prontuario> listAllProntuarios() throws SQLException {
        createConnection();
        List<Prontuario> localProntuario = new ArrayList<>();

        ResultSet resultSet = pstselectAll.executeQuery();
        while (resultSet.next()) {
            Prontuario prontuario = new Prontuario();
            prontuario.setId(resultSet.getInt("id"));
            prontuario.setReceituario(resultSet.getString("receituario"));
            prontuario.setExames(resultSet.getString("exames"));
            prontuario.setObservacao(resultSet.getString("observacao"));
            localProntuario.add(prontuario);
        }
        return localProntuario;
    }

    @Override
    public int deleteProntuario(int id) throws SQLException {
        createConnection();
        pstdelete.clearParameters();
        pstdelete.setInt(1, id);
        return pstdelete.executeUpdate();
    }

    @Override
    public int updateProntuario(String receituario, String exames, String observacao , int whereId) throws SQLException {
        createConnection();
        pstupdate.clearParameters();
        pstupdate.setString(1 , receituario);
        pstupdate.setString(2 , exames);
        pstupdate.setString(3 , observacao);
        pstupdate.setInt(4 , whereId);
        return pstupdate.executeUpdate();
    }
}
