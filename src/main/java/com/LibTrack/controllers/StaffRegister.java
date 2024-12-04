package com.LibTrack.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LibTrack.dao.StaffDao;
import com.LibTrack.models.Staff;

/**
 * Servlet implementation class StaffRegister
 */
@WebServlet("/StaffRegister")
public class StaffRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String role=request.getParameter("role");
		String email=request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		
		Staff staff = new Staff(name, password, role, email, phone, address);
		StaffDao srDao = new StaffDao();
		staff = srDao.insert(staff);
		if (staff == null) {
			// Log the issue (optional)
			System.err.println("Error: Failed to create new staff member.");

			// Set an error message to display to the user
			request.setAttribute("error", "Registration failed. User may already exist");
			response.sendRedirect("memberRegister.jsp?error=1");
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Registration Success</title>");

			// Adding a JavaScript to redirect the user after a delay (e.g., 5 seconds)
			out.println("<script type='text/javascript'>");
			out.println("setTimeout(function(){ window.location.href = 'memberLogin.jsp'; }, 1500);");
			out.println("</script>");

			out.println("</head>");
			out.println("<body>");
			out.println("<h2>Registration successful! You will be redirected to the login pages.</h2>");
			out.println("</body>");
			out.println("</html>");

			out.close();
		}
	}

}
