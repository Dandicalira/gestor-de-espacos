package excecoes;

public class DataIlegalException extends RuntimeException {
	public DataIlegalException() {
		super("Você já possui um agendamento em outra data.");
	}
}
