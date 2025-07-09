package aplicacao;

import entidades.Aluno;
import entidades.EspacoFisico;
import servicos.cadastro.Registro;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static aplicacao.Formulario.mostrarErro;
import static aplicacao.Formulario.mostrarMensagem;
import static aplicacao.Teste.gerarAluno;
import static aplicacao.Teste.gerarSalaDeAula;
import static servicos.agendamento.AgendamentoService.*;
import static servicos.agendamento.Agendar.validarAgendamento;

public class Menu {
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
			default -> menuCadastrarEspacoFisico();
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
			menuListarEspacosFisicos();
		});
		f.adicionarAcao("Agendar espaço", () -> {
			//todo
			f.ocultar();
			menuAgendarEspacoFisico(f);
		});

		f.mostrar();
	}

	private void listarAgendamentosUsuario(Formulario anterior) {
		Formulario f = new Formulario();

		//todo
		f.adicionarAcao("Voltar", f::ocultar);

		f.mostrar();
	}


	private void menuListarEspacosFisicos() {
		//todo
	}

	private void menuAgendarEspacoFisico(Formulario anterior) {
		Formulario f = new Formulario();

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

				LocalDateTime dataHoraInicio = combinarDataEHora(dataInicio, horarioInicio);
				LocalDateTime dataHoraFim = combinarDataEHora(dataFim, horarioFim);

				// TODO
				//validarAgendamento(aluno, dataHoraInicio, dataHoraFim, es);
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
