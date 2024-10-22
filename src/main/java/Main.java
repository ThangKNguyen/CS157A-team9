import java.sql.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws SQLException {
		
		List<Book> books;
		
		BookDao bookDao = new BookDao();
		
		books = bookDao.getBooks();
		
		
		for (Book book: books) {
			System.out.println(book.getTitle());
		}
	}
}
