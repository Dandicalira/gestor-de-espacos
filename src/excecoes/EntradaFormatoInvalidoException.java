package excecoes;

public class EntradaFormatoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 6336713026282013026L;

	public EntradaFormatoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
