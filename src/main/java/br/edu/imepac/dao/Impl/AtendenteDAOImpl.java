package br.edu.imepac.dao.Impl;

import br.edu.imepac.config.ConnectionFactory;
import br.edu.imepac.config.DbConfig;
import br.edu.imepac.dao.AtendenteDAO;
import br.edu.imepac.entities.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 IMPORTANTE:
  Uma consulta marcada como "ativa" não pode ser deletada sem permissão de um Administrador.
  O DELETE PACIENTE DEVE DELETAR INFORMAÇÕES DE PACIENTES
  Campos obrigatórios incluem: nome, cpf, dataNascimento, contato.
  FALTA CRIAR O MEDICO-DAO
*/

public class AtendenteDAOImpl implements AtendenteDAO {

    private void createConnection() {
        try {
            ConnectionFactory.getConnection(DbConfig.ip , DbConfig.porta,
            DbConfig.nomeBanco, DbConfig.usuario,DbConfig.senha);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CONSULTA
    private String insertNewConsulta = "insert into Consulta(data_horario , sintomas , e_retorno , esta_ativa , paciente_id , prontuario_id ,convenio_id , funcionario_id) values(? , ? , ? , ? , ? , ? , ? ,?)";
    private PreparedStatement pstinsert;

    private String selectAllConsulta = "SELECT *FROM Consulta";
    private PreparedStatement pstselectAll;

    private String deleteConsulta = "DELETE FROM Consulta where id = ?";
    private PreparedStatement pstdeleteConsulta;

    private String updateConsulta = "UPDATE Consulta SET sintomas = ? , eRetorno = ? , estaAtiva = ?, paciente_id = ? , prontuario_id = ? , convenio_id = ? where id = ?";
    private PreparedStatement pstupdateConsulta;

    private String getConsultaById = "SELECT *FROM Consulta where id = ?";
    private PreparedStatement pstGetConsultaById;

    //PACIENTE
    private final String insertNewPaciente = "insert into Paciente (nome , idade , sexo , cpf , rua ,numero ,complemento,bairro , cidade ,estado ,contato , email, dataNascimento) values(? , ? , ? , ? , ? , ?, ? , ?, ? , ?, ? , ? , ? )";
    private PreparedStatement pstInsertPaciente;

    private final String selectAllPacientes = "SELECT *FROM Paciente";
    private PreparedStatement pstselectPacientes;

    private final String deletePaciente = "DELETE FROM Paciente where id = ?";
    private PreparedStatement pstdeletePaciente;

    private final String updatePaciente = "UPDATE Paciente SET nome = ? set senha = ? set idade set sexo = ? set cpf = ? set rua = ?  set numero = ? set complemento = ? set bairro = ? set cidade = ? set estado = ? set contato = ? set email = ? dataNascimento = ? where id = ?";
    private PreparedStatement pstupdatePaciente;

    private final String getPacienteByCpf= "SELECT *FROM Paciente where cpf = ?";
    private PreparedStatement pstGetPacienteByCpf;

    private String insertNewConvenio = "insert into Convenio (nome, descricao) values (?, ?)";
    private PreparedStatement preparedStatementInsertConvenio;

    private String getConvenioById = "SELECT *FROM Convenio where id = ?";
    private PreparedStatement preparedStatementGetConvenioById;

    private String deleteConvenioById = "DELETE FROM Convenio where id = ?";
    private PreparedStatement preparedStatementDeleteConvenio;

    private String listAllConvenios = "SELECT *FROM Convenio";
    private PreparedStatement preparedStatementListAllConvenios;

    private String updateConvenio = "UPDATE Convenio SET nome = ?, descricao = ? where id = ?";
    private PreparedStatement preparedStatementUpdateConvenio;


    //constructor
    public AtendenteDAOImpl(final Connection con) throws SQLException {
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

        //CONVENIO
        preparedStatementInsertConvenio = con.prepareStatement(insertNewConvenio);
        preparedStatementGetConvenioById = con.prepareStatement(getConvenioById);
        preparedStatementDeleteConvenio = con.prepareStatement(deleteConvenioById);
        preparedStatementListAllConvenios = con.prepareStatement(listAllConvenios);
        preparedStatementUpdateConvenio = con.prepareStatement(updateConvenio);
    }

    //cadastra uma consulta
    public void cadastrarConsulta(final Consulta consulta) throws SQLException {
        createConnection();
        pstinsert.clearParameters();
        pstinsert.setTimestamp(1, Timestamp.valueOf(consulta.getDataHorario()));
        pstinsert.setString(2, consulta.getSintomas());
        pstinsert.setBoolean(3, consulta.iseRetorno());
        pstinsert.setBoolean(4, consulta.isEstaAtiva());
        pstinsert.setInt(5, consulta.getPaciente().getId());
        pstinsert.setInt(6, consulta.getProntuario().getId());
        pstinsert.setInt(7, consulta.getConvenio().getId());
        pstinsert.setInt(8, consulta.getFuncionario().getId());
        pstinsert.executeUpdate();
    }

    //consulta pelo id
    public Consulta getConsultaById(final int id) throws SQLException {
        createConnection();
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
            consulta.setFuncionario((Funcionario) resultSet.getObject("funcionario_id"));
        }
        return consulta;
    }

    //lista todas as consultas
    public List<Consulta> listAllConsultas() throws SQLException {
        createConnection();
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
            consulta.setFuncionario((Funcionario) result.getObject("funcionario_id"));
            localConsulta.add(consulta);
        }
        return localConsulta;
    }

