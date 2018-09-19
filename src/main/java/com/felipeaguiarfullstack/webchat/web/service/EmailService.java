package com.felipeaguiarfullstack.webchat.web.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class EmailService {
	
	public void enviar(String email, String enderecoIp) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDate localDate = LocalDate.now();
		formatter.format(localDate);
		
	    Email from = new Email("feliperdaguiar@gmail.com");
	    
	    String subject = "Primeiro Login no Web Chat";
	    Email to = new Email(email);
	    Content content = new Content("text/plain", enderecoIp + " - " + formatter.format(localDate));
	    Mail mail = new Mail(from, subject, to, content);
	    
	    SendGrid sendGrid = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sendGrid.api(request);
	      
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      // TODO
	    }
		
	}

}
