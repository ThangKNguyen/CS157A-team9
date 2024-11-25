import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BorrowingHistory
 */
@WebServlet("/BookConnection")
public class BookConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookConnection() {
		super();
	}

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        String searchInput = request.getParameter("bookName");
	        
	        if (searchInput == null || searchInput.trim().isEmpty()) {
	            RequestDispatcher rd = request.getRequestDispatcher("/booksearch.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        
	        BookDao bookDao = new BookDao();
	        List<Book> results = bookDao.searchBooks(searchInput);
	        
	        if (!results.isEmpty()) {
	            request.setAttribute("searchType", "results");
	            request.setAttribute("searchResults", results);
	            // Get author name using the first book's authorID
	            String authorName = bookDao.getAuthorName(results.get(0).getAuthorId());
	            request.setAttribute("authorName", authorName);
	            // Get genre name using the first book's genreID
	            String genreName = bookDao.getGenreName(results.get(0).getGenreId());
	            request.setAttribute("genreName", genreName);
	        }
	 		else {
	            request.setAttribute("searchType", "none");
	            request.setAttribute("errorMessage", "No results found for \"" + searchInput + "\"");
	        }
	        
	        RequestDispatcher rd = request.getRequestDispatcher("/booksearch.jsp");
	        rd.forward(request, response);
	    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}