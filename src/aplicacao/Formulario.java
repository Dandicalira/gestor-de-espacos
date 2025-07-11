package aplicacao;

import excecoes.CampoVazioException;
import excecoes.EntidadeDuplicadaException;
import excecoes.EntidadeInexistenteException;
import servicos.persistencia.PersistenciaService;
import util.FiltroRegex;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static util.FiltroRegex.obterExpressaoRegular;

public class Formulario {
	private final Map<String, JTextField> mapaInputs = new LinkedHashMap<>();
	private final Map<String, JComboBox<String>> mapaDropdowns = new HashMap<>();
	private final Map<String, JPanel> mapaBotoes = new HashMap<>();
	private final Map<String, ButtonGroup> mapaRadios = new HashMap<>();
	private final Set<String> camposObrigatorios = new HashSet<>();
	private final JLabel mensagemErro = new JLabel();
	private JDialog dialog;
	private JPanel painelPrincipal;
	private JPanel painelInferior;
	private JPanel painelCompleto;
	private JPanel painelErro;
	private JPanel painelAcoes;
	private JPanel painelTexto;

	protected Formulario() {
		montarFormulario();
		criarDialogo("Gestor de espaços");
	}

	protected Formulario(String titulo) {
		montarFormulario();
		criarDialogo(titulo);
	}

