package aplicacao;
import excecoes.ForaDoIntervaloException;

import java.util.Scanner;
import java.util.InputMismatchException;

public class EntradaDeDados {

	//Scanner
	static Scanner sc = new Scanner(System.in);
	
	//Método Construtor Privado
	private EntradaDeDados() {
		
	}
	
	//Métodos Estáticos de Leitura de Dados
	public static String lerStringEspacada() {
		return sc.nextLine().trim().replaceAll("\\s+", " ");
		
	}
	public static String lerString() {
		return sc.nextLine().trim().replace(" ", "");
	}
	public static int lerInteiro() {
		int a;
		while (true) {
			try {
				a = sc.nextInt();
				sc.nextLine();
				return a;
			} catch (InputMismatchException e) {
				System.out.print("Entrada incorreta! Por favor, digite um número inteiro válido: ");
				sc.nextLine();
			}
		}
	}

	public static int lerInteiroIntervalo(int min, int max) {
		int a = EntradaDeDados.lerInteiro();
		if (min <= a && a <= max) { // [min, max]
			return a;
		}
		throw new ForaDoIntervaloException(min, max);
	}
	
}
