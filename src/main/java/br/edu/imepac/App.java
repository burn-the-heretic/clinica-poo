package br.edu.imepac;

import br.edu.imepac.config.ConnectionFactory;
import br.edu.imepac.config.DbConfig;
import br.edu.imepac.dao.Impl.AdminDAOImpl;
import br.edu.imepac.dao.Impl.AtendenteDAOImpl;
import br.edu.imepac.entities.Funcionario;
import br.edu.imepac.enums.Tipo;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;


/*
TESTES
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection connection = ConnectionFactory.getConnection(DbConfig.ip , DbConfig.porta,
                    DbConfig.nomeBanco, DbConfig.usuario,DbConfig.senha);
            if (connection != null){
                System.out.printf("Connected to %s\n", connection);
                Funcionario funcionario = new Funcionario();
                funcionario.setUsuario("TITOR");
                funcionario.setCpf("00000000000");
                funcionario.setEmail("admin@imepac.com");
                funcionario.setBairro("Bairro");
                funcionario.setCidade("Cidade");
                funcionario.setEstado("MG");
                funcionario.setRua("Rua");
                funcionario.setComplemento("Complemento");
                funcionario.setNumero("12345");
                funcionario.setContato("999292");
                funcionario.setDataNascimento(LocalDateTime.now());
                funcionario.setSenha(1232434);
                funcionario.setIdade(12);
                funcionario.setRole(Tipo.MEDICO);
//                Paciente paciente = new Paciente();
//
//                paciente.setNome("João Silva"); // Nome do paciente
//                paciente.setIdade(30); // Idade do paciente
//                paciente.setSexo('M'); // Sexo do paciente (M ou F)
//                paciente.setCpf("123.456.789-00"); // CPF do paciente
//                paciente.setRua("Rua das Flores"); // Endereço - Rua
//                paciente.setNumero("123"); // Endereço - Número
//                paciente.setComplemento("Apto 101"); // Complemento do endereço
//                paciente.setBairro("Centro"); // Bairro
//                paciente.setCidade("São Paulo"); // Cidade
//                paciente.setEstado("SP"); // Estado
//                paciente.setContato("99999-8888"); // Contato (telefone)
//                paciente.setEmail("joao.silva@email.com"); // Email
//                paciente.setDataNascimento(LocalDateTime.of(1993, 5, 15, 0, 0));
//
//
                AtendenteDAOImpl atendenteDAO = new AtendenteDAOImpl(connection);
                AdminDAOImpl adminDAO = new AdminDAOImpl(connection);
                System.out.println(adminDAO.listAllFuncionarios());
                adminDAO.cadastrarFuncionario(funcionario);
//                Prontuario prontuario = new Prontuario();

//                Convenio convenio = new Convenio();
//
//// Definindo valores para o convênio// ID do convênio (geralmente auto-incrementado pelo banco de dados)
//                convenio.setNome("Unimed São Paulo"); // Nome do convênio
//                convenio.setDescricao("Convênio médico com cobertura nacional para consultas e exames."); // Descrição do convênio
//// Definindo valores para o prontuário/ ID do prontuário (geralmente auto-incrementado)
//                prontuario.setReceituario("Receita de antibiótico para tratamento de infecção."); // Receituário médico
//                prontuario.setExames("Exame de sangue: tudo normal. Radiografia: não foram encontrados problemas."); // Resultados de exames
//                prontuario.setObservacao("Paciente apresenta sinais de melhora, continuar com o tratamento por mais 7 dias."); // Observações médicas
//
//
//                CadastroConsultaDTO cadastroConsultaDTO = new CadastroConsultaDTO(
//                        LocalDateTime.now(),
//                        "dor de cabeça",
//                        true,
//                        true,
//                        paciente,
//                        prontuario,
//                        convenio
//                );
//                atendenteService.cadastrar
//                atendenteService.cadastrarConsulta(cadastroConsultaDTO);
            }
            else {
                System.out.println("Failed to connect to database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
