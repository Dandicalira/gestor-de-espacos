package aplicacao;

import entidades.EspacoFisico;
import entidades.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static aplicacao.Formulario.mostrarMensagem;
import static servicos.agendamento.AgendamentoService.*;
import static servicos.agendamento.Agendar.validarAgendamento;
import static servicos.autenticacao.AutenticacaoService.autenticarSuperUsuario;
import static servicos.cadastro.CadastroService.cadastrarAluno;
import static servicos.cadastro.Registro.*;

public class Menu {
	Usuario usuarioLogado = null;

	public Menu() {}

	public Menu(Usuario usuario) {
		usuarioLogado = usuario;
	}

	public void menuInicial() {
		Formulario f = new Formulario();

		f.adicionarTexto("MENU INICIAL");
		f.adicionarTexto("Escolha uma opção:");
		f.adicionarAcao("Painel de administração", () -> {
			f.ocultar();
			menuLoginAdmin(f);
		});
		f.adicionarAcao("Entrar como usuário", () -> {
			f.ocultar();
			menuLoginUsuario(f);
		});

		f.mostrar();
	}

	private void menuLoginAdmin(Formulario anterior) {
		Formulario f = new Formulario();

		f.adicionarTexto("Entre como Superusuário");
		f.adicionarInput("Digite a senha", true);
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuInicial();
		});
		f.adicionarAcao("Entrar", () -> {
			if (!f.valido()) return;
			try {
				autenticarSuperUsuario(f.resposta("Digite a senha"));
				f.ocultar();
				menuAdmin(f);
			} catch (Exception e) {
				f.atualizarErro(e.getMessage());
			}
		});

		f.mostrar();
	}

	private void menuAdmin(Formulario anterior) {
		Formulario f = new Formulario();

		f.adicionarDropdown("Escolha uma opção", new String[]{"Aluno", "Professor", "Técnico Administrativo", "Espaço Físico"});
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuInicial();
		});
		f.adicionarAcao("Cadastrar", () -> {
			f.ocultar();
			selecionarCadastro(f);
		});
		f.adicionarAcao("Descadastrar", () -> {
			f.ocultar();
			//todo
		});
		f.adicionarAcao("Listar", () -> {
			//todo
		});

		f.mostrar();
	}

	private void selecionarCadastro(Formulario anterior) {
		switch (anterior.opcao("Escolha uma opção")) {
			case "Aluno" -> menuCadastrarAluno();
			case "Professor" -> menuCadastrarProfessor();
			case "Técnico Administrativo" -> menuCadastrarAdministrativo();
			case "Espaço Físico" -> menuCadastrarEspacoFisico();
		}
	}

	private void menuCadastrarEspacoFisico() {
		Formulario f = new Formulario("Cadastro de Espaço Físico");

		f.adicionarInput("Nome do espaço", true);
		f.adicionarInput("Localização(S2, I3, LAB NEI 2, ...)", true);
		f.adicionarInput("Capacidade", true);

		f.adicionarDropdown("Tipo", new String[]{"Sala de aula", "Laboratório", "Auditório", "Outro"});

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuAdmin(f);
		});

		f.adicionarAcao("Cadastrar", () -> {
			if (!f.valido()) return;

			String nome = f.resposta("Nome do espaço");
			String localizacao = f.resposta("Localização");
			String capacidadeStr = f.resposta("Capacidade");
			String tipo = f.opcao("Tipo");

			// TODO: Validar capacidade como número, cadastrar espaço físico

			f.ocultar();
			menuAdmin(f);
		});

		f.mostrar();
	}

	private void menuCadastrarAdministrativo() {
		//noinspection ExtractMethodRecommender
		Formulario f = new Formulario("Cadastro de Técnico Administrativo");
		f.adicionarTexto("""
				A senha deve conter:
				no mínimo, 8 caracteres e, no máximo, 20 caracteres,
				no mínimo, 1 caractere especial (@, #, ...)
				no mínimo, 1 dígito numérico
				no mínimo, 1 letra maiúscula e 1 letra minúscula
				não deve conter espaços em branco
				""");
		f.adicionarInput("Nome", true);
		f.adicionarInput("Identificação", true);
		f.adicionarInput("Senha", true);
		f.adicionarInput("Setor", true);

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuAdmin(f);
		});

		f.adicionarAcao("Cadastrar", () -> {
			if (!f.valido()) return;

			String nome = f.resposta("Nome");
			String id = f.resposta("Identificação");
			String senha = f.resposta("Senha");
			String setor = f.resposta("Setor");

			// TODO: Cadastrar técnico usando Registro.adicionarServidor(...)

			f.ocultar();
			menuAdmin(f);
		});

		f.mostrar();
	}
	private void menuCadastrarProfessor() {
		Formulario f = new Formulario("Cadastro de Professor");
		f.adicionarTexto("""
				A senha deve conter:
				no mínimo, 8 caracteres e, no máximo, 20 caracteres,
				no mínimo, 1 caractere especial (@, #, ...)
				no mínimo, 1 dígito numérico
				no mínimo, 1 letra maiúscula e 1 letra minúscula
				não deve conter espaços em branco
				""");
		f.adicionarInput("Nome", true);
		f.adicionarInput("Identificação", true);
		f.adicionarInput("Senha", true);
		f.adicionarInput("Departamento", true);

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuAdmin(f);
		});

		f.adicionarAcao("Cadastrar", () -> {
			if (!f.valido()) return;

			String nome = f.resposta("Nome");
			String id = f.resposta("Identificação");
			String senha = f.resposta("Senha");
			String departamento = f.resposta("Departamento");

			// TODO: Cadastrar professor usando Registro.adicionarServidor(...)
			f.ocultar();
			menuAdmin(f);
		});

		f.mostrar();
	}

	private void menuCadastrarAluno() {
		//noinspection ExtractMethodRecommender
		Formulario f = new Formulario("Cadastro de Aluno");

		f.adicionarTexto("""
				A senha deve conter:
				no mínimo, 8 caracteres e, no máximo, 20 caracteres,
				no mínimo, 1 caractere especial (@, #, ...)
				no mínimo, 1 dígito numérico
				no mínimo, 1 letra maiúscula e 1 letra minúscula
				não deve conter espaços em branco
				""");
		f.adicionarInput("Nome", true);
		f.adicionarInput("Senha", true);
		f.adicionarInput("Matrícula", true);
		f.adicionarInput("Email", true);
		f.adicionarInput("Telefone", true);
		f.adicionarInput("Semestre", true);
		f.adicionarInput("Curso", true);

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuAdmin(f);
		});

		f.adicionarAcao("Cadastrar", () -> {
			if (!f.valido()) return;

			try {
				String nome = f.resposta("Nome");
				String senha = f.resposta("Senha");
				String matricula = f.resposta("Matrícula");
				String email = f.resposta("Email");
				String telefone = f.resposta("Telefone");
				String curso = f.resposta("Curso");
				int semestre = Integer.parseInt(f.resposta("Semestre"));

				cadastrarAluno(nome, senha, matricula, email, telefone, curso, semestre);

				f.ocultar();
				menuAdmin(f);
			} catch (Exception e) {
				f.atualizarErro(e.getMessage());
			}
		});

		f.mostrar();
	}

	private void menuLoginUsuario(Formulario anterior) {
		Formulario f = new Formulario();

		f.adicionarTexto("Entre como Usuário");
		f.adicionarInput("Matrícula", true);
		f.adicionarInput("Senha", true);
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});
		f.adicionarAcao("Entrar", () -> {
			if (!f.valido()) return;
			//todo
			f.ocultar();
			menuUsuario(f);
		});

		f.mostrar();
	}

	private void menuUsuario(Formulario anterior) {
		Formulario f = new Formulario();

		f.adicionarTexto("Escolha uma opção:");
		f.adicionarBotao("Histório de agendamentos", "Conferir", () -> {
			listarAgendamentosUsuario(f); // popup
		});
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuInicial();
		});
		f.adicionarAcao("Listar espaços", () -> {
			//todo
			f.ocultar();
			menuListarEspacosFisicos(f);
		});
		f.adicionarAcao("Agendar espaço", () -> {
			//todo
			f.ocultar();
			menuAgendarEspacoFisico(f);
		});

		f.mostrar();
	}

	private void listarAgendamentosUsuario(Formulario anterior) {
		Formulario f = new Formulario("Agendamentos de " + usuarioLogado.getNome());

		String resposta = formatarAgendamentosUsuario(usuarioLogado);

		f.adicionarAcao("Voltar", f::ocultar);
		f.adicionarAcao("💾", ()->{
			f.salvarArquivo(resposta, "Agendamentos de " + usuarioLogado.getNome() + ".txt");
		});

		f.adicionarTexto(resposta);

		f.mostrar();
	}

	private void menuListarEspacosFisicos(Formulario anterior) {
		Formulario f = new Formulario();

		f.adicionarDropdown("Tipo", new String[]{"Salas de aula", "Laboratórios", "Salas de estudos"});
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});
		f.adicionarAcao("Listar", () -> {
			f.ocultar();
			listarEspacoFisico(f);
		});

		f.mostrar();
	}

	private void listarEspacoFisico(Formulario anterior) {
		List<EspacoFisico> espacos = switch (anterior.opcao("Tipo")) {
			case "Salas de aula" -> getSalasDeAula();
			case "Laboratórios" -> getLaboratorios();
			default -> getSalasDeEstudos();
		};

		Formulario f = new Formulario(anterior.opcao("Tipo"));

		if (espacos.isEmpty()) {
			f.adicionarTexto("Nenhum espaço encontrado");
		} else {
			for (EspacoFisico espaco : espacos) {
				f.adicionarBotao(espaco.getLocalizacao(), "Agendamentos", () -> {
					try {
						imprimirAgendamentosEspaco(espaco, f);
					} catch (Exception e) {
						f.atualizarErro(e.getMessage());
					}
				});
				f.adicionarBotao(espaco.getLocalizacao(), "📋", () -> {
					f.copiarTexto(espaco.getLocalizacao());
				});
			}

			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			String Datahoje = LocalDate.now().format(formatador);

			f.adicionarInput("Data inicial", Datahoje);
			f.adicionarInput("Data final");
		}

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuUsuario(f);
		});
		f.mostrar();
	}

	private void imprimirAgendamentosEspaco(EspacoFisico espaco, Formulario anterior) {
		anterior.atualizarErro();

		String stringDataInicio = anterior.resposta("Data inicial");
		String stringDataFim = anterior.resposta("Data final");

		LocalDate dataInicio = stringDataInicio.isEmpty() ? null : parseLocalDate(stringDataInicio);
		LocalDate dataFim = stringDataFim.isEmpty() ? null : parseLocalDate(stringDataFim);

		String resposta = formatarAgendamentosEspaco(espaco, dataInicio, dataFim);

		Formulario f = new Formulario("Agendamentos de " + espaco.getLocalizacao());

		f.adicionarAcao("Voltar", f::ocultar);
		f.adicionarAcao("💾", ()->{
			f.salvarArquivo(resposta, "Agendamentos de " + espaco.getLocalizacao() + ".txt");
		});

		f.adicionarTexto(resposta);

		f.mostrar();
	}

	private void menuAgendarEspacoFisico(Formulario anterior) {
		Formulario f = new Formulario("Agendamento");

		f.adicionarTexto("Agendar espaço físico");

		f.adicionarInput("Data inicial", true);
		f.adicionarInput("Horário inicial", true);

		f.adicionarInput("Data final", true);
		f.adicionarInput("Horário final", true);

		f.adicionarInput("Localização", true);

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});
		f.adicionarAcao("Agendar", () -> {
			try {
				if (!f.valido()) return;
				LocalDate dataInicio = parseLocalDate(f.resposta("Data inicial"));
				LocalDate dataFim = parseLocalDate(f.resposta("Data final"));
				LocalTime horarioInicio = parseLocalTime(f.resposta("Horário inicial"));
				LocalTime horarioFim = parseLocalTime(f.resposta("Horário final"));

				String localizacao = f.resposta("Localização");
				EspacoFisico espaco = obterEspacoFisicoLocalizacao(localizacao);

				LocalDateTime dataHoraInicio = combinarDataEHora(dataInicio, horarioInicio);
				LocalDateTime dataHoraFim = combinarDataEHora(dataFim, horarioFim);

				validarAgendamento(usuarioLogado, dataHoraInicio, dataHoraFim, espaco);
				mostrarMensagem("Agendamento realizado com sucesso!");

				f.ocultar();
				anterior.mostrar();

			} catch (Exception e) {
				f.atualizarErro(e.getMessage());
			}
		});

		f.mostrar();
	}
}
