package servicos.autenticacao;
import entidades.*;
import excecoes.LoginInvalidoException;
import servicos.cadastro.Registro;

public class AutenticacaoService {

	
	//Método Construtor Privado
	private AutenticacaoService() {
		
	}
	
	
	public static Usuario autenticar(String email, String senha) {
		
		Usuario usuario = autenticarEmail(email);
		autenticarSenha(usuario, senha);
		return usuario;
	
	}
	
	//Métodos Privados Internos
	private static Usuario autenticarEmail(String email) {
		
		for (Aluno aluno : Registro.getAlunos()) {
			if (email.equalsIgnoreCase(aluno.getEmail())) {
				return aluno;
			}
		}
	
		for (Servidor servidor : Registro.getServidores()) {
			if (email.equalsIgnoreCase(servidor.getEmail())) {
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
