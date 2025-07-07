package servicos.agendamento;

import entidades.EspacoFisico;
import entidades.Usuario;

import java.time.LocalDateTime;

public record Agendamento(LocalDateTime dataInicio, LocalDateTime dataFim, Usuario usuario, EspacoFisico espaco) {
	public boolean sobrepoe(LocalDateTime inicio, LocalDateTime fim) {
		return dataInicio.isBefore(fim) && dataFim.isAfter(inicio);
	}
}
