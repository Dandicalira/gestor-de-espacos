package entidades;

public class Horario {

	//Atributos da Classe
	int hora;
	int minuto;

	//Métodos Construtores
	public Horario() {

	}

	public Horario(int hora, int minuto) {
		this.hora = hora;
		this.minuto = minuto;
	}

	//Métodos Getters e Setters
	public int getHora() {
		return hora;
	}

	public int getMinuto() {
		return minuto;
	}

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
