package entidades;
public class EspacoFisico {

	//Atributos da Classe
	private int capacidade;
	private String localizacao,
				   tipo; //Sala de Aula, Laboratório ou Sala de Estudos
	Horario horarioInicialDisponivel,
			horarioFinalDisponivel;
	Equipamento[] equipamentos;
	
	//Métodos Construtores
	public EspacoFisico() {
		
	}
	
	public EspacoFisico(int capacidade, Horario horarioInicialDisponivel, Horario horarioFinalDisponivel, String localizacao, String tipo, Equipamento[] equipamentos) {
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
	
	public Horario getHorarioInicialDisponivel() {
		return horarioInicialDisponivel;
	}
	
	public Horario getHorarioFinalDisponivel() {
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

	public void adicionarEquipamento(Equipamento equipamento) {
		Equipamento[] temp = new Equipamento[equipamentos.length + 1];
		for (int i = 0; i < equipamentos.length; i++) {
			temp[i] = equipamentos[i];
		}
		temp[equipamentos.length] = equipamento;
		equipamentos = temp;
	}
	
	//Sobrescrita dos Métodos equals e hashCode
	@Override
	public boolean equals(Object objeto) {
		if (this == objeto) {
			return true;
		}
		if (objeto == null || getClass() != objeto.getClass()) {
			return false;
		}
		EspacoFisico espaco = (EspacoFisico) objeto;
		return localizacao.equals(espaco.localizacao);
	}
	
	@Override
	public int hashCode() {
		return localizacao.hashCode();
	}
}
