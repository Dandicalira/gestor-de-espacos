package servicos.cadastro;
import excecoes.*;
import entidades.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Verificar {

	//Método Construtor Privado
	private Verificar() {
		
	}
	
	//Métodos Estáticos de Verificação
	public static void verificarNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemDigito(nome)) {
			throw new TipoInesperadoException("Não insira números em nomes!");
		}
		if (contemCaractereEspecial(nome)) {
			throw new TipoInesperadoException("Não insira caracteres especiais em nomes!");
		}
	}
	
	public static void verificarEmailAluno(String email) {
		if (email == null || email.isEmpty()) {
			throw new CampoVazioException();
		}
		if (!(email.endsWith("@aluno.unb.br") || email.endsWith("@estudante.unb.br"))) {
			throw new EntradaFormatoInvalidoException("O e-mail deve ser no formato matricula@aluno.unb.br ou matricula@estudante.unb.br!");
		}
		for (Aluno aluno : Registro.getAlunos()) {
			if (email.equals(aluno.getEmail())) {
				throw new EntidadeDuplicadaException("O email já existe no sistema!");
			}
		}
	}
	
	public static void verificarEmailServidor(String email) {
		if (email == null || email.isEmpty()) {
			throw new CampoVazioException();
		}
		if (email.length() < 8) {
			throw new EntradaFormatoInvalidoException("O e-mail deve estar no formato nome.sobrenome@unb.br!");
		}
		if (!email.endsWith("@unb.br")) {
			throw new EntradaFormatoInvalidoException("O e-mail deve estar no formato nome.sobrenome@unb.br!");
		}
		for (Servidor servidor : Registro.getServidores()) {
			if (email.equals(servidor.getEmail())) {
				throw new EntidadeDuplicadaException("O email já existe no sistema!");
			}
		}
	}
	
	public static void verificarTelefone(String telefone) {
		if (telefone == null || telefone.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemNaoDigito(telefone)) {
			throw new TipoInesperadoException("Só são permitidos números em telefones!");
		}
		if (telefone.length() != 11) {
			throw new ForaDoIntervaloException("Telefones devem ter 11 dígitos: YYXXXXXXXXX");
		}
		for (Aluno aluno : Registro.getAlunos()) {
			if (telefone.equals(aluno.getTelefone())) {
				throw new EntidadeDuplicadaException("Esse telefone já existe no sistema!");
			}
		}
		for (Servidor servidor : Registro.getServidores()) {
			if (telefone.equals(servidor.getTelefone())) {
				throw new EntidadeDuplicadaException("Esse telefone já existe no sistema!");
			}
		}
	}
	
	public static void verificarCurso(String curso) {
		if (curso == null || curso.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemDigito(curso) || contemCaractereEspecial(curso)) {
			throw new TipoInesperadoException("Por favor, não insira caracteres inválidos no nome do curso!");
		}
	}
	
	public static void verificarSemestre(int semestre) {
		if (semestre < 1 || semestre > 15) {
			throw new ForaDoIntervaloException("O semestre deve ser um número entre 1 e 15!");
		}
	}
	
	public static void verificarMatricula(String matricula) {
		if (matricula == null || matricula.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemNaoDigito(matricula)) {
			throw new TipoInesperadoException("Só são permitidos números em matrículas!");
		}
		if (matricula.length() != 9) {
			throw new EntradaFormatoInvalidoException("A matrícula deve ter exatamente 9 dígitos!");
		}
		for (Aluno aluno : Registro.getAlunos()) {
			if (matricula.equals(aluno.getMatricula())) {
				throw new EntidadeDuplicadaException("A matrícula já existe no sistema!");
			}
		}
	}
	
	public static void verificarMatriculaInstitucional(String matriculaInstitucional) {
		if (matriculaInstitucional == null || matriculaInstitucional.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemNaoDigito(matriculaInstitucional)) {
			throw new TipoInesperadoException("Só são permitidos números em matrículas institucionais!");
		}
		if (matriculaInstitucional.length() < 7 || matriculaInstitucional.length() > 9) {
			throw new ForaDoIntervaloException("A matrícula institucional não está dentro dos limites permitidos!");
		}
		for (Servidor servidor : Registro.getServidores()) {
			if (matriculaInstitucional.equals(servidor.getMatriculaInstitucional())) {
				throw new EntidadeDuplicadaException("A matrícula institucional já existe no sistema!");
			}
		}
	}

	public static void verificarSenha(String senha) {
		if (senha == null || senha.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemEspacos(senha)) {
			throw new EntradaFormatoInvalidoException("Senhas não podem conter espaços em branco!");
		}
		if (senha.length() < 8 || senha.length() > 20) {
			throw new EntradaFormatoInvalidoException("Senhas devem possuir, no mínimo, 8 caracteres e, no máximo, 20 caracteres");
		}
		if (!contemCaractereEspecial(senha)) {
			throw new EntradaFormatoInvalidoException("Senhas devem possuir, no mínimo, 1 caractere especial (@, #, ...)");
		}
		if (!contemDigito(senha)) {
			throw new EntradaFormatoInvalidoException("Senhas devem possuir, no mínimo, 1 dígito numérico");
		}
		if (!contemMaiuscula(senha) || !contemMinuscula(senha)) {
			throw new EntradaFormatoInvalidoException("Senhas devem possuir, no mínimo, 1 letra maiúscula e 1 letra minúscula");
		}
	}
	
	public static void verificarCargo(String cargo) {
		if (cargo == null || cargo.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemDigito(cargo)) {
			throw new TipoInesperadoException("Não insira números em cargos!");
		}
		if (contemCaractereEspecial(cargo)) {
			throw new TipoInesperadoException("Não insira caracteres especiais em cargos!");
		}
	}
	
	public static void verificarDepartamento(String departamento) {
		if (departamento == null || departamento.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemDigito(departamento)) {
			throw new TipoInesperadoException("Não insira números em departamentos!");
		}
		if (contemCaractereEspecial(departamento)) {
			throw new TipoInesperadoException("Não insira caracteres especiais em departamentos!");
		}
	}
	
	public static void verificarCapacidade(int capacidade) {
		if (capacidade <= 0) {
			throw new ForaDoIntervaloException("Por favor, insira uma capacidade válida!");
		}
	}

	public static void verificarHorarioFuncionamento(LocalTime horarioInicio, LocalTime horarioFim) {
		if (horarioInicio.isAfter(horarioFim)) {
			throw new PeriodoInvalidoException("O horário final deve ser posterior ao horário inicial");
		}
	}
	
	public static void verificarLocalizacao(String localizacao) {
		if (localizacao == null || localizacao.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemCaractereEspecial(localizacao)) {
			throw new TipoInesperadoException("Não insira caracteres especiais na localização!");
		}
		for (EspacoFisico espaco : Registro.getLaboratorios()) {
			if (localizacao.equals(espaco.getLocalizacao())) {
				throw new EntidadeDuplicadaException("A localização já existe no sistema!");
			}
		}
		for (EspacoFisico espaco : Registro.getSalasDeAula()) {
			if (localizacao.equals(espaco.getLocalizacao())) {
				throw new EntidadeDuplicadaException("A localização já existe no sistema!");
			}
		}
		for (EspacoFisico espaco : Registro.getSalasDeEstudos()) {
			if (localizacao.equals(espaco.getLocalizacao())) {
				throw new EntidadeDuplicadaException("A localização já existe no sistema!");
			}
		}
	}
	
	public static void verificarNomeEquipamento(String nome, Equipamento[] equipamentos) {
		if (nome == null || nome.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemCaractereEspecial(nome)) {
			throw new TipoInesperadoException("Não insira caracteres especiais em nomes de equipamentos!");
		}
		for (Equipamento equipamento : equipamentos) {
			if (nome.equals(equipamento.getNome())) {
				throw new EntidadeDuplicadaException("O equipamento já está cadastrado na sala!");
			}
		}
	}
	
	public static void verificarQuantidadeEquipamento(int quantidade) {
		if (quantidade <= 0) {
			throw new ForaDoIntervaloException("Por favor, insira uma quantidade válida!");
		}
	}
	
	public static void verificarHora(int hora) {
		if (hora < 0 || hora > 23) {
			throw new ForaDoIntervaloException("Insira apenas horas entre 0h e 23h!");
		}
	}
	
	public static void verificarMinuto(int minuto) {
		if (minuto < 0 || minuto > 59) {
			throw new ForaDoIntervaloException("Insira apenas minutos entre 00min e 59min!");
		}
	}
	
	public static void verificarTipo(int sel) {
		if (sel < 1 || sel > 3) {
			throw new ForaDoIntervaloException("Por favor, escolha apenas entre um dos 3 tipos de Espaço Físico!");
		}
	}
	
	//Métodos Estáticos Privados Internos
	private static boolean contemDigito(String palavra) {
		for (char caractere : palavra.toCharArray()) {
			if (Character.isDigit(caractere)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean contemCaractereEspecial(String palavra) {
		for (char caractere : palavra.toCharArray() ) {
			if (!Character.isLetter(caractere) && caractere != ' ' && caractere != '\'' && caractere != '-' && !Character.isDigit(caractere)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean contemNaoDigito(String dadoNumerico) {
		for (char caractere : dadoNumerico.toCharArray()) {
			if (!Character.isDigit(caractere)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean contemEspacos(String palavra) {
		for (char caractere : palavra.toCharArray()) {
			if (Character.isWhitespace(caractere)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean contemMaiuscula(String palavra) {
		for (char caractere : palavra.toCharArray()) {
			if (Character.isUpperCase(caractere)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean contemMinuscula(String palavra) {
		for (char caractere : palavra.toCharArray()) {
			if (Character.isLowerCase(caractere)) {
				return true;
			}
		}
		return false;
	}
}
