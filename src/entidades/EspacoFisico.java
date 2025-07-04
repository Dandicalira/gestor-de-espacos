package entidades;

public abstract class EspacoFisico {

	//Atributos da Classe
	private int capacidade,
	            horarioInicialDisponivel,
	            horarioFinalDisponivel;
	private String localizacao;
	
	//Métodos Construtores
	public EspacoFisico() {
		
	}
	
	public EspacoFisico(int capacidade, int horarioInicialDisponivel, int horarioFinalDisponivel, String localizacao) {
		this.capacidade = capacidade;
		this.horarioInicialDisponivel = horarioInicialDisponivel;
		this.horarioFinalDisponivel = horarioFinalDisponivel;
		this.localizacao = localizacao;
	}
	
	//Métodos Getters e Setters
	public int getCapacidade() {
		return capacidade;
	}
	
	public int getHorarioInicialDisponivel() {
		return horarioInicialDisponivel;
	}
	
	public int getHorarioFinalDisponivel() {
		return horarioFinalDisponivel;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}

}
