package servicos.cadastro;

public class Formatar {

	//Método Construtor Privado
	private Formatar() {
		
	}
	
	//Métodos de Formatação
	public static String formatarNome(String nome) {
		if (nome == null) {
			return null;
		}
		return capitalizar(nome.trim().toLowerCase());
	}
	
	//Métodos Estáticos Internos
	public static String capitalizar(String nome) {
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
