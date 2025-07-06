package entidades;

public class Aluno extends Usuario{

	//Atributos da Classe
	private String curso,
	               matricula;
	private int	semestre;
	
	//Métodos Construtores
	public Aluno() {
		
	}
	
	public Aluno(String nome, String email, String senha, String telefone, String curso, String matricula, int semestre) {
		super(nome, email, senha, telefone);
		this.curso = curso;
		this.matricula = matricula;
		this.semestre = semestre;
	}
	
	//Métodos Getters e Setters
	public String getCurso() {
		return curso;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public int getSemestre() {
		return semestre;
	}
	
	//Sobrescrita dos Métodos equals e hashCode
	@Override
	public boolean equals(Object objeto) {
		if (this == objeto) {
			return true;
		}
		if (objeto == null || getClass() != objeto.getClass()) {
			return false;
		}
		Aluno aluno = (Aluno) objeto;
		return matricula.equals(aluno.matricula);
	}
	
	@Override
	public int hashCode() {
		return matricula.hashCode();
	}

	public String getIdentificacao() {
		return getMatricula();
	}
}
