package com.LibTrack.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibTrack.dao.ReservationDao;
import com.LibTrack.models.Member;
import com.LibTrack.models.ReservationItem;

/**
 * Servlet implementation class Reservations
 */
@WebServlet("/Reservations")
public class Reservations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reservations() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("memberLogin.jsp");
			return;
		}
		Member member = (Member) session.getAttribute("loggedInUser");
		if (member == null) {
			response.sendRedirect("memberLogin.jsp");
			return;
		}
		try {
			int memberId = member.getMemberId();
			ReservationDao reservationDao = new ReservationDao();
			// Get borrowing history using DAO
			List<ReservationItem> reservations = reservationDao.getReservationsByMemberId(memberId);

			// Set the borrowing history as a request attribute and forward to JSP
			request.setAttribute("reservations", reservations);
			request.getRequestDispatcher("reservations.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Error retrieving reservations", e);
		}
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
