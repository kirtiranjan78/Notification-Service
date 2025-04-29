package com.mindfire.notificationService.kafka;

import com.mindfire.commonlibraries.dto.CertificateNotificationEvent;
import com.mindfire.commonlibraries.dto.UserNotificationEvent;
import com.mindfire.notificationService.service.MailService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CertificateMailDetailsConsumer {
    private static final Logger log = LoggerFactory.getLogger(CertificateMailDetailsConsumer.class);
    @Autowired
    private MailService mailService;

    @KafkaListener(topics = "${spring.kafka.topic.certificate-notification}", groupId = "${spring.kafka.consumer.certificate.group-id}")
    public void processEmail(CertificateNotificationEvent certificateNotificationEvent) throws MessagingException {
        log.info("userEvent recieved in email service {}", certificateNotificationEvent);
        mailService.sendCertificateGenerationEmail(certificateNotificationEvent);
    }
}
