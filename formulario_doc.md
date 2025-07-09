# 📋 Classe `Formulario`

A classe `Formulario` fornece uma estrutura gráfica modular usando Swing (`JDialog`, `JPanel`, etc.) para criação de formulários.

---

## ✅ Criar um novo formulário

```java
Formulario f = new Formulario("Título do Formulário");
```

---

## 📝 `adicionarTexto(String texto)`

Adiciona um ou mais textos na parte superior. Use `\n` para quebrar linhas.

```java
f.adicionarTexto("Bem-vindo!");
```

---

## ✏️ `adicionarInput(String texto)` / `adicionarInput(String texto, boolean obrigatorio)`

Adiciona um campo de texto. Pode marcar como obrigatório.

```java
f.adicionarInput("Nome");
f.adicionarInput("Email", true);
```

### ➕ Ler valor do input:

```java
String nome = f.resposta("Nome");
```

---

## 🔽 `adicionarDropdown(String texto, String[] opcoes)`

Adiciona um campo de seleção com várias opções.

```java
f.adicionarDropdown("Curso", new String[]{"Engenharia", "Design", "Direito"});
```

### 🔍 Obter valor selecionado:

```java
String curso = f.opcao("Curso");
```

---

## 🔘 `adicionarRadio(String texto, String[] opcoes)`

Adiciona um grupo de botões de seleção única (radio buttons).

```java
f.adicionarRadio("Turno", new String[]{"Manhã", "Tarde", "Noite"});
```

### 🔍 Obter valor selecionado:

```java
String turno = f.selecao("Turno");
```

---

## 🧩 `adicionarBotao(String label, String textoBotao, Runnable acao)`

Adiciona um botão ao corpo do formulário. Se a `label` já existir, novos botões são agrupados na mesma linha.

```java
f.adicionarBotao("Ações", "Salvar", () -> salvarDados());
f.adicionarBotao("Ações", "Cancelar", () -> f.ocultar());
```

---

## 🖱️ `adicionarAcao(String texto, Runnable acao)`

Adiciona um botão na parte inferior (painel de ações).

```java
f.adicionarAcao("Voltar", () -> voltarTelaAnterior());
```

---

## ❌ `atualizarErro(String texto)`

Exibe uma mensagem de erro (em vermelho). Se nula ou vazia, remove a mensagem.

```java
f.atualizarErro("O campo Nome é obrigatório");
```

---

## ✅ `valido()`

Verifica se todos os campos obrigatórios foram preenchidos.

```java
if (!f.valido()) return;
```

---

## 👁️ Mostrar / 🔒 Ocultar formulário

```java
f.mostrar();
f.ocultar();
```

---

## ⚠️ Exceções

- `ComponenteNaoExisteException`: campo não foi adicionado.
- `ComponenteDuplicadoException`: tentativa de adicionar duplicado.

---

## 📌 Exemplo completo

```java
Formulario f = new Formulario("Cadastro");

f.adicionarTexto("Preencha seus dados:");
f.adicionarInput("Nome", true);
f.adicionarInput("Email", true);
f.adicionarDropdown("Curso", new String[]{"Engenharia", "Design", "Direito"});

f.adicionarBotao("Ações", "Salvar", () -> {
    if (!f.valido()) return;

    String nome = f.resposta("Nome");
    String email = f.resposta("Email");
    String curso = f.opcao("Curso");

    System.out.println(nome + " - " + email + " - " + curso);
    f.ocultar();
});

f.adicionarBotao("Ações", "Cancelar", f::ocultar);
f.adicionarAcao("Ajuda", () -> f.atualizarErro("Todos os campos são obrigatórios"));

f.mostrar();
```
