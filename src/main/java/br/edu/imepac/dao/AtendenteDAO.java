package br.edu.imepac.dao;

import br.edu.imepac.entities.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AtendenteDAO {

    private String insertNewConsulta = "insert into p.tb_atendente(data_horario , sintomas , e_retorno , esta_ativa , paciente_id , prontuario_id ,convenio_id) values(? , ? , ? , ? , ? , ?)";
    private PreparedStatement pstinsert;

    public AtendenteDAO (final Connection con) throws SQLException {
        pstinsert = con.prepareStatement(insertNewConsulta);
    }

    public int cadastrarConsulta (final Consulta consulta) throws SQLException {
      pstinsert.clearParameters();
      pstinsert.setTimestamp(1 , Timestamp.valueOf(consulta.getDataHorario()));
      pstinsert.setString(2 , consulta.getSintomas());
      pstinsert.setBoolean(3 , consulta.iseRetorno());
      pstinsert.setBoolean(4 , consulta.isEstaAtiva());
      pstinsert.setObject(5 , consulta.getPaciente());
      pstinsert.setObject(6 , consulta.getProntuario());
      pstinsert.setObject(7 , consulta.getConvenio());
      return pstinsert.executeUpdate();
    }

}