    //deletar consulta
    public void deleteConsulta(final int id) throws SQLException {
        createConnection();
        pstdeleteConsulta.clearParameters();
        pstdeleteConsulta.setInt(1, id);
        pstdeleteConsulta.executeUpdate();
    }

    //atualizar consulta
    public int updateConsulta(final String sintomas , final boolean eRetorno, final boolean estaAtiva, final int pacienteId
            , final int prontuarioId ,final int convenioId , final int whereId) throws SQLException {
        createConnection();
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
        createConnection();
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
        createConnection();
        pstGetConsultaById.clearParameters();
        Paciente paciente = new Paciente();
        pstGetConsultaById.setString(1, cpf);
        ResultSet resultSet = pstGetPacienteByCpf.executeQuery();
        while (resultSet.next()) {
            paciente.setId(resultSet.getInt("id"));
            paciente.setNome(resultSet.getString("nome"));
            paciente.setIdade(resultSet.getInt("idade"));
            paciente.setCpf(resultSet.getString("cpf"));
            paciente.setSexo(resultSet.getString("sexo").charAt(0));
            paciente.setRua(resultSet.getString("rua"));
            paciente.setNumero(resultSet.getString("numero"));
            paciente.setComplemento(resultSet.getString("complemento"));
            paciente.setBairro(resultSet.getString("bairro"));
            paciente.setCidade(resultSet.getString("cidade"));
            paciente.setEstado(resultSet.getString("estado"));
            paciente.setContato(resultSet.getString("contato"));
            paciente.setEmail(resultSet.getString("email"));
            paciente.setDataNascimento(resultSet.getTimestamp("dataNascimento").toLocalDateTime());
        }
        return paciente;
    }

    //lista todas os Pacientes
    public List<Paciente> listAllPacientes() throws SQLException {
        createConnection();
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
            paciente.setDataNascimento(resultSet.getTimestamp("dataNascimento").toLocalDateTime());
            localPaciente.add(paciente);
        }
        return localPaciente;
    }

    //deletar informaçoes de Paciente
    //por enquanto deleta o paciente
    public int deletePaciente(final String cpf) throws SQLException {
        createConnection();
        pstdeletePaciente.clearParameters();
        pstdeletePaciente.setString(1, cpf);
        return pstdeletePaciente.executeUpdate();
    }

    //atualizar Paciente
    public int updatePaciente(String nome , int idade , char sexo , String cpf , String rua ,
                              String numero , String complemento, String bairro , String cidade
            , String estado , String contato , String email, LocalDateTime data_nascimento , final int whereId) throws SQLException {
        createConnection();
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
        return pstupdatePaciente.executeUpdate();
    }

    public int cadastrarConvenio(Convenio convenio) throws SQLException {
        createConnection();
        preparedStatementInsertConvenio.clearParameters();
        preparedStatementInsertConvenio.setString(1, convenio.getNome());
        preparedStatementInsertConvenio.setString(2, convenio.getDescricao());
        return preparedStatementInsertConvenio.executeUpdate();
    }

    public Convenio getConvenioById(final int id) throws SQLException {
        createConnection();
        preparedStatementGetConvenioById.clearParameters();
        Convenio convenio = new Convenio();
        preparedStatementGetConvenioById.setInt(1, id);
        ResultSet resultSet = preparedStatementGetConvenioById.executeQuery();
        while (resultSet.next()) {
            convenio.setId(resultSet.getInt("id"));
            convenio.setNome(resultSet.getString("nome"));
            convenio.setDescricao(resultSet.getString("descricao"));
        }
        return convenio;
    }

    public int deleteConvenioById(final int id) throws SQLException {
        createConnection();
        preparedStatementDeleteConvenio.clearParameters();
        preparedStatementDeleteConvenio.setInt(1, id);
        return preparedStatementDeleteConvenio.executeUpdate();
    }

    public List<Convenio> listAllConvenios() throws SQLException {
        createConnection();
        List<Convenio> convenios = new ArrayList<>();
        ResultSet resultSet = preparedStatementListAllConvenios.executeQuery();
        while (resultSet.next()) {
            Convenio convenio = new Convenio();
            convenio.setId(resultSet.getInt("id"));
            convenio.setNome(resultSet.getString("nome"));
            convenio.setDescricao(resultSet.getString("descricao"));
            convenios.add(convenio);
        }
        return convenios;
    }

    public int updateConvenio(String nome, String descricao, final int id) throws SQLException {
        createConnection();
        preparedStatementUpdateConvenio.clearParameters();
        preparedStatementUpdateConvenio.setString(1, nome);
        preparedStatementUpdateConvenio.setString(2, descricao);
        preparedStatementUpdateConvenio.setInt(3, id);
        return preparedStatementUpdateConvenio.executeUpdate();
    }

}
