package aplicacao;

import excecoes.ForaDoIntervaloException;

import java.util.Objects;

import static aplicacao.Console.*;

public class Menu {
	public Menu() {

	}

	public void iniciarAplicacao() {
		while (true) {
			try {
				imprimirMenuInicial();

				int opcao = EntradaDeDados.lerInteiroIntervalo(0, 2);
				selecionarModulo(opcao);


			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void selecionarModulo(int sel) {
		switch (sel) {
			case 0 -> sair();
			case 1 -> cadastro();
			case 2 -> login();
			default -> throw new ForaDoIntervaloException(1, 3);
		}
	}
	private static void selecionarCadastro(int sel) {
		switch (sel) {
			case 0 -> System.out.println();
			case 1 -> cadastro();
			case 2 -> login();
			default -> throw new ForaDoIntervaloException(1, 3);
		}
	}
	private static void cadastro() {
		System.out.print("Digite a senha mestra: ");
		while (!validarSenhaMestra(EntradaDeDados.lerString())) {
			System.out.println("Senha incorreta. Tente novamente.");
		}
		imprimirOpcoesCadastro();

	}

	private static boolean validarSenhaMestra(String senha) {
		return Objects.equals(senha, "314");
	}

	private static void login() {
		System.out.println("Login de Usuario");
	}

	private static void sair() {
		//TODO
	}
}
