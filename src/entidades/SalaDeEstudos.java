package entidades;

public class SalaDeEstudos extends EspacoFisico{

	//Atributos da Classe
	private int cadeiras,
				mesasRedondas,
				notebooks,
				livros,
				cabinesDeEstudo;
	
	//Métodos Construtores
	public SalaDeEstudos() {
		
	}
	
	public SalaDeEstudos(int capacidade, int horarioInicialDisponivel, int horarioFinalDisponivel, String localizacao,
			          int cadeiras, int mesasRedondas, int notebooks, int livros, int cabinesDeEstudo) {
		super(capacidade, horarioInicialDisponivel, horarioFinalDisponivel, localizacao);
		this.cadeiras = cadeiras;
		this.mesasRedondas = mesasRedondas;
		this.notebooks = notebooks;
		this.livros = livros;
		this.cabinesDeEstudo = cabinesDeEstudo;
	}
	
	//Métodos Getters e Setters
	public int getCadeiras() {
		return cadeiras;
	}
	
	public int getMesasRedondas() {
		return mesasRedondas;
	}
	
	public int getNotebooks() {
		return notebooks;
	}
	
	public int getLivros() {
		return livros;
	}
	
	public int getCabinesDeEstudo() {
		return cabinesDeEstudo;
	}
	
}
