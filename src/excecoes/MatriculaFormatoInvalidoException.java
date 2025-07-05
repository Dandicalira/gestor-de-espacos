package excecoes;

public class MatriculaFormatoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = -8874585938808249384L;

	public MatriculaFormatoInvalidoException() {
		super("A matrícula deve ter exatamente 9 dígitos!");
	}
}
