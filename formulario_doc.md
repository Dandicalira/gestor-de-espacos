# 📋 Classe `Formulario`

A classe `Formulario` fornece uma estrutura gráfica modular usando Swing (`JDialog`, `JPanel`, etc.) para criação e interação com formulários.

---

## ✅ Criar um novo formulário

O título é opcional.

```
Formulario f = new Formulario("Título do Formulário");
```

---

## 📝 `adicionarTexto(String texto)`

Adiciona textos no topo do formulário.

```
f.adicionarTexto("Preencha os campos abaixo:");
```

---

## ✏️ `adicionarInput(...)`

Adiciona campos de entrada de texto (inputs).

```
f.adicionarInput("Nome"); // simples
f.adicionarInput("Email", true); // obrigatório
f.adicionarInput("Horário", "HORARIO"); // com regex
```

## 🔒 `adicionarSenha(String texto)`

Adiciona um campo de senha (obrigatório).

```
f.adicionarSenha("Senha");
```

### 🔎 Ler valor do input/senha:

```
String nome = f.resposta("Nome");
```

---

## 🔽 `adicionarDropdown(String texto, String[] opcoes)`

Adiciona um menu suspenso de seleção única.

```
f.adicionarDropdown("Curso", new String[]{"Engenharia", "Design", "Direito"});
```

### 🔍 Obter valor selecionado:

```
String curso = f.opcao("Curso");
```

---

## 🔘 `adicionarRadio(String texto, String[] opcoes)`

Adiciona um grupo de botões de seleção única (radio buttons).

```
f.adicionarRadio("Turno", new String[]{"Manhã", "Tarde", "Noite"});
```

### 🔍 Obter valor selecionado:

```
String turno = f.selecao("Turno");
```

---

## 🧩 `adicionarBotao(String label, String textoBotao, Runnable acao)`

Adiciona um botão ao corpo do formulário. Se a `label` já existir, novos botões são agrupados na mesma linha.

```
f.adicionarBotao("Ações", "Salvar", () -> salvarDados());
f.adicionarBotao("Ações", "Cancelar", f::ocultar);
```

---

## 🖱️ `adicionarAcao(String texto, Runnable acao)`

Adiciona um botão no painel de ações.

```
f.adicionarAcao("Voltar", () -> voltarTelaAnterior());
```

---

## ⚠️ `exibirAlerta(...)`

Exibe uma mensagem de alerta na parte inferior do formulário.

```
f.exibirAlerta(); // limpa a mensagem de alerta
f.exibirAlerta("Senha incorreta!"); // exibe o alerta em vermelho, por padrão
f.exibirAlerta("Cadastro realizado com sucesso!", Color.BLUE); // exibe o alerta na cor desejada
```

---

## 📋 `copiarTexto(String texto)`

Copia o texto informado para a área de transferência do sistema e exibe um alerta azul de confirmação.

```
f.copiarTexto("123456");
```

---

## 💾 `salvarArquivo(String texto, String nomeArquivo)`

Salva o conteúdo de `texto` em um arquivo dentro da pasta `saida/` e exibe o feedback como alerta.

```
f.salvarArquivo("Dados importantes", "relatorio.txt");
```

---

## 📤 `preencherInput(...)`

Preenche o valor de um campo de texto.

```
f.preencherInput("Nome", "João da Silva"); // especifica um input
f.preencherInput("Valor padrão"); // preenche o último campo adicionado
```

---

## ✅ `valido()`

Verifica se todos os campos obrigatórios foram preenchidos.

```
if (!f.valido()) return;
```

---

## 👁️ Visibilidade do formulário

```
f.mostrar();
f.ocultar();
```

---

## 📌 Exemplo Completo

```
Formulario f = new Formulario("Cadastro de Usuário");

f.adicionarTexto("""
Bem-vindo ao sistema de cadastro.
Preencha os dados abaixo para criar seu perfil.
""");

f.adicionarInput("Nome", true);
f.adicionarInput("Email", true);
f.adicionarSenha("Senha");
f.adicionarDropdown("Curso", new String[]{"Engenharia", "Medicina", "Arquitetura"});
f.adicionarRadio("Turno", new String[]{"Manhã", "Tarde", "Noite"});

f.adicionarAcao("Confirmar", () -> {
if (!f.valido()) return;

    String nome = f.resposta("Nome");
    String email = f.resposta("Email");
    String curso = f.opcao("Curso");
    String turno = f.selecao("Turno");

    System.out.printf("Usuário: %s | %s | %s (%s)\n", nome, email, curso, turno);
    f.exibirAlerta("Cadastro realizado com sucesso!", Color.BLUE);
});

f.adicionarAcao("Cancelar", f::ocultar);

f.mostrar();
```