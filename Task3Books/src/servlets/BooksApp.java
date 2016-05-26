package servlets;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BooksApp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7736833444085204987L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Locale locale = new Locale("en", "US");
		ResourceBundle labels = ResourceBundle.getBundle("i18n.errors", locale);

		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String isbn = request.getParameter("isbn");
		String bookAdded = "";
		Map<String, String> formErrors = new HashMap<String, String>();

		if (!author.matches("[a-zA-Z]+") || title == "" || isbn == ""){
			formErrors.put("fields", labels.getString("fieldsEmpty"));
		}
		if(!isbn.matches("[0-9]+")){
			formErrors.put("fields", labels.getString("isbnNumeric"));
		}
		
		if (formErrors.isEmpty()) {
			BufferedWriter addedPosition = new BufferedWriter(new FileWriter("books.txt", true));
			addedPosition.append("Author: " + author + "\n" +  "Title: " + title + "\n" + "ISBN: " + isbn + "\n");
			addedPosition.newLine();
			addedPosition.close();
			bookAdded = "Book has been added";
			request.setAttribute("bookAdded", bookAdded);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			// map errors and send back to index
			request.setAttribute("formErrors", formErrors);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

}
