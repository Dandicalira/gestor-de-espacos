package util;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class DadoTransferivel implements Transferable {

	// Coloca um identificador do programa nos textos copiados para a área de trasferência
	// Não tenho a menor ideia de como isso funciona

	private final String texto;
	private static final String IDENTIFICADOR_PADRAO = "GestorDeEspacos";

	public static final DataFlavor IDENTIFICADOR_FLAVOR = new DataFlavor(String.class, "Identificador");

	private final DataFlavor[] flavors = { DataFlavor.stringFlavor, IDENTIFICADOR_FLAVOR };

	public DadoTransferivel(String texto) {
		this.texto = texto;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		for (DataFlavor f : flavors) {
			if (f.equals(flavor)) return true;
		}
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (flavor.equals(DataFlavor.stringFlavor)) {
			return texto;
		} else if (flavor.equals(IDENTIFICADOR_FLAVOR)) {
			return "GestorDeEspacos";
		} else {
			throw new UnsupportedFlavorException(flavor);
		}
	}

	public static String lerTextoSeIdentificadorValido(String IDENTIFICADOR) {
		try {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

			// Verifica se o DataFlavor customizado está disponível
			if (clipboard.isDataFlavorAvailable(DadoTransferivel.IDENTIFICADOR_FLAVOR)) {
				String identificadorNoClipboard = (String) clipboard.getData(DadoTransferivel.IDENTIFICADOR_FLAVOR);

				// Verifica se o Identificador é o mesmo
				if (IDENTIFICADOR.equals(identificadorNoClipboard)) {
					if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
						return (String) clipboard.getData(DataFlavor.stringFlavor);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // nada válido encontrado
	}
}
