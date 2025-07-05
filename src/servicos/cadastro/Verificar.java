package servicos.cadastro;
import excecoes.*;
import entidades.*;

public class Verificar {

	//Método Construtor Privado
	private Verificar() {
		
	}
	
	//Métodos Estáticos de Verificação
	//Verificador de Nomes
	public static boolean verificarNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemDigito(nome)) {
			throw new TipoInesperadoException("Não insira números em nomes!");
		}
		if (contemCaractereEspecial(nome)) {
			throw new TipoInesperadoException("Não insira caracteres especiais em nomes!");
		}
		return true;
	}
	//Verificador de E-mails de Alunos
	public static boolean verificarEmailAluno(String email, String matricula) {
		if (email == null || email.isEmpty()) {
			throw new CampoVazioException();
		}
		if (!email.equals((matricula + "@aluno.unb.br"))) {
			throw new EmailAlunoFormatoInvalidoException();
		}
		for (Aluno aluno : Cadastrar.getAlunos()) {
			if (email.equals(aluno.getEmail())) {
				throw new EmailDuplicadoException();
			}
		}
		return true;
	}
	//Verificador de E-mails de Servidores
	public static boolean verificarEmailServidor(String email) {
		if (email == null || email.isEmpty()) {
			throw new CampoVazioException();
		}
		if (email.length() < 8) {
			throw new EmailServidorFormatoInvalidoException();
		}
		if (!email.endsWith("@unb.br")) {
			throw new EmailServidorFormatoInvalidoException();
		}
		for (Servidor servidor : Cadastrar.getServidores()) {
			if (email.equals(servidor.getEmail())) {
				throw new EmailDuplicadoException();
			}
		}
		return true;
	}
	//Verificador de Telefones
	public static boolean verificarTelefone(String telefone) {
		if (telefone == null || telefone.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemNaoDigito(telefone)) {
			throw new TipoInesperadoException("Só são permitidos números em telefones!");
		}
		if (telefone.length() != 11) {
			throw new ForaDoIntervaloException("Telefones devem ter 11 dígitos: YYXXXXXXXXX");
		}
		for (Aluno aluno : Cadastrar.getAlunos()) {
			if (telefone.equals(aluno.getTelefone())) {
				throw new TelefoneDuplicadoException();
			}
		}
		for (Servidor servidor : Cadastrar.getServidores()) {
			if (telefone.equals(servidor.getTelefone())) {
				throw new TelefoneDuplicadoException();
			}
		}
		return true;
	}
	//Verificador de Semestres
	public static boolean verificarSemestre(int semestre) {
		if (semestre < 1 || semestre > 15) {
			throw new ForaDoIntervaloException("O semestre deve ser um número entre 1 e 15!");
		}
		return true;
	}
	//Verificador de Matrículas
	public static boolean verificarMatricula(String matricula) {
		if (matricula == null || matricula.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemNaoDigito(matricula)) {
			throw new TipoInesperadoException("Só são permitidos números em matrículas!");
		}
		if (matricula.length() != 9) {
			throw new MatriculaFormatoInvalidoException();
		}
		for (Aluno aluno : Cadastrar.getAlunos()) {
			if (matricula.equals(aluno.getMatricula())) {
				throw new MatriculaDuplicadaException();
			}
		}
		return true;
	}
	//Verificador de Matrículas Institucionais
	public static boolean verificarMatriculaInstitucional(String matriculaInstitucional) {
		if (matriculaInstitucional == null || matriculaInstitucional.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemNaoDigito(matriculaInstitucional)) {
			throw new TipoInesperadoException("Só são permitidos números em matrículas institucionais!");
		}
		if (matriculaInstitucional.length() < 7 || matriculaInstitucional.length() > 9) {
			throw new ForaDoIntervaloException("A matrícula institucional não está dentro dos limites permitidos!");
		}
		for (Servidor servidor : Cadastrar.getServidores()) {
			if (matriculaInstitucional.equals(servidor.getMatriculaInstitucional())) {
				throw new MatriculaDuplicadaException();
			}
		}
		return true;
	}
	//Verificador de Senhas
	public static boolean verificarSenha(String senha) {
		if (senha == null || senha.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemEspacos(senha)) {
			throw new SenhaFormatoInvalidoException("Senhas não podem conter espaços em branco!");
		}
		if (senha.length() < 8 || senha.length() > 20) {
			throw new SenhaFormatoInvalidoException("Senhas devem possuir, no mínimo, 8 caracteres e, no máximo, 20 caracteres");
		}
		if (!contemCaractereEspecial(senha)) {
			throw new SenhaFormatoInvalidoException("Senhas devem possuir, no mínimo, 1 caractere especial (@, #, ...)");
		}
		if (!contemDigito(senha)) {
			throw new SenhaFormatoInvalidoException("Senhas devem possuir, no mínimo, 1 dígito numérico");
		}
		if (!contemMaiuscula(senha) || !contemMinuscula(senha)) {
			throw new SenhaFormatoInvalidoException("Senhas devem possuir, no mínimo, 1 letra maiúscula e 1 letra minúscula");
		}
		return true;
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
			if (!Character.isLetter(caractere) && caractere != ' ' && caractere != '\'' && caractere != '-') {
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
