package excecoes;

public class DataFuturaException extends RuntimeException {
	public DataFuturaException() {
		super("A data selecionada deve estar no futuro");
	}
}
