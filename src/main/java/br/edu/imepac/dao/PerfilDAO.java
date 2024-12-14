package br.edu.imepac.dao;

import br.edu.imepac.entities.Perfil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PerfilDAO {

    int cadastrarPerfil(Perfil perfil) throws SQLException;

    boolean atualizarPerfil(Perfil perfil) throws SQLException;

    Perfil getPerfilById(int id) throws SQLException;

    List<Perfil> listAllPerfis() throws SQLException;

    boolean deletarPerfil(int id) throws SQLException;
}
