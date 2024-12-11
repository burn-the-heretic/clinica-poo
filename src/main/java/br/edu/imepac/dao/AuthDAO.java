package br.edu.imepac.dao;

import br.edu.imepac.entities.Funcionario;

import java.sql.SQLException;

public interface AuthDAO {
    Funcionario findFuncionarioByUsuarioESenha(String usuario, Integer senha) throws SQLException;
}
