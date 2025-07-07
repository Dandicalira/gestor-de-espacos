package excecoes;

public class ForaDoIntervaloException extends RuntimeException{

	private static final long serialVersionUID = -2900483091417405031L;

	public ForaDoIntervaloException(int min, int max) {
		super("O intervalo deve estar entre " + min + " e " + max);
	}
	public ForaDoIntervaloException(String mensagem) {
		super(mensagem);
	}
}
