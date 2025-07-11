package excecoes;

public class EntidadeDuplicadaException extends RuntimeException{

	private static final long serialVersionUID = 7035550094785631300L;

	public EntidadeDuplicadaException(String mensagem) {
		super(mensagem);
	}
}
