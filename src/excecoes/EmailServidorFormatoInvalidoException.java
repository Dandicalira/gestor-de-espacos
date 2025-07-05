package excecoes;

public class EmailServidorFormatoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = -3471174939303096148L;

	public EmailServidorFormatoInvalidoException() {
		super("O e-mail deve estar no formato nome.sobrenome@unb.br!");
	}
}
