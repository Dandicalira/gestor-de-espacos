package servicos.agendamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AgendamentoService {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

	public static LocalDate parseLocalDate(String dataStr) {
		try {
			return LocalDate.parse(dataStr, DATE_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Data inválida. Use o formato DD/MM/YYYY.");
		}
	}

	public static LocalTime parseLocalTime(String horaStr) {
		try {
			return LocalTime.parse(horaStr, TIME_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Horário inválido. Use o formato HH:mm.");
		}
	}

	public static LocalDateTime combinarDataEHora(LocalDate data, LocalTime hora) {
		return LocalDateTime.of(data, hora);
	}
}
