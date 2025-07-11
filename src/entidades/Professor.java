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
		String professor = """
				------------------------------------------------------------
				                      Ficha do Professor
				------------------------------------------------------------
				""";
		professor += "Nome: " + getNome() + "\n \n";
		professor += "Matrícula Institucional: " + getIdentificacao() + "\n \n";
		professor += "Email: " + getEmail() + "\n \n";
		professor += "Cargo Acadêmico: " + obterCargo(cargoAcademico) + "\n";
		professor += "------------------------------------------------------------\n";
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

		return switch (cargo) {
			case "Professor Associado" -> CargoAcademico.PROFESSORASSOCIADO;
			case "Professor Adjunto" -> CargoAcademico.PROFESSORADJUNTO;
			case "Professor Assistente" -> CargoAcademico.PROFESSORASSISTENTE;
			case "Professor Auxiliar" -> CargoAcademico.PROFESSORAUXILIAR;
			default -> CargoAcademico.PROFESSORTITULAR;
		};
	}

	public static String obterCargo(CargoAcademico cargo) {

		return switch (cargo) {
			case PROFESSORASSOCIADO -> "Professor Associado";
			case PROFESSORADJUNTO -> "Professor Adjunto";
			case PROFESSORASSISTENTE -> "Professor Assistente";
			case PROFESSORAUXILIAR -> "Professor Auxiliar";
			default -> "Professor Titular";
		};
	}
}

