package aplicacao;

import entidades.*;
import servicos.agendamento.Agendar;
import servicos.cadastro.Registro;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Teste {
	static int ano = 2025;
	public static void criarTeste() {
		Aluno aluno = gerarAluno();
		Registro.registrarAluno(aluno);

		Professor professor = gerarProfessor();
		Registro.registrarServidor(professor);

		EspacoFisico s1 = gerarSalaDeAula1();
		Registro.registrarSalaDeAula(s1);
		gerarAgendamento(professor, s1);

		EspacoFisico s2 = gerarSalaDeAula2();
		Registro.registrarSalaDeAula(s2);
		gerarAgendamento(professor, s2);

		EspacoFisico s3 = gerarSalaDeAula3();
		Registro.registrarSalaDeAula(s3);
		gerarAgendamento(professor, s3);

		EspacoFisico s4 = gerarSalaDeAula4();
		Registro.registrarSalaDeAula(s4);
		gerarAgendamento(professor, s4);

		EspacoFisico s5 = gerarSalaDeAula5();
		Registro.registrarSalaDeAula(s5);

		EspacoFisico s6 = gerarSalaDeAula6();
		Registro.registrarSalaDeAula(s6);

		EspacoFisico s7 = gerarSalaDeAula7();
		Registro.registrarSalaDeAula(s7);

		EspacoFisico s8 = gerarSalaDeAula8();
		Registro.registrarSalaDeAula(s8);

		EspacoFisico s9 = gerarSalaDeAula9();
		Registro.registrarSalaDeAula(s9);

		EspacoFisico s10 = gerarSalaDeAula10();
		Registro.registrarSalaDeAula(s10);

		EspacoFisico laboratorio = gerarLaboratorio();
		Registro.registrarLaboratorio(laboratorio);

		gerarAgendamento2(professor, laboratorio);

		gerarAgendamento(aluno, laboratorio);
	}

	public static void gerarAgendamento(Usuario usuario, EspacoFisico espaco) {
		ano += 1;
		LocalDateTime inicio = LocalDateTime.of(ano, 7, 10, 12, 0);
		LocalDateTime fim = LocalDateTime.of(ano, 7, 10, 14, 50);
		Agendar.validarAgendamento(usuario, inicio, fim, espaco);
	}

	public static void gerarAgendamento2(Usuario usuario, EspacoFisico espaco) {
		LocalDateTime inicio = LocalDateTime.of(2025, 7, 12, 10, 0);
		LocalDateTime fim = LocalDateTime.of(2025, 7, 13, 8, 50);
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
				Professor.CargoAcademico.PROFESSORTITULAR
		);
	}

	public static Professor gerarAdmin() {
		return new Professor(
				"João Silva",
				"joao.silva@email.com",
				"123",
				"(61) 91234-5678",
				"1",
				"Engenharia de Software",
				Professor.CargoAcademico.PROFESSORTITULAR
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

	static EspacoFisico gerarSalaDeAula1() {
		Equipamento projetor = new Equipamento("Projetor", 1);
		Equipamento quadroBranco = new Equipamento("Quadro Branco", 2);
		Equipamento[] equipamentos = {projetor, quadroBranco};

		return new EspacoFisico(
				30,
				LocalTime.of(8, 0),
				LocalTime.of(18, 0),
				"S10",
				EspacoFisico.TipoDeEspaco.SALADEAULA,
				equipamentos
		);
	}

	static EspacoFisico gerarSalaDeAula2() {
		Equipamento quadroDigital = new Equipamento("Quadro Digital", 1);
		Equipamento[] equipamentos = {quadroDigital};

		return new EspacoFisico(
				25,
				LocalTime.of(7, 30),
				LocalTime.of(19, 0),
				"S11",
				EspacoFisico.TipoDeEspaco.SALADEAULA,
				equipamentos
		);
	}

	static EspacoFisico gerarSalaDeAula3() {
		Equipamento projetor = new Equipamento("Projetor", 1);
		Equipamento arCondicionado = new Equipamento("Ar Condicionado", 1);
		Equipamento[] equipamentos = {projetor, arCondicionado};

		return new EspacoFisico(
				40,
				LocalTime.of(9, 0),
				LocalTime.of(17, 0),
				"S12",
				EspacoFisico.TipoDeEspaco.SALADEAULA,
				equipamentos
		);
	}

	static EspacoFisico gerarSalaDeAula4() {
		Equipamento quadroBranco = new Equipamento("Quadro Branco", 1);
		Equipamento[] equipamentos = {quadroBranco};

		return new EspacoFisico(
				20,
				LocalTime.of(8, 0),
				LocalTime.of(20, 0),
				"S13",
				EspacoFisico.TipoDeEspaco.SALADEAULA,
				equipamentos
		);
	}

	static EspacoFisico gerarSalaDeAula5() {
		Equipamento projetor = new Equipamento("Projetor", 1);
		Equipamento sistemaSom = new Equipamento("Sistema de Som", 1);
		Equipamento[] equipamentos = {projetor, sistemaSom};

		return new EspacoFisico(
				35,
				LocalTime.of(7, 0),
				LocalTime.of(21, 0),
				"S14",
				EspacoFisico.TipoDeEspaco.SALADEAULA,
				equipamentos
		);
	}

	static EspacoFisico gerarSalaDeAula6() {
		Equipamento quadroBranco = new Equipamento("Quadro Branco", 1);
		Equipamento arCondicionado = new Equipamento("Ar Condicionado", 1);
		Equipamento[] equipamentos = {quadroBranco, arCondicionado};

		return new EspacoFisico(
				28,
				LocalTime.of(8, 0),
				LocalTime.of(19, 0),
				"S15",
				EspacoFisico.TipoDeEspaco.SALADEAULA,
				equipamentos
		);
	}

	static EspacoFisico gerarSalaDeAula7() {
		Equipamento projetor = new Equipamento("Projetor", 1);
		Equipamento quadroDigital = new Equipamento("Quadro Digital", 1);
		Equipamento[] equipamentos = {projetor, quadroDigital};

		return new EspacoFisico(
				45,
				LocalTime.of(9, 0),
				LocalTime.of(18, 30),
				"S16",
				EspacoFisico.TipoDeEspaco.SALADEAULA,
				equipamentos
		);
	}

	static EspacoFisico gerarSalaDeAula8() {
		Equipamento quadroBranco = new Equipamento("Quadro Branco", 2);
		Equipamento sistemaSom = new Equipamento("Sistema de Som", 1);
		Equipamento[] equipamentos = {quadroBranco, sistemaSom};

		return new EspacoFisico(
				33,
				LocalTime.of(7, 30),
				LocalTime.of(20, 30),
				"S17",
				EspacoFisico.TipoDeEspaco.SALADEAULA,
				equipamentos
		);
	}

	static EspacoFisico gerarSalaDeAula9() {
		Equipamento projetor = new Equipamento("Projetor", 1);
		Equipamento quadroBranco = new Equipamento("Quadro Branco", 1);
		Equipamento arCondicionado = new Equipamento("Ar Condicionado", 1);
		Equipamento[] equipamentos = {projetor, quadroBranco, arCondicionado};

		return new EspacoFisico(
				38,
				LocalTime.of(8, 0),
				LocalTime.of(17, 0),
				"S18",
				EspacoFisico.TipoDeEspaco.SALADEAULA,
				equipamentos
		);
	}

	static EspacoFisico gerarSalaDeAula10() {
		Equipamento quadroDigital = new Equipamento("Quadro Digital", 1);
		Equipamento sistemaSom = new Equipamento("Sistema de Som", 1);
		Equipamento arCondicionado = new Equipamento("Ar Condicionado", 1);
		Equipamento[] equipamentos = {quadroDigital, sistemaSom, arCondicionado};

		return new EspacoFisico(
				50,
				LocalTime.of(7, 0),
				LocalTime.of(22, 0),
				"S19",
				EspacoFisico.TipoDeEspaco.SALADEAULA,
				equipamentos
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
				EspacoFisico.TipoDeEspaco.LABORATORIO,   // tipo
				equipamentos     // equipamentos
		);
	}
}
