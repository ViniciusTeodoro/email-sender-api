package br.com.email_sender.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.email_sender.model.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID>{
	

}
