package entidades;

public class SalaDeAula extends EspacoFisico{

	//Atributos da Classe
	private int carteiras,
				lousas,
				projetores,
				pinceis,
				apagadores;
	
	//Métodos Construtores
	public SalaDeAula() {
		
	}
	
	public SalaDeAula(int capacidade, int horarioInicialDisponivel, int horarioFinalDisponivel, String localizacao,
			          int carteiras, int lousas, int projetores, int pinceis, int apagadores) {
		super(capacidade, horarioInicialDisponivel, horarioFinalDisponivel, localizacao);
		this.carteiras = carteiras;
		this.lousas = lousas;
		this.projetores = projetores;
		this.pinceis = pinceis;
		this.apagadores = apagadores;
	}
	
	//Métodos Getters e Setters
	public int getCarteiras() {
		return carteiras;
	}
	
	public int getLousas() {
		return lousas;
	}
	
	public int getProjetores() {
		return projetores;
	}
	
	public int getPinceis() {
		return pinceis;
	}
	
	public int getApagadores() {
		return apagadores;
	}
	
}
