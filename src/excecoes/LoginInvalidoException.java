package excecoes;

public class LoginInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 3110958804735021082L;

	public LoginInvalidoException() {
		super("E-mail ou senha inválidos! Por favor, tente novamente.");
	}
}
