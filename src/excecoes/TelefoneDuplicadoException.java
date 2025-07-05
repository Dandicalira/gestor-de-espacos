package excecoes;

public class TelefoneDuplicadoException extends RuntimeException{

	private static final long serialVersionUID = 6926490071676965577L;

	public TelefoneDuplicadoException() {
		super("Esse telefone já existe no sistema!");
	}
}
