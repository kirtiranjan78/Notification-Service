package com.mindfire.Notification_Service.kafka;

import com.mindfire.Notification_Service.model.UserRegistrationEvent;
import com.mindfire.Notification_Service.service.MailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserMailDetailsConsumer {
    private MailService mailService;

    @KafkaListener(topics = "${spring.kafka.topic.name}" ,groupId = "${spring.kafka.consumer.group-id}")
    public void processEmail(UserRegistrationEvent userMailDetails){
        mailService.sendEmail(userMailDetails);
    }

}
