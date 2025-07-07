package aplicacao;

import entidades.EspacoFisico;
import entidades.Usuario;
import excecoes.ForaDoIntervaloException;
import excecoes.PeriodoInvalidoException;
import excecoes.VoltarException;
import servicos.agendamento.Agendamento;
import servicos.autenticacao.AutenticacaoService;
import servicos.cadastro.CadastroService;
import servicos.cadastro.Registro;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static aplicacao.Console.*;
import static servicos.agendamento.Agendar.validarAgendamento;

public class Menu {
	Usuario usuarioLogado;

	public void iniciarAplicacao() {
		while (true) {
			try {
				limparTela();
				imprimirMenuInicial();
				selecionarModulo(EntradaDeDados.lerInteiroIntervalo(0, 2));

			} catch (VoltarException e) {
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void selecionarModulo(int sel) {
		switch (sel) {
			case 0 -> throw new VoltarException();
			case 1 -> cadastro();
			case 2 -> login();
			default -> throw new ForaDoIntervaloException(0, 2);
		}
	}

	private void selecionarCadastro(int sel) {
		switch (sel) {
			case 0 -> throw new VoltarException();
			case 1 -> CadastroService.cadastrarAluno();
			case 2 -> CadastroService.cadastrarProfessor();
			case 3 -> CadastroService.cadastrarAdministrativo();
			case 4 -> CadastroService.cadastrarEspacoFisico();
			default -> throw new ForaDoIntervaloException(0, 4);
		}
	}

	private void cadastro() {
		limparTela();
		System.out.print("Digite a senha mestra: ");
		while (!validarSenhaMestra(EntradaDeDados.lerString())) {
			limparTela();
			System.out.println("Senha incorreta. Tente novamente.");
			System.out.print("Digite a senha mestra: ");
		}
		while (true) {
			try {
				limparTela();
				imprimirOpcoesCadastro();
				selecionarCadastro(EntradaDeDados.lerInteiroIntervalo(0, 4));
			} catch (VoltarException e) {
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private boolean validarSenhaMestra(String senha) {
		return Objects.equals(senha, "314");
	}

	private void login() {
		limparTela();
		while (true) {
			System.out.println("--- Login ---");
			System.out.print("Digite a matricula [Deixe vazio para voltar]: ");
			String identificacao = EntradaDeDados.lerString();

			if (identificacao.isEmpty()) {
				System.out.println("Login cancelado.");
				return;
			}

			System.out.print("Digite a senha: ");
			String senha = EntradaDeDados.lerString();

			try {
				usuarioLogado = AutenticacaoService.autenticar(identificacao, senha);
				agendamento();
			} catch (VoltarException e) {
				usuarioLogado = null;
				limparTela();
				System.out.println("Usuário desconectado.\n");
			} catch (Exception e) {
				limparTela();
				System.out.println(e.getMessage());
			}
		}
	}

	private void agendamento() {
		limparTela();
		while (true) {
			try {
				imprimirOpcoesAgendamento();
				selecionarAgendamento(EntradaDeDados.lerInteiroIntervalo(0, 4));
			} catch (VoltarException e) {
				limparTela();
				throw new VoltarException();
			} catch (Exception e) {
				limparTela();
				System.out.println(e.getMessage());
			}
		}
	}

	private void selecionarAgendamento(int sel) {
		switch (sel) {
			case 0 -> throw new VoltarException();
			case 1 -> listarHistoricoAgendamentos(usuarioLogado);
			case 2 -> listarEspacosFisicos();
			case 3 -> ConsultarAgendamentosEspaco();
			case 4 -> agendar();
			default -> throw new ForaDoIntervaloException(0, 4);
		}
	}

	private void ConsultarAgendamentosEspaco() {
		List<EspacoFisico> espacos = obterTodosEspacos();
		agendamentoEspaco(espacos);
	}

	private List<EspacoFisico> obterTodosEspacos() {
		List<EspacoFisico> espacos = new ArrayList<>(Registro.getSalasDeAula());
		espacos.addAll(Registro.getLaboratorios());
		espacos.addAll(Registro.getSalasDeEstudos());
		return espacos;
	}

	private void agendar() {
		while (true) {
			try {
				LocalDate diaInicio = EntradaDeDados.lerData("Digite a data inicial: ", false);
				System.out.print("Digite a hora inicial desejada: ");
				int horaInicio = EntradaDeDados.lerInteiro();
				System.out.print("Digite o minuto inicial desejado: ");
				int minutoInicio = EntradaDeDados.lerInteiro();

				LocalDate diaFim = EntradaDeDados.lerData("Digite a data final: ", false);
				System.out.print("Digite a hora final desejada: ");
				int horaFim = EntradaDeDados.lerInteiro();
				System.out.print("Digite o minuto final desejado: ");
				int minutoFim = EntradaDeDados.lerInteiro();

				assert diaInicio != null;
				assert diaFim != null;
				LocalDateTime dataInicio = LocalDateTime.of(diaInicio, LocalTime.of(horaInicio, minutoInicio));
				LocalDateTime dataFim = LocalDateTime.of(diaFim, LocalTime.of(horaFim, minutoFim));

				System.out.print("Digite a localização do espaço a ser agendado: ");
				String localizacao = EntradaDeDados.lerString();

				List<EspacoFisico> espacos = obterTodosEspacos();
				EspacoFisico espacoSelecionado = null;
				for (EspacoFisico espaco : espacos) {
					if (espaco.getLocalizacao().equalsIgnoreCase(localizacao)) {
						espacoSelecionado = espaco;
						break;
					}
				}

				validarAgendamento(usuarioLogado, dataInicio, dataFim, espacoSelecionado);
				limparTela();
				System.out.println("Agendamento realizado com sucesso!\n");
				break;
			} catch (Exception e) {
				limparTela();
				System.out.println(e.getMessage());
			}
		}
	}

	private void selecionarEspacoFisico(int sel) {
		switch (sel) {
			case 0 -> throw new VoltarException();
			case 1 -> listarSalasDeAula();
			case 2 -> listarLaboratorios();
			case 3 -> listarSalasDeEstudos();
			default -> throw new ForaDoIntervaloException(0, 3);
		}
	}

	private void listarEspacosFisicos() {
		limparTela();
		while (true) {
			try {
				imprimirEspacosFisicos();
				selecionarEspacoFisico(EntradaDeDados.lerInteiroIntervalo(0, 3));
			} catch (VoltarException e) {
				break;
			} catch (Exception e) {
				limparTela();
				System.out.println(e.getMessage());
			}
		}
	}

	private void listarHistoricoAgendamentos(Usuario usuarioLogado) {
		limparTela();

		List<Agendamento> agendamentos = usuarioLogado.getAgendamentos();

		if (agendamentos.isEmpty()) {
			System.out.println("Nenhum agendamento encontrado.\n");
			return;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		String nome = usuarioLogado.getNome();
		System.out.println("Histórico de agendamentos de " + nome + ":");
		System.out.println("------------------------");

		for (Agendamento agendamento : agendamentos) {
			String tipo = agendamento.espaco().getTipo();
			String localizacao = agendamento.espaco().getLocalizacao();
			String inicio = agendamento.dataInicio().format(formatter);
			String fim = agendamento.dataFim().format(formatter);

			System.out.println(tipo + ": " + localizacao);
			System.out.println("Início: " + inicio);
			System.out.println("Fim: " + fim);
			System.out.println("------------------------");
		}
		System.out.println();
	}

	private void listarLocalizacoes(List<EspacoFisico> espacos) {
		System.out.println("---------------------------");
		for (EspacoFisico espaco : espacos) {
			System.out.println("Localização: \"" + espaco.getLocalizacao() + "\"");
		}
		System.out.println("---------------------------\n");

	}

	private void agendamentoEspaco(List<EspacoFisico> espacos) {
		System.out.print("Digite a localização do espaço a ser consultado: ");
		String localizacao = EntradaDeDados.lerString();

		EspacoFisico espacoSelecionado = null;
		for (EspacoFisico espaco : espacos) {
			if (espaco.getLocalizacao().equalsIgnoreCase(localizacao)) {
				espacoSelecionado = espaco;
				break;
			}
		}

		if (espacoSelecionado == null) {
			limparTela();
			System.out.println("Espaço com essa localização não encontrado.\n");
			return;
		}

		while (true) {
			LocalDate dataInicio = EntradaDeDados.lerData("Insira a data inicial (dd/MM/yyyy) [Deixe vazio para indefinido]: ", true);
			LocalDate dataFim = EntradaDeDados.lerData("Insira a data final (dd/MM/yyyy) [Deixe vazio para indefinido]: ", true);

			try {
				validarPeriodo(dataInicio, dataFim);
				listarAgendamentosEspaco(espacoSelecionado, dataInicio, dataFim);
				break;

			} catch (Exception e) {
				limparTela();
				System.out.println(e.getMessage() + "\n");
			}
		}
	}

	private static void validarPeriodo(LocalDate dataInicio, LocalDate dataFim) {
		if (dataInicio != null && dataFim != null && dataInicio.isAfter(dataFim)) {
			throw new PeriodoInvalidoException();
		}
	}

	private void listarSalasDeAula() {
		limparTela();
		List<EspacoFisico> espacos = Registro.getSalasDeAula();
		if (espacos.isEmpty()) {
			System.out.println("Nenhuma sala de aula encontrada.\n");
			return;
		}
		System.out.println("Salas de Aula Cadastradas:");

		listarLocalizacoes(espacos);
	}

	private void listarLaboratorios() {
		limparTela();
		List<EspacoFisico> espacos = Registro.getLaboratorios();
		if (espacos.isEmpty()) {
			System.out.println("Nenhum laboratório encontrado.\n");
			return;
		}
		System.out.println("Laboratórios Cadastrados:");

		listarLocalizacoes(espacos);
	}

	private void listarSalasDeEstudos() {
		limparTela();
		List<EspacoFisico> espacos = Registro.getSalasDeEstudos();
		if (espacos.isEmpty()) {
			System.out.println("Nenhuma sala de estudos encontrada.\n");
			return;
		}
		System.out.println("Salas de estudo Cadastradas:");

		listarLocalizacoes(espacos);
	}

	private void listarAgendamentosEspaco(EspacoFisico espaco, LocalDate dataInicio, LocalDate dataFim) {
		limparTela();

		List<Agendamento> agendamentos = espaco.getAgendamentos();

		if (agendamentos.isEmpty()) {
			System.out.println("Nenhum agendamento encontrado.\n");
			return;
		}

		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("Agendamentos de " + espaco.getLocalizacao());
		System.out.println("Tipo: " + espaco.getTipo());
		System.out.println("------------------------");

		boolean encontrouAgendamento = false;

		for (Agendamento agendamento : agendamentos) {
			LocalDate agendamentoInicio = agendamento.dataInicio().toLocalDate();
			LocalDate agendamentoFim = agendamento.dataFim().toLocalDate();

			if (dataSobrepoe(dataInicio, dataFim, agendamentoInicio, agendamentoFim)) {
				System.out.println("Usuário: " + agendamento.usuario().getNome());
				System.out.println("Início: " + agendamento.dataInicio().format(formatador));
				System.out.println("Fim: " + agendamento.dataFim().format(formatador));
				System.out.println("------------------------");
				encontrouAgendamento = true;
			}
		}
		if (!encontrouAgendamento) {
			System.out.println("Nenhum agendamento encontrado no intervalo.");
		}

		System.out.println();
	}

	private boolean dataSobrepoe(LocalDate filtroInicio, LocalDate filtroFim, LocalDate agendamentoInicio, LocalDate agendamentoFim) {
		if (filtroInicio != null && agendamentoFim.isBefore(filtroInicio)) {
			return false;
		}
		return filtroFim == null || !agendamentoInicio.isAfter(filtroFim);
	}

	protected static void limparTela() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

}
