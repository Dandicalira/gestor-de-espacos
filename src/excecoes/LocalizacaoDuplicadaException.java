package excecoes;

public class LocalizacaoDuplicadaException extends RuntimeException{

	private static final long serialVersionUID = 5471383300274874728L;

	public LocalizacaoDuplicadaException() {
		super("A localização já existe no sistema!");
	}
}
