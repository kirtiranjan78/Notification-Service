package com.mindfire.notificationService.kafka;

import com.mindfire.basedomains.dto.UserRegistrationEvent;
import com.mindfire.notificationService.service.MailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Consumer service that listens to user details events from Kafka and
 * triggers an email notification using the MailService.
 */
@Service
@RequiredArgsConstructor
public class UserMailDetailsConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserMailDetailsConsumer.class);

	private final MailService mailService;
	
	/**
	 * Listens to the Kafka topic for incoming user registration events.
	 * Once an event is received, it triggers the email service to send 
	 * a password reset email.
	 *
	 * @param userRegistrationEvent The user registration event received from Kafka.
	 * @throws MessagingException If an error occurs while sending the email.
	 */
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void processEmail(UserRegistrationEvent userRegistrationEvent) throws MessagingException {
		LOGGER.info("userEvent recieved in email service {}", userRegistrationEvent);
		mailService.sendPasswordResetEmail(userRegistrationEvent);
	}
	
	

}
