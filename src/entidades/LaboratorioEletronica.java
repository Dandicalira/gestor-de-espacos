package entidades;

public class LaboratorioEletronica extends EspacoFisico{

	//Atributos da Classe
	private int bancos,
				mesas,
				protoboards,
				circuitosIntegrados,
				placasBasys3,
				LEDs,
				fontes5V,
				jumpers;
	
	//Métodos Construtores
	public LaboratorioEletronica() {
		
	}
	
	public LaboratorioEletronica(int capacidade, int horarioInicialDisponivel, int horarioFinalDisponivel, String localizacao,
			                     int bancos, int mesas, int protoboards, int circuitosIntegrados, int placasBasys3, int LEDs, int fontes5V, int jumpers) {
		super(capacidade, horarioInicialDisponivel, horarioFinalDisponivel, localizacao);
		this.bancos = bancos;
		this.mesas = mesas;
		this.protoboards = protoboards;
		this.circuitosIntegrados = circuitosIntegrados;
		this.placasBasys3 = placasBasys3;
		this.LEDs = LEDs;
		this.fontes5V = fontes5V;
		this.jumpers = jumpers;
	}
	
	//Métodos Getters e Setters
	public int getBancos() {
		return bancos;
	}
	
	public int getMesas() {
		return mesas;
	}
	
	public int getProtoboards() {
		return protoboards;
	}
	
	public int getCircuitosIntegrados() {
		return circuitosIntegrados;
	}
	
	public int getPlacasBasys3() {
		return placasBasys3;
	}
	
	public int getLEDs() {
		return LEDs;
	}
	
	public int getFontes5V() {
		return fontes5V;
	}
	
	public int getJumpers() {
		return jumpers;
	}
}

