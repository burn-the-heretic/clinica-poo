package br.edu.imepac.services.impl;

import br.edu.imepac.dao.AuthDAO;
import br.edu.imepac.entities.Funcionario;

import java.sql.SQLException;

public class AuthService {

    private final AuthDAO funcionarioDAO;

    public AuthService(AuthDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

        public boolean autenticar(String usuario, Integer senha) {
            try {
                Funcionario funcionario = funcionarioDAO.findFuncionarioByUsuarioESenha(usuario, senha);
                return funcionario != null;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
    }
}
