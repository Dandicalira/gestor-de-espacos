package servicos.cadastro;

public class Formatar {

	//Método Construtor Privado
	private Formatar() {
		
	}
	
	//Métodos de Formatação
	public static String FormatarNome(String nome) {
		if (nome == null) {
			return null;
		}
		return Capitalizar(nome.trim().toLowerCase());
	}
	public static String FormatarEmailAluno(String email) {
		if (email == null) {
			return null;
		}
		return email.trim().toLowerCase();
	}
	public static String FormatarEmailServidor(String email) {
		if (email == null) {
			return null;
		}
		return email.trim().toLowerCase();
	}
	public static String FormatarTelefone(String telefone) {
		if (telefone == null) {
			return null;
		}
		return telefone.trim();
	}
	public static String FormatarMatricula(String matricula) {
		if (matricula == null) {
			return null;
		}
		return matricula.trim();
	}
	public static String FormatarMatriculaInstitucional(String matriculaInstitucional) {
		if (matriculaInstitucional == null) {
			return null;
		}
		return matriculaInstitucional.trim();
	}
	public static String FormatarSenha(String senha) {
		if (senha == null) {
			return null;
		}
		return senha.trim();
	}
	//Métodos Estáticos Privados Internos
	public static String Capitalizar(String nome) {
		String novoNome = "";
		novoNome += Character.toUpperCase(nome.charAt(0));
		for (int i = 1; i < nome.length(); i++) {
			if (nome.charAt(i - 1) == ' ') {
				novoNome += Character.toUpperCase(nome.charAt(i));
			}
			else {
				novoNome += nome.charAt(i);
			}
		}
		return novoNome;
	}
}
