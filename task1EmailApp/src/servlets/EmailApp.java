package servlets;

//@author endrju

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailApp extends HttpServlet {

	/**
	 * serialization
	 */
	private static final long serialVersionUID = -3585248524522011431L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> formErrors = new HashMap<String, String>();
		String email;
		
		if(!request.getParameter("formName").matches("[a-zA-Z]+")){
			formErrors.put("name", "name should contain only letters");
			System.out.println("name error");
		}
		if(!request.getParameter("formSurname").matches("[a-zA-Z]+")){
			formErrors.put("surname", "surname should contain only letters");
			System.out.println("surname error");
		}
		if(!request.getParameter("formPhoneNo").matches("[0-9]+")){
			formErrors.put("phone", "phone number should contain only digits");
			System.out.println("phone error");
		}
		
		email = request.getParameter("formEmailAddress");		
		if(!(email.contains("@") && (email.split("@")[1].length() - email.split("@")[1].replace(".", "").length()) == 1)){
			formErrors.put("email", "incorrect email format");
			System.out.println("email error");
		}
		
	    if (formErrors.isEmpty()) {
	        // fields correctly filled
	        ;
	    } else {
	        // Put errors in request scope and forward back to JSP.
	        request.setAttribute("formErrors", formErrors);
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	    }

	}

}
