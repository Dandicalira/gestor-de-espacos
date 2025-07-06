package servicos.agendamento;

import entidades.EspacoFisico;
import entidades.Usuario;

import java.time.LocalDateTime;

public class Agendamento {

	private final LocalDateTime dataInicio;
	private final LocalDateTime dataFim;
	private final Usuario usuario;
	private final EspacoFisico espaco;

	public Agendamento(LocalDateTime dataInicio, LocalDateTime dataFim, Usuario usuario, EspacoFisico espaco) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.usuario = usuario;
		this.espaco = espaco;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public EspacoFisico getEspaco() {
		return espaco;
	}

	public boolean sobrepoe(LocalDateTime inicio, LocalDateTime fim) {
		return !fim.isBefore(this.dataInicio) && !inicio.isAfter(this.dataFim);
	}
}
