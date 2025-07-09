package aplicacao;

public class Menu {
	public static void inicio() {
		Formulario f = new Formulario("Formulário");

		f.adicionarTexto("Testo bem legal");
		f.adicionarTexto("Daniel é legal!!!");
		f.adicionarInput("Nome");
		f.adicionarInput("Endereço");
		f.adicionarDropdown("Curso", new String[]{"Engenharia", "Medicina", "Roblox"});
		f.adicionarBotao("Ir", () -> {
			System.out.println("Nome: " + f.resposta("Nome"));
			f.ocultar();
			dialogo2(f);
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
