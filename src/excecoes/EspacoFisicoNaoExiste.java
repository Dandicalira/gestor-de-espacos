package excecoes;

public class EspacoFisicoNaoExiste extends RuntimeException {
	public EspacoFisicoNaoExiste() {
		super("O espaço físico informado não existe");
	}
}
