package com.devsuperior.integrations.services;

import com.devsuperior.integrations.dto.EmailDTO;
import com.sendgrid.helpers.mail.Mail;

public interface SendgridService {
	
	public void sendEmail(EmailDTO dto);
	
	Mail prepareEmail(EmailDTO dto);
}
