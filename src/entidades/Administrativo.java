package entidades;

public class Administrativo extends Servidor{

	//Atributos da Classe
	private String cargo,
				   departamento;
	
	//Métodos Construtores
	public Administrativo() {
		
	}
	
	public Administrativo(String nome, String email, String senha, String telefone, String matriculaInstitucional, String cargo, String departamento) {
		super(nome, email, senha, telefone, matriculaInstitucional);
		this.cargo = cargo;
		this.departamento = departamento;
	}
	
	//Métodos Getters e Setters
	public String getCargo() {
		return cargo;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	@Override
	public String toString() {
		String administrativo = """
				------------------------------------------------------------
				               Ficha do Técnico-Administrativo
				------------------------------------------------------------
				""";
		administrativo += "Nome: " + getNome() + "\n\n";
		administrativo += "Matrícula Institucional : " + getIdentificacao() + "\n\n";
		administrativo += "Email: " + getEmail() + "\n\n";
		administrativo += "Cargo: " + getCargo() + "\n\n";
		administrativo += "Departamento: " + getDepartamento() + "\n";
		administrativo += "------------------------------------------------------------\n";
		return administrativo;
	}
}
