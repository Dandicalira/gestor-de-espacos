package util;

import java.io.FileWriter;
import java.io.IOException;

public class ArquivoUtil {

    public static void salvarEmArquivo(String texto, String nomeArquivo) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(texto);
            System.out.println("Arquivo salvo com sucesso: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
