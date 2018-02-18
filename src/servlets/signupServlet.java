package servlets;

import entities.Customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class signinServlet
 */
@WebServlet("/signup")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Parse parameters and make the customer object.
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String afmText = request.getParameter("afm");
		String phoneText = request.getParameter("phone");

		long afm;
		long phone;

		try {
			afm = Long.parseLong(afmText);
			phone = Long.parseLong(phoneText);
		} catch (Exception e) {
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().println("<p style='color:red'>Προσπαθήστε ξανά.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("/signup.html");
			rd.include(request, response);
			return;
		}
		Customer customer = new Customer(name, pass, afm, phone);

		// Make the post request
		URL url = new URL("http://localhost:8080/Dist_Vol2/api/customer/new");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("POST");
		con.addRequestProperty("Accept", "application/json");
		con.addRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);
		con.connect();

		// Send the object with request.
		ObjectMapper mapper = new ObjectMapper();
		try {
			BufferedWriter b = new BufferedWriter(
					new OutputStreamWriter(con.getOutputStream(), StandardCharsets.UTF_8));
			System.out.println("WEB SERVER: The mapper produces this: " + mapper.writeValueAsString(customer));
			b.write(mapper.writeValueAsString(customer));
			b.close();
		} catch (Exception x) {
			System.out.println("We hava a request's error!");
		}
		// Take response and set the customer.
		String inputLine = "";
		try {
			InputStream inputStream = con.getInputStream();
			BufferedReader res = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			String temp = "";
			while ((temp = res.readLine()) != null)
				System.out.println(inputLine += temp);
		} catch (Exception e) {
			System.out.println("WEB SERVER: We have a response error.");
			response.setStatus(500);
			response.getWriter().append("There is a server error.");
			return;
		}
		customer = mapper.readValue(inputLine, Customer.class);
		if (customer == null) {
			response.getWriter().append("Error on server.");
		}

		request.getSession().setAttribute("name", customer.getFullname());
		request.getSession().setAttribute("afm", customer.getAfm());
		response.sendRedirect("home.jsp");
	}
}
