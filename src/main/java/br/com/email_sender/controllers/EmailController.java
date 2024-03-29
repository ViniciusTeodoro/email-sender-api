package br.com.email_sender.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.email_sender.services.EmailService;
import br.com.email_sender.dtos.EmailDto;
import br.com.email_sender.model.EmailModel;

@RestController
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@PostMapping("/send_email")
	public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDto emailDto) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
		return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
	}
}
