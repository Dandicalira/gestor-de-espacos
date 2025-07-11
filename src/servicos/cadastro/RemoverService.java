package servicos.cadastro;
import entidades.*;
import excecoes.VoltarException;
import excecoes.EntidadeInexistenteException;
public class RemoverService {

	//Método Construtor Privado
	private RemoverService() {
		
	}
	
	public static void removerAluno() {
		
		imprimirAlunos();
		String matricula = "";
		while (true) {
			try {
				System.out.print("Digite a matrícula do aluno a ser removido (0 para voltar): ");
				//matricula = EntradaDeDados.lerString();
				if (matricula.equals("0")) {
					throw new VoltarException();
				}
				Aluno aluno = buscarAluno(matricula);
				if (aluno == null) {
					throw new EntidadeInexistenteException("A matrícula informada não corresponde a nenhum aluno. Por favor, tente novamente\n");
				}
				else {
					Registro.excluirAluno(aluno);
					System.out.println("O aluno foi removido com êxito!");
					break;
				}
			} catch(Exception e) {
				throw e;
			}
		}
	}
	
	public static void removerServidor() {
		
		imprimirServidores();
		String matriculaInstitucional = "";
		while (true) {
			try {
				System.out.print("Digite a matrícula do servidor a ser removida (0 para voltar): ");
				//matriculaInstitucional = EntradaDeDados.lerString();
				if (matriculaInstitucional.equals("0")) {
					throw new VoltarException();
				}
				Servidor servidor = buscarServidor(matriculaInstitucional);
				if (servidor == null) {
					throw new EntidadeInexistenteException("A matrícula institucional informada não corresponde a nenhum servidor. Por favor, tente novamente\n");
				}
				else {
					Registro.excluirServidor(servidor);
					System.out.println("O servidor foi removido com êxito!");
					break;
				}
			} catch(Exception e) {
				throw e;
			}
		}
	}
	
	public static void removerEspacoFisico() {
		
		imprimirEspacosFisicos();
		String localizacao = "";
		while (true) {
			try {
				System.out.print("Digite a localização exata do espaço a ser removido (0 para voltar): ");
				//localizacao = EntradaDeDados.lerString();
				if (localizacao.equals("0")) {
					throw new VoltarException();
				}
				EspacoFisico espaco = buscarEspacoFisico(localizacao);
				if (espaco == null) {
					throw new EntidadeInexistenteException("A localização não corresponde a nenhum espaço físico cadastrado no sistema. Por favor, tente novamente\n");
				}
				else {
					switch (espaco.getTipo()) {
					case EspacoFisico.TipoDeEspaco.SALADEAULA:
						Registro.excluirSalaDeAula(espaco);
						break;
					case EspacoFisico.TipoDeEspaco.LABORATORIO:
						Registro.excluirLaboratorio(espaco);
						break;
					case EspacoFisico.TipoDeEspaco.SALADEESTUDOS:
						Registro.excluirSalaDeEstudos(espaco);
						break;
				}
					break;
				}
			} catch(Exception e) {
				throw e;
			}
		}
	}
	
	public static void imprimirAlunos() {
		
		if (Registro.getAlunos().isEmpty() ) {
			System.out.print("------------------------------------------------------------------\n"
					         + "                    Nenhum aluno cadastrado!\n"
					         + "------------------------------------------------------------------\n");
		}
		else {
			for (Aluno aluno : Registro.getAlunos()) {
				System.out.print(aluno.toString());
			}
		}
		
	}
	
	public static void imprimirServidores() {
		
		if (Registro.getServidores().isEmpty() ) {
			System.out.print("------------------------------------------------------------------\n"
					         + "                   Nenhum servidor cadastrado!\n"
					         + "------------------------------------------------------------------\n");
		}
		else {
			for (Servidor servidor : Registro.getServidores()) {
				System.out.print(servidor.toString());
			}
		}
		
	}
	
	public static void imprimirEspacosFisicos() {
		
		if (Registro.getSalasDeAula().isEmpty()) {
			System.out.print("------------------------------------------------------------------\n"
			               + "               Nenhuma sala de aula cadastrada\n"
			               + "------------------------------------------------------------------\n");
		}
		else {
			for (EspacoFisico salaDeAula : Registro.getSalasDeAula()) {
				System.out.print(salaDeAula.toString());
			}
		}
		
		if (Registro.getLaboratorios().isEmpty()) {
			System.out.print("------------------------------------------------------------------\n"
			               + "               Nenhum laboratório cadastrado!\n"
			               + "------------------------------------------------------------------\n");
		}
		else {
			for (EspacoFisico laboratorio : Registro.getLaboratorios()) {
				System.out.print(laboratorio.toString());
			}
		}
		
		if (Registro.getSalasDeEstudos().isEmpty()) {
			System.out.print("------------------------------------------------------------------\n"
			               + "               Nenhuma sala de estudos cadastrada!\n"
			               + "------------------------------------------------------------------\n");
		}
		else {
			for (EspacoFisico salaDeEstudos : Registro.getSalasDeEstudos()) {
				System.out.print(salaDeEstudos.toString());
			}
		}
	}
	
	public static Aluno buscarAluno(String matricula) {
		for (Aluno aluno : Registro.getAlunos()) {
			if (matricula.equals(aluno.getIdentificacao())) {
				return aluno;
			}
		}
		return null;
	}
	
	public static Servidor buscarServidor(String matriculaInstitucional) {
		for (Servidor servidor : Registro.getServidores()) {
			if (matriculaInstitucional.equals(servidor.getIdentificacao())) {
				return servidor;
			}
		}
		return null;
	}
	
	public static EspacoFisico buscarEspacoFisico(String localizacao) {
		for (EspacoFisico espaco : Registro.getSalasDeAula()) {
			if (localizacao.equals(espaco.getLocalizacao())) {
				return espaco;
			}
		}
		for (EspacoFisico espaco : Registro.getLaboratorios()) {
			if (localizacao.equals(espaco.getLocalizacao())) {
				return espaco;
			}
		}
		for (EspacoFisico espaco : Registro.getSalasDeEstudos()) {
			if (localizacao.equals(espaco.getLocalizacao())) {
				return espaco;
			}
		}
		return null;
	}
}
