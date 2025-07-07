package entidades;

public class Professor extends Servidor{

	//Atributos da Classe
	private String curso,
				   cargoAcademico;
	
	//Métodos Construtores
	public Professor() {
		
	}
	
	public Professor(String nome, String email, String senha, String telefone, String matriculaInstitucional, String curso, String cargoAcademico) {
		super(nome, email, senha, telefone, matriculaInstitucional);
		this.curso = curso;
		this.cargoAcademico = cargoAcademico;
	}
	
	//Métodos Getters e Setters
	public String getCurso() {
		return curso;
	}
	
	public String getCargoAcademico() {
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
}

