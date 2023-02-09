package com.devsuperior.integrations.services;

import com.devsuperior.integrations.dto.EmailDTO;

public interface EmailService {
	
	public void sendEmail(EmailDTO dto);
}
