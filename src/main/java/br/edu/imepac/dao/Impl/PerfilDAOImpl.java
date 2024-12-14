package br.edu.imepac.dao.Impl;

import br.edu.imepac.config.ConnectionFactory;
import br.edu.imepac.config.DbConfig;
import br.edu.imepac.dao.PerfilDAO;
import br.edu.imepac.entities.Perfil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAOImpl implements PerfilDAO {

    private final String insertPerfil =
            "INSERT INTO Perfil (nome, cadastrarFuncionario, lerFuncionario, atualizarFuncionario, deletarFuncionario, listarFuncionario, " +
                    "cadastrarPaciente, lerPaciente, atualizarPaciente, deletarPaciente, listarPaciente, " +
                    "cadastrarConsulta, lerConsulta, atualizarConsulta, deletarConsulta, listarConsulta, " +
                    "cadastrarEspecialidade, lerEspecialidade, atualizarEspecialidade, deletarEspecialidade, listarEspecialidade, " +
                    "cadastrarConvenio, lerConvenio, atualizarConvenio, deletarConvenio, listarConvenio, " +
                    "cadastrarProntuario, lerProntuario, atualizarProntuario, deletarProntuario, listarProntuario) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final String updatePerfil =
            "UPDATE Perfil SET nome = ?, cadastrarFuncionario = ?, lerFuncionario = ?, atualizarFuncionario = ?, deletarFuncionario = ?, listarFuncionario = ?, " +
                    "cadastrarPaciente = ?, lerPaciente = ?, atualizarPaciente = ?, deletarPaciente = ?, listarPaciente = ?, " +
                    "cadastrarConsulta = ?, lerConsulta = ?, atualizarConsulta = ?, deletarConsulta = ?, listarConsulta = ?, " +
                    "cadastrarEspecialidade = ?, lerEspecialidade = ?, atualizarEspecialidade = ?, deletarEspecialidade = ?, listarEspecialidade = ?, " +
                    "cadastrarConvenio = ?, lerConvenio = ?, atualizarConvenio = ?, deletarConvenio = ?, listarConvenio = ?, " +
                    "cadastrarProntuario = ?, lerProntuario = ?, atualizarProntuario = ?, deletarProntuario = ?, listarProntuario = ? " +
                    "WHERE id = ?;";

    private final String selectPerfilById = "SELECT * FROM Perfil WHERE id = ?;";
    private final String selectAllPerfis = "SELECT * FROM Perfil;";
    private final String deletePerfil = "DELETE FROM Perfil WHERE id = ?;";

    private final PreparedStatement pstInsert;
    private final PreparedStatement pstUpdate;
    private final PreparedStatement pstSelectById;
    private final PreparedStatement pstSelectAll;
    private final PreparedStatement pstDelete;

    public PerfilDAOImpl(final Connection connection) throws SQLException {
        this.pstInsert = connection.prepareStatement(insertPerfil, Statement.RETURN_GENERATED_KEYS);
        this.pstUpdate = connection.prepareStatement(updatePerfil);
        this.pstSelectById = connection.prepareStatement(selectPerfilById);
        this.pstSelectAll = connection.prepareStatement(selectAllPerfis);
        this.pstDelete = connection.prepareStatement(deletePerfil);
    }

    private void createConnection() {
        try {
            ConnectionFactory.getConnection(DbConfig.ip, DbConfig.porta,
                    DbConfig.nomeBanco, DbConfig.usuario, DbConfig.senha);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int cadastrarPerfil(Perfil perfil) throws SQLException {
        createConnection();
        pstInsert.clearParameters();
        pstInsert.setString(1, perfil.getNome());
        pstInsert.setBoolean(2, perfil.isCadastrarFuncionario());
        pstInsert.setBoolean(3, perfil.isLerFuncionario());
        pstInsert.setBoolean(4, perfil.isAtualizarFuncionario());
        pstInsert.setBoolean(5, perfil.isDeletarFuncionario());
        pstInsert.setBoolean(6, perfil.isListarFuncionario());
        pstInsert.setBoolean(7, perfil.isCadastrarPaciente());
        pstInsert.setBoolean(8, perfil.isLerPaciente());
        pstInsert.setBoolean(9, perfil.isAtualizarPaciente());
        pstInsert.setBoolean(10, perfil.isDeletarPaciente());
        pstInsert.setBoolean(11, perfil.isListarPaciente());
        pstInsert.setBoolean(12, perfil.isCadastrarConsulta());
        pstInsert.setBoolean(13, perfil.isLerConsulta());
        pstInsert.setBoolean(14, perfil.isAtualizarConsulta());
        pstInsert.setBoolean(15, perfil.isDeletarConsulta());
        pstInsert.setBoolean(16, perfil.isListarConsulta());
        pstInsert.setBoolean(17, perfil.isCadastrarEspecialidade());
        pstInsert.setBoolean(18, perfil.isLerEspecialiade());
        pstInsert.setBoolean(19, perfil.isAtualizarEspecialidade());
        pstInsert.setBoolean(20, perfil.isDeletarEspecialidade());
        pstInsert.setBoolean(21, perfil.isListarEspecialidade());
        pstInsert.setBoolean(22, perfil.isCadastrarConvenio());
        pstInsert.setBoolean(23, perfil.isLerConvenio());
        pstInsert.setBoolean(24, perfil.isAtualizarConvenio());
        pstInsert.setBoolean(25, perfil.isDeletarConvenio());
        pstInsert.setBoolean(26, perfil.isListarConvenio());
        pstInsert.setBoolean(27, perfil.isCadastrarProntuario());
        pstInsert.setBoolean(28, perfil.isLerProntuario());
        pstInsert.setBoolean(29, perfil.isAtualizarProntuario());
        pstInsert.setBoolean(30, perfil.isDeletarProntuario());
        pstInsert.setBoolean(31, perfil.isListarPronturario());
        pstInsert.executeUpdate();

        ResultSet generatedKeys = pstInsert.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else {
            throw new SQLException("Falha ao cadastrar perfil, nenhum ID gerado.");
        }
    }

    @Override
    public boolean atualizarPerfil(Perfil perfil) throws SQLException {
        createConnection();
        pstUpdate.clearParameters();
        pstUpdate.setString(1, perfil.getNome());
        pstUpdate.setBoolean(2, perfil.isCadastrarFuncionario());
        pstUpdate.setBoolean(3, perfil.isLerFuncionario());
        pstUpdate.setBoolean(4, perfil.isAtualizarFuncionario());
        pstUpdate.setBoolean(5, perfil.isDeletarFuncionario());
        pstUpdate.setBoolean(6, perfil.isListarFuncionario());
        pstUpdate.setBoolean(7, perfil.isCadastrarPaciente());
        pstUpdate.setBoolean(8, perfil.isLerPaciente());
        pstUpdate.setBoolean(9, perfil.isAtualizarPaciente());
        pstUpdate.setBoolean(10, perfil.isDeletarPaciente());
        pstUpdate.setBoolean(11, perfil.isListarPaciente());
        pstUpdate.setBoolean(12, perfil.isCadastrarConsulta());
        pstUpdate.setBoolean(13, perfil.isLerConsulta());
        pstUpdate.setBoolean(14, perfil.isAtualizarConsulta());
        pstUpdate.setBoolean(15, perfil.isDeletarConsulta());
        pstUpdate.setBoolean(16, perfil.isListarConsulta());
        pstUpdate.setBoolean(17, perfil.isCadastrarEspecialidade());
        pstUpdate.setBoolean(18, perfil.isLerEspecialiade());
        pstUpdate.setBoolean(19, perfil.isAtualizarEspecialidade());
        pstUpdate.setBoolean(20, perfil.isDeletarEspecialidade());
        pstUpdate.setBoolean(21, perfil.isListarEspecialidade());
        pstUpdate.setBoolean(22, perfil.isCadastrarConvenio());
        pstUpdate.setBoolean(23, perfil.isLerConvenio());
        pstUpdate.setBoolean(24, perfil.isAtualizarConvenio());
        pstUpdate.setBoolean(25, perfil.isDeletarConvenio());
        pstUpdate.setBoolean(26, perfil.isListarConvenio());
        pstUpdate.setBoolean(27, perfil.isCadastrarProntuario());
        pstUpdate.setBoolean(28, perfil.isLerProntuario());
        pstUpdate.setBoolean(29, perfil.isAtualizarProntuario());
        pstUpdate.setBoolean(30, perfil.isDeletarProntuario());
        pstUpdate.setBoolean(31, perfil.isListarPronturario());
        pstUpdate.setInt(32, perfil.getId());
        return pstUpdate.executeUpdate() > 0;
    }

    @Override
    public Perfil getPerfilById(int id) throws SQLException {
        createConnection();
        pstSelectById.clearParameters();
        pstSelectById.setInt(1, id);
        ResultSet resultSet = pstSelectById.executeQuery();
        if (resultSet.next()) {
            return mapResultSetToPerfil(resultSet);
        }
        return null;
    }

    @Override
    public List<Perfil> listAllPerfis() throws SQLException {
        createConnection();
        List<Perfil> perfis = new ArrayList<>();
        pstSelectAll.clearParameters();
        ResultSet resultSet = pstSelectAll.executeQuery();
        while (resultSet.next()) {
            perfis.add(mapResultSetToPerfil(resultSet));
        }
        return perfis;
    }

    @Override
    public boolean deletarPerfil(int id) throws SQLException {
        createConnection();
        pstDelete.clearParameters();
        pstDelete.setInt(1, id);
        return pstDelete.executeUpdate() > 0;
    }

    private Perfil mapResultSetToPerfil(ResultSet resultSet) throws SQLException {
        Perfil perfil = new Perfil();
        perfil.setId(resultSet.getInt("id"));
        perfil.setNome(resultSet.getString("nome"));
        perfil.setCadastrarFuncionario(resultSet.getBoolean("cadastrarFuncionario"));
        perfil.setLerFuncionario(resultSet.getBoolean("lerFuncionario"));
        perfil.setAtualizarFuncionario(resultSet.getBoolean("atualizarFuncionario"));
        perfil.setDeletarFuncionario(resultSet.getBoolean("deletarFuncionario"));
        perfil.setListarFuncionario(resultSet.getBoolean("listarFuncionario"));
        perfil.setCadastrarPaciente(resultSet.getBoolean("cadastrarPaciente"));
        perfil.setLerPaciente(resultSet.getBoolean("lerPaciente"));
        perfil.setAtualizarPaciente(resultSet.getBoolean("atualizarPaciente"));
        perfil.setDeletarPaciente(resultSet.getBoolean("deletarPaciente"));
        perfil.setListarPaciente(resultSet.getBoolean("listarPaciente"));
        perfil.setCadastrarConsulta(resultSet.getBoolean("cadastrarConsulta"));
        perfil.setLerConsulta(resultSet.getBoolean("lerConsulta"));
        perfil.setAtualizarConsulta(resultSet.getBoolean("atualizarConsulta"));
        perfil.setDeletarConsulta(resultSet.getBoolean("deletarConsulta"));
        perfil.setListarConsulta(resultSet.getBoolean("listarConsulta"));
        perfil.setCadastrarEspecialidade(resultSet.getBoolean("cadastrarEspecialidade"));
        perfil.setLerEspecialiade(resultSet.getBoolean("lerEspecialidade"));
        perfil.setAtualizarEspecialidade(resultSet.getBoolean("atualizarEspecialidade"));
        perfil.setDeletarEspecialidade(resultSet.getBoolean("deletarEspecialidade"));
        perfil.setListarEspecialidade(resultSet.getBoolean("listarEspecialidade"));
        perfil.setCadastrarConvenio(resultSet.getBoolean("cadastrarConvenio"));
        perfil.setLerConvenio(resultSet.getBoolean("lerConvenio"));
        perfil.setAtualizarConvenio(resultSet.getBoolean("atualizarConvenio"));
        perfil.setDeletarConvenio(resultSet.getBoolean("deletarConvenio"));
        perfil.setListarConvenio(resultSet.getBoolean("listarConvenio"));
        perfil.setCadastrarProntuario(resultSet.getBoolean("cadastrarProntuario"));
        perfil.setLerProntuario(resultSet.getBoolean("lerProntuario"));
        perfil.setAtualizarProntuario(resultSet.getBoolean("atualizarProntuario"));
        perfil.setDeletarProntuario(resultSet.getBoolean("deletarProntuario"));
        perfil.setListarPronturario(resultSet.getBoolean("listarProntuario"));
        return perfil;
    }
}