package aplicacao;

import excecoes.ComponenteNaoExisteException;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Formulario {
	private final Map<String, JTextField> mapaInputs = new HashMap<>();
	private final Map<String, JComboBox<String>> mapaDropdowns = new HashMap<>();
	private final JDialog dialog;
	private final JPanel painelPrincipal;
	private final JPanel painelBotoes;
	private final JPanel painelTexto;

	protected Formulario(String titulo) {
		painelPrincipal = new JPanel(new GridLayout(0, 2));
		painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelTexto = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JPanel painelCompleto = new JPanel(new BorderLayout());
		painelCompleto.add(painelTexto, BorderLayout.NORTH);
		painelCompleto.add(painelPrincipal, BorderLayout.CENTER);
		painelCompleto.add(painelBotoes, BorderLayout.SOUTH);

		JOptionPane optionPane = new JOptionPane(painelCompleto, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		dialog = optionPane.createDialog(titulo);
		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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

		JTextField input = new JTextField();
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
		painelTexto.add(new JLabel(texto));
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
			throw new IllegalArgumentException("Input \"" + texto +"\" duplicado");
		}
	}

	private void verificarDropdownValido(String texto) {
		if (mapaInputs.containsKey(texto)) {
			throw new IllegalArgumentException("Input \"" + texto +"\" duplicado");
		}
	}

	private void atualizar() {
		painelBotoes.revalidate();
		painelBotoes.repaint();
	}
}
