package servicos.cadastro;
import entidades.*;

import java.time.LocalTime;

public class CadastroService {

	//Método Construtor Privado
	private CadastroService() {
		
	}
	
	//Métodos de Cadastro
	public static void cadastrarAluno(String nome, String senha, String matricula, String email, String telefone, String curso, int semestre) {
		//Leitura dos Atributos
		nome = cadastrarNome(nome);
		senha = cadastrarSenha(senha);
		matricula = cadastrarMatricula(matricula);
		email = cadastrarEmailAluno(email);
		telefone = cadastrarTelefone(telefone);
		curso = cadastrarCurso(curso);
		semestre = cadastrarSemestre(semestre);
		
		//Criação e Armazenamento do Objeto
		Aluno aluno = new Aluno(nome, email, senha, telefone, curso, matricula, semestre);
		Registro.registrarAluno(aluno);
		
	}
	
	public static void cadastrarProfessor(String nome, String senha, String matriculaInstitucional, String email, String telefone, String curso, Professor.CargoAcademico cargoAcademico) {
		//Leitura dos Atributos
		nome = cadastrarNome(nome);
		senha = cadastrarSenha(senha);
		matriculaInstitucional = cadastrarMatriculaInstitucional(matriculaInstitucional);
		email = cadastrarEmailServidor(email);
		telefone = cadastrarTelefone(telefone);
		curso = cadastrarCurso(curso);
		cargoAcademico = cadastrarCargoAcademico(cargoAcademico);
	
	//Criação e Armazenamento do Objeto
	Professor professor = new Professor(nome, email, senha, telefone, matriculaInstitucional, curso, cargoAcademico);
	Registro.registrarServidor(professor);
	}
	
		public static void cadastrarAdministrativo(String nome, String senha, String matriculaInstitucional, String email, String telefone, String departamento, String cargo) {
			//Leitura dos Atributos
			nome = cadastrarNome(nome);
			senha = cadastrarSenha(senha);
			matriculaInstitucional = cadastrarMatriculaInstitucional(matriculaInstitucional);
			email = cadastrarEmailServidor(email);
			telefone = cadastrarTelefone(telefone);
			departamento = cadastrarDepartamento(departamento);
			cargo = cadastrarCargo(cargo);
		
		//Criação e Armazenamento do Objeto
		Administrativo administrativo = new Administrativo(nome, email, senha, telefone, matriculaInstitucional, cargo, departamento);
		Registro.registrarServidor(administrativo);
		}
	
		public static void cadastrarEspacoFisico(int capacidade, LocalTime horarioInicialDisponivel, LocalTime horarioFinalDisponivel, String localizacao, EspacoFisico.TipoDeEspaco tipo) {
			//Leitura dos Atributos
			capacidade = cadastrarCapacidade(capacidade);
			horarioInicialDisponivel = cadastrarHorarioInicialDisponivel(horarioInicialDisponivel.getHour(), horarioInicialDisponivel.getMinute());
			horarioFinalDisponivel = cadastrarHorarioFinalDisponivel(horarioFinalDisponivel.getHour(), horarioFinalDisponivel.getMinute());
			localizacao = cadastrarLocalizacao(localizacao);
			Equipamento[] equipamentos = new Equipamento[0];
			/*String resp = "";
			/*
			 * do { equipamentos =
			 * EspacoFisico.adicionarEquipamento(cadastrarEquipamento(equipamentos),
			 * equipamentos);
			 * System.out.print("Deseja cadastrar mais um tipo de equipamento? (S / N): ");
			 * resp = EntradaDeDados.lerString().toLowerCase(); } while (!resp.equals("n")
			 * && !resp.equals("não") && !resp.equals("nao"));
			 */
			//Criação e Armazenamento do Objeto
			EspacoFisico espaco = new EspacoFisico(capacidade, horarioInicialDisponivel, horarioFinalDisponivel, 
					                               localizacao, tipo, equipamentos);
			switch (espaco.getTipo()) {
			case SALADEAULA:
				Registro.registrarSalaDeAula(espaco);
				break;
			case LABORATORIO:
				Registro.registrarLaboratorio(espaco);
				break;
			case SALADEESTUDOS:
			}
		}
	
