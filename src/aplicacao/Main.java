package aplicacao;

import servicos.persistencia.PersistenciaService;

public class Main {
	public static void main(String[] args) {
		
		PersistenciaService.carregarDados();

		Menu m = new Menu(Teste.criarTeste());
		m.menuInicial();


	}
}
