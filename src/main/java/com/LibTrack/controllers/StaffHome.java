package com.LibTrack.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibTrack.models.Staff;

/**
 * Servlet implementation class StaffHome
 */
@WebServlet("/StaffHome")
public class StaffHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffHome() {
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
			response.sendRedirect("staffLogin.jsp");
			return;
		}
		Staff staff = (Staff) session.getAttribute("loggedInUser");
		if (staff == null) {
			response.sendRedirect("staffLogin.jsp");
			return;
		}
		
		try {
			int staffId = staff.getStaffId();
			
			request.getRequestDispatcher("staffHome.jsp").forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException("Error retrieving staff", e);
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
