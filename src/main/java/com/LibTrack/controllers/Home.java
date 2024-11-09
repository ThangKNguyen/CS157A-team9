package com.LibTrack.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibTrack.dao.BorrowingHistoryDao;
import com.LibTrack.dao.FineDao;
import com.LibTrack.models.BorrowingHistoryItem;
import com.LibTrack.models.FineItem;
import com.LibTrack.models.Member;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			int memberId = member.getMemberId();
			
			FineDao fineDao = new FineDao();
			List<FineItem> fines = fineDao.getFinesByMemberId(memberId);
			
			BorrowingHistoryDao borrowingHistoryDAO = new BorrowingHistoryDao();
			List<BorrowingHistoryItem> borrowingHistory = borrowingHistoryDAO.getBorrowingHistoryByMemberId(memberId);

			
			request.setAttribute("borrowingHistory", borrowingHistory);
			request.setAttribute("fines", fines);
			request.getRequestDispatcher("homePage.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Error retrieving fines", e);
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
