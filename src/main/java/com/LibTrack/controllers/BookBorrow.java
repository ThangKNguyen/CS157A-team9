package com.LibTrack.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

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
 * Servlet implementation class BookBorrow
 */
@WebServlet("/BookBorrow")
public class BookBorrow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookBorrow() {
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
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			int memberId = member.getMemberId();

			// create borrowing history for book
			BorrowingHistoryDao bhdao = new BorrowingHistoryDao();
			// create due Date
			Date dueDate = Date.valueOf(LocalDate.now().plusDays(14));
			boolean isBorrowingCreated = bhdao.createBorrowingRecord(memberId, bookId, dueDate);

			// update books status
			BookDao bdao = new BookDao();
			boolean isBookUpdated = bdao.setBookBorrowed(bookId);

			if (isBorrowingCreated && isBookUpdated) {
				String referer = request.getHeader("Referer");
				request.setAttribute("message", "Book borrowed successfully!");
				if (referer != null) {
					// Redirect the user back to the exact same URL they came from
					response.sendRedirect(referer);
				} else {
					// If there's no referer, redirect to a fallback page (like home)
					response.sendRedirect("homePage.jsp");
				}
			} else {
				request.setAttribute("errorMessage", "There was an issue borrowing the book. Please try again.");
				response.getWriter().append("an error occured");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
