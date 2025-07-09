package aplicacao;

public class Menu {
	public static void inicio() {
		Formulario f = new Formulario("Formulário");

		f.adicionarInput("Nome");
		f.adicionarTexto("Text1aaaaaaa");
		f.adicionarInput("Endereço");
		f.adicionarDropdown("Curso", new String[]{"Engenharia", "Medicina", "Roblox"});
		f.adicionarBotao("Ir", () -> {
			System.out.println("Nome: " + f.resposta("Nome"));
			f.atualizarErro("Mensagem erro");
		});
		f.adicionarBotao("Fechar", () -> System.exit(0));

		f.mostrar();
	}

	private static void dialogo2(Formulario anterior) {
		Formulario f = new Formulario(anterior.opcao("Curso"));

		f.adicionarInput("Nome2");
		f.adicionarInput("Endereço2");
		f.adicionarDropdown("Sei La", new String[]{"A", "B", "C"});
		f.adicionarBotao("Enviar", () -> {
			System.out.println("Nome2: " + f.resposta("Nome2"));
			System.out.println("Endereço2: " + f.resposta("Endereço2"));
		});
		f.adicionarBotao("Voltar", () -> {
			f.ocultar();
			inicio();
		} );

		f.mostrar();
	}
}
