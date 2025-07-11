package excecoes;

public class PeriodoInvalidoException extends RuntimeException {
	public PeriodoInvalidoException() {
		super("A data final deve ser posterior à data inicial");
	}

	public PeriodoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
