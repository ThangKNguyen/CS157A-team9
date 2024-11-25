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

	public boolean setBookAvailable(int bookId) {
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

	public boolean setBookBorrowed(int bookId) {
		String query = "UPDATE LibTrack.Books SET Availability = 'Borrowed' WHERE BookID = ?";

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
	
	public String getNameByBookID(int bookID) {
		loadDriver(dbdriver);
		Connection con = getConnection();
		String query = "SELECT Title FROM books WHERE Title = ?";
		String res = null;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bookID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // Check if there is a result
				res = rs.getString("Title");
			} else {
				System.out.println("No Book found with the title: " + bookID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;

	}

	public String getAuthorName(int authorID) {
	    String authorName = "";
	    loadDriver(dbdriver);
	    Connection con = getConnection();
	    
	    String query = "SELECT Name FROM Authors WHERE AuthorID = ?";
	    
	    try {
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, authorID);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            authorName = rs.getString("Name");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return authorName;
	}
	
	public String getGenreName(int genreID) {
	    String genreName = "";
	    loadDriver(dbdriver);
	    Connection con = getConnection();
	    
	    String query = "SELECT GenreName FROM Genres WHERE GenreID = ?";
	    
	    try {
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, genreID);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            genreName = rs.getString("GenreName");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return genreName;
	}
	
	// returns a list of books from the database
	public List<Book> searchBooks(String searchParam) {
	    List<Book> results = new ArrayList<>();
	    loadDriver(dbdriver);
	    Connection con = getConnection();
	    
	    String query = "SELECT Books.*, Authors.Name FROM Books " +
	                  "JOIN Authors ON Books.AuthorID = Authors.AuthorID " +
	                  "WHERE LOWER(Books.Title) LIKE LOWER(?) " +
	                  "OR LOWER(Authors.Name) LIKE LOWER(?)";
	                  
	    try {
	        PreparedStatement stmt = con.prepareStatement(query);
	        String searchTerm = "%" + searchParam + "%";
	        stmt.setString(1, searchTerm);
	        stmt.setString(2, searchTerm);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            Book book = new Book(
	                rs.getInt("BookID"),
	                rs.getString("Title"),
	                rs.getInt("AuthorID"),
	                rs.getInt("CategoryID"),
	                rs.getInt("GenreID"),
	                rs.getString("ISBN"),
	                rs.getString("Availability")
	            );
	            // Store the author name in the request attributes through BookConnection
	            results.add(book);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return results;
	}

}
