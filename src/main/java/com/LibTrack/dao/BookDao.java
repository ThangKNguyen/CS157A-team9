package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LibTrack.models.BookDetails;
import com.LibTrack.utils.DatabaseConn;

public class BookDao {

	public List<BookDetails> getBookDetailsByCategory(int categoryId) {
		List<BookDetails> books = new ArrayList<>();
		String query = "SELECT Books.BookID, Books.Title, Books.ISBN, Authors.Name AS AuthorName, "
				+ "Categories.CategoryName, Genres.GenreName, Books.Availability " + "FROM LibTrack.Books "
				+ "JOIN LibTrack.Authors ON Books.AuthorID = Authors.AuthorID "
				+ "JOIN LibTrack.Categories ON Books.CategoryID = Categories.CategoryID "
				+ "JOIN LibTrack.Genres ON Books.GenreID = Genres.GenreID " + "WHERE Books.CategoryID = ?;";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BookDetails book = new BookDetails();
				book.setBookId(rs.getInt("BookID"));
				book.setTitle(rs.getString("Title"));
				book.setISBN(rs.getString("ISBN"));
				book.setAuthor(rs.getString("AuthorName"));
				book.setCategory(rs.getString("CategoryName"));
				book.setGenre(rs.getString("GenreName"));
				book.setAvailability(rs.getString("Availability"));
				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	public List<BookDetails> getBookDetailsByAuthor(int authorId) {
		List<BookDetails> books = new ArrayList<>();
		String query = "SELECT Books.BookID, Books.Title, Books.ISBN, Authors.Name AS AuthorName, "
				+ "Categories.CategoryName, Genres.GenreName, Books.Availability " + "FROM LibTrack.Books "
				+ "JOIN LibTrack.Authors ON Books.AuthorID = Authors.AuthorID "
				+ "JOIN LibTrack.Categories ON Books.CategoryID = Categories.CategoryID "
				+ "JOIN LibTrack.Genres ON Books.GenreID = Genres.GenreID " + "WHERE Books.AuthorID = ?;";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, authorId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BookDetails book = new BookDetails();
				book.setBookId(rs.getInt("BookID"));
				book.setTitle(rs.getString("Title"));
				book.setISBN(rs.getString("ISBN"));
				book.setAuthor(rs.getString("AuthorName"));
				book.setCategory(rs.getString("CategoryName"));
				book.setGenre(rs.getString("GenreName"));
				book.setAvailability(rs.getString("Availability"));
				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	public List<BookDetails> getBookDetailsByGenre(int genreID) {
		List<BookDetails> books = new ArrayList<>();
		String query = "SELECT Books.BookID, Books.Title, Books.ISBN, Authors.Name AS AuthorName, "
				+ "Categories.CategoryName, Genres.GenreName, Books.Availability " + "FROM LibTrack.Books "
				+ "JOIN LibTrack.Authors ON Books.AuthorID = Authors.AuthorID "
				+ "JOIN LibTrack.Categories ON Books.CategoryID = Categories.CategoryID "
				+ "JOIN LibTrack.Genres ON Books.GenreID = Genres.GenreID " + "WHERE Books.GenreID = ?;";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, genreID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BookDetails book = new BookDetails();
				book.setBookId(rs.getInt("BookID"));
				book.setTitle(rs.getString("Title"));
				book.setISBN(rs.getString("ISBN"));
				book.setAuthor(rs.getString("AuthorName"));
				book.setCategory(rs.getString("CategoryName"));
				book.setGenre(rs.getString("GenreName"));
				book.setAvailability(rs.getString("Availability"));
				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	public List<BookDetails> getBooksWithDetails() {
		List<BookDetails> books = new ArrayList<>();
		String query = "SELECT Books.BookID, Books.Title, Books.ISBN, Authors.Name AS AuthorName, "
				+ "Categories.CategoryName, Genres.GenreName, Books.Availability " + "FROM LibTrack.Books "
				+ "JOIN LibTrack.Authors ON Books.AuthorID = Authors.AuthorID "
				+ "JOIN LibTrack.Categories ON Books.CategoryID = Categories.CategoryID "
				+ "JOIN LibTrack.Genres ON Books.GenreID = Genres.GenreID";

		try (Connection con = DatabaseConn.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				BookDetails book = new BookDetails();
				book.setBookId(rs.getInt("BookID"));
				book.setTitle(rs.getString("Title"));
				book.setISBN(rs.getString("ISBN"));
				book.setAuthor(rs.getString("AuthorName"));
				book.setCategory(rs.getString("CategoryName"));
				book.setGenre(rs.getString("GenreName"));
				book.setAvailability(rs.getString("Availability"));
				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	public List<BookDetails> getAvailableBooksWithDetails() {
		List<BookDetails> books = new ArrayList<>();
		String query = "SELECT Books.BookID, Books.Title, Books.ISBN, Authors.Name AS AuthorName, "
				+ "Categories.CategoryName, Genres.GenreName, Books.Availability " + "FROM LibTrack.Books "
				+ "JOIN LibTrack.Authors ON Books.AuthorID = Authors.AuthorID "
				+ "JOIN LibTrack.Categories ON Books.CategoryID = Categories.CategoryID "
				+ "JOIN LibTrack.Genres ON Books.GenreID = Genres.GenreID " + "WHERE Books.Availability = 'Available'";

		try (Connection con = DatabaseConn.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				BookDetails book = new BookDetails();
				book.setBookId(rs.getInt("BookID"));
				book.setTitle(rs.getString("Title"));
				book.setISBN(rs.getString("ISBN"));
				book.setAuthor(rs.getString("AuthorName"));
				book.setCategory(rs.getString("CategoryName"));
				book.setGenre(rs.getString("GenreName"));
				book.setAvailability(rs.getString("Availability"));
				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	public boolean updateBookAvailability(int bookId) {
		String query = "UPDATE LibTrack.Books SET Availability = 'Available' WHERE BookID = ?";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

			// Set the bookId parameter in the query
			ps.setInt(1, bookId);

			// Execute the update query
			int rowsAffected = ps.executeUpdate();

			// If at least one row was updated, return true
			if (rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Return false if the update failed
		return false;
	}

}