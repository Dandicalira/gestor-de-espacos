package entidades;

public record Horario(int hora, int minuto) {

	public boolean isBefore(Horario outro) {
		return (this.hora < outro.hora) || (this.hora == outro.hora) && (this.minuto < outro.minuto);
	}

	public boolean isAfter(Horario outro) {
		return (this.hora > outro.hora) || (this.hora == outro.hora) && (this.minuto > outro.minuto);
	}

	public boolean equals(Horario outro) {
		return (this.hora == outro.hora) && (this.minuto == outro.minuto);
	}

	public boolean isBetween(Horario inicio, Horario fim, boolean inclusivo) {
		if (inclusivo) {
			return (!this.isBefore(inicio) && !this.isAfter(fim)); // [inicio, fim]
		}
		return (this.isAfter(inicio) && this.isBefore(fim)); // (inicio, fim)
	}

}
