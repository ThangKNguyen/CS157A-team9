package com.LibTrack.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibTrack.dao.AuthorDao;
import com.LibTrack.dao.BookDao;
import com.LibTrack.dao.CategoryDao;
import com.LibTrack.dao.GenreDao;
import com.LibTrack.dao.MemberDao;
import com.LibTrack.models.Author;
import com.LibTrack.models.Book;
import com.LibTrack.models.BookDetails;
import com.LibTrack.models.Category;
import com.LibTrack.models.FineItem;
import com.LibTrack.models.Genre;
import com.LibTrack.models.Member;

/**
 * Servlet implementation class ViewBooks
 */
@WebServlet("/ViewBooks")
public class ViewBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("staffLogin.jsp");
			return;
		}
		Member member = (Member) session.getAttribute("loggedInUser");
		if (member == null) {
			response.sendRedirect("staffLogin.jsp");
			return;
		}
		try {
			BookDao bookDao = new BookDao();
			CategoryDao categoryDao = new CategoryDao();
			
			List<BookDetails> books = new ArrayList<BookDetails>();
			
			// Get list of categories and retrieve all books sequentially by category
			for (Category c:categoryDao.getAllCategories()) {
				books.addAll(bookDao.getBookDetailsByCategory(c.getId()));
			}

			// Set the list of books as a request attribute and forward to JSP
			request.setAttribute("books", books);
			request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Error retrieving members", e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("staffLogin.jsp");
			return;
		}

		Member member = (Member) session.getAttribute("loggedInUser");
		if (member == null) {
			response.sendRedirect("staffLogin.jsp");
			return;
		}

		try {
			
			BookDao bookDao = new BookDao();
			
			String author;
			String category;
			String genre;
			
			
			int authorId = -1;
			int categoryId = -1;
			int genreId = -1;
			
			//title isbn author category genre availability
			AuthorDao authorDao = new AuthorDao();
			CategoryDao categoryDao = new CategoryDao();
			GenreDao genreDao = new GenreDao();
			List<Author> authors = authorDao.getAllAuthors();
			List<Category> categories = categoryDao.getAllCategories();
			List<Genre> genres = genreDao.getAllGenres();
			

			// if have title then add/update
			if (request.getParameter("title") != null || request.getParameter("updateTitle")!=null) {
				
				if (request.getParameter("updateTitle")!=null) {
					author = request.getParameter("updateAuthor");
					category = request.getParameter("updateCategory");
					genre = request.getParameter("updateGenre");
				} else {
					author = request.getParameter("author");
					category = request.getParameter("category");
					genre = request.getParameter("genre");
				}
				
				for(Author a:authors) {
					if (a.getName().equals(author)) {
						authorId = a.getId();
					}
				}
				
				for(Category c:categories) {
					if (c.getName().equals(category)) {
						categoryId = c.getId();
					}
				}
				
				for(Genre g:genres) {
					if (g.getName().equals(genre)) {
						genreId = g.getId();
					}
				}
				
				if (authorId == -1) {
					request.setAttribute("error", "author not found");
					request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
					return;
				} else if (categoryId == -1) {
					request.setAttribute("error", "category not found");
					request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
					return;
				} else if (genreId == -1) {
					request.setAttribute("error", "genre not found");
					request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
					return;
				}
				
				if (request.getParameter("updateTitle")!=null) {
					Book newBook = new Book(0, request.getParameter("updateTitle"), authorId,
							categoryId, genreId,
							request.getParameter("updateISBN"), request.getParameter("updateAvailability"));
					
					bookDao.update(newBook);
				} else {
					Book newBook = new Book(0, request.getParameter("title"), authorId,
							categoryId, genreId,
							request.getParameter("isbn"), "available");
					bookDao.insert(newBook);
				}
				
				
				// if case for add/update
				Book newBook = new Book(0, request.getParameter("title"), authorId,
						categoryId, genreId,
						request.getParameter("isbn"), "available");
				
				bookDao.insert(newBook);
			} else if (request.getParameter("searchISBN") != null) {
				int searchISBN = Integer.valueOf(request.getParameter("searchISBN"));
				
				if (searchISBN > 0) {
					bookDao.removeBookByISBN(Integer.valueOf(request.getParameter("searchISBN")));
				} else {
					request.setAttribute("error", "not a valid ISBN");
					request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
					return;
				}
			} 

			// Redirect to avoid form resubmission issue
			response.sendRedirect("ViewBooks");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Error adding or removing members", e);
		}
	}

}
