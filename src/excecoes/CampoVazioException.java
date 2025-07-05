package excecoes;

public class CampoVazioException extends RuntimeException{

	private static final long serialVersionUID = -8645029721974591152L;

	public CampoVazioException() {
		super("O campo não pode ser deixado em branco!");
	}
}
