package entidades;

public abstract class Servidor extends Usuario{
	
	//Atributos da Classe
	private String matriculaInstitucional;
	
	//Métodos Construtores
	public Servidor() {
		
	}
	
	public Servidor(String nome, String email, String senha, String telefone, String matriculaInstitucional) {
		super(nome, email, senha, telefone);
		this.matriculaInstitucional = matriculaInstitucional;
	}
	
	//Métodos Getters e Setters
	public String getMatriculaInstitucional() {
		return matriculaInstitucional;
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
		Servidor servidor = (Servidor) objeto;
		return matriculaInstitucional == servidor.matriculaInstitucional;
	}
	
	@Override
	public int hashCode() {
		return matriculaInstitucional.hashCode();
	}
}
