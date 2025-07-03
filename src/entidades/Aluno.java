package entidades;

public class Aluno extends Usuario{

	//Atributos da Classe
	private String curso;
	private int matricula,
				semestre;
	
	//Métodos Construtores
	public Aluno() {
		
	}
	
	public Aluno(String nome, String email, String senha, int telefone, String curso, int matricula, int semestre) {
		super(nome, email, senha, telefone);
		this.curso = curso;
		this.matricula = matricula;
		this.semestre = semestre;
	}
	
	//Métodos Getters e Setters
	public String getCurso() {
		return curso;
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public int getSemestre() {
		return semestre;
	}
	

}
