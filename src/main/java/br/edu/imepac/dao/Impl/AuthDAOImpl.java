package br.edu.imepac.dao.Impl;

import br.edu.imepac.config.ConnectionFactory;
import br.edu.imepac.config.DbConfig;
import br.edu.imepac.dao.AuthDAO;
import br.edu.imepac.entities.Funcionario;
import br.edu.imepac.enums.Tipo;

import java.sql.*;

public class AuthDAOImpl implements AuthDAO {

    private Connection connection;

    private String selectFuncionarioByUsuarioESenha =
            "SELECT * FROM Funcionario WHERE usuario = ? AND senha = ?";
    private PreparedStatement pstSelectFuncionario;

    public AuthDAOImpl(final Connection con) throws SQLException {
        this.connection = con;
        this.pstSelectFuncionario = con.prepareStatement(selectFuncionarioByUsuarioESenha);
    }

    // Cria a conexão com o banco de dados
    private void createConnection() {
        try {
            connection = ConnectionFactory.getConnection(
                    DbConfig.ip,
                    DbConfig.porta,
                    DbConfig.nomeBanco,
                    DbConfig.usuario,
                    DbConfig.senha
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Funcionario findFuncionarioByUsuarioESenha(String usuario, Integer senha) throws SQLException {
        createConnection();
        pstSelectFuncionario.clearParameters();
        pstSelectFuncionario.setString(1, usuario);
        pstSelectFuncionario.setInt(2, senha);

        ResultSet rs = pstSelectFuncionario.executeQuery();

        if (rs.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(rs.getInt("id"));
            funcionario.setUsuario(rs.getString("usuario"));
            funcionario.setSenha(rs.getInt("senha"));
            funcionario.setIdade(rs.getInt("idade"));
            funcionario.setSexo(rs.getString("sexo").charAt(0));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setRua(rs.getString("rua"));
            funcionario.setNumero(rs.getString("numero"));
            funcionario.setComplemento(rs.getString("complemento"));
            funcionario.setBairro(rs.getString("bairro"));
            funcionario.setCidade(rs.getString("cidade"));
            funcionario.setEstado(rs.getString("estado"));
            funcionario.setContato(rs.getString("contato"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setRole(Tipo.valueOf(rs.getString("tipo")));
            funcionario.setDataNascimento(rs.getTimestamp("dataNascimento").toLocalDateTime());
            return funcionario;
        } else {
            return null;
        }
    }
}
