package excecoes;

public class UsuarioNaoExisteException extends RuntimeException {
	public UsuarioNaoExisteException() {
		super("O usuário informado não existe");
	}
}
