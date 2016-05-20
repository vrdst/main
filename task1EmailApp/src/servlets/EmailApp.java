package servlets;

//@author endrju

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailApp extends HttpServlet{

	/**
	 * serialization 
	 */
	private static final long serialVersionUID = -3585248524522011431L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		out.print("pocz¹tek servletu");
		
		
	}

}
