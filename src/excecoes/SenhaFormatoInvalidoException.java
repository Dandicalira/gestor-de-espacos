package excecoes;

public class SenhaFormatoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 6158297709924520357L;

	public SenhaFormatoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
