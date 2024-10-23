import java.sql.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws SQLException {
		
		
		
		BookDao bookDao = new BookDao();
		
		List<Book> books = bookDao.getBooks();
		
		
		for (Book book: books) {
			System.out.println(book.getTitle());
		}
	}
}
