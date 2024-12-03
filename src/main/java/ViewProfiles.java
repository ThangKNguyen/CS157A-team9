package com.LibTrack.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibTrack.dao.MemberDao;
import com.LibTrack.models.Member;

/**
 * Servlet implementation class Fines
 */
@WebServlet("/ViewProfiles")
public class ViewProfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewProfiles() {
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
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("staffLogin.jsp");
			return;
		}
		Member member = (Member) session.getAttribute("loggedInUser");
		if (member == null) {
			response.sendRedirect("staffLogin.jsp");
			return;
		}
		try {
			MemberDao memberDao = new MemberDao();
			// Get list of members using memberDAO
			List<Member> members = memberDao.getMembers(memberId);

			// Set the borrowing history as a request attribute and forward to JSP
			request.setAttribute("members", members);
			request.getRequestDispatcher("viewProfiles.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Error retrieving members", e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("loggedInUser") == null) {
	        response.sendRedirect("staffLogin.jsp");
	        return;
	    }

	    Member member = (Member) session.getAttribute("loggedInUser");
	    if (member == null) {
	        response.sendRedirect("staffLogin.jsp");
	        return;
	    }

	    try {
	        // Add/remove members
	        MemberDao memberDao = new MemberDao();
	        
	        String memberName = request.getParameter("name");
	        int memberID = Integer.valueOf(request.getParameter("memberId"));
	        
	        // if have name then add
	        if (!(memberID>0)) {
	        	request.setAttribute("error", "member not found");
	        	request.getRequestDispatcher("viewProfiles.jsp").forward(request, response);
	            return;
	        } else if(memberName==null) { // if have id and no name, remove
	        	
	        } else {
	        	Member newMember = new Member(
	        			request.getParameter("name"),
	        			request.getParameter("email"),
	        			request.getParameter("password"),
	        			request.getParameter("phone"),
	        			request.getParameter("address"));
	        	memberDao.insert(newMember);
	        }

	        // Redirect to avoid form resubmission issue
	        response.sendRedirect("viewProfiles");
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new ServletException("Error adding or removing members", e);
	    }
	}

}