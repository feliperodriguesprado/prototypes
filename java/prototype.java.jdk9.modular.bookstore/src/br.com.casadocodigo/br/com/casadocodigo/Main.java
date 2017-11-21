package br.com.casadocodigo;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import br.com.casadocodigo.http.*;
import br.com.casadocodigo.domain.model.*;
import br.com.casadocodigo.nf.service.*;

public class Main {

	public static void main(String ...args) {

		System.out.println("\nLista de livros disponíveis \n");

		List<Book> books = Books.all();

		IntStream.range(0, books.size()).forEach(i -> {
			System.out.println(i + " - " + books.get(i).getName());
		});

		Scanner scanner = new Scanner(System.in);
		System.out.print("\nDigite o número do livro que quer comprar: ");

		try {
			int number = scanner.nextInt();
			Book book = books.get(number);

			System.out.println("\nO livro escolhido foi: " + book.getName());
			System.out.print("\nInforme seu nome, para que possamos emitir a nota fiscal: ");

			scanner = new Scanner(System.in);
			String name = scanner.nextLine();
			NFEmissor emissor = new NFEmissor();
			emissor.emit(name, book);

			System.out.println("Obrigado!");

			Books.findSimilar(book).ifPresentOrElse(showSimilar, noSuggestions);

			// segura a execução para o código assíncrono terminar
			System.out.println("Aperte o enter para sair");
			new Scanner(System.in).nextLine();

			emissor.close();
		} catch(Exception e) {
			System.err.println("Ops, aconteceu um erro: " + e);
		}

	}

	private static Consumer<Book> showSimilar = similar -> {
		System.out.println("\nTalvez você goste do livro: " + similar.getName());
	};

	private static Runnable noSuggestions = () -> {
		System.out.println("\nNão temos nenhuma sugestão de livro similar no momento");
	};
}
