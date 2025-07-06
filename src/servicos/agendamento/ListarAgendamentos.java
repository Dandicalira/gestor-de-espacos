package servicos.agendamento;

import entidades.EspacoFisico;
import util.LocalDateUtils;
import util.LocalTimeUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListarAgendamentos {

	public static List<AgendamentoParcial> obterAgendamentosParciaisData(EspacoFisico espaco, LocalDate dataAlvo) {
		List<AgendamentoParcial> agendamentosDia = new ArrayList<>();
		LocalTime horarioMinimo = espaco.getHorarioInicialDisponivel();
		LocalTime horarioMaximo = espaco.getHorarioFinalDisponivel();

		for (Agendamento agendamento : espaco.getAgendamentos()) {
			if (mesmoDia(agendamento, dataAlvo)) {
				LocalTime inicio = calcularInicioParcial(agendamento, dataAlvo, horarioMinimo);
				LocalTime fim = calcularFimParcial(agendamento, dataAlvo, horarioMaximo);

				agendamentosDia.add(new AgendamentoParcial(agendamento, inicio, fim));
			}
		}

		agendamentosDia.sort(Comparator.comparing(AgendamentoParcial::inicio));
		return agendamentosDia;
	}

	private static boolean mesmoDia(Agendamento agendamento, LocalDate data) {
		LocalDate inicio = agendamento.dataInicio().toLocalDate();
		LocalDate fim = agendamento.dataFim().toLocalDate();
		return LocalDateUtils.isBetween(data, inicio, fim, true);
	}

	private static LocalTime calcularInicioParcial(Agendamento agendamento, LocalDate dataAlvo, LocalTime horarioMinimo) {
		LocalDate inicio = agendamento.dataInicio().toLocalDate();
		if (dataAlvo.equals(inicio)) {
			return LocalTimeUtils.max(agendamento.dataInicio().toLocalTime(), horarioMinimo);
		}
		return horarioMinimo;
	}

	private static LocalTime calcularFimParcial(Agendamento agendamento, LocalDate dataAlvo, LocalTime horarioMaximo) {
		LocalDate fim = agendamento.dataFim().toLocalDate();
		if (dataAlvo.equals(fim)) {
			return LocalTimeUtils.min(agendamento.dataFim().toLocalTime(), horarioMaximo);
		}
		return horarioMaximo;
	}

}
