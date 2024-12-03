package com.LibTrack.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibTrack.dao.ReservationDao;
import com.LibTrack.models.Member;

@WebServlet("/BookReserve")
public class BookReserveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            // Add reservation logic
            ReservationDao reservationDao = new ReservationDao();
            boolean isReserved = reservationDao.createReservation(memberId, bookId);

            if (isReserved) {
                String referer = request.getHeader("Referer");
                if (referer != null) {
                    response.sendRedirect(referer); // Redirect back to referring page
                } else {
                    response.sendRedirect("homePage.jsp"); // Fallback if referer is unavailable
                }
            } else {
                response.getWriter().write("Failed to reserve the book. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while processing the reservation.");
        }
    }
}


