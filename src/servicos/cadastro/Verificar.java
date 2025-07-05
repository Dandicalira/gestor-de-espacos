package servicos.cadastro;
import excecoes.*;
import entidades.*;

public class Verificar {

	//Método Construtor Privado
	private Verificar() {
		
	}
	
	//Métodos Estáticos de Verificação
	//Verificador de Nomes
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
	//Verificador de E-mails de Alunos
	public static void verificarEmailAluno(String email, String matricula) {
		if (email == null || email.isEmpty()) {
			throw new CampoVazioException();
		}
		if (!(email.equals((matricula + "@aluno.unb.br")) || email.equals(matricula + "@estudante.unb.br"))) {
			throw new EmailAlunoFormatoInvalidoException();
		}
		for (Aluno aluno : Registro.getAlunos()) {
			if (email.equals(aluno.getEmail())) {
				throw new EmailDuplicadoException();
			}
		}
	}
	//Verificador de E-mails de Servidores
	public static void verificarEmailServidor(String email) {
		if (email == null || email.isEmpty()) {
			throw new CampoVazioException();
		}
		if (email.length() < 8) {
			throw new EmailServidorFormatoInvalidoException();
		}
		if (!email.endsWith("@unb.br")) {
			throw new EmailServidorFormatoInvalidoException();
		}
		for (Servidor servidor : Registro.getServidores()) {
			if (email.equals(servidor.getEmail())) {
				throw new EmailDuplicadoException();
			}
		}
	}
	//Verificador de Telefones
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
				throw new TelefoneDuplicadoException();
			}
		}
		for (Servidor servidor : Registro.getServidores()) {
			if (telefone.equals(servidor.getTelefone())) {
				throw new TelefoneDuplicadoException();
			}
		}
	}
	//Verificaro de Cursos
	public static void verificarCurso(String curso) {
		if (curso == null || curso.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemDigito(curso) || contemCaractereEspecial(curso)) {
			throw new TipoInesperadoException("Por favor, não insira caracteres inválidos no nome do curso!");
		}
	}
	//Verificador de Semestres
	public static void verificarSemestre(int semestre) {
		if (semestre < 1 || semestre > 15) {
			throw new ForaDoIntervaloException("O semestre deve ser um número entre 1 e 15!");
		}
	}
	//Verificador de Matrículas
	public static void verificarMatricula(String matricula) {
		if (matricula == null || matricula.isEmpty()) {
			throw new CampoVazioException();
		}
		if (contemNaoDigito(matricula)) {
			throw new TipoInesperadoException("Só são permitidos números em matrículas!");
		}
		if (matricula.length() != 9) {
			throw new MatriculaFormatoInvalidoException();
		}
		for (Aluno aluno : Registro.getAlunos()) {
			if (matricula.equals(aluno.getMatricula())) {
				throw new MatriculaDuplicadaException();
			}
		}
	}
	//Verificador de Matrículas Institucionais
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
				throw new MatriculaDuplicadaException();
			}
		}
	}
	//Verificador de Senhas
	public static void verificarSenha(String senha) {
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
	}
	//Verificador de Cargos
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
		//Verificador de Departamentos
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
		//Verificador de Cargos Acadêmicos
		public static void verificarCargoAcademico(String cargoAcademico) {
			if (cargoAcademico == null || cargoAcademico.isEmpty()) {
				throw new CampoVazioException();
			}
			String auxiliar = cargoAcademico.replace(" ", "").toLowerCase();
			if (auxiliar != "professorauxiliar" && auxiliar != "professorassistente" &&
			    auxiliar != "professoradjunto" && auxiliar != "professorassociado" && auxiliar != "professortitular") {
				throw new ForaDoIntervaloException("Insira um cargo acadêmico válido: Professor Auxiliar, Professor Assistente, "
						                         + "Professor Adjunto, Professor Associado ou Professor Titular");
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
