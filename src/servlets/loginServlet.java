package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.spi.activation.Server;
import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;

import entities.Customer;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.sendRedirect("login.html");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name=request.getParameter("username");
		String pass=request.getParameter("password");
		String appserver = getServletContext().getInitParameter("AppServer");
		//Request to api
		URL url = new URL(appserver+"api/customer/login");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		try {
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.connect();
		BufferedWriter b =new BufferedWriter( new OutputStreamWriter(con.getOutputStream(),StandardCharsets.UTF_8));
			b.write("username="+name+"&password="+pass);
			b.close();
		}catch(Exception e) {
			System.err.println("WEB SERVER: Api-call request error.");
			response.setCharacterEncoding("UTF-8");
	    	response.setContentType("text/html");
	    	response.getWriter().println("<p style='color:red'>Προσπαθήστε ξανά.</p>");
	    	RequestDispatcher rd = request.getRequestDispatcher("/login.html");
	    	rd.include(request,response);
			return;
		}
		
		//Response from api call.
		String inputLine="";
		try {
		BufferedReader res = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8 ));
	    String temp ="";
	    while ((temp = res.readLine()) != null) inputLine+=temp;
		}catch(Exception e ) {
			System.err.println("WEB SERVER: LoginServlet Response error.");
			response.setCharacterEncoding("UTF-8");
	    	response.setContentType("text/html");
	    	response.getWriter().println("<p style='color:red'>Προσπαθήστε ξανά.</p>");
	    	RequestDispatcher rd = request.getRequestDispatcher("/login.html");
	    	rd.include(request,response);
			return;
		}
	    if(!inputLine.isEmpty()) {
	    	//login success.
		    ObjectMapper mapper = new ObjectMapper();
		    Customer customer = mapper.readValue(inputLine, Customer.class);
		    if(customer==null) { 
		    	System.err.println("Customer is null."); 
		    	response.sendRedirect("/login.html");
		    	return;
		    }
		    HttpSession session= request.getSession();
		    session.setAttribute("name", customer.getFullname());
		    session.setAttribute("afm", customer.getAfm());
	 		response.sendRedirect("jsp/home.jsp");
	    }else {
	    	//login fail.
	    	System.err.println("Web Server: Login fail.");
	    	response.setCharacterEncoding("UTF-8");
	    	response.setContentType("text/html");
	    	response.getWriter().println("<p style='color:red'>Προσπαθήστε ξανά.</p>");
	    	RequestDispatcher rd = request.getRequestDispatcher("/login.html");
	    	rd.include(request,response);
	    }
	}
}
