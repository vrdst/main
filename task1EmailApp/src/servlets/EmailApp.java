package servlets;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//@author endrju

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class EmailApp extends HttpServlet {

	/**
	 * serialization
	 */
	private static final long serialVersionUID = -3585248524522011431L;
	
	Locale locale = new Locale("en", "US");

	ResourceBundle labels = ResourceBundle.getBundle("i18n.errors", locale);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, String> formErrors = new HashMap<String, String>();
		
		String name = request.getParameter("formName");
		String surname = request.getParameter("formSurname");
		String phone = request.getParameter("formPhoneNo");
		String email = request.getParameter("formEmailAddress");
		
		if(!name.matches("[a-zA-Z]+")){
			formErrors.put("name", labels.getString("nameError"));
		}
		if(!surname.matches("[a-zA-Z]+")){
			formErrors.put("surname", labels.getString("surnameError"));
		}
		if(!phone.matches("[0-9]+")){
			formErrors.put("phone", labels.getString("phoneNoError"));
		}
			
		if(!(email.contains("@") && (email.split("@")[1].length() - email.split("@")[1].replace(".", "").length()) == 1)){
			formErrors.put("email", labels.getString("emailError"));
		}
		
	    if (formErrors.isEmpty()) {
	        Document document = new Document();
	        	
            try {
				PdfWriter.getInstance(document, new FileOutputStream("Contact.pdf"));
	           		document.open();
					document.add(new Paragraph("Name: " + name));
					document.add(new Paragraph("Surname: " + surname));
					document.add(new Paragraph("Phone: " + phone));
					document.add(new Paragraph("Email: " + email));
				} catch (DocumentException e) {
					e.printStackTrace();
				}	catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

	            document.close();
	    } else {
	        // map errors and send back to index
	        request.setAttribute("formErrors", formErrors);
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	    }

	}
}
