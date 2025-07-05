package excecoes;

public class EmailAlunoFormatoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 67249133940531764L;

	public EmailAlunoFormatoInvalidoException() {
		super("O e-mail deve ser no formato matricula@aluno.unb.br!");
	}
}
