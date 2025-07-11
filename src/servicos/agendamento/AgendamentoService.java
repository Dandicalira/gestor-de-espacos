package servicos.agendamento;

import entidades.EspacoFisico;
import entidades.Usuario;
import excecoes.PeriodoInvalidoException;
import util.LocalDateUtils;
import util.LocalTimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AgendamentoService {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

	public static LocalDate parseLocalDate(String data) {
		try {
			return LocalDate.parse(data, DATE_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Data inválida. Use o formato DD/MM/YYYY.");
		}
	}

	public static LocalTime parseLocalTime(String horario) {
		try {
			return LocalTime.parse(horario, TIME_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Horário inválido. Use o formato HH:mm.");
		}
	}

	public static LocalDateTime combinarDataEHora(LocalDate data, LocalTime hora) {
		return LocalDateTime.of(data, hora);
	}

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

	public static String formatarAgendamentosUsuario(Usuario usuario) {
		StringBuilder sb = new StringBuilder();
		List<Agendamento> agendamentos = usuario.getAgendamentos();

		if (agendamentos.isEmpty()) {
			return "Nenhum agendamento encontrado";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		sb.append("------------------------\n");

		for (Agendamento agendamento : agendamentos) {
			EspacoFisico.TipoDeEspaco tipo = agendamento.espaco().getTipo();
			String localizacao = agendamento.espaco().getLocalizacao();
			String inicio = agendamento.dataInicio().format(formatter);
			String fim = agendamento.dataFim().format(formatter);

			sb.append(tipo).append(": ").append(localizacao).append("\n");
			sb.append("Início: ").append(inicio).append("\n");
			sb.append("Fim:    ").append(fim).append("\n");
			sb.append("------------------------\n");
		}
		sb.append("\n");

		return sb.toString();
	}

	public static String formatarAgendamentosEspaco(EspacoFisico espaco, LocalDate dataInicio, LocalDate dataFim) {
		StringBuilder sb = new StringBuilder();

		validarPeriodo(dataInicio, dataFim);

		sb.append("Período de agendamento: [")
		    .append(espaco.getHorarioInicialDisponivel())
		    .append(" - ")
		    .append(espaco.getHorarioFinalDisponivel())
		  .append("]\n\n");

		if (dataInicio != null && Objects.equals(dataInicio, dataFim)) { // Mesma data
			sb.append(formatarAgendamentosParciaisData(espaco, dataInicio));
			return sb.toString();
		}

		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		List<Agendamento> agendamentos = espaco.getAgendamentos();
		agendamentos.sort(Comparator.comparing(Agendamento::dataInicio));

		if (agendamentos.isEmpty()) {
			sb.append("Nenhum agendamento encontrado.");
			return sb.toString();
		}

		boolean encontrouAgendamento = false;

		for (Agendamento agendamento : agendamentos) {
			LocalDate agendamentoInicio = agendamento.dataInicio().toLocalDate();
			LocalDate agendamentoFim = agendamento.dataFim().toLocalDate();

			if (datasSobrepoem(dataInicio, dataFim, agendamentoInicio, agendamentoFim)) {
				if (!encontrouAgendamento) sb.append("------------------------\n");

				sb.append("Usuário: ").append(agendamento.usuario().getNome()).append("\n");
				sb.append("Início: ").append(agendamento.dataInicio().format(formatador)).append("\n");
				sb.append("Fim: ").append(agendamento.dataFim().format(formatador)).append("\n");
				sb.append("------------------------\n");
				encontrouAgendamento = true;
			}
		}
		if (!encontrouAgendamento) {
			sb.append("Nenhum agendamento encontrado.\n");
		}

		sb.append("\n");

		return sb.toString();
	}

	private static String formatarAgendamentosParciaisData(EspacoFisico espaco, LocalDate data) {
		StringBuilder sb = new StringBuilder();

		List<AgendamentoParcial> agendamentosDia = obterAgendamentosParciaisData(espaco, data);

		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		if (agendamentosDia.isEmpty()) {
			sb.append("Nenhum agendamento encontrado.");
			return sb.toString();
		}

		sb.append("Agendamentos no dia ").append(data.format(formatadorData)).append(":\n------------------------\n");
		for (AgendamentoParcial parcial : agendamentosDia) {
			sb.append("Nome: ").append(parcial.agendamento().usuario().getNome()).append("\n");
			sb.append("Horário: ").append(parcial.inicio()).append(" até ").append(parcial.fim()).append("\n");
			sb.append("------------------------\n");
		}

		return sb.toString();
	}

	private static boolean datasSobrepoem(LocalDate filtroInicio, LocalDate filtroFim, LocalDate agendamentoInicio, LocalDate agendamentoFim) {
		if (filtroInicio != null && agendamentoFim.isBefore(filtroInicio)) {
			return false;
		}
		return filtroFim == null || !agendamentoInicio.isAfter(filtroFim);
	}

	private static void validarPeriodo(LocalDate dataInicio, LocalDate dataFim) {
		if (dataInicio != null && dataFim != null && dataInicio.isAfter(dataFim)) {
			throw new PeriodoInvalidoException();
		}
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
