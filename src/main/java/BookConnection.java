import java.io.IOException;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookConnection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//get the title that the user inputed on the webpage
        String searchInput = request.getParameter("bookName");
        String title = "";
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getBooks();
        boolean hasBook = false;
        
        for(Book book:books) {
        	//if the title that user inputed is in the the list of books, it will display on the webpage by setting an attribute and forwarding it 
        	if(searchInput.equals(book.getTitle())) {
        		title = book.getTitle();
        		hasBook = true;
        		break;
        	}
        }
        if(hasBook) {
        	request.setAttribute("name", title);
    		RequestDispatcher rd =request.getRequestDispatcher("/booksearch.jsp");
    		rd.forward(request, response);
    		return;
        }
        else {
    		title = "No book with the title, " + "\"" + searchInput + "\"" + " was found";
    		request.setAttribute("name", title);
    		RequestDispatcher rd =request.getRequestDispatcher("/booksearch.jsp");
    		rd.forward(request, response);
    		return;
        }
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
