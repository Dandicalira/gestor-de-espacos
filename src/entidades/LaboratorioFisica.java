package entidades;

public class LaboratorioFisica extends EspacoFisico{

	//Atributos da Classe
	private int bancos,
				mesas,
				trilhosDeAr,
				carrinhos,
				polias,
				paquimetros,
				balancas;
	
	//Métodos Construtores
	public LaboratorioFisica() {
		
	}
	
	public LaboratorioFisica(int capacidade, int horarioInicialDisponivel, int horarioFinalDisponivel, String localizacao,
			                 int bancos, int mesas, int trilhosDeAr, int carrinhos, int polias, int paquimetros, int balancas) {
		super(capacidade, horarioInicialDisponivel, horarioFinalDisponivel, localizacao);
		this.bancos = bancos;
		this.mesas = mesas;
		this.trilhosDeAr = trilhosDeAr;
		this.carrinhos = carrinhos;
		this.polias = polias;
		this.paquimetros = paquimetros;
		this.balancas = balancas;
	}
	
	//Métodos Getters e Setters
	public int getBancos() {
		return bancos;
	}
	
	public int getMesas() {
		return mesas;
	}
	
	public int getTrilhosDeAr() {
		return trilhosDeAr;
	}
	
	public int getCarrinhos() {
		return carrinhos;
	}
	
	public int getPolias() {
		return polias;
	}
	
	public int getPaquimetros() {
		return paquimetros;
	}
	
	public int getBalancas() {
		return balancas;
	}
}

