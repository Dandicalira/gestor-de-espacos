package excecoes;

public class MatriculaDuplicadaException extends RuntimeException{

	private static final long serialVersionUID = 6293666621648371621L;

	public MatriculaDuplicadaException() {
		super("A matrícula já existe no sistema!");
	}
}
