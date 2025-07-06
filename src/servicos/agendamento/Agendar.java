package servicos.agendamento;

import entidades.Aluno;
import entidades.EspacoFisico;
import entidades.Usuario;
import excecoes.DiasExcedidosException;
import excecoes.HorarioIndisponivelException;
import excecoes.HorarioNaoElegivelException;
import excecoes.PeriodoInvalidoException;
import util.LocalTimeUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Agendar {
	public static void validarAgendamento(Usuario usuario, LocalDateTime dataInicio, LocalDateTime dataFim, EspacoFisico espaco) {
		verificarPeriodoValido(dataInicio, dataFim);
		verificarHorarioElegivel(espaco, dataInicio, dataFim);
		verificarDisponibilidade(espaco, dataInicio, dataFim);
		verificarLimiteDias(usuario, dataInicio, dataFim);

		agendarEspaco(usuario, dataInicio, dataFim, espaco);
	}

	private static boolean ehAluno(Usuario usuario) {
		return (usuario instanceof Aluno);
	}
	
	private static void verificarLimiteDias(Usuario usuario, LocalDateTime dataInicio, LocalDateTime dataFim) {
		if (ehAluno(usuario)) {
			validarDuracaoPermitidaDias(dataInicio, dataFim, 1);
		}
	}

	private static void verificarPeriodoValido(LocalDateTime dataInicio, LocalDateTime dataFim) {
		if (!dataInicio.isBefore(dataFim)) {
			throw new PeriodoInvalidoException();
		}
	}

	private static void verificarHorarioElegivel(EspacoFisico espaco, LocalDateTime dataInicio, LocalDateTime dataFim) {
		LocalTime horaInicialDisponivel = espaco.getHorarioInicialDisponivel();
		LocalTime horaFinalDisponivel = espaco.getHorarioFinalDisponivel();
		LocalTime horaInicialSelecionada = LocalTime.of(dataInicio.getHour(), dataInicio.getMinute());
		LocalTime horaFinalSelecionada = LocalTime.of(dataFim.getHour(), dataFim.getMinute());

		if (!LocalTimeUtils.isBetween(horaInicialSelecionada, horaInicialDisponivel, horaFinalDisponivel, true)
				|| !LocalTimeUtils.isBetween(horaFinalSelecionada, horaInicialDisponivel, horaFinalDisponivel, true)) {
			throw new HorarioNaoElegivelException();
		}
	}

	private static void validarDuracaoPermitidaDias(LocalDateTime dataInicio, LocalDateTime dataFim, int limiteDias) {
		if (limiteDias <= 0) return;
		long diasSelecionados = ChronoUnit.DAYS.between(dataInicio.toLocalDate(), dataFim.toLocalDate()) + 1;

		if (diasSelecionados > limiteDias) {
			throw new DiasExcedidosException(limiteDias, diasSelecionados);
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
