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



    public class AdminDAOImpl implements AdminDAO {

        private void createConnection() {
            try {
                ConnectionFactory.getConnection(DbConfig.ip , DbConfig.porta,
                        DbConfig.nomeBanco, DbConfig.usuario,DbConfig.senha);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private String insertNewFuncionario = "insert into Funcionario (usuario ,  email ,senha , idade, sexo , cpf , rua ,numero , complemento , bairro ,cidade , estado , contato ,tipo,dataNascimento) values (? , ? ,? , ? , ?, ? , ? , ?, ? , ? , ?, ? , ? , ?, ?)";
        private PreparedStatement preparedStatementInsert;
        private String getFuncionarioById = "select from Funcionario where id=?";
        private PreparedStatement preparedStatementGetFuncionario;
        private String deleteFuncionarioById = "delete from Funcionario where id=?";
        private PreparedStatement preparedStatementDeleteFuncionario;
        private String listAllfuncionarios = "select *from Funcionario";
        private PreparedStatement preparedStatementListAll;
        private String updateFuncionario = "update Funcionario set usuario = ? set senha = ? set idade set sexo = ? set cpf = ? set rua = ?  set numero = ? set complemento = ? set bairro = ? set cidade = ? set estado = ? set contato = ? set email = ? dataNascimento = ? set tipo = ? where id = ?";
        private PreparedStatement preparedStatementUpdateFuncionario;

        //CONSULTA
        private String insertNewConsulta = "insert into Consulta(data_horario , sintomas , e_retorno , esta_ativa , paciente_id , prontuario_id ,convenio_id , funcionario_id) values(? , ? , ? , ? , ? , ? , ? ,?)";
        private PreparedStatement pstinsert;

        private String selectAllConsulta = "select * from Consulta";
        private PreparedStatement pstselectAll;

        private String deleteConsulta = "delete from Consulta where id = ?";
        private PreparedStatement pstdeleteConsulta;

        private String updateConsulta = "update Consulta set sintomas = ? set e_retorno = ? set esta_ativa set paciente_id = ? set prontuario_id = ? set convenio_id = ? where id = ?";
        private PreparedStatement pstupdateConsulta;

        private String getConsultaById = "select from Consulta where id = ?";
        private PreparedStatement pstGetConsultaById;

        //PACIENTE
        private final String insertNewPaciente = "insert into Paciente (nome , idade , sexo , cpf , rua ,numero ,complemento,bairro , cidade ,estado ,contato , email, dataNascimento) values(? , ? , ? , ? , ? , ?, ? , ?, ? , ?, ? , ? , ? )";
        private PreparedStatement pstInsertPaciente;

        private final String selectAllPacientes = "select * from Paciente";
        private PreparedStatement pstselectPacientes;

        private final String deletePaciente = "delete from Paciente where id = ?";
        private PreparedStatement pstdeletePaciente;

        private final String updatePaciente = "update Paciente set nome = ? set senha = ? set idade set sexo = ? set cpf = ? set rua = ?  set numero = ? set complemento = ? set bairro = ? set cidade = ? set estado = ? set contato = ? set email = ? dataNascimento = ? where id = ?";
        private PreparedStatement pstupdatePaciente;

        private final String getPacienteByCpf= "select from Paciente where cpf = ?";
        private PreparedStatement pstGetPacienteByCpf;


        private String getConsulta = "select *from Consulta where funcionario_id= ?";
        private PreparedStatement pstGetConsultasById;

        private String insertNewProntuario = "insert into Prontuario(receituario , exames , observacao) values(? , ? , ?)";
        private PreparedStatement pstinsertProntuario;

        private String selectAllProntuarios = "select * from Prontuario";
        private PreparedStatement pstselectAllProntuario;

        private String deleteProntuario = "delete from Prontuario where id = ?";
        private PreparedStatement pstdelete;

        private String updateProntuario = "update Prontuario set receituario = ? set exames = ? set observacao where id = ?";
        private PreparedStatement pstupdate;

        private String getProntuarioById = "select from Prontuario where id = ?";
        private PreparedStatement pstGetProntuarioById;

        private String insertNewConvenio = "insert into Convenio (nome, descricao) values (?, ?)";
        private PreparedStatement preparedStatementInsertConvenio;

        private String getConvenioById = "select * from Convenio where id = ?";
        private PreparedStatement preparedStatementGetConvenioById;

        private String deleteConvenioById = "delete from Convenio where id = ?";
        private PreparedStatement preparedStatementDeleteConvenio;

        private String listAllConvenios = "select * from Convenio";
        private PreparedStatement preparedStatementListAllConvenios;

        private String updateConvenio = "update Convenio set nome = ?, descricao = ? where id = ?";
        private PreparedStatement preparedStatementUpdateConvenio;

        // Comandos SQL para Especialidade
        private String insertNewEspecialidade = "insert into Especialidade (nome, descricao) values (?, ?)";
        private PreparedStatement preparedStatementInsertEspecialidade;

        private String getEspecialidadeById = "select * from Especialidade where id = ?";
        private PreparedStatement preparedStatementGetEspecialidadeById;

        private String deleteEspecialidadeById = "delete from Especialidade where id = ?";
        private PreparedStatement preparedStatementDeleteEspecialidade;

        private String listAllEspecialidades = "select * from Especialidade";
        private PreparedStatement preparedStatementListAllEspecialidades;

        private String updateEspecialidade = "update Especialidade set nome = ?, descricao = ? where id = ?";
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
            pstinsert = connection.prepareStatement(insertNewProntuario);
            pstselectAll = connection.prepareStatement(selectAllProntuarios);
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
        public int cadastrarFuncionario(Funcionario funcionario) throws SQLException {
            createConnection();
            preparedStatementInsert.clearParameters();
            preparedStatementInsert.setString(1, funcionario.getUsuario());
            preparedStatementInsert.setString(2 , funcionario.getEmail());
            preparedStatementInsert.setInt(3 , funcionario.getSenha());
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
            preparedStatementInsert.setString(14 , String.valueOf(funcionario.getRole()));
            preparedStatementInsert.setTimestamp(15 , Timestamp.valueOf(funcionario.getDataNascimento()));
            return preparedStatementInsert.executeUpdate();
        }

        //pegar o funcionario pela id
        public Funcionario getFuncionarioById(final int id) throws SQLException {
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
                 funcionario.setPerfil(resultSet.getObject("perfil", Perfil.class));
                 funcionario.setEspecialidade(resultSet.getObject("especialidade", Especialidade.class));
             }
             return funcionario;
        }

        //deleta o funcionario pelo id
        public int deleteFuncionarioById(final int id) throws SQLException {
            createConnection();
            preparedStatementDeleteFuncionario.clearParameters();
            preparedStatementDeleteFuncionario.setInt(1, id);
            return preparedStatementDeleteFuncionario.executeUpdate();
        }

        //listar todos os funcionarios
        public List<Funcionario> listAllFuncionarios() throws SQLException {
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
                funcionario.setSexo(result.getString("sexo").charAt(0)); // Assume-se que o sexo é armazenado como 'M' ou 'F'
                funcionario.setCpf(result.getString("cpf"));
                funcionario.setRua(result.getString("rua"));
                funcionario.setNumero(result.getString("numero"));
                funcionario.setComplemento(result.getString("complemento"));
                funcionario.setDataNascimento(result.getTimestamp("dataNascimento").toLocalDateTime()); // Caso esteja armazenado como DATETIME
                funcionario.setRole(Tipo.valueOf(result.getString("tipo")));
                localList.add(funcionario);
            }
            return localList;
        }

        public int updateFuncionario(String usuario , int idade , char sexo , String cpf , String rua ,
                                  String numero , String complemento, String bairro , String cidade
                , String estado , String contato , String email,int senha ,LocalDateTime dataNnascimento ,Tipo tipo, final int whereId) throws SQLException {
             createConnection();
             preparedStatementUpdateFuncionario.clearParameters();
             preparedStatementUpdateFuncionario.setString(1, usuario);
             preparedStatementUpdateFuncionario.setInt(2, idade);
             preparedStatementUpdateFuncionario.setInt(3, sexo);
             preparedStatementUpdateFuncionario.setString(4, cpf);
             preparedStatementUpdateFuncionario.setString(5, rua);
             preparedStatementUpdateFuncionario.setString(6, numero);
             preparedStatementUpdateFuncionario.setString(7, complemento);
             preparedStatementUpdateFuncionario.setString(8, bairro);
             preparedStatementUpdateFuncionario.setString(9, cidade);
             preparedStatementUpdateFuncionario.setString(10, estado);
             preparedStatementUpdateFuncionario.setString(11, contato);
             preparedStatementUpdateFuncionario.setString(12, email);
             preparedStatementUpdateFuncionario.setInt(13,  senha);
             preparedStatementUpdateFuncionario.setTimestamp(14 , Timestamp.valueOf(dataNnascimento));
             preparedStatementUpdateFuncionario.setObject(15 , tipo);
             preparedStatementUpdateFuncionario.setInt(16 , whereId);
             return preparedStatementUpdateFuncionario.executeUpdate();
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

        public List<Consulta> listMyConsultas(int medicoId) throws SQLException {
            createConnection();
            pstGetConsultasById.clearParameters();
            List<Consulta> localConsulta = new ArrayList<>();
            pstGetConsultasById.setInt(1 , medicoId);
            ResultSet resultSet = pstGetConsultasById.executeQuery();
            while (resultSet.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(resultSet.getInt("id"));
                consulta.setDataHorario(resultSet.getTimestamp("data_horario").toLocalDateTime());
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
        }

        public int cadastrarPronturario(Prontuario prontuario) throws SQLException {
            createConnection();
            pstinsertProntuario.clearParameters();
            pstinsertProntuario.setString(1 , prontuario.getReceituario());
            pstinsertProntuario.setString(2 , prontuario.getExames());
            pstinsertProntuario.setString(3 , prontuario.getObservacao());
            pstinsertProntuario.executeQuery();
            return pstinsertProntuario.executeUpdate();
        }

        public Prontuario getProntuarioById(int id) throws SQLException {
            createConnection();
            pstGetProntuarioById.clearParameters();
            Prontuario prontuario = new Prontuario();
            pstGetProntuarioById.setInt(1 , id);
            ResultSet resultSet = pstGetProntuarioById.executeQuery();
            while (resultSet.next()) {
                prontuario.setId(resultSet.getInt("id"));
                prontuario.setReceituario(resultSet.getString("receituario"));
                prontuario.setExames(resultSet.getString("exames"));
                prontuario.setObservacao(resultSet.getString("observacao"));
            }
            return prontuario;
        }

        public List<Prontuario> listAllProntuarios() throws SQLException {
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
        }

        public int deleteProntuario(int id) throws SQLException {
            createConnection();
            pstdelete.clearParameters();
            pstdelete.setInt(1, id);
            return pstdelete.executeUpdate();
        }

        public int updateProntuario(String receituario, String exames, String observacao , int whereId) throws SQLException {
            createConnection();
            pstupdate.clearParameters();
            pstupdate.setString(1 , receituario);
            pstupdate.setString(2 , exames);
            pstupdate.setString(3 , observacao);
            pstupdate.setInt(4 , whereId);
            return pstupdate.executeUpdate();
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

        public int cadastrarEspecialidade(Especialidade especialidade) throws SQLException {
            createConnection();
            preparedStatementInsertEspecialidade.clearParameters();
            preparedStatementInsertEspecialidade.setString(1, especialidade.getNome());
            preparedStatementInsertEspecialidade.setString(2, especialidade.getDescricao());
            return preparedStatementInsertEspecialidade.executeUpdate();
        }

        public Especialidade getEspecialidadeById(final int id) throws SQLException {
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
        }

        public int deleteEspecialidadeById(final int id) throws SQLException {
            createConnection();
            preparedStatementDeleteEspecialidade.clearParameters();
            preparedStatementDeleteEspecialidade.setInt(1, id);
            return preparedStatementDeleteEspecialidade.executeUpdate();
        }

        public List<Especialidade> listAllEspecialidades() throws SQLException {
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
        }

        public int updateEspecialidade(String nome, String descricao, final int id) throws SQLException {
            createConnection();
            preparedStatementUpdateEspecialidade.clearParameters();
            preparedStatementUpdateEspecialidade.setString(1, nome);
            preparedStatementUpdateEspecialidade.setString(2, descricao);
            preparedStatementUpdateEspecialidade.setInt(3, id);
            return preparedStatementUpdateEspecialidade.executeUpdate();
        }
    }

