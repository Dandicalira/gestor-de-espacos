package entidades;

public abstract class Usuario {

	//Atributos da Classe
	private String nome,
			       email,
			       senha,
	               telefone;
	
	//Métodos Construtores
	public Usuario() {
		
	}
	
	public Usuario(String nome, String email, String senha, String telefone) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
	}
	
	//Métodos Getters e Setters
	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getTelefone() {
		return telefone;
	}
}
