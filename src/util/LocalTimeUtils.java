package util;

import java.time.LocalTime;

public class LocalTimeUtils {
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
