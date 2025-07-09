package aplicacao;

public enum MenuStatus {

	VOLTAR(0),
	CONTINUAR(1);
	
	private int codigo;
	MenuStatus(int codigo) {
		this.codigo = codigo;
	}
}
