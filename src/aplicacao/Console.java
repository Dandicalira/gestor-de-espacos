package aplicacao;

public class Console {
	protected static void imprimirMenuInicial() {
		System.out.println("""
				--- Sistema de Gestão de Espaços Físicos da Universidade ---
				Escolha um módulo para acessar:
				0 - Sair do sistema
				1 - Cadastro de Usuários e Espaços Físicos (senha de Superusuário necessária)
				2 - Login como Usuário para serviços como Agendamentos e Emissão de Relatório
				""");
	}

	protected  static void imprimirOpcoesCadastro() {
		System.out.println("""
				Escolha um tipo para cadastrar:
				0 - Voltar
				1 - Aluno
				2 - Professor
				3 - Técnico administrativo
				4 - Espaço físico
				""");	}
}
