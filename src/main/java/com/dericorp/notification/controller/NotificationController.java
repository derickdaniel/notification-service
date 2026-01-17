package com.dericorp.notification.controller;

import com.dericorp.notification.service.NotificationService;
import jakarta.mail.MessagingException;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    NotificationService notificationService;

    @PostMapping("/send-email")
    public String createIssue(@RequestBody Map<String, Object> body) {

    	body.forEach((k, v) -> System.out.println(k +" : " + v));
    	
        notificationService.sendEmailUsingFreeMarker(body);

        return "success";
    }
}
