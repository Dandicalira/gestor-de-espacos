package aplicacao;

public class Menu {
	public static void menuInicial() {
		Formulario f = new Formulario();

		f.adicionarTexto("MENU INICIAL");
		f.adicionarTexto("Escolha uma opção:");
		f.adicionarBotao("Painel de administração", () -> {
			f.ocultar();
			menuLoginAdmin(f);
		});
		f.adicionarBotao("Entrar como usuário", () -> {
			//todo
		});

		f.mostrar();
	}

	private static void menuLoginAdmin(Formulario anterior) {
		Formulario f = new Formulario("Painel de administração");

		f.adicionarTexto("Entre como Superusuário");
		f.adicionarInput("Digite a senha:");
		f.adicionarBotao("Entrar", () -> {
			//todo
			f.ocultar();
			menuAdmin(f);
		});
		f.adicionarBotao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});

		f.mostrar();
	}

	private static void menuAdmin(Formulario anterior) {
		Formulario f = new Formulario("Painel de administração");

		f.adicionarDropdown("Escolha uma opção", new String[]{"Aluno", "Professor", "Técnico Administrativo", "Espaço Físico"});
		f.adicionarBotao("Cadastrar", () -> {
			f.ocultar();
			selecionarCadastro(f);
		});
		f.adicionarBotao("Descadastrar", () -> {
			f.ocultar();
			//todo
		});
		f.adicionarBotao("Listar", () -> {
			f.ocultar();
			//todo
		});
		f.adicionarBotao("Voltar", () -> {
			f.ocultar();
			menuInicial();
		});

		f.mostrar();
	}

	private static void selecionarCadastro(Formulario anterior) {
		switch (anterior.opcao("Escolha uma opção")) {
			case "Aluno" -> menuCadastrarAluno();
			case "Professor" -> menuCadastrarProfessor();
			case "Técnico Administrativo" -> menuCadastrarAdministrativo();
			default -> menuCadastrarEspacoFisico();
		}
	}

	private static void menuCadastrarEspacoFisico() {
		//todo
	}

	private static void menuCadastrarAdministrativo() {
		//todo
	}

	private static void menuCadastrarProfessor() {
		//todo
	}

	private static void menuCadastrarAluno() {
		//todo
	}
}
