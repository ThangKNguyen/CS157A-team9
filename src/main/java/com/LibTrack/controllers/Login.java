package com.LibTrack.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.LibTrack.dao.MemberDao;
import com.LibTrack.models.Member;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
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
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.validateMember(email, password);
		if (member != null) {
			// login user
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", member);
			session.setAttribute("memberId", member.getMemberId());
			response.sendRedirect("homePage.jsp");  // Correct redirect
		} else {
			// user does not exist
			response.sendRedirect("memberLogin.jsp?error=invalid_credentials");  // Error handling
		}
	}
}
