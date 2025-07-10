package aplicacao;

import entidades.Aluno;
import servicos.cadastro.Registro;
import servicos.persistencia.PersistenciaService;

import static aplicacao.Teste.gerarAluno;

public class Main {
	public static void main(String[] args) {
		//PersistenciaService.carregarDados();

		Menu m = new Menu(Teste.criarTeste());
		m.menuInicial();


	}
}
