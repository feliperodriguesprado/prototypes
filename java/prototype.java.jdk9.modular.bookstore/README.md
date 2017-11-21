# Prototype Java JDK 9 - Bookstore

Protótipo criado com estudos do livro "Java 9 - Interativo, reativo e modularizado". Projeto utilizando a arquitetura modular do JDk9.

**Compilar projeto:**
`javac -d mods/NOME_MODULO --module-path mods src/NOME_MODULO/module-info.java $(find src/NOME_MODULO -name "*.java")`

**Executar o projeto:**
`java --module-path mods -m NOME_MODULO/br.com.casadocodigo.Main`
