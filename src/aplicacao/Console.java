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
				""");
	}

	protected  static void imprimirOpcoesAgendamento() {
		System.out.println("""
				Escolha uma opção:
				0 - Voltar
				1 - Listar histórico de agendamentos
				2 - Listar espaços físicos
				3 - Listar agendamentos de um espaço
				4 - Agendar espaço
				""");
	}

	protected  static void imprimirEspacosFisicos() {
		System.out.println("""
				Escolha uma opção para listar:
				0 - Voltar
				1 - Salas de aula
				2 - Laboratórios
				3 - Sala de estudos
				""");
	}
}
