package servicos.persistencia;

import servicos.persistencia.adaptadores.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entidades.*;
import servicos.agendamento.Agendamento;
import servicos.cadastro.Registro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersistenciaService {

    private static final String ARQUIVO_JSON = "dados.json";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new AdaptadorLocalDate())
            .registerTypeAdapter(LocalTime.class, new AdaptadorLocalTime())
            .registerTypeAdapter(LocalDateTime.class, new AdaptadorLocalDateTime())
            .create();

    /**
     * Classe interna para agrupar todos os dados que serão salvos em um único objeto JSON.
     */
    private static class DadosSalvos {
        List<Aluno> alunos = new ArrayList<>();
        List<Professor> professores = new ArrayList<>();
        List<Administrativo> administrativos = new ArrayList<>();
        List<EspacoFisico> salasDeAula = new ArrayList<>();
        List<EspacoFisico> laboratorios = new ArrayList<>();
        List<EspacoFisico> salasDeEstudos = new ArrayList<>();
        List<AgendamentoDTO> agendamentos = new ArrayList<>();
    }

    public static void salvarDados() {
        DadosSalvos dados = new DadosSalvos();

        // 1. Coletar todos os usuários e espaços
        dados.alunos = new ArrayList<>(Registro.getAlunos());
        dados.professores = Registro.getServidores().stream()
                .filter(s -> s instanceof Professor)
                .map(s -> (Professor) s)
                .collect(Collectors.toList());
        dados.administrativos = Registro.getServidores().stream()
                .filter(s -> s instanceof Administrativo)
                .map(s -> (Administrativo) s)
                .collect(Collectors.toList());

        dados.salasDeAula = new ArrayList<>(Registro.getSalasDeAula());
        dados.laboratorios = new ArrayList<>(Registro.getLaboratorios());
        dados.salasDeEstudos = new ArrayList<>(Registro.getSalasDeEstudos());

        // 2. Coletar todos os agendamentos e convertê-los para DTOs
        List<Usuario> todosUsuarios = Stream.concat(Registro.getAlunos().stream(), Registro.getServidores().stream())
                .collect(Collectors.toList());

        dados.agendamentos = todosUsuarios.stream()
                .flatMap(u -> u.getAgendamentos().stream())
                .distinct() // Evita duplicatas se o agendamento estiver em várias listas
                .map(ag -> new AgendamentoDTO(
                        ag.dataInicio(),
                        ag.dataFim(),
                        ag.usuario().getIdentificacao(),
                        ag.espaco().getLocalizacao()))
                .collect(Collectors.toList());


        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(dados, writer);
            System.out.println("Dados salvos com sucesso em " + ARQUIVO_JSON);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static void carregarDados() {
        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            Type tipoDados = new TypeToken<DadosSalvos>(){}.getType();
            DadosSalvos dados = gson.fromJson(reader, tipoDados);

            if (dados == null) return; // Arquivo vazio ou inválido

            // 1. Carregar usuários e espaços para o Registro
            dados.alunos.forEach(Registro::registrarAluno);
            dados.professores.forEach(Registro::registrarServidor);
            dados.administrativos.forEach(Registro::registrarServidor);
            dados.salasDeAula.forEach(Registro::registrarSalaDeAula);
            dados.laboratorios.forEach(Registro::registrarLaboratorio);
            dados.salasDeEstudos.forEach(Registro::registrarSalaDeEstudos);

            // 2. "Religar" os agendamentos
            List<Usuario> todosUsuarios = Stream.concat(Registro.getAlunos().stream(), Registro.getServidores().stream())
                                                 .collect(Collectors.toList());
            List<EspacoFisico> todosEspacos = Stream.of(
                    Registro.getSalasDeAula(), Registro.getLaboratorios(), Registro.getSalasDeEstudos())
                    .flatMap(List::stream).collect(Collectors.toList());

            dados.agendamentos.forEach(dto -> {
                // Encontra o usuário e o espaço correspondentes nos dados já carregados
                Usuario usuario = todosUsuarios.stream()
                        .filter(u -> u.getIdentificacao().equals(dto.usuarioId()))
                        .findFirst().orElse(null);

                EspacoFisico espaco = todosEspacos.stream()
                        .filter(e -> e.getLocalizacao().equals(dto.espacoId()))
                        .findFirst().orElse(null);

                if (usuario != null && espaco != null) {
                    // Recria o objeto Agendamento completo
                    Agendamento agendamento = new Agendamento(dto.dataInicio(), dto.dataFim(), usuario, espaco);
                    // Adiciona a referência do agendamento de volta ao usuário e ao espaço
                    usuario.adicionarAgendamento(agendamento);
                    espaco.adicionarAgendamento(agendamento);
                }
            });

            System.out.println("Dados carregados com sucesso de " + ARQUIVO_JSON);

        } catch (IOException e) {
            System.out.println("Arquivo de dados não encontrado. Iniciando com sistema vazio.");
        }
    }
}