package br.edu.imepac.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection(
            final String enderecoIP,
            final String enderecoPorta,
            final String nomeBanco,
            final String usuario,
            final String senha
    ) throws SQLException {
      return DriverManager.getConnection(
              "jdbc:mysql//"+enderecoIP+":"+enderecoPorta+"/"+nomeBanco,
              usuario,
              senha
      );
    }
}
