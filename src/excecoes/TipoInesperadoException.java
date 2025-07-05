package excecoes;

public class TipoInesperadoException extends RuntimeException{

	private static final long serialVersionUID = -5666094240987882593L;

	public TipoInesperadoException(String mensagem) {
		super(mensagem);
	}
}
