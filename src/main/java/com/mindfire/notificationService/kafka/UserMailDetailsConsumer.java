package com.mindfire.notificationService.kafka;

import com.mindfire.basedomains.dto.UserRegistrationEvent;
import com.mindfire.notificationService.service.MailService;

import jakarta.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service

public class UserMailDetailsConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserMailDetailsConsumer.class);

	@Autowired
	private MailService mailService;

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void processEmail(UserRegistrationEvent userRegistrationEvent) throws MessagingException {
		LOGGER.info("userEvent recieved in email service {}", userRegistrationEvent);
		mailService.sendPasswordResetEmail(userRegistrationEvent);
	}

}
