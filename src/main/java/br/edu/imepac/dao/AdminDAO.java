package br.edu.imepac.dao;

import br.edu.imepac.entities.Funcionario;
import br.edu.imepac.enums.Cargo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
[
  fazer query de update funcionario
]
*/

public class AdminDAO {

    private String insertNewFuncionario = "insert into p.tb_funcionario (usuario ,  email ,senha , idade, sexo , cpf , rua ,numero , complemento , bairro ,cidade , estado , contato ,cargo ,data_nascimento) values (? , ? ,? , ? , ?, ? , ? , ?, ? , ? , ?, ? , ? , ?, ?);";
    private PreparedStatement preparedStatementInsert;
    private String getFuncionarioById = "select from p.tb_funcionario where id=?";
    private PreparedStatement preparedStatementGetFuncionario;
    private String deleteFuncionarioById = "delete from p.tb_funcionario where id=?";
    private PreparedStatement preparedStatementDeleteFuncionario;
    private String listAllfuncionarios = "select *from p.tb_funcionario";
    private PreparedStatement preparedStatementListAll;

    public AdminDAO (final Connection connection) throws SQLException {
        preparedStatementInsert = connection.prepareStatement(insertNewFuncionario);
        preparedStatementGetFuncionario = connection.prepareStatement(getFuncionarioById);
        preparedStatementDeleteFuncionario = connection.prepareStatement(deleteFuncionarioById);
        preparedStatementListAll = connection.prepareStatement(listAllfuncionarios);
    }

    //cadastrar o funcionario
    public int cadastrarFuncionario(Funcionario funcionario) throws SQLException {
        preparedStatementInsert.clearParameters();
        preparedStatementInsert.setString(1, funcionario.getUsuario());
        preparedStatementInsert.setString(2 , funcionario.getEmail());
        preparedStatementInsert.setInt(3 , funcionario.getSenha());
        preparedStatementInsert.setInt(4, funcionario.getIdade());
        preparedStatementInsert.setString(5, String.valueOf(funcionario.getSexo()));
        preparedStatementInsert.setString(6, funcionario.getCpf());
        preparedStatementInsert.setString(7, funcionario.getRua());
        preparedStatementInsert.setString(8, funcionario.getNumero());
        preparedStatementInsert.setString(9, funcionario.getComplemento());
        preparedStatementInsert.setString(10, funcionario.getBairro());
        preparedStatementInsert.setString(11, funcionario.getCidade());
        preparedStatementInsert.setString(12, funcionario.getEstado());
        preparedStatementInsert.setString(13, funcionario.getContato());
        preparedStatementInsert.setString(14 , String.valueOf(funcionario.getRole()));
        preparedStatementInsert.setTimestamp(15 , Timestamp.valueOf(funcionario.getDataNascimento()));
        return preparedStatementInsert.executeUpdate();
    }

    //pegar o funcionario pela id
    public Funcionario getFuncionarioById(final int id) throws SQLException {
         preparedStatementGetFuncionario.clearParameters();
         Funcionario funcionario = new Funcionario();
         preparedStatementGetFuncionario.setInt(1, id);
         ResultSet resultSet = preparedStatementGetFuncionario.executeQuery();
         while (resultSet.next()) {
             funcionario.setId(resultSet.getInt("id"));
             funcionario.setUsuario(resultSet.getString("usuario"));
             funcionario.setEmail(resultSet.getString("email"));
             funcionario.setSenha(resultSet.getInt("senha"));
             funcionario.setIdade(resultSet.getInt("idade"));
             //add o resto dos atributos
         }
         return funcionario;
    }

    //deleta o funcionario pelo id
    public int deleteFuncionarioById(final int id) throws SQLException {
        preparedStatementDeleteFuncionario.clearParameters();
        preparedStatementDeleteFuncionario.setInt(1, id);
        return preparedStatementDeleteFuncionario.executeUpdate();
    }

    //listar todos os funcionarios
    public List<Funcionario> listAllFuncionarios() throws SQLException {
        List<Funcionario> localList = new ArrayList<>();

        ResultSet result = preparedStatementListAll.executeQuery();
        while (result.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(result.getInt("id"));
            funcionario.setUsuario(result.getString("usuario"));
            funcionario.setEmail(result.getString("email"));
            funcionario.setSenha(result.getInt("senha"));
            funcionario.setIdade(result.getInt("idade"));
            funcionario.setBairro(result.getString("bairro"));
            funcionario.setCidade(result.getString("cidade"));
            funcionario.setEstado(result.getString("estado"));
            funcionario.setContato(result.getString("contato"));
            //add o resto dos atributos
            localList.add(funcionario);
        }
        return localList;
    }
    //fazer as queries das outras classes primeiro , já que o admin vai ter todas
}
