package com.midtree.Files.JsonDataTodatabase.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.midtree.Files.JsonDataTodatabase.Model.Author;
import com.midtree.Files.JsonDataTodatabase.Model.Book;

public class JsonToDatabseDaoImpl implements JsonToDatabseDao {

	public static Connection ConnectToDB() throws Exception {
		// Registering the Driver
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		// Getting the connection
		String mysqlUrl = "jdbc:mysql://localhost/jsonbooks";
		Connection con = DriverManager.getConnection(mysqlUrl, "root", "21497518");
		System.out.println("Connection established......");
		return con;
	}

	public void storeBooks(int id, String title, int price) throws Exception {

		Connection connection = ConnectToDB();

		String sql = "INSERT INTO books values (?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, title);
		preparedStatement.setInt(3, price);
		preparedStatement.executeUpdate();

		preparedStatement.close();
		connection.close();
	}

	public void storeAuthors(int id, String name) throws Exception {

		Connection connection = ConnectToDB();

		String sql = "INSERT INTO authors values (?, ?)";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, name);
		preparedStatement.executeUpdate();

		preparedStatement.close();
		connection.close();
	}

	public void storeBooksAuthors(int BookId, int AuthorId) throws Exception {
		Connection connection = ConnectToDB();

		String sql = "INSERT INTO books_authors(book_id,author_id) values (?, ?)";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, BookId);
		preparedStatement.setInt(2, AuthorId);
		preparedStatement.executeUpdate();

		preparedStatement.close();
		connection.close();
	}

	public Set<Book> getBooks() throws Exception {
		Connection con = ConnectToDB();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		Set<Book> books = new LinkedHashSet<Book>();
		try {
			String query = "select * from books";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookId = rs.getInt("id");
				String bookName = rs.getString("title");
				int bookPrice = rs.getInt("price");

				Set<Author> bookAuthors = new LinkedHashSet<Author>();
				String query2 = "SELECT a.id, a.name from authors a join books_authors r where r.book_id=" + bookId
						+ " and a.id=r.author_id";
				pstmt2 = con.prepareStatement(query2);
				ResultSet rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					int authorId = rs2.getInt("id");
					String authorName = rs2.getString("name");
					bookAuthors.add(new Author(authorId, authorName));
				}
				books.add(new Book(bookId, bookName, bookPrice, bookAuthors));

			}

			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

}
