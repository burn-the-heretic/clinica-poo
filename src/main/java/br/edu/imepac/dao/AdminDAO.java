package br.edu.imepac.dao;

import br.edu.imepac.entities.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdminDAO {

    private String insertNewFuncionario = "insert into p.tb_funcionario (usuario , senha, senha , idade, sexo , cpf , rua ,numero , complemento , bairro ,cidade , estado , contato , email ,data_nascimento) values (? , ? , ? ,? , ? , ?, ? , ? , ?, ? , ? , ?, ? , ? , ?);";
    private PreparedStatement preparedStatementInsert;
    private String getFuncionarioById = "select from p.tb_funcionario where id=?";
    private PreparedStatement preparedStatementGetFuncionario;
    private String deleteFuncionarioById = "";
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
    public Funcionario cadastrarFuncionario(){
        return null;
    }
    //pegar o funcionario pela id
    public Funcionario getFuncionarioById(int id){
        return  null;
    }
    //deleta o funcionario pelo id
    public void deleteFuncionarioById(int id){

    }
    //listar todos os funcionarios
    public List<Funcionario> listAllFuncionarios(){
        return null;
    }
}
