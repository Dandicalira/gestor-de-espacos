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
			//todo
			f.ocultar();
			menuAdmin(f);
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
			f.ocultar();
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
		//todo
	}

	private void menuCadastrarAdministrativo() {
		//todo
	}

	private void menuCadastrarProfessor() {
		//todo
	}

	private void menuCadastrarAluno() {
		//todo
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
			menuUsuario();
		});

		f.mostrar();
	}

	private void menuUsuario() {
		Formulario f = new Formulario();
		f.adicionarTexto("Usuário: " + usuarioLogado.getNome());
		f.adicionarTexto("Matrícula: " + usuarioLogado.getIdentificacao());
		f.adicionarTexto("Email: " + usuarioLogado.getEmail());
		f.adicionarTexto("Telefone: " + usuarioLogado.getTelefone());

		f.adicionarBotao("Histório de agendamentos", "Conferir", () -> {
			listarAgendamentosUsuario(f); // popup
		});
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuInicial();
		});
		f.adicionarAcao("Listar espaços", () -> {
			f.ocultar();
			menuListarEspacosFisicos(f);
		});
		f.adicionarAcao("Agendar espaço", () -> {
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
			menuUsuario();
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
