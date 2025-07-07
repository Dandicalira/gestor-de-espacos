package aplicacao;

public class Menu {
	
	public static void main(String[] args) {
		Menu a = new Menu();
		a.imprimirMenuInicial();
	}
	
	//Métodos Construtores
	public Menu() {
		
	}
	
	
	
	public void menuInicial() {
		
		imprimirMenuInicial();
	}
	
	private void imprimirMenuInicial() {
		
		System.out.print("--- Sistema de Gestão de Espaços Físicos da Universidade ---\n"
			       + "Escolha um módulo para acessá-lo:\n"
			       + "1 - Cadastro de Usuários e Espaços Físicos (senha de Superusuário necessária)\n"
			       + "2 - Login como Usuário para serviços como Agendamentos e Emissão de Relatório\n"
			       + "3 - Sair do sistema\n");
	}
}
