package excecoes;

public class ComponenteDuplicadoException extends RuntimeException {
	public ComponenteDuplicadoException(String texto) {
		super("Componente \"" + texto + "\" duplicado");
	}
}
