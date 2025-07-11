package aplicacao;

import servicos.persistencia.PersistenciaService;

import static aplicacao.Teste.criarTeste;

public class Main {
	public static void main(String[] args) {
		
		PersistenciaService.carregarDados();

		//criarTeste();

		Menu m = new Menu();
		m.menuInicial();


	}
}
