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
}
