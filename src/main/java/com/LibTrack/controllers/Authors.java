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
import com.LibTrack.models.Author;

/**
 * Servlet implementation class Authors
 */
@WebServlet("/Authors")
public class Authors extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authors() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuthorDao authorDao = new AuthorDao();
		List<Author> authors = authorDao.getAllAuthors();
		// Set the list of authors as a request attribute for the JSP
		request.setAttribute("authors", authors);
		// Forward to the authors.jsp page to display the authors
		RequestDispatcher dispatcher = request.getRequestDispatcher("authors.jsp");
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