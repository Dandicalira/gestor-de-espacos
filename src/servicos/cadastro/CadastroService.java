package servicos.cadastro;
import entidades.*;
import aplicacao.EntradaDeDados;
import excecoes.*;

public class CadastroService {

	//Método Construtor Privado
	private CadastroService() {
		
	}
	
	
	//Métodos de Cadastro
	public static void cadastrarAluno() {
		//Leitura dos Atributos
		String nome = cadastrarNome();
		String senha = cadastrarSenha();
		String matricula = cadastrarMatricula();
		String email = cadastrarEmailAluno(matricula);
		String telefone = cadastrarTelefone();
		String curso = cadastrarCurso();
		int semestre = cadastrarSemestre();
		
		//Criação e Armazenamento do Objeto
		Aluno aluno = new Aluno(nome, email, senha, telefone, curso, matricula, semestre);
		Registro.registrarAluno(aluno);
		
		System.out.println("O cadastro do aluno foi realizado com sucesso!");
	}
	
	public static void cadastrarProfessor() {
		//Leitura dos Atributos
		String nome = cadastrarNome();
		String senha = cadastrarSenha();
		String matriculaInstitucional = cadastrarMatriculaInstitucional();
		String email = cadastrarEmailServidor();
		String telefone = cadastrarTelefone();
		String curso = cadastrarCurso();
		String cargoAcademico = cadastrarCargoAcademico();
		
		//Criação e Armazenamento do Objeto
		Professor professor = new Professor(nome, email, senha, telefone, matriculaInstitucional, curso, cargoAcademico);
		Registro.registrarServidor(professor);
		
		System.out.println("O cadastro do professor foi realizado com sucesso!");
	}
	
	public static void cadastrarAdministrativo() {
		//Leitura dos Atributos
		String nome = cadastrarNome();
		String senha = cadastrarSenha();
		String matriculaInstitucional = cadastrarMatriculaInstitucional();
		String email = cadastrarEmailServidor();
		String telefone = cadastrarTelefone();
		String departamento = cadastrarDepartamento();
		String cargo = cadastrarCargo();
		
		//Criação e Armazenamento do Objeto
		Administrativo administrativo = new Administrativo(nome, email, senha, telefone, matriculaInstitucional, cargo, departamento);
		Registro.registrarServidor(administrativo);
		
		System.out.println("O cadastro do servidor administrativo foi realizado com sucesso!");
	}
	
	//Métodos Estáticos Privados
	private static String cadastrarNome() {
		String nome;
		while (true) {
			try {
				System.out.print("Digite o seu nome completo: ");
				nome = EntradaDeDados.lerStringEspacada();
				nome = Formatar.formatarNome(nome);
				Verificar.verificarNome(nome);
				return nome;
			} catch (CampoVazioException | TipoInesperadoException e ) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static String cadastrarEmailAluno(String matricula) {
		String email;
		while (true) {
			try {
				System.out.print("Digite o seu e-mail: ");
				email = EntradaDeDados.lerString().toLowerCase();
				Verificar.verificarEmailAluno(email, matricula);
				return email;
			} catch (CampoVazioException | EmailAlunoFormatoInvalidoException | EmailDuplicadoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static String cadastrarEmailServidor() {
		String email;
		while (true) {
			try {
				System.out.print("Digite o seu e-mail: ");
				email = EntradaDeDados.lerString().toLowerCase();
				Verificar.verificarEmailServidor(email);
				return email;
			} catch (CampoVazioException | EmailServidorFormatoInvalidoException | EmailDuplicadoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static String cadastrarTelefone() {
		String telefone;
		while (true) {
			try {
				System.out.print("Digite o seu telefone: ");
				telefone = EntradaDeDados.lerString();
				Verificar.verificarTelefone(telefone);
				return telefone;
			} catch (CampoVazioException | TipoInesperadoException | ForaDoIntervaloException | TelefoneDuplicadoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static String cadastrarCurso() {
		String curso;
		while (true) {
			try {
				System.out.print("Digite o seu curso: ");
				curso = Formatar.capitalizar(EntradaDeDados.lerStringEspacada());
				Verificar.verificarCurso(curso);
				return curso;
			} catch (CampoVazioException | TipoInesperadoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static String cadastrarMatricula() {
		String matricula;
		while (true) {
			try {
				System.out.print("Digite a sua matrícula: ");
				matricula = EntradaDeDados.lerString();
				Verificar.verificarMatricula(matricula);
				return matricula;
			} catch (CampoVazioException | TipoInesperadoException | MatriculaFormatoInvalidoException | MatriculaDuplicadaException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static int cadastrarSemestre() {
		int semestre;
		while (true) {
			try {
				System.out.print("Digite o seu semestre: ");
				semestre = EntradaDeDados.lerInteiro();
				Verificar.verificarSemestre(semestre);
				return semestre;
			} catch (ForaDoIntervaloException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static String cadastrarMatriculaInstitucional() {
		String matriculaInstitucional;
		while (true) {
			try {
				System.out.print("Digite a sua matrícula institucional: ");
				matriculaInstitucional = EntradaDeDados.lerString();
				Verificar.verificarMatriculaInstitucional(matriculaInstitucional);
				return matriculaInstitucional;
			} catch (CampoVazioException | TipoInesperadoException | ForaDoIntervaloException | MatriculaDuplicadaException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static String cadastrarCargo() {
		String cargo;
		while (true) {
			try {
				System.out.print("Digite o seu cargo: ");
				cargo = Formatar.capitalizar(EntradaDeDados.lerStringEspacada());
				Verificar.verificarCargo(cargo);
				return cargo;
			} catch (CampoVazioException | TipoInesperadoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static String cadastrarDepartamento() {
		String departamento;
		while (true) {
			try {
				System.out.print("Digite o seu departamento: ");
				departamento = Formatar.capitalizar(EntradaDeDados.lerStringEspacada());
				Verificar.verificarDepartamento(departamento);
				return departamento;
			} catch (CampoVazioException | TipoInesperadoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static String cadastrarCargoAcademico() {
		String cargoAcademico;
		while (true) {
			try {
				System.out.print("Digite o seu cargo acadêmico: ");
				cargoAcademico = Formatar.capitalizar(EntradaDeDados.lerStringEspacada());
				Verificar.verificarCargoAcademico(cargoAcademico);
				return cargoAcademico;
			} catch (CampoVazioException | ForaDoIntervaloException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static String cadastrarSenha() {
		String senha;
		while (true) {
			try {
				System.out.println("A senha deve conter:\n"
						         + "no mínimo, 8 caracteres e, no máximo, 20 caracteres,\n"
						         + "no mínimo, 1 caractere especial (@, #, ...)\n"
						         + "no mínimo, 1 dígito numérico\n"
						         + "no mínimo, 1 letra maiúscula e 1 letra minúscula\n"
						         + "não deve conter espaços em branco\n");
				System.out.print("Digite a sua senha: ");
				senha = EntradaDeDados.lerString();
				Verificar.verificarSenha(senha);
				return senha;
			} catch (CampoVazioException | SenhaFormatoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
