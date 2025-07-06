package excecoes;

public class PeriodoMinimoException extends RuntimeException {
	public PeriodoMinimoException(int periodoMinimo) {
		super("O período mínimo de agendamento é de " + periodoMinimo + " minutos");
	}
}
