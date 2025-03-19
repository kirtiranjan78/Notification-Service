package com.mindfire.Notification_Service.model;

public class UserRegistrationEvent{
    private String email;
    private String verificationLink;

    public UserRegistrationEvent(String email, String verificationLink) {
        this.email = email;
        this.verificationLink = verificationLink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationLink() {
        return verificationLink;
    }

    public void setVerificationLink(String verificationLink) {
        this.verificationLink = verificationLink;
    }
}
