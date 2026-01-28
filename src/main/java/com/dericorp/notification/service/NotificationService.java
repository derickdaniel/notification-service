package com.dericorp.notification.service;

import java.util.Map;

import jakarta.mail.MessagingException;

public interface NotificationService {

	void sendEmail(String email, String subject, String body) throws MessagingException;

	void sendEmailUsingFreeMarker(Map<String, Object> templateModel);

}
