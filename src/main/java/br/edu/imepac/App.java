package br.edu.imepac;

import br.edu.imepac.config.ConnectionFactory;
import br.edu.imepac.config.DbConfig;
import br.edu.imepac.dao.AdminDAO;
import br.edu.imepac.dao.Impl.AdminDAOImpl;
import br.edu.imepac.dao.Impl.AtendenteDAOImpl;
import br.edu.imepac.entities.*;
import br.edu.imepac.enums.Tipo;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static br.edu.imepac.config.ConnectionFactory.getConnection;


/*
TESTES
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AdminDAO adminDAO = new AdminDAOImpl(getConnection(DbConfig.ip , DbConfig.porta,
                DbConfig.nomeBanco, DbConfig.usuario,DbConfig.senha));

        try {
            // Testar o cadastro de um novo funcionário
            Funcionario funcionario = new Funcionario();
            funcionario.setId(2);
            funcionario.setUsuario("joao123");
            funcionario.setEmail("joao@example.com");
            funcionario.setSenha(123456);
            funcionario.setIdade(30);
            funcionario.setSexo('M');
            funcionario.setCpf("12345678901");
            funcionario.setRua("Rua Principal");
            funcionario.setNumero("123");
            funcionario.setComplemento("Apto 1");
            funcionario.setBairro("Centro");
            funcionario.setCidade("São Paulo");
            funcionario.setEstado("SP");
            funcionario.setContato("(11) 98765-4321");
            funcionario.setRole(Tipo.ATENDENTE);
            funcionario.setDataNascimento(LocalDateTime.of(1993, 5, 10, 0, 0));

            // Testar o cadastro de uma especialidade
            Especialidade especialidade = new Especialidade();
            especialidade.setNome("Cardiologia");
            especialidade.setDescricao("Especialidade médica focada nas doenças do coração.");

            int especialidadeResult = adminDAO.cadastrarEspecialidade(especialidade);
            System.out.println("Especialidade cadastrada com sucesso. Linhas afetadas: " + especialidadeResult);

            // Testar o cadastro de um prontuário
            Prontuario prontuario = new Prontuario();
            prontuario.setId(1);
            prontuario.setReceituario("Receituário padrão");
            prontuario.setExames("Exames gerais realizados");
            prontuario.setObservacao("Paciente em bom estado geral");

            int prontuarioResult = adminDAO.cadastrarProntuario(prontuario);
            System.out.println("Prontuário cadastrado com sucesso. Linhas afetadas: " + prontuarioResult);

            // Testar o cadastro de um convênio
            Convenio convenio = new Convenio();
            convenio.setId(1);
            convenio.setNome("Plano Saúde Vida");
            convenio.setDescricao("Convênio médico completo");

            int convenioResult = adminDAO.cadastrarConvenio(convenio);
            System.out.println("Convênio cadastrado com sucesso. Linhas afetadas: " + convenioResult);

            // Testar o cadastro de um novo paciente
            Paciente paciente = new Paciente();
            paciente.setId(99);
            paciente.setNome("Joao zeca");
            paciente.setIdade(25);
            paciente.setSexo('F');
            paciente.setCpf("98765432100");
            paciente.setRua("Rua das Flores");
            paciente.setNumero("321");
            paciente.setComplemento("Casa 2");
            paciente.setBairro("Jardim");
            paciente.setCidade("Curitiba");
            paciente.setEstado("PR");
            paciente.setContato("(41) 91234-5678");
            paciente.setEmail("maria.silva@example.com");
            paciente.setDataNascimento(LocalDateTime.of(1998, 7, 20, 0, 0));

            int pacienteResult = adminDAO.cadastrarPaciente(paciente);
            System.out.println("Paciente cadastrado com sucesso. Linhas afetadas: " + pacienteResult);

            // Testar a busca de um paciente pelo CPF
            Paciente fetchedPaciente = adminDAO.getPacienteByCpf("98765432100");
            System.out.println("Paciente buscado: " + fetchedPaciente.getNome());

            // Testar a listagem de todos os pacientes
            List<Paciente> pacientes = adminDAO.listAllPacientes();
            System.out.println("Lista de pacientes:");
            for (Paciente p : pacientes) {
                System.out.println("- " + p.getNome());
            }

            int result = adminDAO.cadastrarFuncionario(funcionario);
            System.out.println("Funcionário cadastrado com sucesso. Linhas afetadas: " + result);

            // Testar a busca de um funcionário pelo ID
            Funcionario fetchedFuncionario = adminDAO.getFuncionarioById(1);
            System.out.println("Funcionário buscado: " + fetchedFuncionario.getUsuario());

            // Testar a atualização de um funcionário
            int updateResult = adminDAO.updateFuncionario(
                    "joao_updated", 43 , 'M', "12345678901", "Rua Nova", "456",
                    "Apto 2", "Bairro Novo", "Rio de Janeiro", "RJ",
                    "(21) 99999-9999", "joao_updated@example.com", 654321,
                    LocalDateTime.of(1992, 6, 15, 0, 0) , Tipo.ATENDENTE, 1
            );
            System.out.println("Funcionário atualizado. Linhas afetadas: " + updateResult);


            // Testar a listagem de todos os funcionários
            List<Funcionario> funcionarios = adminDAO.listAllFuncionarios();
            System.out.println("Lista de funcionários:");
            for (Funcionario f : funcionarios) {
                System.out.println("- " + f.getUsuario());
            }

            // Testar a busca de uma consulta pelo ID
            Consulta fetchedConsulta = adminDAO.getConsultaById(1);
            System.out.println("Consulta buscada: " + fetchedConsulta.getSintomas());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}