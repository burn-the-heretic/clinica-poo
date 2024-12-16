    package br.edu.imepac.dao.Impl;

    import br.edu.imepac.config.ConnectionFactory;
    import br.edu.imepac.config.DbConfig;
    import br.edu.imepac.dao.AdminDAO;
    import br.edu.imepac.entities.*;
    import br.edu.imepac.enums.Tipo;

    import java.sql.*;
    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;


/*
  linha 186 = agregar juntamente com o perfil e especialidade , provavelmente mudando tambem no banco de dados
 */
    public class AdminDAOImpl implements AdminDAO {

    private void createConnection() {
        try {
            ConnectionFactory.getConnection(DbConfig.ip, DbConfig.porta,
                    DbConfig.nomeBanco, DbConfig.usuario, DbConfig.senha);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String insertNewFuncionario = "insert into Funcionario (usuario, email, senha, idade, sexo, cpf, rua, numero, complemento, bairro, cidade, estado, contato, tipo, dataNascimento, id_especialidade, id_perfil)  values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private PreparedStatement preparedStatementInsert;
    private String getFuncionarioById = "SELECT *FROM Funcionario where id=?";
    private PreparedStatement preparedStatementGetFuncionario;
    private String deleteFuncionarioById = "DELETE FROM Funcionario where id=?";
    private PreparedStatement preparedStatementDeleteFuncionario;
    private String listAllfuncionarios = "SELECT *FROM Funcionario";
    private PreparedStatement preparedStatementListAll;
    private String updateFuncionario = "UPDATE Funcionario SET usuario = ?, senha = ?, idade = ?, sexo = ?, cpf = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, contato = ?, email = ?, dataNascimento = ?, tipo = ? WHERE id = ?";
    private PreparedStatement preparedStatementUpdateFuncionario;

    //CONSULTA
    private String insertNewConsulta = "insert into Consulta(dataHorario , sintomas , eRetorno , estaAtiva , id_paciente , id_funcionario , id_prontuario ,id_convenio) values(? , ? , ? , ? , ? , ? , ? ,?)";
    private PreparedStatement pstinsert;

    private String selectAllConsulta = "SELECT *FROM Consulta";
    private PreparedStatement pstselectAll;

    private String deleteConsulta = "DELETE FROM Consulta where id = ?";
    private PreparedStatement pstdeleteConsulta;

    private String updateConsulta = "UPDATE Consulta SET sintomas = ? set e_retorno = ? set esta_ativa set paciente_id = ? set prontuario_id = ? set convenio_id = ? where id = ?";
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

    private final String getPacienteByCpf = "SELECT *FROM Paciente WHERE cpf = ?";
    private PreparedStatement pstGetPacienteByCpf;


    private String getConsulta = "SELECT *FROM Consulta where funcionario_id= ?";
    private PreparedStatement pstGetConsultasById;

    private String insertNewProntuario = "insert into Prontuario(receituario , exames , observacao) values (? , ? , ?)";
    private PreparedStatement pstinsertProntuario;

    private String selectAllProntuarios = "SELECT *FROM Prontuario";
    private PreparedStatement pstselectAllProntuario;

    private String deleteProntuario = "DELETE FROM Prontuario where id = ?";
    private PreparedStatement pstdelete;

    private String updateProntuario = "UPDATE Prontuario SET receituario = ? set exames = ? set observacao where id = ?";
    private PreparedStatement pstupdate;

    private String getProntuarioById = "SELECT *FROM Prontuario where id = ?";
    private PreparedStatement pstGetProntuarioById;

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

    // Comandos SQL para Especialidade
    private String insertNewEspecialidade = "insert into Especialidade (nome, descricao) values (?, ?)";
    private PreparedStatement preparedStatementInsertEspecialidade;

    private String getEspecialidadeById = "SELECT *FROM Especialidade where id = ?";
    private PreparedStatement preparedStatementGetEspecialidadeById;

    private String deleteEspecialidadeById = "DELETE FROM Especialidade where id = ?";
    private PreparedStatement preparedStatementDeleteEspecialidade;

    private String listAllEspecialidades = "SELECT *FROM Especialidade";
    private PreparedStatement preparedStatementListAllEspecialidades;

    private String updateEspecialidade = "UPDATE Especialidade SET nome = ?, descricao = ? where id = ?";
    private PreparedStatement preparedStatementUpdateEspecialidade;


    public AdminDAOImpl(final Connection connection) throws SQLException {
        preparedStatementInsert = connection.prepareStatement(insertNewFuncionario);
        preparedStatementGetFuncionario = connection.prepareStatement(getFuncionarioById);
        preparedStatementDeleteFuncionario = connection.prepareStatement(deleteFuncionarioById);
        preparedStatementListAll = connection.prepareStatement(listAllfuncionarios);
        preparedStatementUpdateFuncionario = connection.prepareStatement(updateFuncionario);
        pstinsert = connection.prepareStatement(insertNewConsulta);
        pstselectAll = connection.prepareStatement(selectAllConsulta);
        pstdeleteConsulta = connection.prepareStatement(deleteConsulta);
        pstupdateConsulta = connection.prepareStatement(updateConsulta);
        pstGetConsultaById = connection.prepareStatement(getConsultaById);

        //PACIENTE
        pstInsertPaciente = connection.prepareStatement(insertNewPaciente);
        pstselectPacientes = connection.prepareStatement(selectAllPacientes);
        pstdeletePaciente = connection.prepareStatement(deletePaciente);
        pstupdatePaciente = connection.prepareStatement(updatePaciente);
        pstGetPacienteByCpf = connection.prepareStatement(getPacienteByCpf);

        //MEDICO
        pstGetConsultasById = connection.prepareStatement(getConsulta);
        pstinsertProntuario = connection.prepareStatement(insertNewProntuario);
        pstselectAllProntuario = connection.prepareStatement(selectAllProntuarios);
        pstdelete = connection.prepareStatement(deleteProntuario);
        pstupdate = connection.prepareStatement(updateProntuario);
        pstGetProntuarioById = connection.prepareStatement(getProntuarioById);

        //CONVENIO
        preparedStatementInsertConvenio = connection.prepareStatement(insertNewConvenio);
        preparedStatementGetConvenioById = connection.prepareStatement(getConvenioById);
        preparedStatementDeleteConvenio = connection.prepareStatement(deleteConvenioById);
        preparedStatementListAllConvenios = connection.prepareStatement(listAllConvenios);
        preparedStatementUpdateConvenio = connection.prepareStatement(updateConvenio);

        //ESPECIALIDADE
        preparedStatementInsertEspecialidade = connection.prepareStatement(insertNewEspecialidade);
        preparedStatementGetEspecialidadeById = connection.prepareStatement(getEspecialidadeById);
        preparedStatementDeleteEspecialidade = connection.prepareStatement(deleteEspecialidadeById);
        preparedStatementListAllEspecialidades = connection.prepareStatement(listAllEspecialidades);
        preparedStatementUpdateEspecialidade = connection.prepareStatement(updateEspecialidade);

    }

    //cadastrar o funcionario
    public int cadastrarFuncionario(Funcionario funcionario) {
        try {
            createConnection();
            preparedStatementInsert.clearParameters();
            preparedStatementInsert.setString(1, funcionario.getUsuario());
            preparedStatementInsert.setString(2, funcionario.getEmail());
            preparedStatementInsert.setInt(3, funcionario.getSenha());
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
            preparedStatementInsert.setString(14, String.valueOf(funcionario.getRole()));
            preparedStatementInsert.setTimestamp(15, Timestamp.valueOf(funcionario.getDataNascimento()));

            // Setando o id da especialidade e do perfil
            preparedStatementInsert.setInt(16, funcionario.getEspecialidade() != null ? funcionario.getEspecialidade().getId() : 0);  // Definindo valor 0 para caso de especialidade nula
            preparedStatementInsert.setInt(17, funcionario.getPerfil() != null ? funcionario.getPerfil().getId() : 0);  // Definindo valor 0 para caso de perfil nulo

            return preparedStatementInsert.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar o funcionário: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar o funcionário no banco de dados.", e);
        }
    }

    public Funcionario getFuncionarioById(final int id) {
        try {
            createConnection();
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
                funcionario.setRole(Tipo.valueOf(resultSet.getString("tipo")));
                funcionario.setSexo(resultSet.getString("sexo").charAt(0));
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setRua(resultSet.getString("rua"));
                funcionario.setNumero(resultSet.getString("numero"));
                funcionario.setComplemento(resultSet.getString("complemento"));
                funcionario.setBairro(resultSet.getString("bairro"));
                funcionario.setCidade(resultSet.getString("cidade"));
                funcionario.setEstado(resultSet.getString("estado"));
                funcionario.setContato(resultSet.getString("contato"));
                funcionario.setDataNascimento(resultSet.getTimestamp("dataNascimento").toLocalDateTime());
            }
            return funcionario;
        } catch (SQLException e) {
            System.err.println("Erro ao obter o funcionário com id " + id + ": " + e.getMessage());
            throw new RuntimeException("Erro ao consultar o banco de dados para obter o funcionário.", e);
        }
    }

    // Deleta o funcionario pelo id
    public int deleteFuncionarioById(final int id) {
        try {
            createConnection();
            preparedStatementDeleteFuncionario.clearParameters();
            preparedStatementDeleteFuncionario.setInt(1, id);
            return preparedStatementDeleteFuncionario.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o funcionário com id " + id + ": " + e.getMessage());
            throw new RuntimeException("Erro ao deletar o funcionário no banco de dados.", e);
        }
    }

    // Listar todos os funcionários
    public List<Funcionario> listAllFuncionarios() {
        try {
            createConnection();
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
                funcionario.setSexo(result.getString("sexo").charAt(0));
                funcionario.setCpf(result.getString("cpf"));
                funcionario.setRua(result.getString("rua"));
                funcionario.setNumero(result.getString("numero"));
                funcionario.setComplemento(result.getString("complemento"));
                funcionario.setDataNascimento(result.getTimestamp("dataNascimento").toLocalDateTime());
                funcionario.setRole(Tipo.valueOf(result.getString("tipo")));
                localList.add(funcionario);
            }
            return localList;
        } catch (SQLException e) {
            System.err.println("Erro ao listar os funcionários: " + e.getMessage());
            throw new RuntimeException("Erro ao listar os funcionários no banco de dados.", e);
        }
    }

    // Atualizar funcionário
    public int updateFuncionario(String usuario, int idade, char sexo, String cpf, String rua,
                                 String numero, String complemento, String bairro, String cidade,
                                 String estado, String contato, String email, int senha, LocalDateTime dataNnascimento, Tipo tipo, final int whereId) {
        try {
            createConnection();
            preparedStatementUpdateFuncionario.clearParameters();
            preparedStatementUpdateFuncionario.setString(1, usuario);
            preparedStatementUpdateFuncionario.setInt(2, senha);
            preparedStatementUpdateFuncionario.setInt(3, idade);
            preparedStatementUpdateFuncionario.setString(4, String.valueOf(sexo));
            preparedStatementUpdateFuncionario.setString(5, cpf);
            preparedStatementUpdateFuncionario.setString(6, rua);
            preparedStatementUpdateFuncionario.setString(7, numero);
            preparedStatementUpdateFuncionario.setString(8, complemento);
            preparedStatementUpdateFuncionario.setString(9, bairro);
            preparedStatementUpdateFuncionario.setString(10, cidade);
            preparedStatementUpdateFuncionario.setString(11, estado);
            preparedStatementUpdateFuncionario.setString(12, contato);
            preparedStatementUpdateFuncionario.setString(13, email);
            preparedStatementUpdateFuncionario.setTimestamp(14, Timestamp.valueOf(dataNnascimento));
            preparedStatementUpdateFuncionario.setString(15, String.valueOf(tipo));
            preparedStatementUpdateFuncionario.setInt(16, whereId);
            return preparedStatementUpdateFuncionario.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o funcionário com id " + whereId + ": " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar os dados do funcionário no banco de dados.", e);
        }
    }

    // Cadastrar uma consulta
    public void cadastrarConsulta(final Consulta consulta) {
        try {
            createConnection();
            pstinsert.clearParameters();
            pstinsert.setTimestamp(1, Timestamp.valueOf(consulta.getDataHorario()));
            pstinsert.setString(2, consulta.getSintomas());
            pstinsert.setBoolean(3, consulta.iseRetorno());
            pstinsert.setBoolean(4, consulta.isEstaAtiva());
            pstinsert.setInt(5, consulta.getPaciente().getId());
            pstinsert.setInt(6, consulta.getFuncionario().getId());
            pstinsert.setInt(7, consulta.getProntuario().getId());
            pstinsert.setInt(8, consulta.getConvenio().getId());
            pstinsert.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar consulta: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar consulta no banco de dados.", e);
        }
    }

    // Consulta pelo id
    public Consulta getConsultaById(final int id) {
        try {
            createConnection();
            pstGetConsultaById.clearParameters();
            Consulta consulta = new Consulta();
            pstGetConsultaById.setInt(1, id);
            ResultSet resultSet = pstGetConsultaById.executeQuery();
            while (resultSet.next()) {
                consulta.setId(resultSet.getInt("id"));
                consulta.setDataHorario(resultSet.getTimestamp("dataHorario").toLocalDateTime());
                consulta.setSintomas(resultSet.getString("sintomas"));
                consulta.seteRetorno(resultSet.getBoolean("e_retorno"));
                consulta.setEstaAtiva(resultSet.getBoolean("esta_ativa"));
                consulta.setConvenio((Convenio) resultSet.getObject("convenio_id"));
                consulta.setPaciente((Paciente) resultSet.getObject("paciente_id"));
                consulta.setProntuario((Prontuario) resultSet.getObject("prontuario_id"));
                consulta.setFuncionario((Funcionario) resultSet.getObject("funcionario_id"));
            }
            return consulta;
        } catch (SQLException e) {
            System.err.println("Erro ao obter consulta com id " + id + ": " + e.getMessage());
            throw new RuntimeException("Erro ao consultar a consulta no banco de dados.", e);
        }
    }

    // Lista todas as consultas
    public List<Consulta> listAllConsultas() {
        try {
            createConnection();
            List<Consulta> localConsulta = new ArrayList<>();
            ResultSet result = pstselectAll.executeQuery();
            while (result.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(result.getInt("id"));
                consulta.setDataHorario(result.getTimestamp("dataHorario").toLocalDateTime());
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
        } catch (SQLException e) {
            System.err.println("Erro ao listar as consultas: " + e.getMessage());
            throw new RuntimeException("Erro ao listar as consultas no banco de dados.", e);
        }
    }

    // Deletar consulta
    public void deleteConsulta(final int id) {
        try {
            createConnection();
            pstdeleteConsulta.clearParameters();
            pstdeleteConsulta.setInt(1, id);
            pstdeleteConsulta.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar consulta com id " + id + ": " + e.getMessage());
            throw new RuntimeException("Erro ao deletar a consulta no banco de dados.", e);
        }
    }

    // Atualizar consulta
    public int updateConsulta(final String sintomas, final boolean eRetorno, final boolean estaAtiva, final int pacienteId,
                              final int prontuarioId, final int convenioId, final int whereId) {
        try {
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
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar consulta com id " + whereId + ": " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar a consulta no banco de dados.", e);
        }
    }

    //cadastra um Paciente
    public int cadastrarPaciente(final Paciente paciente) {
        try {
            createConnection();
            pstInsertPaciente.clearParameters();
            pstInsertPaciente.setString(1, paciente.getNome());
            pstInsertPaciente.setInt(2, paciente.getIdade());
            pstInsertPaciente.setString(3, String.valueOf(paciente.getSexo()));
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
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar paciente", e);
        }
    }

    // Consulta um Paciente pelo CPF
    public Paciente getPacienteByCpf(final String cpf) {
        try {
            createConnection();
            pstGetPacienteByCpf.clearParameters();
            Paciente paciente = new Paciente();
            pstGetPacienteByCpf.setString(1, cpf);
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
        } catch (SQLException e) {
            System.err.println("Erro ao consultar paciente por CPF: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar paciente", e);
        }
    }

    // Lista todos os pacientes
    public List<Paciente> listAllPacientes() {
        try {
            createConnection();
            List<Paciente> localPaciente = new ArrayList<>();
            ResultSet resultSet = pstselectPacientes.executeQuery();
            while (resultSet.next()) {
                Paciente paciente = new Paciente();
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
                localPaciente.add(paciente);
            }
            return localPaciente;
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
            throw new RuntimeException("Erro ao listar pacientes", e);
        }
    }

    // Deletar informações de paciente
    public int deletePaciente(final String cpf) {
        try {
            createConnection();
            pstdeletePaciente.clearParameters();
            pstdeletePaciente.setString(1, cpf);
            return pstdeletePaciente.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar paciente", e);
        }
    }

    // Atualizar Paciente
    public int updatePaciente(String nome, int idade, char sexo, String cpf, String rua, String numero, String complemento,
                              String bairro, String cidade, String estado, String contato, String email, LocalDateTime dataNascimento, final int whereId) {
        try {
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
            pstupdatePaciente.setTimestamp(13, Timestamp.valueOf(dataNascimento));
            pstupdatePaciente.setInt(14, whereId);
            return pstupdatePaciente.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar paciente", e);
        }
    }

    // Listar Consultas de um médico
    public List<Consulta> listMyConsultas(int medicoId) {
        try {
            createConnection();
            pstGetConsultasById.clearParameters();
            List<Consulta> localConsulta = new ArrayList<>();
            pstGetConsultasById.setInt(1, medicoId);
            ResultSet resultSet = pstGetConsultasById.executeQuery();
            while (resultSet.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(resultSet.getInt("id"));
                consulta.setDataHorario(resultSet.getTimestamp("dataHorario").toLocalDateTime());
                consulta.setSintomas(resultSet.getString("sintomas"));
                consulta.seteRetorno(resultSet.getBoolean("e_retorno"));
                consulta.setEstaAtiva(resultSet.getBoolean("esta_ativa"));
                consulta.setConvenio((Convenio) resultSet.getObject("convenio_id"));
                consulta.setPaciente((Paciente) resultSet.getObject("paciente_id"));
                consulta.setProntuario((Prontuario) resultSet.getObject("prontuario_id"));
                consulta.setFuncionario((Funcionario) resultSet.getObject("funcionario_id"));
                localConsulta.add(consulta);
            }
            return localConsulta;
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas: " + e.getMessage());
            throw new RuntimeException("Erro ao listar consultas", e);
        }
    }

    // Cadastrar prontuário
    public int cadastrarProntuario(Prontuario prontuario) {
        try {
            createConnection();
            pstinsertProntuario.clearParameters();
            pstinsertProntuario.setString(1, prontuario.getReceituario());
            pstinsertProntuario.setString(2, prontuario.getExames());
            pstinsertProntuario.setString(3, prontuario.getObservacao());
            return pstinsertProntuario.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar prontuário: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar prontuário", e);
        }
    }

    // Consultar prontuário por ID
    public Prontuario getProntuarioById(int id) {
        try {
            createConnection();
            pstGetProntuarioById.clearParameters();
            Prontuario prontuario = new Prontuario();
            pstGetProntuarioById.setInt(1, id);
            ResultSet resultSet = pstGetProntuarioById.executeQuery();
            while (resultSet.next()) {
                prontuario.setId(resultSet.getInt("id"));
                prontuario.setReceituario(resultSet.getString("receituario"));
                prontuario.setExames(resultSet.getString("exames"));
                prontuario.setObservacao(resultSet.getString("observacao"));
            }
            return prontuario;
        } catch (SQLException e) {
            System.err.println("Erro ao consultar prontuário: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar prontuário", e);
        }
    }

    // Listar todos os prontuários
    public List<Prontuario> listAllProntuarios() {
        try {
            createConnection();
            List<Prontuario> localProntuario = new ArrayList<>();
            ResultSet resultSet = pstselectAllProntuario.executeQuery();
            while (resultSet.next()) {
                Prontuario prontuario = new Prontuario();
                prontuario.setId(resultSet.getInt("id"));
                prontuario.setReceituario(resultSet.getString("receituario"));
                prontuario.setExames(resultSet.getString("exames"));
                prontuario.setObservacao(resultSet.getString("observacao"));
                localProntuario.add(prontuario);
            }
            return localProntuario;
        } catch (SQLException e) {
            System.err.println("Erro ao listar prontuários: " + e.getMessage());
            throw new RuntimeException("Erro ao listar prontuários", e);
        }
    }

    // Deletar prontuário
    public int deleteProntuario(int id) {
        try {
            createConnection();
            pstdelete.clearParameters();
            pstdelete.setInt(1, id);
            return pstdelete.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar prontuário: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar prontuário", e);
        }
    }

    // Atualizar prontuário
    public int updateProntuario(String receituario, String exames, String observacao, int whereId) {
        try {
            createConnection();
            pstupdate.clearParameters();
            pstupdate.setString(1, receituario);
            pstupdate.setString(2, exames);
            pstupdate.setString(3, observacao);
            pstupdate.setInt(4, whereId);
            return pstupdate.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar prontuário: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar prontuário", e);
        }
    }

    // Cadastrar convênio
    public int cadastrarConvenio(Convenio convenio) {
        try {
            createConnection();
            preparedStatementInsertConvenio.clearParameters();
            preparedStatementInsertConvenio.setString(1, convenio.getNome());
            preparedStatementInsertConvenio.setString(2, convenio.getDescricao());
            return preparedStatementInsertConvenio.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar convênio: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar convênio", e);
        }
    }

    // Consultar convênio por ID
    public Convenio getConvenioById(final int id) {
        try {
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
        } catch (SQLException e) {
            System.err.println("Erro ao consultar convênio: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar convênio", e);
        }
    }

    public int deleteConvenioById(final int id) {
        try {
            createConnection();
            preparedStatementDeleteConvenio.clearParameters();
            preparedStatementDeleteConvenio.setInt(1, id);
            return preparedStatementDeleteConvenio.executeUpdate();
        } catch (SQLException e) {
            // Log ou tratar a exceção de forma adequada
            System.err.println("Erro ao deletar convênio: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar convênio", e);  // Pode lançar a exceção ou criar uma customizada
        }
    }

    public List<Convenio> listAllConvenios() {
        try {
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
        } catch (SQLException e) {
            // Log ou tratar a exceção de forma adequada
            System.err.println("Erro ao listar convênios: " + e.getMessage());
            throw new RuntimeException("Erro ao listar convênios", e);
        }
    }

    public int updateConvenio(String nome, String descricao, final int id) {
        try {
            createConnection();
            preparedStatementUpdateConvenio.clearParameters();
            preparedStatementUpdateConvenio.setString(1, nome);
            preparedStatementUpdateConvenio.setString(2, descricao);
            preparedStatementUpdateConvenio.setInt(3, id);
            return preparedStatementUpdateConvenio.executeUpdate();
        } catch (SQLException e) {
            // Log ou tratar a exceção de forma adequada
            System.err.println("Erro ao atualizar convênio: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar convênio", e);
        }
    }

    public int cadastrarEspecialidade(Especialidade especialidade) {
        try {
            createConnection();
            preparedStatementInsertEspecialidade.clearParameters();
            preparedStatementInsertEspecialidade.setString(1, especialidade.getNome());
            preparedStatementInsertEspecialidade.setString(2, especialidade.getDescricao());
            return preparedStatementInsertEspecialidade.executeUpdate();
        } catch (SQLException e) {
            // Log ou tratar a exceção de forma adequada
            System.err.println("Erro ao cadastrar especialidade: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar especialidade", e);
        }
    }

    public Especialidade getEspecialidadeById(final int id) {
        try {
            createConnection();
            preparedStatementGetEspecialidadeById.clearParameters();
            Especialidade especialidade = new Especialidade();
            preparedStatementGetEspecialidadeById.setInt(1, id);
            ResultSet resultSet = preparedStatementGetEspecialidadeById.executeQuery();
            while (resultSet.next()) {
                especialidade.setId(resultSet.getInt("id"));
                especialidade.setNome(resultSet.getString("nome"));
                especialidade.setDescricao(resultSet.getString("descricao"));
            }
            return especialidade;
        } catch (SQLException e) {
            // Log ou tratar a exceção de forma adequada
            System.err.println("Erro ao obter especialidade: " + e.getMessage());
            throw new RuntimeException("Erro ao obter especialidade", e);
        }
    }

    public int deleteEspecialidadeById(final int id) {
        try {
            createConnection();
            preparedStatementDeleteEspecialidade.clearParameters();
            preparedStatementDeleteEspecialidade.setInt(1, id);
            return preparedStatementDeleteEspecialidade.executeUpdate();
        } catch (SQLException e) {
            // Log ou tratar a exceção de forma adequada
            System.err.println("Erro ao deletar especialidade: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar especialidade", e);
        }
    }

    public List<Especialidade> listAllEspecialidades() {
        try {
            createConnection();
            List<Especialidade> especialidades = new ArrayList<>();
            ResultSet resultSet = preparedStatementListAllEspecialidades.executeQuery();
            while (resultSet.next()) {
                Especialidade especialidade = new Especialidade();
                especialidade.setId(resultSet.getInt("id"));
                especialidade.setNome(resultSet.getString("nome"));
                especialidade.setDescricao(resultSet.getString("descricao"));
                especialidades.add(especialidade);
            }
            return especialidades;
        } catch (SQLException e) {
            // Log ou tratar a exceção de forma adequada
            System.err.println("Erro ao listar especialidades: " + e.getMessage());
            throw new RuntimeException("Erro ao listar especialidades", e);
        }
    }

    public int updateEspecialidade(String nome, String descricao, final int id) {
        try {
            createConnection();
            preparedStatementUpdateEspecialidade.clearParameters();
            preparedStatementUpdateEspecialidade.setString(1, nome);
            preparedStatementUpdateEspecialidade.setString(2, descricao);
            preparedStatementUpdateEspecialidade.setInt(3, id);
            return preparedStatementUpdateEspecialidade.executeUpdate();
        } catch (SQLException e) {
            // Log ou tratar a exceção de forma adequada
            System.err.println("Erro ao atualizar especialidade: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar especialidade", e);
        }
    }
}


