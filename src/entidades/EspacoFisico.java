package entidades;

import servicos.agendamento.Agendamento;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import entidades.Professor.CargoAcademico;

import java.util.Arrays;

public class EspacoFisico {

	//Atributos da Classe
	private int capacidade;
	private String localizacao; //Sala de Aula, Laboratório ou Sala de Estudos
	private LocalTime horarioInicialDisponivel,
				      horarioFinalDisponivel;
	private List<Equipamento> equipamentos;
	private transient List<Agendamento> agendamentos = new ArrayList<>();
	private TipoDeEspaco tipo;

	//Métodos Construtores
	public EspacoFisico() {}

	public EspacoFisico(int capacidade, LocalTime horarioInicialDisponivel, LocalTime horarioFinalDisponivel, String localizacao, TipoDeEspaco tipo, List<Equipamento> equipamentos) {
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
	
	public TipoDeEspaco getTipo() {
		return tipo;
	}
	
	public List<Equipamento> getEquipamentos() {
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
		String espaco = """
				------------------------------------------------------------
				                   Ficha do Espaço Físico
				------------------------------------------------------------
				""";
		espaco += "Tipo: " + obterTipoDeEspaco(tipo) + "\n\n";
		espaco += "Localização: " + getLocalizacao() + "\n\n";
		espaco += "Capacidade: " + getCapacidade() + "\n\n";
		espaco += "Equipamentos: " + listarEquipamentos() + "\n";
		espaco += "------------------------------------------------------------\n";
		return espaco;
	}
	
	public enum TipoDeEspaco {
		SALADEAULA,
		LABORATORIO,
		SALADEESTUDOS;
	}

	public String listarEquipamentos() {
		if (equipamentos == null || equipamentos.isEmpty()) {
			return "Nenhum equipamento cadastrado.\n";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (int i = 0; i < equipamentos.size(); i++) {
			Equipamento eq = equipamentos.get(i);
			sb.append("  ").append(i + 1).append(". ").append(eq).append("\n");
		}
		return sb.toString();
	}

	public static TipoDeEspaco obterTipoDeEspaco(String tipoDeEspaco) {
		return switch (tipoDeEspaco) {
			case "Laboratório" -> TipoDeEspaco.LABORATORIO;
			case "Sala de Estudos" -> TipoDeEspaco.SALADEESTUDOS;
			default -> TipoDeEspaco.SALADEAULA;
		};
	}

	public static String obterTipoDeEspaco(TipoDeEspaco tipoDeEspaco) {
		return switch (tipoDeEspaco) {
			case LABORATORIO -> "Laboratório";
			case SALADEESTUDOS -> "Sala de Estudos";
			default -> "Sala de Aula";
		};
	}

}
