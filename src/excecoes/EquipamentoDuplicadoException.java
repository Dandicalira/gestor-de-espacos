package excecoes;

public class EquipamentoDuplicadoException extends RuntimeException{

	public EquipamentoDuplicadoException() {
		super("O equipamento já está cadastrado na sala!");
	}
}
