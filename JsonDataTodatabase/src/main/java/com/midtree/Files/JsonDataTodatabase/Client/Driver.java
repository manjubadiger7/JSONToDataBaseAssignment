package com.midtree.Files.JsonDataTodatabase.Client;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.midtree.Files.JsonDataTodatabase.Model.Author;
import com.midtree.Files.JsonDataTodatabase.Model.Book;
import com.midtree.Files.JsonDataTodatabase.Service.JsonTodatabase;
import com.midtree.Files.JsonDataTodatabase.Service.JsonTodatabaseImpl;

public class Driver {

	public static void main(String[] args) {

		System.out.println("Welcome to application");

		String file = "C:/Users/M1064478/Desktop/Eclipse WorkSpaces/XML&JSON/Files/books.json";

		Set<Book> books = readJson(file);
		// displayBooks(books);
		
		JsonTodatabase jsonTodatabase = new JsonTodatabaseImpl();
		try {
			jsonTodatabase.getBooks();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		try {
			jsonTodatabase.storeJsonData(books);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private static void displayBooks(Set<Book> books) {
		for (Book book : books) {
			System.out.println(book.getId());
			System.out.println(book.getTitle());
			System.out.println(book.getPrice());
			System.out.println("************************");
			for (Author author : book.getAuthors()) {
				System.out.println(author.getId());
				System.out.println(author.getName());

			}
		}
	}

	private static Set<Book> readJson(String file) {

		// Creating a JSONParser object
		JSONParser jsonParser = new JSONParser();
		Set<Book> books = new HashSet<Book>();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
			// Retrieving the array
			JSONArray jsonArrayBooks = (JSONArray) jsonObject.get("books");

			for (Object object : jsonArrayBooks) {

				JSONObject record = (JSONObject) object;

				int booksId = Integer.parseInt((String) record.get("id"));
				String title = (String) record.get("title");
				int price = Integer.parseInt(record.get("price").toString());

//				System.out.println("==========================================");
//				System.out.println("Inner loop");
//				System.out.println(booksId + " " + title + " " + price);
//				System.out.println("==========================================");

				JSONArray jsonArrayAuthors = (JSONArray) record.get("authors");
				Set<Author> authors = new HashSet<Author>();
				for (Object object2 : jsonArrayAuthors) {

					JSONObject record2 = (JSONObject) object2;

					int authorId = Integer.parseInt((String) record2.get("id"));
					String authorName = (String) record2.get("name");

					authors.add(new Author(authorId, authorName));

//					System.out.println("******************************************");
//					System.out.println("Inner loop of authors");
//					System.out.println(authorId + " " + authorName);
//					System.out.println("******************************************");

				}
				books.add(new Book(booksId, title, price, authors));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

}
