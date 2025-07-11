package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeUtils {
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
	
	public static boolean isBetween(LocalDate alvo, LocalDate inicio, LocalDate fim, boolean inclusivo) {
		if (inclusivo) {
			return !alvo.isBefore(inicio) && !alvo.isAfter(fim); // [inicio, fim]
		} else {
			return alvo.isAfter(inicio) && alvo.isBefore(fim);  // (inicio, fim)
		}
	}

	public static boolean isBetween(LocalTime alvo, LocalTime inicio, LocalTime fim, boolean inclusivo) {
		if (inclusivo) {
			return !alvo.isBefore(inicio) && !alvo.isAfter(fim); // [inicio, fim]
		} else {
			return alvo.isAfter(inicio) && alvo.isBefore(fim);  // (inicio, fim)
		}
	}

	public static LocalTime max(LocalTime a, LocalTime b) {
		return a.isAfter(b) ? a : b;
	}

	public static LocalTime min(LocalTime a, LocalTime b) {
		return a.isBefore(b) ? a : b;
	}
}
