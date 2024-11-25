package br.edu.imepac.dao;

import br.edu.imepac.entities.Consulta;
import br.edu.imepac.entities.Convenio;
import br.edu.imepac.entities.Paciente;
import br.edu.imepac.entities.Prontuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtendenteDAO {

    private String insertNewConsulta = "insert into p.tb_consulta(data_horario , sintomas , e_retorno , esta_ativa , paciente_id , prontuario_id ,convenio_id) values(? , ? , ? , ? , ? , ?)";
    private PreparedStatement pstinsert;

    private String selectAllConsulta = "select * from p.tb_consulta";
    private PreparedStatement pstselectAll;

    private String deleteConsulta = "delete from p.tb_consulta where id = ?";
    private PreparedStatement pstdeleteConsulta;

    private String updateConsulta = "update p.tb_consulta set sintomas = ? set e_retorno = ? set esta_ativa set paciente_id = ? set prontuario_id = ? set convenio_id = ? where id = ?";
    private PreparedStatement pstupdateConsulta;

    //constructor
    public AtendenteDAO(final Connection con) throws SQLException {
        pstinsert = con.prepareStatement(insertNewConsulta);
        pstselectAll = con.prepareStatement(selectAllConsulta);
        pstdeleteConsulta = con.prepareStatement(deleteConsulta);
        pstupdateConsulta = con.prepareStatement(updateConsulta);
    }

    //cadastra uma consulta
    public int cadastrarConsulta(final Consulta consulta) throws SQLException {
        pstinsert.clearParameters();
        pstinsert.setTimestamp(1, Timestamp.valueOf(consulta.getDataHorario()));
        pstinsert.setString(2, consulta.getSintomas());
        pstinsert.setBoolean(3, consulta.iseRetorno());
        pstinsert.setBoolean(4, consulta.isEstaAtiva());
        pstinsert.setInt(5, consulta.getPaciente().getId());
        pstinsert.setInt(6, consulta.getProntuario().getId());
        pstinsert.setInt(7, consulta.getConvenio().getId());
        return pstinsert.executeUpdate();
    }

    //lista todas as consultas
    public List<Consulta> listAllConsultas() throws SQLException {
        List<Consulta> localConsulta = new ArrayList<>();

        ResultSet result = pstselectAll.executeQuery();
        while (result.next()) {
            Consulta consulta = new Consulta();
            consulta.setId(result.getInt("id"));
            consulta.setDataHorario(result.getTimestamp("data_horario").toLocalDateTime());
            consulta.seteRetorno(result.getBoolean("e_retorno"));
            consulta.setEstaAtiva(result.getBoolean("esta_ativa"));
            consulta.setSintomas(result.getString("sintomas"));
            consulta.setConvenio((Convenio) result.getObject("convenio_id"));
            consulta.setPaciente((Paciente) result.getObject("paciente_id"));
            consulta.setProntuario((Prontuario) result.getObject("prontuario_id"));
        }
        return localConsulta;
    }

    //deletar consulta
    public int deleteConsulta(final int id) throws SQLException {
        pstdeleteConsulta.clearParameters();
        pstdeleteConsulta.setInt(1, id);
        return pstdeleteConsulta.executeUpdate();
    }

    //atualizar consulta
    public int updateConsulta(final String sintomas , final boolean eRetorno, final boolean estaAtiva, final int pacienteId , final int prontuarioId ,final int convenioId , final int whereId) throws SQLException {
        pstupdateConsulta.clearParameters();
        pstupdateConsulta.setString(1, sintomas);
        pstupdateConsulta.setBoolean(2, eRetorno);
        pstupdateConsulta.setBoolean(3, estaAtiva);
        pstupdateConsulta.setInt(4, pacienteId);
        pstupdateConsulta.setInt(5, prontuarioId);
        pstupdateConsulta.setInt(6, convenioId);
        pstupdateConsulta.setInt(7, whereId);
        return pstupdateConsulta.executeUpdate();
    }



}
