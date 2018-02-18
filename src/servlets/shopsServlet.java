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

/**
 * Servlet implementation class shopsServlet
 */
@WebServlet("/shops")
public class shopsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String appserver = this.getServletContext().getInitParameter("AppServer");
		URL url = new URL(appserver + "api/shop/all");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		try {
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			con.connect();
		} catch (Exception e) {
			System.err.println("WEB SERVER: ShopServlet Request error.");
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
			System.err.println("WEB SERVER: LoginServlet Response error.");
			System.out.println("Response is" + inputLine);
			response.setStatus(500);
			return;
		}
		if (!inputLine.isEmpty()) {
			response.setStatus(200);
			response.getWriter().append(inputLine);
		} else {
			// login fail.
			response.setStatus(500);
			response.getWriter().append(null);
		}
	}

}
