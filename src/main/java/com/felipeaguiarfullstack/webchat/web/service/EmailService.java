package com.felipeaguiarfullstack.webchat.web.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	public void enviar(String email, String enderecoIp) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime localDate = LocalDateTime.now();
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

			logger.info(Integer.toString(response.getStatusCode()));
			logger.info(response.getBody());
			logger.info(response.getHeaders().toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

}
