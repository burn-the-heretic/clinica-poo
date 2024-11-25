package br.edu.imepac.dao;

import br.edu.imepac.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 IMPORTANTE:
  Uma consulta marcada como "ativa" não pode ser deletada sem permissão de um Administrador.
  O DELETE PACIENTE DEVE DELETAR INFORMAÇÕES DE PACIENTES
  Campos obrigatórios incluem: nome, cpf, dataNascimento, contato.
  FALTA CRIAR O MEDICO-DAO
*/

public class AtendenteDAO {

    //CONSULTA
    private String insertNewConsulta = "insert into p.tb_consulta(data_horario , sintomas , e_retorno , esta_ativa , paciente_id , prontuario_id ,convenio_id) values(? , ? , ? , ? , ? , ? , ?)";
    private PreparedStatement pstinsert;

    private String selectAllConsulta = "select * from p.tb_consulta";
    private PreparedStatement pstselectAll;

    private String deleteConsulta = "delete from p.tb_consulta where id = ?";
    private PreparedStatement pstdeleteConsulta;

    private String updateConsulta = "update p.tb_consulta set sintomas = ? set e_retorno = ? set esta_ativa set paciente_id = ? set prontuario_id = ? set convenio_id = ? where id = ?";
    private PreparedStatement pstupdateConsulta;

    private String getConsultaById = "select from p.tb_consulta where id = ?";
    private PreparedStatement pstGetConsultaById;

    //PACIENTE
    private final String insertNewPaciente = "insert into p.tb_paciente (nome , idade , sexo , cpf , rua ,numero ,complemento,bairro , cidade ,estado ,contato , email, data_nascimento) values(? , ? , ? , ? , ? , ?, ? , ?, ? , ?, ? , ? , ? )";
    private PreparedStatement pstInsertPaciente;

    private final String selectAllPacientes = "select * from p.tb_paciente";
    private PreparedStatement pstselectPacientes;

    private final String deletePaciente = "delete from p.tb_paciente where id = ?";
    private PreparedStatement pstdeletePaciente;

    private final String updatePaciente = "update p.tb_paciente set nome = ? set senha = ? set idade set sexo = ? set cpf = ? set rua = ?  set numero = ? set complemento = ? set bairro = ? set cidade = ? set estado = ? set contato = ? set email = ? data_nascimento = ? where id = ?";
    private PreparedStatement pstupdatePaciente;

    private final String getPacienteByCpf= "select from p.tb_consulta where cpf = ?";
    private PreparedStatement pstGetPacienteByCpf;


