package excecoes;

public class EmailDuplicadoException extends RuntimeException{

	private static final long serialVersionUID = -931071668056817569L;

	public EmailDuplicadoException() {
		super("O email já existe no sistema!");
	}
}
