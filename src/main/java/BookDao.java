
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	private String dburl="jdbc:mysql://localhost:3306/libtrack";
	private String dbuname="root";
	private String dbpassword="Apes2getherstrong!";
	private String dbdriver="com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbdriver) {
		try {
			Class.forName(dbdriver);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dburl, dbuname, dbpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
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
			if (rs.next()) {  // Check if there is a result
	            res = rs.getString("Title");
	        } else {
	            System.out.println("No Book found with the title: " + bookID);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	//returns a list of books from the database
	public List<Book> getBooks() {
		loadDriver(dbdriver);
		Connection con = getConnection();
		String query = "SELECT BookID, Title, ISBN, AuthorID, CategoryID, GenreID, Availability FROM books";
        List<Book> books = new ArrayList<>();
		try {
			//statement that lets us get to the database and result set stores the information from the databse 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			//while the result set is 
			while (rs.next()) {
				int bookID = rs.getInt("BookID");
				int authorID = rs.getInt("AuthorID");
				int categoryID = rs.getInt("CategoryID");
				int genreID = rs.getInt("GenreID");
				String ISBN = rs.getString("ISBN");
				String title = rs.getString("Title");
				String availability = rs.getString("Availability").toString();
				Book book = new Book(bookID, authorID, categoryID, genreID, ISBN, title, availability);
				books.add(book);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
}
