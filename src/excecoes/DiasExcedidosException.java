package excecoes;

public class DiasExcedidosException extends RuntimeException {
	public DiasExcedidosException(int limiteDias) {
		super("Limite de dias excedido. (máximo: " + limiteDias + ")");
	}
}
