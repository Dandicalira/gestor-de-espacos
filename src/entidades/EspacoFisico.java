package entidades;

import servicos.agendamento.Agendamento;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class EspacoFisico {

	//Atributos da Classe
	private int capacidade;
	private String localizacao,
				   tipo; //Sala de Aula, Laboratório ou Sala de Estudos
	private LocalTime horarioInicialDisponivel,
				      horarioFinalDisponivel;
	private Equipamento[] equipamentos;
	private transient List<Agendamento> agendamentos = new ArrayList<>();

	//Métodos Construtores
	public EspacoFisico() {}

	public EspacoFisico(int capacidade, LocalTime horarioInicialDisponivel, LocalTime horarioFinalDisponivel, String localizacao, String tipo, Equipamento[] equipamentos) {
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
	
	public LocalTime getHorarioInicialDisponivel() {
		return horarioInicialDisponivel;
	}
	
	public LocalTime getHorarioFinalDisponivel() {
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

	public static Equipamento[] adicionarEquipamento(Equipamento equipamento, Equipamento[] equipamentos) {
		Equipamento[] temp = new Equipamento[equipamentos.length + 1];
		for (int i = 0; i < equipamentos.length; i++) {
			temp[i] = equipamentos[i];
		}
		temp[equipamentos.length] = equipamento;
		equipamentos = temp;
		return equipamentos;
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

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void adicionarAgendamento(Agendamento agendamento) {
		agendamentos.add(agendamento);
	}

	public void removerAgendamento(Agendamento agendamento) {
		agendamentos.remove(agendamento);
	}
	
	@Override
	public String toString() {
		String espaco = "------------------------------------------------------------\n"
				      + "                   Ficha do Espaço Físico\n"
				      + "------------------------------------------------------------\n";
		espaco += "Tipo         : " + getTipo() + "\n";
		espaco += "Localização  : " + getLocalizacao() + "\n";
		espaco += "Capacidade   : " + getCapacidade() + "\n";
		espaco += "Equipamentos : " + Arrays.toString(equipamentos) + "\n";
		espaco += "------------------------------------------------------------\n\n";
		return espaco;
	}

}
