package servicos.cadastro;
import entidades.*;
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
	
	public static ArrayList<EspacoFisico> getSalasDeAula() {
		return salasDeAula;
	}
	
	public static ArrayList<EspacoFisico> getLaboratorios() {
		return laboratorios;
	}
	
	public static ArrayList<EspacoFisico> getSalasDeEstudos() {
		return salasDeEstudos;
	}
	
	public static ArrayList<EspacoFisico> getTodosOsEspacos() {
		ArrayList<EspacoFisico> todos = new ArrayList<>();
		todos.addAll(salasDeAula);
		todos.addAll(laboratorios);
		todos.addAll(salasDeEstudos);
		return todos;
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
