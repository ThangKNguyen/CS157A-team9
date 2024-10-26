package com.LibTrack.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LibTrack.dao.AuthorDao;
import com.LibTrack.dao.BookDao;
import com.LibTrack.models.BookDetails;

/**
 * Servlet implementation class BooksByAuthor
 */
@WebServlet("/BooksByAuthor")
public class BooksByAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BooksByAuthor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("authorId") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Authors");
			dispatcher.forward(request, response);
		}
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		BookDao bookDao = new BookDao();
		List<BookDetails> books = bookDao.getBookDetailsByAuthor(authorId);
		// Set the list of books as a request attribute for the JSP
		request.setAttribute("books", books);
		// set the name of the author
		AuthorDao authorDao = new AuthorDao();
		String authorName = authorDao.getAuthorById(authorId);
		request.setAttribute("authorName", authorName);
		// Forward to the booksByAuthor.jsp page to display the books
		RequestDispatcher dispatcher = request.getRequestDispatcher("Authors");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}