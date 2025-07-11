package excecoes;

public class LoginInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 3110958804735021082L;

	public LoginInvalidoException() {
		super("Falha na autenticação. Credenciais inválidas.");
	}
}
