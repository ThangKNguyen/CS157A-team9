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
import com.LibTrack.dao.ReservationDao;
import com.LibTrack.models.Member;

@WebServlet("/BookReturn")
public class BookReturn extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            int borrowId = Integer.parseInt(request.getParameter("borrowId"));

            BorrowingHistoryDao bhdao = new BorrowingHistoryDao();
            boolean isReturned = bhdao.markBookAsReturned(borrowId);

            if (isReturned) {
                request.setAttribute("message", "Book returned successfully!");
                request.getRequestDispatcher("BorrowingHistory").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to return the book. Please try again.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}


