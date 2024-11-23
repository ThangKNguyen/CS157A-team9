package com.LibTrack.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibTrack.dao.BookDao;
import com.LibTrack.dao.BorrowingHistoryDao;
import com.LibTrack.models.Member;

/**
 * Servlet implementation class BookReturn
 */
@WebServlet("/BookReturn")
public class BookReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookReturn() {
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
		// we need BookID to update Book status, MemberID and BorrowID to update
		// borrowing History
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			int borrowId = Integer.parseInt(request.getParameter("borrowId"));

			// update borrowing history status
			BorrowingHistoryDao bhdao = new BorrowingHistoryDao();
			boolean isBorrowingUpdated = bhdao.markBookAsReturned(borrowId);

			// update books status
			BookDao bdao = new BookDao();
			boolean isBookUpdated = bdao.updateBookAvailability(bookId);

			if (isBorrowingUpdated && isBookUpdated) {
				request.setAttribute("message", "Book returned successfully!");
				request.getRequestDispatcher("BorrowingHistory").forward(request, response);
			} else {
				request.setAttribute("errorMessage", "There was an issue returning the book. Please try again.");
				response.getWriter().append("an error occured");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
