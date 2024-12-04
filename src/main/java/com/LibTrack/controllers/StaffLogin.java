package com.LibTrack.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.LibTrack.dao.StaffDao;
import com.LibTrack.models.Staff;

@WebServlet("/StaffLogin")
public class StaffLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StaffLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		StaffDao staffDao = new StaffDao();
		Staff staff = staffDao.validateStaff(email, password);
		if (staff != null) {
			// login user
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", staff);
			session.setAttribute("staffId", staff.getStaffId());
			// redirect based on role
			if(staff.getRole().equals("Admin")) {
				session.setAttribute("staffRole", staff.getRole()); // potential attribute error
				response.sendRedirect("AdminHome");  // Correct redirect
			} else {
				session.setAttribute("staffRole", staff.getRole()); // potential attribute error
				response.sendRedirect("StaffHome");  // Correct redirect
			}
			
			
//			session.setAttribute("loggedInUser", staff);
//			session.setAttribute("staffId", staff.getStaffId());
//			response.sendRedirect("StaffHome");  // Correct redirect
		} else {
			// user does not exist
			response.sendRedirect("staffLogin.jsp?error=invalid_credentials");  // Error handling
		}
	}
}