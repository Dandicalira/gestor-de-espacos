package entidades;

public abstract class Servidor extends Usuario{
	
	//Atributos da Classe
	private int matriculaInstitucional;
	
	//Métodos Construtores
	public Servidor() {
		
	}
	
	public Servidor(String nome, String email, String senha, int telefone, int matriculaInstitucional) {
		super(nome, email, senha, telefone);
		this.matriculaInstitucional = matriculaInstitucional;
	}
	
	//Métodos Getters e Setters
	public int getMatriculaInstitucional() {
		return matriculaInstitucional;
	}

}
