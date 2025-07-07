package excecoes;

import java.time.LocalDate;

public class DataIlegalException extends RuntimeException {
	public DataIlegalException(LocalDate data) {
		super("Você já possui um agendamento em " + data + ". Só é possível escolher períodos na mesma data.");
	}
}
