package aplicacao;

import entidades.*;
import servicos.agendamento.AgendamentoParcial;
import servicos.agendamento.Agendar;
import servicos.agendamento.ListarAgendamentos;
import servicos.cadastro.Registro;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Teste {
	public static void criarTeste() {
		Aluno aluno = gerarAluno();
		Registro.registrarAluno(aluno);

		Professor professor = gerarProfessor();
		Registro.registrarServidor(professor);

		EspacoFisico sala = gerarSalaDeAula();
		Registro.registrarSalaDeAula(sala);

		EspacoFisico laboratorio = gerarLaboratorio();
		Registro.registrarLaboratorio(laboratorio);

		gerarAgendamento(professor, laboratorio);
	}

	public static void gerarAgendamento(Usuario usuario, EspacoFisico espaco) {
		LocalDateTime inicio = LocalDateTime.of(2025, 7, 10, 10, 0);
		LocalDateTime fim = LocalDateTime.of(2025, 7, 11, 8, 50);
		Agendar.validarAgendamento(usuario, inicio, fim, espaco);
	}

	public static Professor gerarProfessor() {
		return new Professor(
				"João Silva",
				"joao.silva@email.com",
				"123",
				"(61) 91234-5678",
				"1",
				"Engenharia de Software",
				"Professor"
		);
	}

	public static Aluno gerarAluno() {
		return new Aluno(
				"Pedro Veiga",
				"pedro.veiga@email.com",
				"123",
				"(62) 51224-4516",
				"Engenharia de Energia",
				"2",
				4
		);
	}

	static EspacoFisico gerarSalaDeAula() {
		Equipamento projetor = new Equipamento("Projetor", 1);
		Equipamento quadroBranco = new Equipamento("Quadro Branco", 2);
		Equipamento[] equipamentos = {projetor, quadroBranco};

		LocalTime inicio = LocalTime.of(8, 0);
		LocalTime fim = LocalTime.of(18, 0);

		return new EspacoFisico(
				30,    // capacidade
				inicio,          // horário inicial disponível
				fim,             // horário final disponível
				"S10",           // localização
				"Sala de Aula",  // tipo
				equipamentos     // equipamentos
		);
	}

	static EspacoFisico gerarLaboratorio() {
		Equipamento mesas = new Equipamento("Mesas", 7);
		Equipamento[] equipamentos = {mesas};

		LocalTime inicio = LocalTime.of(7, 0);
		LocalTime fim = LocalTime.of(16, 0);

		return new EspacoFisico(
				15,    // capacidade
				inicio,          // horário inicial disponível
				fim,             // horário final disponível
				"I9",            // localização
				"Laboratório",   // tipo
				equipamentos     // equipamentos
		);
	}
}
