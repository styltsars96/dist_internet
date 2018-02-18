package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Customer;

/**
 * Servlet implementation class deliveryServlet
 */
@WebServlet("/process")
public class processServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long afm = (long) request.getSession(false).getAttribute("afm");
		String appserver = getServletContext().getInitParameter("AppServer");
		// Request to api
		URL url = new URL(appserver + "/api/process/list/" + afm);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		try {
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json;charset=utf-8");
			con.setDoOutput(false);
			con.connect();
		} catch (Exception e) {
			System.err.println("WEB SERVER: DeliveryServlet Request error.");
			e.printStackTrace();
			response.setStatus(500);
			return;
		}

		// Response from api
		String inputLine = "";
		try {
			BufferedReader res = new BufferedReader(
					new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
			String temp = "";
			while ((temp = res.readLine()) != null)
				inputLine += temp;
		} catch (Exception e) {
			System.err.println("WEB SERVER: DeliveryServlet Response error.");
			response.setStatus(500);
			return;
		}
		if (!inputLine.isEmpty()) {
			response.setStatus(200);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(inputLine);
		} else {
			response.setStatus(500);
		}
	}

}
