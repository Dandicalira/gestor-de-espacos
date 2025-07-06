package excecoes;

public class EquipamentoDuplicadoException extends RuntimeException{

	private static final long serialVersionUID = -8017153864683135358L;

	public EquipamentoDuplicadoException() {
		super("O equipamento já está cadastrado na sala!");
	}
}
