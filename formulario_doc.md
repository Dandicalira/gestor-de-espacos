# 📋 Classe `Formulario`

A classe `Formulario` fornece uma estrutura gráfica modular usando Swing (`JDialog`, `JPanel`, etc.) para criação e interação com formulários.

---

## ✅ Criar um novo formulário

O título é opcional.

```java
Formulario f = new Formulario("Título do Formulário");
```

---

## 📝 `adicionarTexto(String texto)`

Adiciona textos no topo do formulário.

```java
f.adicionarTexto("Preencha os campos abaixo:");
```

---

## ✏️ `adicionarInput(...)`

Adiciona campos de entrada de texto (inputs).

```java
f.adicionarInput("Nome"); // simples
f.adicionarInput("Email", true); // obrigatório
f.adicionarInput("Horário", true, "HORARIO"); // com regex
f.adicionarInput("ID", "ALGARISMOS"); // com regex, não obrigatório
```

### 🔎 Ler valor do input:

```java
String nome = f.resposta("Nome");
```

---

## 🔒 `adicionarSenha(String texto)`

Adiciona um campo de senha (com máscara oculta).

```java
f.adicionarSenha("Senha");
```

---

## 🔽 `adicionarDropdown(String texto, String[] opcoes)`

Adiciona um menu suspenso de seleção única.

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
f.adicionarBotao("Ações", "Cancelar", f::ocultar);
```

---

## 🖱️ `adicionarAcao(String texto, Runnable acao)`

Adiciona um botão na parte inferior (painel de ações).

```java
f.adicionarAcao("Voltar", () -> voltarTelaAnterior());
```

---

## ❌ `atualizarErro(String texto)`

Exibe uma mensagem de erro em vermelho. Se `texto` for nulo ou vazio, a mensagem é apagada.

```java
f.atualizarErro("O campo Nome é obrigatório");
f.atualizarErro(); // limpa erro
```

---

## 🔵 `atualizarErro(String texto, Boolean azul)`

Permite exibir mensagens em azul (informativas), se o parâmetro azul for `true`.

Exibe a mensagem em preto caso o parâmetro seja `false`.

```java
f.atualizarErro("Cadastrado com sucesso!", true);
```


---

## 📋 `copiarTexto(String texto)`

Copia o texto informado para a área de transferência do sistema.

```java
f.copiarTexto("123456");
```

---

## 💾 `salvarArquivo(String texto, String nomeArquivo)`

Salva o conteúdo de `texto` em um arquivo dentro da pasta `saida/`.

```java
f.salvarArquivo("Dados importantes", "relatorio.txt");
```

---

## 📤 `preencherInput(...)`

Preenche o valor de um campo de texto.

```java
f.preencherInput("Nome", "João da Silva");
```

Se quiser preencher o último campo adicionado:

```java
f.preencherInput("Valor padrão");
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

## 📌 Exemplo

```java
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
    f.atualizarErro("Cadastro realizado com sucesso!", true);
});

f.adicionarAcao("Cancelar", f::ocultar);

f.mostrar();
```

---
