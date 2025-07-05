package servicos.cadastro;
import entidades.*;
import java.util.ArrayList;

public class Cadastrar {

	//Atributos da Classe
	private static ArrayList<Aluno> alunos = new ArrayList<>();
	private static ArrayList<Servidor> servidores = new ArrayList<>();
	private static ArrayList<EspacoFisico> salasDeAula = new ArrayList<>();
	private static ArrayList<EspacoFisico> laboratorios = new ArrayList<>();
	private static ArrayList<EspacoFisico> salasDeEstudos = new ArrayList<>();
	
	//Método Construtor Privado
	private Cadastrar() {
		
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
	
	//Cadastro de Entidades
	public static void cadastrarAluno(Aluno aluno) {
		alunos.add(aluno);
	}
	
	public static void cadastrarServidor(Servidor servidor) {
		servidores.add(servidor);
	}
	
	public static void cadastrarSalaDeAula(EspacoFisico salaDeAula) {
		salasDeAula.add(salaDeAula);
	}
	
	public static void cadastrarLaboratorio(EspacoFisico laboratorio) {
		laboratorios.add(laboratorio);
	}
	
	public static void cadastrarSalaDeEstudos(EspacoFisico salaDeEstudos) {
		salasDeEstudos.add(salaDeEstudos);
	}
	
	//Exclusão de Entidades
	public static void removerAluno(Aluno aluno) {
		alunos.remove(aluno);
	}
	
	public static void removerServidor(Servidor servidor) {
		servidores.remove(servidor);
	}
	
	public static void removerSalaDeAula(EspacoFisico salaDeAula) {
		salasDeAula.remove(salaDeAula);
	}
	
	public static void removerLaboratorio(EspacoFisico laboratorio) {
		laboratorios.remove(laboratorio);
	}
	
	public static void removerSalaDeEstudos(EspacoFisico salaDeEstudos) {
		salasDeEstudos.remove(salaDeEstudos);
	}
}
