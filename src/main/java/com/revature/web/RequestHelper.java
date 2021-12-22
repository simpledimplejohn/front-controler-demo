package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	//logger
	private static Logger logger = Logger.getLogger(RequestHelper.class);
	//employee service instance
	private static EmployeeService eserv = new EmployeeService(new EmployeeDAO());
	//object mapper
	private static ObjectMapper om = new ObjectMapper(); // how to convert JSON to string
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//extract paramters (username and password)
		// reading input from the request body:
		/*
		 * ANNOTHER WAY TO DO THIS
		BufferedReader reader = request.getReader(); //username=something&password=somethingelse
		StringBuilder s = new StringBuilder();
		String line = reader.readLine(); //returns the string created when user inputs
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		String[] valuesSerByAmp = body.split("&"); // [username=something, password=somethingelse]
		List<String> values = new ArrayList<String>();
		for(String pair : valuesSerByAmp) {
			values.add(pair.substring(pair.indexOf("=") +1)); //only the value after = is left
		}
		// capture actual username/password
		String username = values.get(0);
		String password = values.get(1);
		*/
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		logger.info("user attempted to login with " + username);
		// call confirm loggin
		Employee e = eserv.confirmLogin(username, password);
		// save user to the session and print to the screen
		if(e != null) {
			// grab session and add user
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e); //binding that new user to the session
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			// convert with Jacson and print
			out.println(om.writeValueAsString(e));
			
		} else {
			//if object is null use special code
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("no user found");
			response.setStatus(204);
		}
		
		//call sericec layer
		//return response
	}
	
	public static void processError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// redirect to custom 404 page
		request.getRequestDispatcher("err.html").forward(request, response);
	}
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// set content type
		response.setContentType("text/html");
		// call findAll() and save to a list
		List<Employee> allEmployees = eserv.findAll(); //calls the DAO 
		// marshall the list of java objects to JSON using jackson
		String jsonString = om.writeValueAsString(allEmployees);
		// call the printwriter to write it to the clinet
		PrintWriter out = response.getWriter();
		out.println(jsonString);
		
	}
}






















