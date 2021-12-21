package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// responsible for determining what resource the client is requesting
	// it will clean the URL and only capture the end part (/login)
	// Once it caputres this end point (destination)
	// -calls a requestHelper which supplys the functionality
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. clean string
		final String URI = request.getRequestURI().replace("/FrontControllerDemo/", "");
		// 
		switch(URI) {
		case "login":
			// call login menthod
			RequestHelper.processLogin(request, response);
			break;
		case "error":
			// call error method
			RequestHelper.processError(request, response);
			break;
		default:
			// call error method
			break;
			
		}
		
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if a client sends a post request it will invoke doGet()instead
		doGet(request, response);
	}

}
