package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
import javax.websocket.Session;

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
		Map<String, String> formErrors = new HashMap<String, String>();

		if (!author.matches("[a-zA-Z]+") || !title.matches("[a-zA-Z]+") || !isbn.matches("[0-9]+")) {
			formErrors.put("fields", labels.getString("fieldsEmpty"));
		}
		
		if (formErrors.isEmpty()) {
			
			File file = new File("books.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Author: " + author + ", title: " + title + ", ISBN" + isbn);
			bw.close();
			
		} else {
			// map errors and send back to index
			request.setAttribute("formErrors", formErrors);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}
	
/*    public void ReadFileContents(){ 

        BufferedReader reader = new BufferedReader(new FileReader(books.txt));
        StringBuilder sb = new StringBuilder();
        String line;

        while((line = reader.readLine())!= null){
            sb.append(line+"\n");
        }
        out.println(sb.toString());
    }*/
	
}