	//Métodos Estáticos Privados
		private static String cadastrarNome(String nome) {
			nome = Formatar.formatarNome(nome);
			Verificar.verificarNome(nome);
			return nome;
		}

	private static String cadastrarEmailAluno(String email) {
		
				Verificar.verificarEmailAluno(email);
				return email;
	}
	
	private static String cadastrarEmailServidor(String email) {
		
				Verificar.verificarEmailServidor(email);
				return email;
	}
	
	private static String cadastrarTelefone(String telefone) {
		
				Verificar.verificarTelefone(telefone);
				return telefone;
	}
	
	private static String cadastrarCurso(String curso) {
		
				curso = Formatar.capitalizar(curso);
				Verificar.verificarCurso(curso);
				return curso;
	
	}
	
	private static String cadastrarMatricula(String matricula) {
		
				Verificar.verificarMatricula(matricula);
				return matricula;
			
	}
	
	private static int cadastrarSemestre(int semestre) {
	
				Verificar.verificarSemestre(semestre);
				return semestre;
	}
	
	private static String cadastrarMatriculaInstitucional(String matriculaInstitucional) {
		
				Verificar.verificarMatriculaInstitucional(matriculaInstitucional);
				return matriculaInstitucional;
	}
	
	private static String cadastrarCargo(String cargo) {
	
				cargo = Formatar.capitalizar(cargo);
				Verificar.verificarCargo(cargo);
				return cargo;
	}
	
	private static String cadastrarDepartamento(String departamento) {
				departamento = Formatar.capitalizar(departamento);
				Verificar.verificarDepartamento(departamento);
				return departamento;
	}
	
	private static Professor.CargoAcademico cadastrarCargoAcademico(Professor.CargoAcademico cargoAcademico) {
				return cargoAcademico;
	}
	
	private static String cadastrarSenha(String senha) {
		
				Verificar.verificarSenha(senha);
				return senha;
	}
	
	private static int cadastrarCapacidade(int capacidade) {

				Verificar.verificarCapacidade(capacidade);
				return capacidade;
	}
	
	private static String cadastrarLocalizacao(String localizacao) {
				Verificar.verificarLocalizacao(localizacao);
				return localizacao;
	}
	
	/*
	 * private static Equipamento cadastrarEquipamento(Equipamento[] equipamentos) {
	 * String nome; int quantidade; while (true) { try {
	 * System.out.print("Digite o nome do equipamento: "); nome =
	 * EntradaDeDados.lerString(); Verificar.verificarNomeEquipamento(nome,
	 * equipamentos); break; } catch (CampoVazioException | TipoInesperadoException
	 * | EquipamentoDuplicadoException e) { System.out.println(e.getMessage()); } }
	 * while (true) { try {
	 * System.out.print("Digite a quantidade do equipamento: "); quantidade =
	 * EntradaDeDados.lerInteiro();
	 * Verificar.verificarQuantidadeEquipamento(quantidade); break; } catch
	 * (ForaDoIntervaloException e) { System.out.println(e.getMessage()); } } return
	 * new Equipamento(nome, quantidade); }
	 */
	private static LocalTime cadastrarHorarioInicialDisponivel(int hora, int minuto) {
	
		Verificar.verificarHora(hora);
		Verificar.verificarMinuto(minuto);
	
		return LocalTime.of(hora, minuto);
	}
	
	private static LocalTime cadastrarHorarioFinalDisponivel(int hora, int minuto) {
		
		Verificar.verificarHora(hora);
		Verificar.verificarMinuto(minuto);
				
		return LocalTime.of(hora, minuto);
	}
}
	
	
