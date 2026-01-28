package com.dericorp.notification.service.builder;

import com.dericorp.notification.dto.NotificationRequest;
import org.springframework.stereotype.Service;

public class NotificationMessageBuilder {

    private static final String APP_NAME = "Developer Issue Book";

    public static String buildSubject(NotificationRequest req) {
        switch (req.getAction()) {
            case "REGISTER":
                return "Welcome to " + APP_NAME;
            case "LOGIN":
                return "Login Alert: New Sign In Detected";
            case "CREATED":
                return "New Issue Created";
            case "UPDATED":
                return "Issue Updated";
            case "DELETED":
                return "Issue Deleted";
            default:
                return "Notification from " + APP_NAME;
        }
    }

    public static String buildMessage(NotificationRequest req) {
        String actionMessage;

        switch (req.getAction()) {
            case "REGISTER":
                actionMessage = "Your account was created successfully on " + req.getDate() + ".";
                break;
            case "LOGIN":
                actionMessage = "A new login to your account is detected on " + req.getDate();
                break;
            case "CREATED":
                actionMessage = "Your issue is created successfully on " + req.getDate() + ".";
                break;
            case "UPDATED":
                actionMessage = "Your issue is updated successfully on " + req.getDate() + ".";
                break;
            case "DELETED":
                actionMessage = "Your issue is deleted on " + req.getDate() + ".";
                break;
            default:
                actionMessage = "You have a new notification from " + APP_NAME + ".";
        }

        return String.format(
                "Hey %s,\n\n%s\n\nRegards,\n%s",
                req.getUserName(),
                actionMessage,
                APP_NAME
        );
    }

}

