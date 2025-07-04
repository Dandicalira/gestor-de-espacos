package entidades;

public class EspacoFisico {

	//Atributos da Classe
	private int capacidade,
	            horarioInicialDisponivel,
	            horarioFinalDisponivel;
	private String localizacao,
				   tipo; //Sala de Aula, Laboratório ou Sala de Estudos
	Equipamento[] equipamentos;
	
	//Métodos Construtores
	public EspacoFisico() {
		
	}
	
	public EspacoFisico(int capacidade, int horarioInicialDisponivel, int horarioFinalDisponivel, String localizacao, String tipo, Equipamento[] equipamentos) {
		this.capacidade = capacidade;
		this.horarioInicialDisponivel = horarioInicialDisponivel;
		this.horarioFinalDisponivel = horarioFinalDisponivel;
		this.localizacao = localizacao;
		this.tipo = tipo;
		this.equipamentos = equipamentos;
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
	
	public String getTipo() {
		return tipo;
	}
	
	public Equipamento[] getEquipamentos() {
		return equipamentos;
	}
}
