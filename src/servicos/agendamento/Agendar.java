package servicos.agendamento;

import entidades.Aluno;
import entidades.EspacoFisico;
import entidades.Usuario;
import excecoes.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static servicos.cadastro.Registro.getEspacosFisicos;
import static servicos.cadastro.Registro.getUsuarios;
import static util.LocalDateTimeUtils.isBetween;

public class Agendar {
	public static void validarAgendamento(Usuario usuario, LocalDateTime dataInicio, LocalDateTime dataFim, EspacoFisico espaco) {
		verificarExistenciaUsuario(usuario);
		verificarExistenciaEspacoFisico(espaco);
		verificarPeriodoValido(dataInicio, dataFim);
		verificarDataFutura(dataInicio);
		verificarPeriodoMinimoMinutos(dataInicio, dataFim, 20);
		verificarHorarioElegivel(espaco, dataInicio, dataFim);
		verificarDisponibilidade(espaco, dataInicio, dataFim);
		verificarLimitesUsuario(usuario, dataInicio, dataFim);

		agendarEspaco(usuario, dataInicio, dataFim, espaco);
	}

	private static void verificarExistenciaEspacoFisico(EspacoFisico espaco) {
		List<EspacoFisico> espacos = getEspacosFisicos();

		if (espaco == null || !espacos.contains(espaco)) {
			throw new EntidadeInexistenteException("O espaço físico informado não existe");
		}
	}

	private static void verificarExistenciaUsuario(Usuario usuario) {
		List<Usuario> usuarios = getUsuarios();

		if (usuario == null || !usuarios.contains(usuario)) {
			throw new EntidadeInexistenteException("O usuário informado não existe");
		}
	}

	private static void verificarPeriodoMinimoMinutos(LocalDateTime dataInicio, LocalDateTime dataFim, int periodoMinimo) {
		Duration duracao = Duration.between(dataInicio, dataFim);
		if (duracao.toMinutes() < periodoMinimo) {
			throw new PeriodoMinimoException(periodoMinimo);
		}
	}

	private static boolean ehAluno(Usuario usuario) {
		return (usuario instanceof Aluno);
	}

	private static void verificarLimitesUsuario(Usuario usuario, LocalDateTime dataInicio, LocalDateTime dataFim) {
		if (ehAluno(usuario)) {
			validarDuracaoPermitidaDias(dataInicio, dataFim, 1);
			verificarPermissaoData(usuario, dataFim);
		}
	}

	private static void verificarDataFutura(LocalDateTime dataInicio) {
		LocalDateTime agora = LocalDateTime.now();
		if (dataInicio.isBefore(agora)) {
			throw new DataFuturaException();
		}
	}

	private static void verificarPermissaoData(Usuario usuario, LocalDateTime dataFim) {
		LocalDateTime agora = LocalDateTime.now();
		List<Agendamento> agendamentos = usuario.getAgendamentos();
		Agendamento agendamentoMaisRecente = obterAgendamentoMaisRecente(agendamentos);

		if (agendamentoMaisRecente == null) {
			return;
		}

		if (!agora.isBefore(agendamentoMaisRecente.dataFim())) {
			return;
		}

		if (dataFim.toLocalDate().equals(agendamentoMaisRecente.dataFim().toLocalDate())) {
			return;
		}

		throw new DataIlegalException();
	}

	public static Agendamento obterAgendamentoMaisRecente(List<Agendamento> agendamentos) {
		if (agendamentos == null || agendamentos.isEmpty()) {
			return null;
		}

		Agendamento agendamentoMaisRecente = agendamentos.get(0);

		for (Agendamento agendamento : agendamentos) {
			if (agendamento.dataInicio().isAfter(agendamentoMaisRecente.dataInicio())) {
				agendamentoMaisRecente = agendamento;
			}
		}
		return agendamentoMaisRecente;
	}


	private static void verificarPeriodoValido(LocalDateTime dataInicio, LocalDateTime dataFim) {
		if (dataInicio.isAfter(dataFim)) {
			throw new PeriodoInvalidoException();
		}
	}

	private static void verificarHorarioElegivel(EspacoFisico espaco, LocalDateTime dataInicio, LocalDateTime dataFim) {
		LocalTime min = espaco.getHorarioInicialDisponivel();
		LocalTime max = espaco.getHorarioFinalDisponivel();
		LocalTime horarioInicial = dataInicio.toLocalTime();
		LocalTime horarioFinal = dataFim.toLocalTime();

		if (!isBetween(horarioInicial, min, max, true)
				|| !isBetween(horarioFinal, min, max, true)
				|| horarioFinal.equals(min)
				|| horarioInicial.equals(max)) {
			throw new HorarioNaoElegivelException();
		}
	}

	private static void validarDuracaoPermitidaDias(LocalDateTime dataInicio, LocalDateTime dataFim, int limiteDias) {
		if (limiteDias <= 0) return;
		long diasSelecionados = ChronoUnit.DAYS.between(dataInicio.toLocalDate(), dataFim.toLocalDate()) + 1;

		if (diasSelecionados > limiteDias) {
			throw new DiasExcedidosException(limiteDias);
		}
	}

	private static void verificarDisponibilidade(EspacoFisico espaco, LocalDateTime dataInicio, LocalDateTime dataFim) {
		for (Agendamento agendamento : espaco.getAgendamentos()) {
			if (agendamento.sobrepoe(dataInicio, dataFim)) {
				throw new HorarioIndisponivelException();
			}
		}
	}

	private static void agendarEspaco(Usuario usuario, LocalDateTime dataInicio, LocalDateTime dataFim, EspacoFisico espaco) {
		Agendamento agendamento = new Agendamento(dataInicio, dataFim, usuario, espaco);
		espaco.adicionarAgendamento(agendamento);
		usuario.adicionarAgendamento(agendamento);
	}

	private static void desagendarEspaco(Agendamento agendamento) {
		Usuario usuario = agendamento.usuario();
		EspacoFisico espaco = agendamento.espaco();
		espaco.removerAgendamento(agendamento);
		usuario.removerAgendamento(agendamento);
	}

}
