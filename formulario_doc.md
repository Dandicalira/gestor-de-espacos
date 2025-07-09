# 📋 Documentação — Classe `Formulario`

A classe `Formulario` fornece uma maneira simples e modular de criar formulários gráficos utilizando Swing (`JDialog`, `JPanel`, etc.).

---

## ✅ Como criar um formulário

```java
Formulario f = new Formulario("Título do Formulário");
```

Cria um novo formulário com o título especificado. Todos os componentes (inputs, textos, botões, dropdowns) são adicionados por métodos.

---

## ✏️ `adicionarInput(String texto)`

Adiciona um campo de texto com o rótulo informado.

```java
f.adicionarInput("Nome");
```

### Recuperar valor do input:

```java
String nome = f.resposta("Nome");
```

---

## 🔽 `adicionarDropdown(String texto, String[] opcoes)`

Adiciona uma lista suspensa (combo box).

```java
f.adicionarDropdown("Curso", new String[]{"Engenharia", "Medicina", "Arquitetura"});
```

### Recuperar opção selecionada:

```java
String cursoSelecionado = f.opcao("Curso");
```

---

## 📝 `adicionarTexto(String texto)`

Adiciona um ou mais textos no topo. Pode quebrar linhas usando `\n`.

```java
f.adicionarTexto("Preencha os campos abaixo\ncom atenção:");
```

---

## ❌ `atualizarErro(String mensagem)`

Mostra uma mensagem de erro em vermelho na parte inferior. Se for nula ou vazia, remove a mensagem mas mantém o espaço.

```java
f.atualizarErro("Nome não pode estar vazio");
```

---

## 🧩 `adicionarBotao(String texto, Runnable acao)`

Adiciona um botão com a ação associada ao clique.

```java
f.adicionarBotao("Enviar", () -> {
    String nome = f.resposta("Nome");
    System.out.println("Nome digitado: " + nome);
});
```

---

## 👁️ `mostrar()`

Exibe o formulário na tela.

```java
f.mostrar();
```

---

## 🔒 `ocultar()`

Oculta a janela do formulário.

```java
f.ocultar();
```

---

## ⚠️ Exceções

- `ComponenteNaoExisteException`: Lançada quando um campo buscado por `resposta()` ou `opcao()` não existe.
- `ComponenteDuplicadoException`: Lançada ao tentar adicionar um componente duplicado (ex.: dois inputs com mesmo nome).

---

## 📌 Exemplo completo

```java
Formulario f = new Formulario("Cadastro");

f.adicionarTexto("Bem-vindo!\nPreencha os dados:");
f.adicionarInput("Nome");
f.adicionarInput("Email");
f.adicionarDropdown("Curso", new String[]{"Engenharia", "Design", "Direito"});

f.adicionarBotao("Enviar", () -> {
    String nome = f.resposta("Nome");
    String email = f.resposta("Email");
    String curso = f.opcao("Curso");

    if (nome.isBlank() || email.isBlank()) {
        f.atualizarErro("Todos os campos devem ser preenchidos.");
    } else {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Curso: " + curso);
        f.ocultar();
    }
});

f.adicionarBotao("Cancelar", () -> System.exit(0));

f.mostrar();
```
