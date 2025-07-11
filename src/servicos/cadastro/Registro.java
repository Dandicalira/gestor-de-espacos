package servicos.cadastro;
import entidades.*;
import excecoes.EntidadeInexistenteException;

import java.util.ArrayList;

public class Registro {

	//Atributos da Classe
	private static ArrayList<Aluno> alunos = new ArrayList<>();
	private static ArrayList<Servidor> servidores = new ArrayList<>();
	private static ArrayList<EspacoFisico> salasDeAula = new ArrayList<>();
	private static ArrayList<EspacoFisico> laboratorios = new ArrayList<>();
	private static ArrayList<EspacoFisico> salasDeEstudos = new ArrayList<>();
	
	//Método Construtor Privado
	private Registro() {
		
	}
	
	//Métodos Getters e Setters
	public static ArrayList<Aluno> getAlunos() {
		return alunos;
	}
	
	public static ArrayList<Servidor> getServidores() {
		return servidores;
	}

	public static ArrayList<Professor> getProfessores() {
		ArrayList<Professor> professores = new ArrayList<>();

		for (Servidor servidor : servidores) {
			if (servidor instanceof Professor professor) {
				professores.add(professor);
			}
		}
		return professores;
	}

	public static ArrayList<Administrativo> getAdministrativos() {
		ArrayList<Administrativo> administrativos = new ArrayList<>();

		for (Servidor servidor : servidores) {
			if (servidor instanceof Administrativo administrativo) {
				administrativos.add(administrativo);
			}
		}
		return administrativos;
	}
	
	public static ArrayList<EspacoFisico> getSalasDeAula() {
		return salasDeAula;
	}
	
	public static ArrayList<EspacoFisico> getLaboratorios() {
		return laboratorios;
	}
	
	public static ArrayList<EspacoFisico> getSalasDeEstudos() {
		return salasDeEstudos;
	}
	
	public static ArrayList<EspacoFisico> getEspacosFisicos() {
		ArrayList<EspacoFisico> todos = new ArrayList<>();
		todos.addAll(salasDeAula);
		todos.addAll(laboratorios);
		todos.addAll(salasDeEstudos);
		return todos;
	}

	public static ArrayList<Usuario> getUsuarios() {
		ArrayList<Usuario> todos = new ArrayList<>();
		todos.addAll(alunos);
		todos.addAll(servidores);
		return todos;
	}

	public static EspacoFisico obterEspacoFisicoLocalizacao(String localizacao) {
		ArrayList<EspacoFisico> espacos = getEspacosFisicos();
		for (EspacoFisico espaco : espacos) {
			if (espaco.getLocalizacao().equals(localizacao)) {
				return espaco;
			}
		}

		throw new EntidadeInexistenteException("O espaço físico informado não existe");
	}
	
	//Registro de Entidades
	public static void registrarAluno(Aluno aluno) {
		alunos.add(aluno);
	}
	
	public static void registrarServidor(Servidor servidor) {
		servidores.add(servidor);
	}
	
	public static void registrarSalaDeAula(EspacoFisico salaDeAula) {
		salasDeAula.add(salaDeAula);
	}
	
	public static void registrarLaboratorio(EspacoFisico laboratorio) {
		laboratorios.add(laboratorio);
	}
	
	public static void registrarSalaDeEstudos(EspacoFisico salaDeEstudos) {
		salasDeEstudos.add(salaDeEstudos);
	}
	
	//Exclusão de Entidades
	public static void excluirAluno(Aluno aluno) {
		alunos.remove(aluno);
	}
	
	public static void excluirServidor(Servidor servidor) {
		servidores.remove(servidor);
	}
	
	public static void excluirSalaDeAula(EspacoFisico salaDeAula) {
		salasDeAula.remove(salaDeAula);
	}
	
	public static void excluirLaboratorio(EspacoFisico laboratorio) {
		laboratorios.remove(laboratorio);
	}
	
	public static void excluirSalaDeEstudos(EspacoFisico salaDeEstudos) {
		salasDeEstudos.remove(salaDeEstudos);
	}
}
