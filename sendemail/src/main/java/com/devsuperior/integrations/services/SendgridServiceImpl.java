package com.devsuperior.integrations.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.integrations.services.exceptions.EmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;

public class SendgridServiceImpl extends SendgridServiceAbstract {

	private Logger LOG = LoggerFactory.getLogger(SendgridServiceImpl.class);

	@Autowired
	private SendGrid sendGrid;

	@Override
	protected void callEmailAPI(Mail mail) {

		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			LOG.info("Sending email from: " + mail.getFrom().getEmail());
			Response response = sendGrid.api(request);
			if (response.getStatusCode() >= 400 && response.getStatusCode() <= 500) {
				LOG.error("Error sending email: " + response.getBody());
				throw new EmailException(response.getBody());
			}
			LOG.info("Email sent! Status = " + response.getStatusCode());
		} catch (IOException e) {
			throw new EmailException(e.getMessage());
		}
	}
}
