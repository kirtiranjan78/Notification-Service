package com.mindfire.Notification_Service.serviceImpl;

import com.mindfire.Notification_Service.service.MailService;
import com.mindfire.basedomains.dto.UserRegistrationEvent;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

	private final JavaMailSender javaMailSender;

	public MailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(UserRegistrationEvent userDetails) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(userDetails.getEmail());
		message.setSubject("Password Reset Request for Your Account");
		message.setText(userDetails.getVerificationLink());
		javaMailSender.send(message);
	}
}