    //constructor
    public AtendenteDAO(final Connection con) throws SQLException {
        //CONSULTA
        pstinsert = con.prepareStatement(insertNewConsulta);
        pstselectAll = con.prepareStatement(selectAllConsulta);
        pstdeleteConsulta = con.prepareStatement(deleteConsulta);
        pstupdateConsulta = con.prepareStatement(updateConsulta);
        pstGetConsultaById = con.prepareStatement(getConsultaById);

        //PACIENTE
        pstInsertPaciente = con.prepareStatement(insertNewPaciente);
        pstselectPacientes = con.prepareStatement(selectAllPacientes);
        pstdeletePaciente = con.prepareStatement(deletePaciente);
        pstupdatePaciente = con.prepareStatement(updatePaciente);
        pstGetPacienteByCpf = con.prepareStatement(getPacienteByCpf);
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

    //consulta pelo id
    public Consulta getFuncionarioById(final int id) throws SQLException {
        pstGetConsultaById.clearParameters();
        Consulta consulta = new Consulta();
        pstGetConsultaById.setInt(1, id);
        ResultSet resultSet = pstGetConsultaById.executeQuery();
        while (resultSet.next()) {
            consulta.setId(resultSet.getInt("id"));
            consulta.setDataHorario(resultSet.getTimestamp("data_horario").toLocalDateTime());
            consulta.setSintomas(resultSet.getString("sintomas"));
            consulta.seteRetorno(resultSet.getBoolean("e_retorno"));
            consulta.setEstaAtiva(resultSet.getBoolean("esta_ativa"));
            consulta.setConvenio((Convenio) resultSet.getObject("convenio_id"));
            consulta.setPaciente((Paciente) resultSet.getObject("paciente_id"));
            consulta.setProntuario((Prontuario) resultSet.getObject("prontuario_id"));
        }
        return consulta;
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
    public int updateConsulta(final String sintomas , final boolean eRetorno, final boolean estaAtiva, final int pacienteId
            , final int prontuarioId ,final int convenioId , final int whereId) throws SQLException {
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

    //cadastra um Paciente
    public int cadastrarPaciente(final Paciente paciente) throws SQLException {
        pstInsertPaciente.clearParameters();
        pstInsertPaciente.setString(1 , paciente.getNome() );
        pstInsertPaciente.setInt(2 , paciente.getIdade());
        pstInsertPaciente.setString(3 , String.valueOf(paciente.getSexo()));
        pstInsertPaciente.setString(4, paciente.getCpf());
        pstInsertPaciente.setString(5, paciente.getRua());
        pstInsertPaciente.setString(6, paciente.getNumero());
        pstInsertPaciente.setString(7, paciente.getComplemento());
        pstInsertPaciente.setString(8, paciente.getBairro());
        pstInsertPaciente.setString(9, paciente.getCidade());
        pstInsertPaciente.setString(10, paciente.getEstado());
        pstInsertPaciente.setString(11, paciente.getContato());
        pstInsertPaciente.setString(12, paciente.getEmail());
        pstInsertPaciente.setTimestamp(13, Timestamp.valueOf(paciente.getDataNascimento()));
        return pstInsertPaciente.executeUpdate();
    }

    //consulta um Paciente pelo CPF
    public Paciente getPacienteByCpf(final String cpf) throws SQLException {
        pstGetConsultaById.clearParameters();
        Paciente paciente = new Paciente();
        pstGetConsultaById.setString(1, cpf);
        ResultSet resultSet = pstGetPacienteByCpf.executeQuery();
        while (resultSet.next()) {
            paciente.setId(resultSet.getInt("id"));
            paciente.setNome(resultSet.getString("nome"));
            paciente.setIdade(resultSet.getInt("idade"));
            paciente.setCpf(resultSet.getString("cpf"));
            paciente.setSexo((Character) resultSet.getObject("sexo"));
            paciente.setRua(resultSet.getString("rua"));
            paciente.setNumero(resultSet.getString("numero"));
            paciente.setComplemento(resultSet.getString("complemento"));
            paciente.setBairro(resultSet.getString("bairro"));
            paciente.setCidade(resultSet.getString("cidade"));
            paciente.setEstado(resultSet.getString("estado"));
            paciente.setContato(resultSet.getString("contato"));
            paciente.setEmail(resultSet.getString("email"));
            paciente.setDataNascimento(resultSet.getTimestamp("data_nascimento").toLocalDateTime());
        }
        return paciente;
    }

    //lista todas os Pacientes
    public List<Paciente> listAllPacientes() throws SQLException {
        List<Paciente> localPaciente = new ArrayList<>();

        ResultSet resultSet = pstselectPacientes.executeQuery();
        while (resultSet.next()) {
            Paciente paciente = new Paciente();
            paciente.setId(resultSet.getInt("id"));
            paciente.setNome(resultSet.getString("nome"));
            paciente.setIdade(resultSet.getInt("idade"));
            paciente.setCpf(resultSet.getString("cpf"));
            paciente.setSexo((Character) resultSet.getObject("sexo"));
            paciente.setRua(resultSet.getString("rua"));
            paciente.setNumero(resultSet.getString("numero"));
            paciente.setComplemento(resultSet.getString("complemento"));
            paciente.setBairro(resultSet.getString("bairro"));
            paciente.setCidade(resultSet.getString("cidade"));
            paciente.setEstado(resultSet.getString("estado"));
            paciente.setContato(resultSet.getString("contato"));
            paciente.setEmail(resultSet.getString("email"));
            paciente.setDataNascimento(resultSet.getTimestamp("data_nascimento").toLocalDateTime());
        }
        return localPaciente;
    }

    //deletar informaçoes de Paciente
    //por enquanto deleta o paciente
    public int deletePaciente(final int id) throws SQLException {
        pstdeletePaciente.clearParameters();
        pstdeletePaciente.setInt(1, id);
        return pstdeletePaciente.executeUpdate();
    }

    //atualizar Paciente
    public int updatePaciente(String nome , int idade , char sexo , String cpf , String rua ,
                              String numero ,String complemento, String bairro , String cidade
            ,String estado ,String contato , String email, String data_nascimento , final int whereId) throws SQLException {
        pstupdatePaciente.clearParameters();
        pstupdatePaciente.setString(1, nome);
        pstupdatePaciente.setInt(2, idade);
        pstupdatePaciente.setObject(3, sexo);
        pstupdatePaciente.setString(4, cpf);
        pstupdatePaciente.setString(5, rua);
        pstupdatePaciente.setString(6, numero);
        pstupdatePaciente.setString(7, complemento);
        pstupdatePaciente.setString(8, bairro);
        pstupdatePaciente.setString(9, cidade);
        pstupdatePaciente.setString(10, estado);
        pstupdatePaciente.setString(11, contato);
        pstupdatePaciente.setString(12, email);
        pstupdatePaciente.setTimestamp(13, Timestamp.valueOf(data_nascimento));
        pstupdatePaciente.setInt(14, whereId);
        return pstupdateConsulta.executeUpdate();
    }
}
