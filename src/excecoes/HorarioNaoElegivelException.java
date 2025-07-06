package excecoes;

public class HorarioNaoElegivelException extends RuntimeException {
	public HorarioNaoElegivelException() {
		super("O horário selecionado não é elegível para agendamentos");
	}
}
