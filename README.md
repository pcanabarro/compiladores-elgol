# Analisador Léxico - Elgol

## Sobre
Projeto em **Java** que implementa um analisador léxico para a linguagem de programação **Elgol**.  
Ele identifica tokens como identificadores, números, palavras reservadas, operadores, funções e comentários.

## Regras principais da linguagem Elgol

- **Identificadores:**  
  Começam com letra maiúscula, só letras, mínimo 3 caracteres.  
  Ex: `Teste` (válido), `teste` (inválido), `Te1` (inválido)

- **Nomes de função:**  
  Começam com `_` seguido de identificador válido.  
  Ex: `_Teste` (válido), `_te34` (inválido)

- **Números inteiros:**  
  Começam com dígito diferente de zero, só dígitos.  
  O número 0 é representado pelo token especial `zero`.

- **Números negativos:**  
  Usam o operador `comp`, não são escritos diretamente.

- **Palavras reservadas:**  
  `elgol`, `inteiro`, `zero`, `comp`, `enquanto`, `se`, `entao`, `senao`,  
  `inicio`, `fim`, `maior`, `menor`, `igual`, `diferente`.

- **Operadores:**  
  `=`, `+`, `-`, `x`, `/`

- **Comentários:**  
  Começam com `#` e vão até o fim da linha.

- **Sintaxe:**  
  Cada comando termina com ponto final (`.`) e fica em uma linha.

## Como utilizar
Execute:
`javac Main.java`

`AnalisadorLexico.java`

`java Main`

## Integrantes:
Leonardo Pinto Machado

Pedro Canabarro

Everton Santos
