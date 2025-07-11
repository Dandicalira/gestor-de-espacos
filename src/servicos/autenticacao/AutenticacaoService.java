package servicos.autenticacao;
import entidades.*;
import excecoes.LoginInvalidoException;
import servicos.cadastro.Registro;

public class AutenticacaoService {

	
	//Método Construtor Privado
	private AutenticacaoService() {
		
	}
	
	
	public static Usuario autenticarLogin(String identificacao, String senha) {
		
		try {
			Usuario usuario = autenticarIdentificacao(identificacao);
			autenticarSenha(usuario, senha);
			return usuario;
		} catch (Exception e) {
			throw new LoginInvalidoException();
		}

	}
	
	public static void autenticarSuperUsuario(String senha) {
		if (!senha.equals("314")) {
			throw new LoginInvalidoException();
		}
	}
	
	//Métodos Privados Internos
	private static Usuario autenticarIdentificacao(String identificacao) {
		
		for (Aluno aluno : Registro.getAlunos()) {
			if (identificacao.equalsIgnoreCase(aluno.getIdentificacao())) {
				return aluno;
			}
		}
	
		for (Servidor servidor : Registro.getServidores()) {
			if (identificacao.equalsIgnoreCase(servidor.getIdentificacao())) {
				return servidor;
			}
		}
		throw new LoginInvalidoException();
	}
	private static void autenticarSenha(Usuario usuario, String senha) {
		
		if (usuario.getSenha().equals(senha) ) {
			return;
		}
		throw new LoginInvalidoException();
	}
}