	protected static void mostrarMensagem(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);
	}

	protected static void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Gestor de espaços", JOptionPane.PLAIN_MESSAGE);
	}

	private void montarFormulario() {
		gerarPaineis();
		configurarMensagemErro();
		montarPainelInferior();
		montarPainelCompleto();
	}

	private void criarDialogo(String titulo) {
		JOptionPane optionPane = new JOptionPane(painelCompleto, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		dialog = optionPane.createDialog(titulo);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Encerrar a aplicação ao fechar a janela
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent e) {
				PersistenciaService.salvarDados();
				System.exit(0);
			}
		});
	}

	private void montarPainelCompleto() {
		painelCompleto.add(painelTexto, BorderLayout.NORTH);
		painelCompleto.add(painelPrincipal, BorderLayout.CENTER);
		painelCompleto.add(painelInferior, BorderLayout.SOUTH);
	}

	private void montarPainelInferior() {
		painelInferior.add(painelErro, BorderLayout.CENTER);
		painelInferior.add(painelAcoes, BorderLayout.SOUTH);
	}

	private void configurarMensagemErro() {
		mensagemErro.setForeground(Color.RED);
		painelErro.add(mensagemErro);
		painelErro.setPreferredSize(new Dimension(0, 20));
		painelErro.setVisible(true);
	}

	private void gerarPaineis() {
		painelCompleto = new JPanel(new BorderLayout());
		painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelInferior = new JPanel(new BorderLayout());

		painelPrincipal = new JPanel(new GridLayout(0, 2));
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(2, 2, 8, 2));

		painelErro = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelErro.setPreferredSize(new Dimension(0, 30));

		painelTexto = new JPanel();
		painelTexto.setLayout(new BoxLayout(painelTexto, BoxLayout.Y_AXIS));
		painelTexto.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
	}

	protected void mostrar() {
		dialog.pack(); // ajusta o tamanho da janela
		dialog.setVisible(true);
	}

	protected void ocultar() {
		dialog.setVisible(false);
	}

	protected String resposta(String input) {
		JTextField campo = mapaInputs.get(input);

		if (campo == null) {
			throw new EntidadeInexistenteException("O input \"" + input + "\" não existe");
		}

		return campo.getText();
	}

	protected String opcao(String dropdown) {
		JComboBox<String> opcoes = mapaDropdowns.get(dropdown);

		if (opcoes == null) {
			throw new EntidadeInexistenteException("O dropdown \"" + dropdown + "\" não existe");
		}

		Object selecionado = opcoes.getSelectedItem();

		assert selecionado != null;
		return selecionado.toString();
	}

	protected String selecao(String radio) {
		ButtonGroup opcoes = mapaRadios.get(radio);

		if (opcoes == null) {
			throw new EntidadeInexistenteException("O radio \"" + radio + "\" não existe");
		}

		for (AbstractButton botao : Collections.list(opcoes.getElements())) {
			if (botao.isSelected()) {
				return botao.getText();
			}
		}

		throw new CampoVazioException();
	}

	protected void adicionarInput(String texto, boolean obrigatorio, String regex) {
		verificarInputValido(texto);

		String labelTexto = texto + (obrigatorio ? "*" : "");
		painelPrincipal.add(new JLabel(labelTexto));

		JTextField input = new JTextField(20);
		painelPrincipal.add(input);

		mapaInputs.put(texto, input);

		if (obrigatorio) {
			camposObrigatorios.add(texto);
		}

		if (regex != null) {
			String expressao = obterExpressaoRegular(regex);
			((AbstractDocument) input.getDocument()).setDocumentFilter(new FiltroRegex(expressao));
		}

		atualizar();
	}

	protected void adicionarSenha(String texto) {
		verificarInputValido(texto);

		String labelTexto = texto + "*";
		painelPrincipal.add(new JLabel(labelTexto));

		JPasswordField inputSenha = new JPasswordField(20);
		painelPrincipal.add(inputSenha);

		mapaInputs.put(texto, inputSenha);

		camposObrigatorios.add(texto);

		atualizar();
	}

	protected void adicionarInput(String texto) {
		adicionarInput(texto, false, null);
	}

	protected void adicionarInput(String texto, boolean obrigatorio) {
		adicionarInput(texto, obrigatorio, null);
	}

	protected void adicionarInput(String texto, String regex) {
		adicionarInput(texto, false, regex);
	}

	protected void preencherInput(String input, String texto) {
		JTextField campo = mapaInputs.get(input);

		if (campo != null) {
			campo.setText(texto);

		} else {
			throw new EntidadeInexistenteException("O componente \"" + input + "\" não existe");
		}
	}

	protected void preencherInput(String novoValor) {
		if (mapaInputs.isEmpty()) return;

		String ultimoInput = null;
		for (String chave : mapaInputs.keySet()) {
			ultimoInput = chave;
		}

		if (ultimoInput != null) {
			preencherInput(ultimoInput, novoValor);
		}
	}

	protected void adicionarDropdown(String texto, String[] opcoes) {
		verificarDropdownValido(texto);

		painelPrincipal.add(new JLabel(texto));

		JComboBox<String> dropdown = new JComboBox<>(opcoes);
		painelPrincipal.add(dropdown);

		mapaDropdowns.put(texto, dropdown);

		atualizar();
	}

	protected void adicionarRadio(String texto, String[] opcoes) {
		if (mapaRadios.containsKey(texto)) {
			throw new EntidadeDuplicadaException("Componente \"" + texto + "\" duplicado");
		}

		painelPrincipal.add(new JLabel(texto + "*"));

		JPanel painelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ButtonGroup radio = new ButtonGroup();

		boolean selecionar = true;
		for (String opcao : opcoes) {
			JRadioButton botaoRadio = new JRadioButton(opcao);
			radio.add(botaoRadio);
			painelRadio.add(botaoRadio);

			if (selecionar) {
				botaoRadio.setSelected(true);
				selecionar = false;
			}
		}

		painelPrincipal.add(painelRadio);
		mapaRadios.put(texto, radio);

		atualizar();
	}


	protected void adicionarTexto(String texto) {
		for (String linha : texto.split("\n", -1)) {
			JLabel componente = new JLabel(linha.isBlank() ? " " : linha);
			painelTexto.add(componente);
		}
	}

	protected void atualizarErro(String texto) {
		mensagemErro.setForeground(Color.RED);

		if (texto == null || texto.isBlank()) {
			mensagemErro.setText(" ");
		} else {
			mensagemErro.setText(texto);
		}
		atualizar();
	}

	protected void atualizarErro(String texto, Boolean azul) {
		atualizarErro(texto);

		if (azul) mensagemErro.setForeground(Color.BLUE);
		atualizar();
	}

	protected void copiarTexto(String texto) {
		StringSelection selecao = new StringSelection(texto);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selecao, null);
		atualizarErro("'" + texto + "' copiado para a área de transferência!", true);
	}

	protected void salvarArquivo(String texto, String nomeArquivo) {
		try {
			Path pastaOutput = Paths.get("saida");
			if (!Files.exists(pastaOutput)) {
				Files.createDirectories(pastaOutput);
			}

			Path caminhoCompleto = pastaOutput.resolve(nomeArquivo);
			Files.writeString(caminhoCompleto, texto);

			atualizarErro("Arquivo salvo com sucesso!", true);
		} catch (IOException e) {
			atualizarErro("Erro ao salvar arquivo.");
			System.out.println(e.getMessage());
		}
	}

	protected void atualizarErro() {
		mensagemErro.setText(" ");
		atualizar();
	}

	protected void adicionarBotao(String texto, String textoBotao, Runnable acao) {
		if (texto == null) {
			texto = "";
		}

		JPanel painelBotoesLinha = mapaBotoes.get(texto);

		if (painelBotoesLinha == null) {
			painelPrincipal.add(new JLabel(texto));

			painelBotoesLinha = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
			painelPrincipal.add(painelBotoesLinha);
			mapaBotoes.put(texto, painelBotoesLinha);
		}

		JButton botao = new JButton(textoBotao);
		botao.addActionListener(e -> acao.run());
		painelBotoesLinha.add(botao);

		atualizar();
	}


	protected void adicionarAcao(String texto, Runnable acao) {
		JButton botao = new JButton(texto);
		botao.addActionListener(e -> acao.run());
		painelAcoes.add(botao);

		atualizar();
	}

	protected boolean valido() {
		for (String campo : camposObrigatorios) {
			String valor = resposta(campo);
			if (valor == null || valor.isBlank()) {
				atualizarErro("O campo \"" + campo + "\" é obrigatório.");
				return false;
			}
		}
		atualizarErro(null);
		return true;
	}

	private void verificarInputValido(String texto) {
		if (mapaInputs.containsKey(texto)) {
			throw new EntidadeDuplicadaException("Componente \"" + texto + "\" duplicado.");
		}
	}

	private void verificarDropdownValido(String texto) {
		if (mapaDropdowns.containsKey(texto)) {
			throw new EntidadeDuplicadaException("Componente \"" + texto + "\" duplicado.");
		}
	}

	private void atualizar() {
		painelAcoes.revalidate();
		painelAcoes.repaint();
		painelPrincipal.revalidate();
		painelPrincipal.repaint();
	}
}
