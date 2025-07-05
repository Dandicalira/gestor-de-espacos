package excecoes;

public class TipoInteiroEsperadoException extends RuntimeException{

	private static final long serialVersionUID = -6004473036743852952L;

	public TipoInteiroEsperadoException() {
		super("Insira apenas valores inteiros!");
	}
}
