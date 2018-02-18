package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import entities.Car;
import entities.Customer;
import entities.Delivery;
import entities.Process;
import entities.Shop;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/delivery")
public class deliveryServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("jsp/newProcess.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
		System.out.println("POST Request has: "+ map);//DEBUG
		/////////////////////////////////////////////////
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String appserver = getServletContext().getInitParameter("AppServer");
		String lontext, lattext;
		double lon=0;
		double lat=0;
		try { 
			lontext=request.getParameter("lon");
			lon = Double.parseDouble(lontext);
			lattext=request.getParameter("lat");
			lat = Double.parseDouble(lattext);
		}catch(Exception e) {
			//Wrong numbers
			System.err.println("Web Server: New delivery fail.");
			response.getWriter().println("<p style='color:red'>Προσπαθήστε ξανά.</p>");
			RequestDispatcher rd =request.getRequestDispatcher("jsp/newProcess.jsp");
			rd.include(request, response);
			return;
		}
		String model=request.getParameter("model");
		String plate=request.getParameter("plate");
		Long afm = (Long) request.getSession(false).getAttribute("afm");
		String fuel = (String) request.getParameter("fuel");
		
		
		if (model==null||model.isEmpty() || plate==null || plate.isEmpty()||afm==0||fuel==null||fuel.isEmpty()) {
			response.setCharacterEncoding("UTF-8");
	    	response.setContentType("text/html");
	    	response.getWriter().println("<p style='color:red'>Προσπαθήστε ξανά.</p>");
	    	RequestDispatcher rd = request.getRequestDispatcher("jsp/newProcess.jsp");
	    	rd.include(request,response);
			return;
		}
		
		Customer customer = new Customer(null,null,afm,0);
		Car car = new Car(customer,model,plate,fuel,2003,null);
		
		//Find closest shop, from an api call.
		URL shopurl = new URL(appserver+"api/shop/closest_shop");
		HttpURLConnection con = (HttpURLConnection)shopurl.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		con.connect();
		
		ObjectMapper mapper = new ObjectMapper();
		BufferedWriter b =new BufferedWriter( new OutputStreamWriter(con.getOutputStream(), StandardCharsets.UTF_8));
		System.out.println("WEB SERVER: The mapper produces this: "+mapper.writeValueAsString(lon));
		b.write("lon="+lontext+"&lat="+lattext);
		b.close();
		String inputLine="";
		BufferedReader res = new BufferedReader(new InputStreamReader(con.getInputStream(),StandardCharsets.UTF_8));
	    String temp ="";
	    while ((temp = res.readLine()) != null) System.out.println(inputLine+=temp);
	    
	    if(inputLine.isEmpty()) {
	    	//TODO: redirect
	    	System.err.println("Web Server: Far away.");
			response.getWriter().println("<p style='color:red'>Η τοποθεσία είναι πολύ μακρυά. Εισάγετε μια κοντινότερη τοποθεσία.</p>");
			RequestDispatcher rd =request.getRequestDispatcher("jsp/newProcess.jsp");
			rd.include(request, response);
			return;
	    }
	    //Set up the shop.
	    Shop shop = mapper.readValue(inputLine, Shop.class);
	    System.out.println(shop.getLat());//TODO: DELETE!
	    
		
		Process process = new Process(car,null,shop);
		Delivery delivery = new Delivery(process,lat,lon);
		
		//Send the object with new delivery...
		URL url = new URL(appserver+"api/delivery/new");
		con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type","application/json;charset=utf-8");
		con.setRequestProperty("charset", "UTF-8");
		con.setDoOutput(true);
		con.connect();
		
		//Send the object with request.
		 mapper = new ObjectMapper();
		 b =new BufferedWriter( new OutputStreamWriter(con.getOutputStream(), StandardCharsets.UTF_8));
		System.out.println("WEB SERVER: The mapper produces this: "+mapper.writeValueAsString(delivery));
		b.write(mapper.writeValueAsString(delivery));
		b.close();
		
		//The response...		
		 inputLine="";
		 res = new BufferedReader(new InputStreamReader(con.getInputStream(),StandardCharsets.UTF_8));
	     temp ="";
	    while ((temp = res.readLine()) != null)  inputLine+=temp;

	    response.getWriter().println("<p style='color:green'>Η αίτηση αποθηκεύτηκε.</p>");
		RequestDispatcher rd =request.getRequestDispatcher("jsp/home.jsp");
		rd.include(request, response);
	}



	
}
