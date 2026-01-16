package com.dericorp.notification.service;

import jakarta.mail.MessagingException;

public interface NotificationService {

    void sendEmail(String email, String subject, String body) throws MessagingException;
}
