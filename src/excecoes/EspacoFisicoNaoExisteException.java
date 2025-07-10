package excecoes;

public class EspacoFisicoNaoExisteException extends RuntimeException {
	public EspacoFisicoNaoExisteException() {
		super("O espaço físico informado não existe");
	}
}
