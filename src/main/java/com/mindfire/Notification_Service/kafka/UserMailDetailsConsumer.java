package com.mindfire.Notification_Service.kafka;

import com.mindfire.Notification_Service.service.MailService;
import com.mindfire.basedomains.dto.UserRegistrationEvent;

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
	public void processEmail(UserRegistrationEvent userRegistrationEvent) {
		LOGGER.info("userEvent recieved in email service {}", userRegistrationEvent);
		mailService.sendEmail(userRegistrationEvent);
	}

}
