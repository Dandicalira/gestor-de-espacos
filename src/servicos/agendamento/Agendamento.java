package servicos.agendamento;

import entidades.Usuario;

import java.time.LocalDateTime;

public class Agendamento {

	LocalDateTime dataInicio,
			      dataFim;
	Usuario usuario;
	int id;

	public Agendamento(LocalDateTime dataInicio, LocalDateTime dataFim, Usuario usuario) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.usuario = usuario;
		generateId();
	}

	private void generateId() {
		// Deixa somente os números
		id = Integer.parseInt(("" + dataInicio + dataFim).replaceAll("[^0-9]", ""));
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public int getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
