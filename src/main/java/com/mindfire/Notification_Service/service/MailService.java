package com.mindfire.Notification_Service.service;

import org.springframework.stereotype.Service;

import com.mindfire.basedomains.dto.UserRegistrationEvent;

@Service
public interface MailService {
    public void sendEmail(UserRegistrationEvent userDetails);
}
