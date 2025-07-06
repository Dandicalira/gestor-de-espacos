package util;

import java.time.LocalDate;

public class LocalDateUtils {
	public static boolean isBetween(LocalDate alvo, LocalDate inicio, LocalDate fim, boolean inclusivo) {
		if (inclusivo) {
			return !alvo.isBefore(inicio) && !alvo.isAfter(fim); // [inicio, fim]
		} else {
			return alvo.isAfter(inicio) && alvo.isBefore(fim);  // (inicio, fim)
		}
	}
}
