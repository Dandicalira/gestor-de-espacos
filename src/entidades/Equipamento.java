package entidades;

public class Equipamento {

	//Atributos da Classe
	String nome;
	int quantidade;
	
	//Métodos Construtores
	public Equipamento() {
		
	}
	
	public Equipamento(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	//Métodos Getters e Setters
	public String getNome() {
		return nome;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%d unidades)", getNome(), getQuantidade());
	}
}
