package servicos.persistencia;

import java.time.LocalDateTime;

/**
 * DTO para quebrar a referência circular ao salvar Agendamentos.
 * Armazena o ID do usuário e do espaço em vez dos objetos completos.
 */
public record AgendamentoDTO(
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        String usuarioId,
        String espacoId) {
}