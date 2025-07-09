package excecoes;

public class ComponenteNaoExisteException extends RuntimeException {
	public ComponenteNaoExisteException(String texto) {
		super("O componente \"" + texto + "\" não existe");
	}
}
