package com.mindfire.Notification_Service.service;

import com.mindfire.commonlibraries.dto.CertificateNotificationEvent;
import com.mindfire.commonlibraries.dto.UserNotificationEvent;
import com.mindfire.notificationService.service.impl.MailServiceImpl;
import com.mindfire.notificationService.util.PasswordResetTemplateProcessor;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for MailServiceImpl")
public class MailServiceImplTests {
    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private PasswordResetTemplateProcessor passwordResetTemplateProcessor;

    @InjectMocks
    private MailServiceImpl mailService;

    @Mock
    private MimeMessage mimeMessage;

    @BeforeEach
    void setUp() {
        given(javaMailSender.createMimeMessage()).willReturn(mimeMessage);
    }


    @Test
    @DisplayName("sendEmail - should send a simple email without error")
    void shouldSendSimpleEmailSuccessfully() throws MessagingException {
        // given
        String to = "test@example.com";
        String subject = "Test Subject";
        String content = "<h1>Hello</h1>";
        boolean isHtml = true;

        // when
        mailService.sendEmail(to, subject, content, isHtml);

        // then
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    @Test
    @DisplayName("sendPasswordResetEmail - should generate HTML and send reset email")
    void shouldSendPasswordResetEmail() throws MessagingException {
        // given
        UserNotificationEvent userDetails = new UserNotificationEvent();
        userDetails.setEmail("user@example.com");
        userDetails.setFirstName("John");
        userDetails.setVerificationLink("http://localhost/reset");

        String htmlContent = "<html>Reset Link</html>";

        given(passwordResetTemplateProcessor.generateEmailHtml(anyString(), anyString()))
                .willReturn(htmlContent);

        // when
        mailService.sendPasswordResetEmail(userDetails);

        // then
        verify(passwordResetTemplateProcessor, times(1))
                .generateEmailHtml(userDetails.getVerificationLink(), userDetails.getFirstName());
        verify(javaMailSender, times(1))
                .send(mimeMessage);
    }

    @Test
    @DisplayName("sendCertificateGenerationEmail - should send a certificate mail")
    void shouldSendCertificateGenerationEmail() throws MessagingException {
        // given
        CertificateNotificationEvent certificateNotificationEvent = new CertificateNotificationEvent();
        certificateNotificationEvent.setEmail("cert@example.com");
        certificateNotificationEvent.setFirstName("Alice");

        // when
        mailService.sendCertificateGenerationEmail(certificateNotificationEvent);

        // then
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

}
