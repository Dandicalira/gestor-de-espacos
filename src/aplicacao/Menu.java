package aplicacao;

import entidades.EspacoFisico;
import entidades.Professor;
import entidades.Professor;
import entidades.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static aplicacao.Formulario.mostrarMensagem;
import static servicos.agendamento.AgendamentoService.*;
import static servicos.agendamento.Agendar.validarAgendamento;
import servicos.autenticacao.*;
import servicos.cadastro.*;
import servicos.persistencia.*;

@SuppressWarnings("ExtractMethodRecommender")
public class Menu {
	Usuario usuarioLogado = null;

	public Menu() {
	}

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
		f.adicionarSenha("Senha");
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuInicial();
		});
		f.adicionarAcao("Entrar", () -> {
			if (!f.valido()) return;
			try {
				AutenticacaoService.autenticarSuperUsuario(f.resposta("Senha"));
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

	private void selecionarCadastro(Formulario f) {
		switch (f.opcao("Escolha uma opção")) {
			case "Aluno" -> menuCadastrarAluno(f);
			case "Professor" -> menuCadastrarProfessor(f);
			case "Técnico Administrativo" -> menuCadastrarAdministrativo(f);
			case "Espaço Físico" -> menuCadastrarEspacoFisico(f);
		}
	}

	private void menuCadastrarEspacoFisico(Formulario anterior) {
		Formulario f = new Formulario("Cadastro de Espaço Físico");

		f.adicionarInput("Localização", true);
		f.adicionarInput("Capacidade", true, "ALGARISMOS");

		f.adicionarDropdown("Tipo", new String[]{"Sala de aula", "Laboratório", "Sala de estudos"});

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});

		f.adicionarAcao("Cadastrar", () -> {
			if (!f.valido()) return;

			String nome = f.resposta("Nome do espaço");
			String localizacao = f.resposta("Localização");
			String capacidadeStr = f.resposta("Capacidade");
			String tipo = f.opcao("Tipo");

			// TODO: Validar capacidade como número, cadastrar espaço físico

            mostrarMensagem("Cadastro realizado com sucesso!");
		});

		f.mostrar();
	}

	private void menuCadastrarAdministrativo(Formulario anterior) {
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
		f.adicionarSenha("Senha");
		f.adicionarInput("Email", true);
		f.adicionarInput("Telefone", true, "ALGARISMOS");
		f.adicionarInput("Matrícula Institucional", true);
		f.adicionarInput("Cargo", true);
		f.adicionarInput("Departamento", true);
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});

		f.adicionarAcao("Cadastrar", () -> {
			if (!f.valido()) return;

			try {
				String nome = f.resposta("Nome");
				String senha = f.resposta("Senha");
				String matriculaInstitucional = f.resposta("Matrícula Institucional");
				String email = f.resposta("Email");
				String telefone = f.resposta("Telefone");
				String cargo = f.resposta("Cargo");
				
				
				String departamento = f.resposta("Departamento");

				CadastroService.cadastrarAdministrativo(nome, senha, matriculaInstitucional, email, telefone, departamento, cargo);
				mostrarMensagem("Cadastro do técnico-administrativo realizado com sucesso!");
				PersistenciaService.salvarDados();
				f.ocultar();
				anterior.mostrar();
			} catch (Exception e) {
				f.atualizarErro(e.getMessage());
			}
		});

		f.mostrar();
	}

	private void menuCadastrarProfessor(Formulario anterior) {
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
		f.adicionarSenha("Senha");
		f.adicionarInput("Email", true);
		f.adicionarInput("Telefone", true, "ALGARISMOS");
		f.adicionarInput("Matrícula Institucional", true);
		f.adicionarInput("Curso", true);
		f.adicionarDropdown("Cargo Acadêmico", new String[]{"Professor Titular", "Professor Associado", "Professor Adjunto", "Professor Assistente", "Professor Auxiliar"});
		
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});

		f.adicionarAcao("Cadastrar", () -> {
			if (!f.valido()) return;

			try {
				String nome = f.resposta("Nome");
				String senha = f.resposta("Senha");
				String matriculaInstitucional = f.resposta("Matrícula Institucional");
				String email = f.resposta("Email");
				String telefone = f.resposta("Telefone");
				String curso = f.resposta("Curso");
				Professor.CargoAcademico cargoAcademico = Professor.obterCargo(f.opcao("Cargo Acadêmico"));
				CadastroService.cadastrarProfessor(nome, senha, matriculaInstitucional, email, telefone, curso, cargoAcademico);
				mostrarMensagem("Cadastro do professor realizado com sucesso!");
				PersistenciaService.salvarDados();
				f.ocultar();
				anterior.mostrar();
			} catch (Exception e) {
				f.atualizarErro(e.getMessage());
			}
		});

		f.mostrar();
	}

	private void menuCadastrarAluno(Formulario anterior) {
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
		f.adicionarSenha("Senha");
		f.adicionarInput("Email", true);
		f.adicionarInput("Telefone", true, "ALGARISMOS");
		f.adicionarInput("Matrícula", true);
		f.adicionarRadio("Semestre",new String[]{"1º","2º","3º","4º","5º","6º","7º","8º","9º","10º"});
		f.adicionarInput("Curso", true);

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
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

				String strSemestre = f.selecao("Semestre").replace("º", "");
				int semestre = Integer.parseInt(strSemestre);

				CadastroService.cadastrarAluno(nome, senha, matricula, email, telefone, curso, semestre);
				mostrarMensagem("Cadastro do aluno realizado com sucesso!");
				PersistenciaService.salvarDados();
				f.ocultar();
				anterior.mostrar();
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
		f.adicionarSenha("Senha");
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});
		f.adicionarAcao("Entrar", () -> {
			if (!f.valido()) return;
			try {
				usuarioLogado = AutenticacaoService.autenticarLogin(f.resposta("Matrícula"), f.resposta("Senha"));
				//System.out.println(usuarioLogado);
				f.ocultar();
				menuUsuario(f);
			} catch (Exception e) {
				f.atualizarErro(e.getMessage());
			}
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
			menuAgendarEspacoFisico(f, null);
		});

		f.mostrar();
	}

	private void listarAgendamentosUsuario(Formulario anterior) {
		Formulario f = new Formulario("Agendamentos de " + usuarioLogado.getNome());

		String resposta = formatarAgendamentosUsuario(usuarioLogado);

		f.adicionarAcao("Voltar", f::ocultar);
		f.adicionarAcao("💾", () -> {
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
			case "Salas de aula" -> Registro.getSalasDeAula();
			case "Laboratórios" -> Registro.getLaboratorios();
			default -> Registro.getSalasDeEstudos();
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
				f.adicionarBotao(espaco.getLocalizacao(), "Agendar", () -> {
					f.ocultar();
					menuAgendarEspacoFisico(f,espaco.getLocalizacao());
				});
			}

			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			String Datahoje = LocalDate.now().format(formatador);

			f.adicionarInput("Data inicial", "DATA");
			f.preencherInput(Datahoje);

			f.adicionarInput("Data final", "DATA");
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
		f.adicionarAcao("💾", () -> {
			f.salvarArquivo(resposta, "Agendamentos de " + espaco.getLocalizacao() + ".txt");
		});

		f.adicionarTexto(resposta);

		f.mostrar();
	}

	private void menuAgendarEspacoFisico(Formulario anterior, String localizacaoTransferida) {
		Formulario f = new Formulario("Agendamento");

		f.adicionarTexto("Agendar espaço físico");

		f.adicionarInput("Data inicial", true, "DATA");
		f.adicionarInput("Horário inicial", true, "HORARIO");

		f.adicionarInput("Data final", true, "DATA");
		f.adicionarInput("Horário final", true, "HORARIO");

		f.adicionarInput("Localização", true);
		f.preencherInput(localizacaoTransferida);

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
				EspacoFisico espaco = Registro.obterEspacoFisicoLocalizacao(localizacao);

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
