package com.LibTrack.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibTrack.dao.BorrowingHistoryDao;
import com.LibTrack.dao.ReviewDao;
import com.LibTrack.models.Member;

/**
 * Servlet implementation class AddReview
 */
@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddReview() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
		// we need BookID, MemberID, to insert review
		try {
			int memberId = member.getMemberId();
			int rating = Integer.parseInt(request.getParameter("rating"));
			// get bookId from borrowId
			int borrowId = Integer.parseInt(request.getParameter("borrowId"));
			BorrowingHistoryDao bhdao = new BorrowingHistoryDao();
			int bookId = bhdao.getBookIdFromBorrowId(borrowId);
			String reviewText = request.getParameter("review");

			ReviewDao reviewDao = new ReviewDao();
			reviewDao.addReview(memberId, bookId, rating, reviewText);
			response.sendRedirect("BorrowingHistory");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
