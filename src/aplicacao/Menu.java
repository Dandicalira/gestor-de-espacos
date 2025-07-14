package aplicacao;

import entidades.*;
import excecoes.CampoVazioException;
import excecoes.EntidadeDuplicadaException;
import excecoes.ForaDoIntervaloException;
import servicos.autenticacao.AutenticacaoService;
import servicos.cadastro.CadastroService;
import servicos.cadastro.Registro;
import servicos.persistencia.PersistenciaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static aplicacao.Formulario.mostrarMensagem;
import static entidades.EspacoFisico.obterTipoDeEspaco;
import static servicos.agendamento.AgendamentoService.formatarAgendamentosEspaco;
import static servicos.agendamento.AgendamentoService.formatarAgendamentosUsuario;
import static servicos.agendamento.Agendar.validarAgendamento;
import static servicos.cadastro.CadastroService.cadastrarEspacoFisico;
import static servicos.cadastro.RemoverService.removerEspacoFisico;
import static util.LocalDateTimeUtils.*;

@SuppressWarnings("ExtractMethodRecommender")
public class Menu {
	Usuario usuarioLogado;
	boolean admin;

	public Menu() {
	}

	public void menuInicial() {
		usuarioLogado = null;
		admin = false;

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
		admin = true;
		Formulario f = new Formulario();

		f.adicionarDropdown("Escolha uma opção", new String[]{"Aluno", "Professor", "Técnico-Administrativo", "Espaço Físico"});
		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			menuInicial();
		});
		f.adicionarAcao("Cadastrar", () -> {
			f.ocultar();
			selecionarCadastro(f);
		});
		f.adicionarAcao("Listar", () -> {
			menuListar(f);
		});

		f.mostrar();
	}

	private void menuListar(Formulario f) {
		String opcao = f.opcao("Escolha uma opção");
		switch (opcao) {
			case "Aluno" -> listarAlunos(f);
			case "Professor" -> listarProfessores(f);
			case "Técnico-Administrativo" -> listarAdministrativos(f);
			case "Espaço Físico" -> menuListarEspacosFisicos(f);
		}
	}

	@SuppressWarnings("DuplicatedCode")
	private void listarAdministrativos(Formulario anterior) {
		Formulario f = new Formulario("Técnicos-administrativos cadastrados");
		List<Administrativo> administrativos = Registro.getAdministrativos();

		if (administrativos.isEmpty()) {
			f.adicionarTexto("Nenhum técnico-administrativo encontrado");
		} else {
			for (Administrativo administrativo : administrativos) {
				String nomeMatricula = administrativo.getNome() + " (" + administrativo.getMatriculaInstitucional() + ")";

				f.adicionarBotao(nomeMatricula, "Agendamentos", () -> {
					try {
						listarAgendamentosUsuario(administrativo);
					} catch (Exception e) {
						f.atualizarErro(e.getMessage());
					}
				});

				f.adicionarBotao(nomeMatricula, "Informações", () -> {
					mostrarMensagem(administrativo.toString());
				});

				f.adicionarBotao(nomeMatricula, "Remover", () -> {
					Registro.excluirServidor(administrativo);
					mostrarMensagem("Técnico-administrativo " + nomeMatricula + " removido com sucesso!");
					f.ocultar();
					listarAdministrativos(anterior);
				});
			}
		}

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});
		f.mostrar();
	}

	@SuppressWarnings("DuplicatedCode")
	private void listarProfessores(Formulario anterior) {
		Formulario f = new Formulario("Professores cadastrados");
		List<Professor> professores = Registro.getProfessores();

		if (professores.isEmpty()) {
			f.adicionarTexto("Nenhum professor encontrado");
		} else {
			for (Professor professor : professores) {
				String nomeMatricula = professor.getNome() + " (" + professor.getMatriculaInstitucional() + ")";

				f.adicionarBotao(nomeMatricula, "Agendamentos", () -> {
					try {
						listarAgendamentosUsuario(professor);
					} catch (Exception e) {
						f.atualizarErro(e.getMessage());
					}
				});

				f.adicionarBotao(nomeMatricula, "Informações", () -> {
					mostrarMensagem(professor.toString());
				});

				f.adicionarBotao(nomeMatricula, "Remover", () -> {
					Registro.excluirServidor(professor);
					mostrarMensagem("Professor " + nomeMatricula + " removido com sucesso!");
					f.ocultar();
					listarProfessores(anterior);
				});
			}
		}

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});
		f.mostrar();
	}


	private void listarAlunos(Formulario anterior) {
		Formulario f = new Formulario("Alunos cadastrados");
		List<Aluno> alunos = Registro.getAlunos();

		if (alunos.isEmpty()) {
			f.adicionarTexto("Nenhum aluno encontrado");
		} else {
			for (Aluno aluno : alunos) {
				String nomeMatricula = aluno.getNome() + " (" + aluno.getMatricula() + ")";

				f.adicionarBotao(nomeMatricula, "Agendamentos", () -> {
					try {
						listarAgendamentosUsuario(aluno);
					} catch (Exception e) {
						f.atualizarErro(e.getMessage());
					}
				});

				f.adicionarBotao(nomeMatricula, "Informações", () -> {
					mostrarMensagem(aluno.toString());
				});

				f.adicionarBotao(nomeMatricula, "Remover", () -> {
					Registro.excluirAluno(aluno);
					mostrarMensagem("Aluno " + nomeMatricula + " removido com sucesso!");
					f.ocultar();
					listarAlunos(anterior);
				});
			}
		}

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});
		f.mostrar();
	}


	private void selecionarCadastro(Formulario f) {
		switch (f.opcao("Escolha uma opção")) {
			case "Aluno" -> menuCadastrarAluno(f);
			case "Professor" -> menuCadastrarProfessor(f);
			case "Técnico-Administrativo" -> menuCadastrarAdministrativo(f);
			case "Espaço Físico" -> menuCadastrarEspacoFisico(f);
		}
	}

	private void menuCadastrarEspacoFisico(Formulario anterior) {
		Formulario f = new Formulario("Cadastro de Espaço Físico");

		f.adicionarDropdown("Tipo", new String[]{"Sala de Aula", "Laboratório", "Sala de Estudos"});

		f.adicionarInput("Localização", true);
		f.adicionarInput("Capacidade", true, "ALGARISMOS");

		f.adicionarInput("Horário inicial de funcionamento", true, "HORARIO");
		f.adicionarInput("Horário final de funcionamento", true, "HORARIO");

		// Mágica para guardar os equipamentos
		AtomicReference<List<Equipamento>> equipamentosRef = new AtomicReference<>(new ArrayList<>());

		f.adicionarBotao("Equipamentos", "Cadastrar", () -> {
			f.ocultar();
			menuCadastrarEquipamento(f, equipamentosRef.get());
		});

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});

		f.adicionarAcao("Cadastrar", () -> {
			if (!f.valido()) return;
			try {
				String localizacao = f.resposta("Localização");
				EspacoFisico.TipoDeEspaco tipo = obterTipoDeEspaco(f.opcao("Tipo"));

				LocalTime horarioInicial = parseLocalTime(f.resposta("Horário inicial de funcionamento"));
				LocalTime horarioFinal = parseLocalTime(f.resposta("Horário final de funcionamento"));

				int capacidade = Integer.parseInt(f.resposta("Capacidade"));

				List<Equipamento> equipamentos = equipamentosRef.get(); // Recupera os equipamentos

				cadastrarEspacoFisico(capacidade, horarioInicial, horarioFinal, localizacao, tipo, equipamentos);

				mostrarMensagem("Cadastro realizado com sucesso!");
			} catch (Exception e) {
				f.atualizarErro(e.getMessage());
			}
		});

		f.mostrar();
	}

	private void menuCadastrarEquipamento(Formulario anterior, List<Equipamento> equipamentos) {
		Formulario f = new Formulario("Cadastro de equipamento");

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});
		f.adicionarAcao("Adicionar equipamento", () -> {
			if (!f.valido()) return;
			try {
				String nome = f.resposta("Nome");
				int quantidade = Integer.parseInt(f.resposta("Quantidade"));

				Equipamento equipamento = new Equipamento(nome, quantidade);
				validarEquipamento(equipamento, equipamentos);

				equipamentos.add(equipamento);
				f.atualizarErro("Equipamento adicionado com sucesso!", true);
			} catch (Exception e) {
				f.atualizarErro(e.getMessage());
			}
		});

		f.adicionarBotao("Equipamentos", "Ver lista", () -> {
			listarEquipamentosEmProcessamento(f, equipamentos);
		});

		f.adicionarInput("Nome", true);
		f.adicionarInput("Quantidade", true, "ALGARISMOS");

		f.mostrar();

	}

	private void validarEquipamento(Equipamento equipamento, List<Equipamento> equipamentos) {
		if (equipamento.getNome().isEmpty()) {
			throw new CampoVazioException();
		}

		if (equipamento.getQuantidade() == 0) {
			throw new ForaDoIntervaloException("A quantidade deve ser maior que 0");
		}

		for (Equipamento equipamentoListado : equipamentos) {
			if (equipamentoListado.getNome().equals(equipamento.getNome())) {
				throw new EntidadeDuplicadaException("Equipamento \"" + equipamento.getNome() + "\" ja foi cadastrado!");
			}
		}
	}

	private void listarEquipamentosEmProcessamento(Formulario anterior, List<Equipamento> equipamentos) {
		Formulario f = new Formulario("Lista de equipamentos");

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});

		if (equipamentos.isEmpty()) {
			f.adicionarTexto("Nenhum equipamento cadastrado.");
			f.mostrar();
			return;
		}

		for (Equipamento equipamento : equipamentos) {
			String nomeQuantidade = equipamento.getNome() + " (" + equipamento.getQuantidade() + ")";
			f.adicionarBotao(nomeQuantidade, "Remover", () -> {
				equipamentos.remove(equipamento);

				f.ocultar();
				listarEquipamentosEmProcessamento(anterior, equipamentos);
			});
		}

		f.mostrar();
	}

	private void menuCadastrarAdministrativo(Formulario anterior) {
		Formulario f = new Formulario("Cadastro de Técnico-Administrativo");
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
		f.adicionarInput("Email (@unb.br)", true);
		f.adicionarInput("Telefone (com DDD)", true, "ALGARISMOS");
		f.adicionarInput("Matrícula Institucional (7-9 dígitos)", true);
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
				String matriculaInstitucional = f.resposta("Matrícula Institucional (7-9 dígitos)");
				String email = f.resposta("Email (@unb.br)");
				String telefone = f.resposta("Telefone (com DDD)");
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
		f.adicionarInput("Email (@unb.br)", true);
		f.adicionarInput("Telefone (com DDD)", true, "ALGARISMOS");
		f.adicionarInput("Matrícula Institucional (7-9 dígitos)", true);
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
				String matriculaInstitucional = f.resposta("Matrícula Institucional (7-9 dígitos)");
				String email = f.resposta("Email (@unb.br)");
				String telefone = f.resposta("Telefone (com DDD)");
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
		f.adicionarInput("Email (@aluno.unb.br)", true);
		f.adicionarInput("Telefone (com DDD)", true, "ALGARISMOS");
		f.adicionarInput("Matrícula (9 dígitos)", true);

		String[] semestres = new String[15];


		f.adicionarRadio("Semestre", new String[]{"1º", "2º", "3º", "4º", "5º", "6º", "7º", "8º", "9º", "10º", "11º", "12º", "13º", "14º", "15º"});
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
				String matricula = f.resposta("Matrícula (9 dígitos)");
				String email = f.resposta("Email (@aluno.unb.br)");
				String telefone = f.resposta("Telefone (com DDD)");
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

		f.adicionarTexto(usuarioLogado.toString());

		f.adicionarBotao("Histório de agendamentos", "Conferir", () -> {
			listarAgendamentosUsuario(usuarioLogado);
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
			menuAgendarEspacoFisico(f, null);
		});

		f.mostrar();
	}

	private void listarAgendamentosUsuario(Usuario usuario) {
		Formulario f = new Formulario("Agendamentos de " + usuario.getNome());

		String resposta = formatarAgendamentosUsuario(usuario);

		f.adicionarAcao("Voltar", f::ocultar);
		f.adicionarAcao("💾", () -> {
			f.salvarArquivo(resposta, "Agendamentos de " + usuario.getNome() + ".txt");
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

				if (!admin) {
					f.adicionarBotao(espaco.getLocalizacao(), "Agendar", () -> {
						f.ocultar();
						menuAgendarEspacoFisico(f, espaco.getLocalizacao());
					});
				} else {
					f.adicionarBotao(espaco.getLocalizacao(), "Informações", () -> {
						mostrarMensagem(espaco.toString());
					});
					f.adicionarBotao(espaco.getLocalizacao(), "Remover", () -> {
						removerEspacoFisico(espaco);
						mostrarMensagem("Espaço " + espaco.getLocalizacao() + " removido com sucesso!");
						f.ocultar();
						listarEspacoFisico(anterior);
					});
				}
			}

			if (!admin) {
				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				String Datahoje = LocalDate.now().format(formatador);

				f.adicionarInput("Data inicial", "DATA");
				f.preencherInput(Datahoje);

				f.adicionarInput("Data final", "DATA");
			}
		}

		f.adicionarAcao("Voltar", () -> {
			f.ocultar();
			anterior.mostrar();
		});
		f.mostrar();
	}

	private void imprimirAgendamentosEspaco(EspacoFisico espaco, Formulario anterior) {
		anterior.atualizarErro();

		String stringDataInicio = "";
		String stringDataFim = "";

		if (!admin) {
			stringDataInicio = anterior.resposta("Data inicial");
			stringDataFim = anterior.resposta("Data final");
		}

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
