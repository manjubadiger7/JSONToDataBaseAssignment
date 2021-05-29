//package com.midtree.Files.JsonDataTodatabase;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.text.ParseException;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//public class JsondataToDatabase {
//
//
//	public static void main(String args[]) {
//		// Creating a JSONParser object
//		JSONParser jsonParser = new JSONParser();
//		try {
//			// Parsing the contents of the JSON file
//			JSONObject jsonObject = (JSONObject) jsonParser
//					.parse(new FileReader("C:/Users/M1064478/Desktop/Eclipse WorkSpaces/XML&JSON/Files/books.json"));
//			// Retrieving the array
//			System.out.println(jsonObject);
//			JSONArray jsonArrayBooks = (JSONArray) jsonObject.get("books");
//			System.out.println(jsonArrayBooks);
//			Connection con = ConnectToDB();
//			// Insert a row into the MyPlayers table
//			PreparedStatement pstmt = con.prepareStatement("INSERT INTO books values (?, ?, ?)");
//			PreparedStatement pstmt2 = con.prepareStatement("insert into authors values (?,?)");
//			PreparedStatement pstmt3 = con.prepareStatement("insert into books_authors (book_id,author_id) values (?,?)");
//
//			for (Object object : jsonArrayBooks) {
//
//				JSONObject record = (JSONObject) object;
//
//				int booksId = Integer.parseInt((String) record.get("id"));
//				String title = (String) record.get("title");
//
//				int price = Integer.parseInt(record.get("price").toString());
//
//				System.out.println("==========================================");
//				System.out.println("Inner loop");
//				System.out.println(booksId + " " + title + " " + price);
//				System.out.println("==========================================");
//
//				pstmt.setInt(1, booksId);
//				pstmt.setString(2, title);
//				pstmt.setInt(3, price);
//				 pstmt.executeUpdate();
//
//				JSONArray jsonArrayAuthors = (JSONArray) record.get("authors");
//				int count = 1;
//				System.out.println("This is " + count);
//				System.out.println("---------------------------------------------------------------------------");
//
//				for (Object object2 : jsonArrayAuthors) {
//
//					JSONObject record2 = (JSONObject) object2;
//
//					int authorId = Integer.parseInt((String) record2.get("id"));
//					String authorName = (String) record2.get("name");
//
//					System.out.println("******************************************");
//					System.out.println("Inner loop of authors");
//					System.out.println(authorId + " " + authorName);
//					System.out.println("******************************************");
//
//					pstmt2.setInt(1, authorId);
//					pstmt2.setString(2, authorName);
//					 pstmt2.executeUpdate();
//
//					pstmt3.setInt(1, booksId);
//					pstmt3.setInt(2, authorId);
//					pstmt3.executeUpdate();
//
//					count++;
//					
//					pstmt3.close();
//					pstmt2.close();
//
//				}
//
//			}
//
//			
//			pstmt.close();
//			con.close();
//
//			System.out.println("Records inserted.....");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//	}
//}