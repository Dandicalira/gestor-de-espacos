package excecoes;

public class DiasExcedidosException extends RuntimeException {
	public DiasExcedidosException(int limiteDias, long diasSelecionados) {
		super("Limite de dias excedido: " + diasSelecionados + " (máximo: " + limiteDias + ")");
	}
}