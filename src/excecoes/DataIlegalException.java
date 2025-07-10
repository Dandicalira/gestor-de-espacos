package excecoes;

public class DataIlegalException extends RuntimeException {
	public DataIlegalException() {
		super("Só é possível criar agendamentos na mesma data.");
	}
}
