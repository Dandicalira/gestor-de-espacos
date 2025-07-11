package entidades;

public class Professor extends Servidor{

	//Atributos da Classe
	private String curso;
	private CargoAcademico cargoAcademico;
	
	//Métodos Construtores
	public Professor() {
		
	}
	
	public Professor(String nome, String email, String senha, String telefone, String matriculaInstitucional, String curso, CargoAcademico cargoAcademico) {
		super(nome, email, senha, telefone, matriculaInstitucional);
		this.curso = curso;
		this.cargoAcademico = cargoAcademico;
	}
	
	//Métodos Getters e Setters
	public String getCurso() {
		return curso;
	}
	
	public CargoAcademico getCargoAcademico() {
		return cargoAcademico;
	}
	
	@Override
	public String toString() {
		String professor = "------------------------------------------------------------\n"
				         + "                      Ficha do Professor\n"
				         + "------------------------------------------------------------\n";
		professor += "Nome                    : " + getNome() + "\n";
		professor += "Matrícula Institucional : " + getIdentificacao() + "\n";
		professor += "Email                   : " + getEmail() + "\n";
		professor += "Cargo Acadêmico         : " + getCargoAcademico() + "\n";
		professor += "------------------------------------------------------------\n\n";
		return professor;
	}
	
	public enum CargoAcademico {
		
		PROFESSORTITULAR,
		PROFESSORASSOCIADO,
		PROFESSORADJUNTO,
		PROFESSORASSISTENTE,
		PROFESSORAUXILIAR;
	}
	
	public static CargoAcademico obterCargo(String cargo) {
		
		switch (cargo) {
		
		case "Professor Titular":
			return CargoAcademico.PROFESSORTITULAR;
			
		case "Professor Associado":
			return CargoAcademico.PROFESSORASSOCIADO;
			
		case "Professor Adjunto":
			return CargoAcademico.PROFESSORADJUNTO;
			
		case "Professor Assistente":
			return CargoAcademico.PROFESSORASSISTENTE;
			
		case "Professor Auxiliar":
			return CargoAcademico.PROFESSORAUXILIAR;
		default:
			return CargoAcademico.PROFESSORTITULAR;
		}
	}
}

