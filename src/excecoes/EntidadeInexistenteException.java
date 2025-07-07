package excecoes;

public class EntidadeInexistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeInexistenteException(String mensagem) {
		super(mensagem);
	}
}
