package com.mindfire.notificationService.serviceImpl;

import com.mindfire.basedomains.dto.UserRegistrationEvent;
import com.mindfire.notificationService.service.MailService;
import com.mindfire.notificationService.util.PasswordResetTemplateProcessor;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	private final JavaMailSender javaMailSender;

	private final PasswordResetTemplateProcessor passwordResetTemplateProcessor;

	public void sendEmail(String to, String subject, String content, boolean isHtml) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, isHtml);
		javaMailSender.send(message);
	}

	public void sendPasswordResetEmail(UserRegistrationEvent userDetails) throws MessagingException {
		String emailContent = passwordResetTemplateProcessor.generateEmailHtml(userDetails.getVerificationLink(),
				userDetails.getFirstName());
		sendEmail(userDetails.getEmail(), "Password Reset Request for Your Account", emailContent, true);
	}

}
