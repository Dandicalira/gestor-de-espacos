package servicos.agendamento;

import entidades.Aluno;
import entidades.EspacoFisico;
import entidades.Horario;
import entidades.Usuario;
import excecoes.DiasExcedidosException;
import excecoes.HorarioIndisponivelException;
import excecoes.HorarioNaoElegivelException;
import excecoes.PeriodoInvalidoException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Agendar {
	public static void validarAgendamento(Usuario usuario, LocalDateTime dataInicio, LocalDateTime dataFim, EspacoFisico espaco) {
		verificarPeriodoValido(dataInicio, dataFim);
		verificarHorarioElegivel(espaco, dataInicio, dataFim);
		verificarDisponibilidade(espaco, dataInicio, dataFim);
		if (ehAluno(usuario)) verificarLimiteDias(dataInicio, dataFim);

		agendarEspaco(usuario, dataInicio, dataFim, espaco);
	}

	private static boolean ehAluno(Usuario usuario) {
		return (usuario instanceof Aluno);
	}

	private static void verificarPeriodoValido(LocalDateTime dataInicio, LocalDateTime dataFim) {
		if (!dataInicio.isBefore(dataFim)) {
			throw new PeriodoInvalidoException();
		}
	}

	private static void verificarHorarioElegivel(EspacoFisico espaco, LocalDateTime dataInicio, LocalDateTime dataFim) {
		Horario horaInicialDisponivel = espaco.getHorarioInicialDisponivel();
		Horario horaFinalDisponivel = espaco.getHorarioFinalDisponivel();
		Horario horaInicialSelecionada = new Horario(dataInicio.getHour(), dataInicio.getMinute());
		Horario horaFinalSelecionada = new Horario(dataFim.getHour(), dataFim.getMinute());

		if (!horaInicialSelecionada.isBetween(horaInicialDisponivel, horaFinalDisponivel, true)
				|| !horaFinalSelecionada.isBetween(horaInicialDisponivel, horaFinalDisponivel, true)) {
			throw new HorarioNaoElegivelException();
		}
	}

	private static void verificarLimiteDias(LocalDateTime dataInicio, LocalDateTime dataFim) {
		final int limiteDias = 1;

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

}
