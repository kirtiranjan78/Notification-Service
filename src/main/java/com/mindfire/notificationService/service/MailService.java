package com.mindfire.notificationService.service;


import com.mindfire.commonlibraries.dto.CertificateNotificationEvent;
import com.mindfire.commonlibraries.dto.UserNotificationEvent;
import jakarta.mail.MessagingException;

import org.springframework.stereotype.Service;

/**
 * Service interface for handling email notifications.
 */
@Service
public interface MailService {

    /**
     * Sends an email to the specified recipient.
     * This method supports both plain text and HTML-formatted emails.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param content The content/body of the email.
     * @param isHtml  A boolean flag indicating whether the email content is in HTML format.
     * @throws MessagingException 
     */
    void sendEmail(String to, String subject, String content, boolean isHtml) throws MessagingException;

    /**
     * Sends a password reset email to a user.
     * This method generates an HTML email using the password reset template and sends it to the user.
     *
     * @param userDetails The user details containing the email address, first name, and verification link.
     * @throws MessagingException 
     */
    void sendPasswordResetEmail(UserNotificationEvent userDetails) throws MessagingException;

    /**
     * Sends a informational mail regarding the certificate issuance
     *
     * @param certificateNotificationEvent The user details containing the email address of the user
     * @throws MessagingException
     */
    void sendCertificateGenerationEmail(CertificateNotificationEvent certificateNotificationEvent) throws MessagingException;
}
