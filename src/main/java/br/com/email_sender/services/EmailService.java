package br.com.email_sender.services;

import java.time.LocalDateTime;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.email_sender.enums.StatusEmail;
import br.com.email_sender.model.EmailModel;
import br.com.email_sender.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	JavaMailSender emailSender;

	@SuppressWarnings("finally")
	public EmailModel sendEmail(EmailModel emailModel) {
		
		emailModel.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText()); 
			emailSender.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
			
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
			// TODO: handle exception
		}
		finally {
			return emailRepository.save(emailModel);
		}
	}
	
}
