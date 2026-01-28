package com.dericorp.notification.service.impl;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.dericorp.notification.service.NotificationService;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationServiceImpl implements NotificationService {

	Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private FreeMarkerConfigurer freemarkerConfigurer;

	public void sendPlainText(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

	/*@Override
	public void sendEmail(String to, String subject, String body) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		mailSender.send(message);
	}*/

	@Override
	public void sendEmailUsingFreeMarker(Map<String, Object> templateModel) {
		try {
			
			Template freemarkerTemplate = freemarkerConfigurer.getConfiguration().getTemplate("notification.ftl");
			String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
			//helper.setTo("derickdaniel44@gmail.com");
            helper.setTo("rajniminj96@gmail.com");
			helper.setSubject("Your Developer Book has been updated!!");
			helper.setText(htmlBody, true);
			mailSender.send(message);
		} catch (IOException | TemplateException | MessagingException e) {
			logger.error("There has been an error sending email", e.getMessage());
			e.printStackTrace();
		}
	}

    @Override
    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
