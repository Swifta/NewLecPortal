package com.lonestarcell.mtn.spring.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class EmailServiceImpl implements IEmailService {

	@Autowired
	public JavaMailSender emailSender;

	public boolean sendSimpleMessage(String to, String subject, String html) {

		try {

			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					false, "utf-8");
			mimeMessage.setContent(html, "text/html");
			helper.setTo(to);
			helper.setSubject(subject);
			emailSender.send(mimeMessage);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return false;

	}
	
	
	
	
	
	
	
	
	
}