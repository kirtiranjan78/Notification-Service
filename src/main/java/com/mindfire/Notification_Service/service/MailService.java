package com.mindfire.Notification_Service.service;

import com.mindfire.Notification_Service.model.UserRegistrationEvent;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    public void sendEmail(UserRegistrationEvent userDetails);
}
