package aplicacao;

import excecoes.ComponenteDuplicadoException;
import excecoes.ComponenteNaoExisteException;
import servicos.persistencia.PersistenciaService;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Formulario {
	private final Map<String, JTextField> mapaInputs = new HashMap<>();
	private final Map<String, JComboBox<String>> mapaDropdowns = new HashMap<>();
	private final JLabel mensagemErro = new JLabel();
	private JDialog dialog;
	private JPanel painelPrincipal;
	private JPanel painelInferior;
	private JPanel painelCompleto;
	private JPanel painelErro;
	private JPanel painelBotoes;
	private JPanel painelTexto;

	protected Formulario() {
		montarFormulario();
		criarDialogo("Gestor de espaços");
	}

	protected Formulario(String titulo) {
		montarFormulario();
		criarDialogo(titulo);
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
		painelInferior.add(painelBotoes, BorderLayout.SOUTH);
	}

	private void configurarMensagemErro() {
		mensagemErro.setForeground(Color.RED);
		painelErro.add(mensagemErro);
		painelErro.setPreferredSize(new Dimension(0, 20));
		painelErro.setVisible(true);
	}

	private void gerarPaineis() {
		painelCompleto = new JPanel(new BorderLayout());
		painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelInferior = new JPanel(new BorderLayout());

		painelPrincipal = new JPanel(new GridLayout(0, 2));
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(2, 0, 8, 0));

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
			throw new ComponenteNaoExisteException(input);
		}

		return campo.getText();
	}

	protected String opcao(String dropdown) {
		JComboBox<?> opcoes = mapaDropdowns.get(dropdown);

		if (opcoes == null) {
			throw new ComponenteNaoExisteException(dropdown);
		}

		Object selecionado = opcoes.getSelectedItem();

		assert selecionado != null;
		return selecionado.toString();
	}

	protected void adicionarInput(String texto) {
		verificarInputValido(texto);

		painelPrincipal.add(new JLabel(texto));

		JTextField input = new JTextField(20);
		painelPrincipal.add(input);

		mapaInputs.put(texto, input); // adiciona o input ao mapa

		atualizar();
	}

	protected void adicionarDropdown(String texto, String[] opcoes) {
		verificarDropdownValido(texto);

		painelPrincipal.add(new JLabel(texto));

		JComboBox<String> dropdown = new JComboBox<>(opcoes);
		painelPrincipal.add(dropdown);

		mapaDropdowns.put(texto, dropdown); // adiciona o dropdown ao mapa

		atualizar();
	}

	protected void adicionarTexto(String texto) {
		// Divide o texto em vários componentes
		for (String linha : texto.split("\n")) {
			JLabel componente = new JLabel(linha);

			painelTexto.add(componente);

		}
	}

	protected void atualizarErro(String texto) {
		if (texto == null || texto.isBlank()) {
			mensagemErro.setText(" ");
		} else {
			mensagemErro.setText(texto);
		}
		atualizar();
	}

	protected void adicionarBotao(String texto, Runnable acao) {
		JButton botao = new JButton(texto);
		botao.addActionListener(e -> acao.run());
		painelBotoes.add(botao);

		atualizar();
	}

	private void verificarInputValido(String texto) {
		if (mapaInputs.containsKey(texto)) {
			throw new ComponenteDuplicadoException(texto);
		}
	}

	private void verificarDropdownValido(String texto) {
		if (mapaInputs.containsKey(texto)) {
			throw new ComponenteDuplicadoException(texto);
		}
	}

	private void atualizar() {
		painelBotoes.revalidate();
		painelBotoes.repaint();
	}
}
