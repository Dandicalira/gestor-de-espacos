package excecoes;

public class HorarioIndisponivelException extends RuntimeException {
	public HorarioIndisponivelException() {
		super("O horário selecionado já está sendo ocupado");
	}
}
