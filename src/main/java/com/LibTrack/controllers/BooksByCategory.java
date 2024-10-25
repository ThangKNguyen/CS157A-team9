package com.LibTrack.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LibTrack.dao.BookDao;
import com.LibTrack.dao.CategoryDao;
import com.LibTrack.models.BookDetails;

/**
 * Servlet implementation class BooksByCategory
 */
@WebServlet("/BooksByCategory")
public class BooksByCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BooksByCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("categoryId") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Categories");
			dispatcher.forward(request, response);
		}
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

		BookDao bookDao = new BookDao();
		List<BookDetails> books = bookDao.getBookDetailsByCategory(categoryId);

		// Set the list of books as a request attribute for the JSP
		request.setAttribute("books", books);

		// set the name of the category
		CategoryDao categoryDao = new CategoryDao();
		String categoryName = categoryDao.getCategoryById(categoryId);
		request.setAttribute("categoryName", categoryName);

		// Forward to the booksByCategory.jsp page to display the books
		RequestDispatcher dispatcher = request.getRequestDispatcher("Categories");
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
