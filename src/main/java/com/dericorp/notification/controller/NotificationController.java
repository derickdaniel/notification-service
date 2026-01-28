package com.dericorp.notification.controller;

import com.dericorp.notification.dto.NotificationRequest;
import com.dericorp.notification.service.NotificationService;
import com.dericorp.notification.service.builder.NotificationMessageBuilder;
import jakarta.mail.MessagingException;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    NotificationService notificationService;

    @PostMapping("/send-email")
    public String createIssue(@RequestBody Map<String, Object> body) {

        body.forEach((k, v) -> System.out.println(k + " : " + v));

        notificationService.sendEmailUsingFreeMarker(body);

        return "success";
    }

    @PostMapping("/action")
    public ResponseEntity<String> notifyAction(
            @RequestBody NotificationRequest request) throws MessagingException {

        String subject = NotificationMessageBuilder.buildSubject(request);
        String body = NotificationMessageBuilder.buildMessage(request);

        notificationService.sendEmail(
                request.getEmail(),
                subject,
                body
        );

        return ResponseEntity.ok("Notification sent");
    }
}
