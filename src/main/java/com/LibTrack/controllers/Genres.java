package com.LibTrack.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LibTrack.dao.GenreDao;
import com.LibTrack.models.Genre;

/**
 * Servlet implementation class Genres
 */
@WebServlet("/Genres")
public class Genres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Genres() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GenreDao genreDao = new GenreDao();
		List<Genre> genres = genreDao.getAllGenres();

		// Set the Genres as a request attribute to be used in JSP
		request.setAttribute("genres", genres);

		// Forward to genres.jsp where users can select a category
		RequestDispatcher dispatcher = request.getRequestDispatcher("genres.jsp");
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
