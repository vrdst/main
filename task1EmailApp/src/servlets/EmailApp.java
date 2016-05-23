package servlets;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//@author endrju

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.mail.smtp.SMTPTransport;



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
        
		Properties properties = new Properties();
		properties.put("mail.smtps.host","smtp.gmail.com");
		properties.put("mail.smtps.auth","true");
	    
	    //set session for email sending
	    Session session = Session.getInstance(properties);
		
		//field input validation
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
	    	//create pdf doc and copy form data inside
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
            //create message, attach pdf and try to send (tested with mercury mail server)
            MimeMessage message = new MimeMessage(session);
            try {
            	Address fromAddress = new InternetAddress("endrjuencedece@gmail.com");
            	Multipart multipart = new MimeMultipart();
            	BodyPart part = new MimeBodyPart();
            	part.setText("Contact data sent with EmailApp form");
            	multipart.addBodyPart(part);
            	message.setContent(multipart);
            	
                SMTPTransport transport =(SMTPTransport)session.getTransport("smtps");
                transport.connect("smtp.gmail.com", "endrjuncdc@gmail.com", "dupa.123");
                
                part = new MimeBodyPart();
                String filename = "Contact.pdf";
                DataSource source = new FileDataSource(filename);
                part.setDataHandler(new DataHandler(source));
                part.setFileName(filename);
                multipart.addBodyPart(part);

                // Send the complete message parts
                message.setContent(multipart);
            	
            	message.setFrom(fromAddress);
            	message.setSubject("Contact email from EmailApp");
				message.setRecipients(Message.RecipientType.TO, email);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
				//Transport.send(message);
				System.out.println("email sent");
				
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("failed to send email");
			}
	           
	    } else {
	        // map errors and send back to index
	        request.setAttribute("formErrors", formErrors);
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	    }

	}
}
