package util;

import javax.swing.text.*;
import java.util.regex.Pattern;

public class FiltroRegex extends DocumentFilter {
	private final Pattern pattern;

	public FiltroRegex(String regex) {
		this.pattern = Pattern.compile(regex);
	}

	// Só permite escrever no campo de texto seguindo uma expressão regular
	// Não sei como funciona

	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
		StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
		sb.insert(offset, string);

		if (pattern.matcher(sb.toString()).matches()) {
			super.insertString(fb, offset, string, attr);
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
		sb.replace(offset, offset + length, text);

		if (pattern.matcher(sb.toString()).matches()) {
			super.replace(fb, offset, length, text, attrs);
		}
	}

	// Predefinições

	public static String obterExpressaoRegular(String regex) {
		return switch (regex.toUpperCase()) {
			case "DATA" -> "^\\d{0,2}(/\\d{0,2}(/\\d{0,4})?)?$";
			case "HORARIO" -> "^\\d{0,2}(:\\d{0,2})?$";
			case "ALGARISMOS" -> "^\\d*$";
			default -> regex;
		};
	}
}
