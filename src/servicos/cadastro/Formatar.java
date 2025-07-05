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
	public static String FormatarEmail(String email) {
		if (email == null) {
			return null;
		}
		return email.trim().toLowerCase();
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
