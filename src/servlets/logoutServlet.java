package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class logoutServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		session.removeAttribute("afm");
		session.removeAttribute("name");
		session.invalidate();
		response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html");
    	response.getWriter().println("<h1 style='color:red'>May the Force be with you!<h1>");
    	RequestDispatcher rd = request.getRequestDispatcher("/login.html");
    	rd.include(request,response);
	}

	
}
