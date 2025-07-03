package entidades;

public class Professor extends Servidor{

	//Atributos da Classe
	private String curso,
				   cargoAcademico;
	
	//Métodos Construtores
	public Professor() {
		
	}
	
	public Professor(String nome, String email, String senha, int telefone, int matriculaInstitucional, String curso, String cargoAcademico) {
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
}

