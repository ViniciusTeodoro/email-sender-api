package br.com.email_sender.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.email_sender.dtos.EmailDto;
import br.com.email_sender.model.EmailModel;
import br.com.email_sender.services.EmailService;

@Component
public class EmailConsumer {
	@Autowired
	EmailService emailService;
	
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void listen(@Payload EmailDto emailDto) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
		System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
	}
}
