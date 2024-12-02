package com.LibTrack.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibTrack.dao.BorrowingHistoryDao;
import com.LibTrack.dao.CategoryDao;
import com.LibTrack.models.Category;
import com.LibTrack.models.Member;

/**
 * Servlet implementation class Categories
 */
@WebServlet("/Categories")
public class Categories extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categories() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

        // Fetch all categories
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.getAllCategories();
        request.setAttribute("categories", categories);

        // Fetch borrowed books for the logged-in user
        BorrowingHistoryDao borrowingHistoryDao = new BorrowingHistoryDao();
        List<Integer> borrowedBookIds = borrowingHistoryDao.getBorrowedBookIdsByMemberId(member.getMemberId());
        request.setAttribute("borrowedBookIds", borrowedBookIds);

        // Forward to categories.jsp where users can select a category
        RequestDispatcher dispatcher = request.getRequestDispatcher("categories.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

