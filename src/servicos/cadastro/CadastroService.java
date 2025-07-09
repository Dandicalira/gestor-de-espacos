package servicos.cadastro;
import entidades.*;
import aplicacao.EntradaDeDados;
import excecoes.*;

import java.time.LocalTime;

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
		
	}
	
	public static void cadastrarEspacoFisico() {
		//Leitura dos Atributos
		int capacidade = cadastrarCapacidade();
		LocalTime horarioInicialDisponivel = cadastrarHorarioInicialDisponivel();
		LocalTime horarioFinalDisponivel = cadastrarHorarioFinalDisponivel();
		String localizacao = cadastrarLocalizacao();
		String tipo = cadastrarTipo();
		Equipamento[] equipamentos = new Equipamento[0];
		String resp = "";
		do {
			equipamentos = EspacoFisico.adicionarEquipamento(cadastrarEquipamento(equipamentos), equipamentos);
			System.out.print("Deseja cadastrar mais um tipo de equipamento? (S / N): ");
			resp = EntradaDeDados.lerString().toLowerCase();
		} while (!resp.equals("n") && !resp.equals("não") && !resp.equals("nao"));
		
		//Criação e Armazenamento do Objeto
		EspacoFisico espaco = new EspacoFisico(capacidade,horarioInicialDisponivel,horarioFinalDisponivel, 
				                               localizacao, tipo, equipamentos);
		switch (espaco.getTipo()) {
		case "Sala de Aula":
			Registro.registrarSalaDeAula(espaco);
			break;
		case "Laboratório":
			Registro.registrarLaboratorio(espaco);
			break;
		case "Sala de Estudos":
			Registro.registrarSalaDeEstudos(espaco);
			break;
		}
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
				System.out.println("""
						A senha deve conter:
						no mínimo, 8 caracteres e, no máximo, 20 caracteres,
						no mínimo, 1 caractere especial (@, #, ...)
						no mínimo, 1 dígito numérico
						no mínimo, 1 letra maiúscula e 1 letra minúscula
						não deve conter espaços em branco
						""");
				System.out.print("Digite a sua senha: ");
				senha = EntradaDeDados.lerString();
				Verificar.verificarSenha(senha);
				return senha;
			} catch (CampoVazioException | SenhaFormatoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static int cadastrarCapacidade() {
		int capacidade;
		while (true) {
			try {
				System.out.print("Digite a capacidade do espaço: ");
				capacidade = EntradaDeDados.lerInteiro();
				Verificar.verificarCapacidade(capacidade);
				return capacidade;
			} catch (ForaDoIntervaloException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	private static String cadastrarLocalizacao() {
		String localizacao;
		while (true) {
			try {
				System.out.print("Digite a localização da sala(S2, I3, LAB NEI 2, ...): ");
				localizacao = EntradaDeDados.lerStringEspacada().toUpperCase();
				Verificar.verificarLocalizacao(localizacao);
				return localizacao;
			} catch (CampoVazioException | TipoInesperadoException | LocalizacaoDuplicadaException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static Equipamento cadastrarEquipamento(Equipamento[] equipamentos) {
		String nome;
		int quantidade;
		while (true) {
			try {
				System.out.print("Digite o nome do equipamento: ");
				nome = EntradaDeDados.lerString();
				Verificar.verificarNomeEquipamento(nome, equipamentos);
				break;
			} catch (CampoVazioException | TipoInesperadoException | EquipamentoDuplicadoException e) {
				System.out.println(e.getMessage());
			}
		}
		while (true) {
			try {
				System.out.print("Digite a quantidade do equipamento: ");
				quantidade = EntradaDeDados.lerInteiro();
				Verificar.verificarQuantidadeEquipamento(quantidade);
				break;
			} catch (ForaDoIntervaloException e) {
				System.out.println(e.getMessage());
			}
		}
		return new Equipamento(nome, quantidade);
	}
	
	private static LocalTime cadastrarHorarioInicialDisponivel() {
		int hora;
		int minuto;
		while (true) {
			try {
				System.out.print("Digite a hora inicial de disponibilidade da sala: ");
				hora = EntradaDeDados.lerInteiro();
				Verificar.verificarHora(hora);
				break;
			} catch (ForaDoIntervaloException e) {
				System.out.println(e.getMessage());
			}
		}
		while (true) {
			try {
				System.out.print("Digite o minuto inicial de disponibilidade da sala: ");
				minuto = EntradaDeDados.lerInteiro();
				Verificar.verificarMinuto(minuto);
				break;
			} catch (ForaDoIntervaloException e) {
				System.out.println(e.getMessage());
			}
		}
		return LocalTime.of(hora, minuto);
	}
	
	private static LocalTime cadastrarHorarioFinalDisponivel() {
		int hora;
		int minuto;
		while (true) {
			try {
				System.out.print("Digite a hora final de disponibilidade da sala: ");
				hora = EntradaDeDados.lerInteiro();
				Verificar.verificarHora(hora);
				break;
			} catch (ForaDoIntervaloException e) {
				System.out.println(e.getMessage());
			}
		}
		while (true) {
			try {
				System.out.print("Digite o minuto final de disponibilidade da sala: ");
				minuto = EntradaDeDados.lerInteiro();
				Verificar.verificarMinuto(minuto);
				break;
			} catch (ForaDoIntervaloException e) {
				System.out.println(e.getMessage());
			}
		}
		return LocalTime.of(hora, minuto);
	}
	
	private static String cadastrarTipo() {
		int sel;
		System.out.println("Dentre os 3 tipos a seguir: ");
		while (true) {
			try {
				System.out.print("""
						1. Sala de Aula
						2. Laboratório
						3. Sala de Estudos
						Escolha um número para o tipo da sala:\s""");
				sel = EntradaDeDados.lerInteiro();
				Verificar.verificarTipo(sel);
				break;
			} catch (ForaDoIntervaloException e) {
				System.out.println(e.getMessage());
			}
			
		}
		return switch (sel) {
			case 1 -> "Sala de Aula";
			case 2 -> "Laboratório";
			case 3 -> "Sala de Estudos";
			default -> "";
		};
	}
}

