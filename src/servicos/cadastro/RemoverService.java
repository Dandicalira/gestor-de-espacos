package servicos.cadastro;
import entidades.*;
import excecoes.VoltarException;
import excecoes.EntidadeInexistenteException;
public class RemoverService {

	//Método Construtor Privado
	private RemoverService() {
		
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
	
	public static void removerEspacoFisico(EspacoFisico espaco) {
		switch (espaco.getTipo()) {
		case SALADEAULA:
			Registro.excluirSalaDeAula(espaco);
			break;
		case LABORATORIO:
			Registro.excluirLaboratorio(espaco);
			break;
		case SALADEESTUDOS:
			Registro.excluirSalaDeEstudos(espaco);
			break;
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
