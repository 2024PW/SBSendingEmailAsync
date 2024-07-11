package com.SBSendingEmaiAsync.service.impl;

import com.SBSendingEmaiAsync.utility.PropertiesLoader;
import com.SBSendingEmaiAsync.model.Email;
import com.SBSendingEmaiAsync.service.EmailService;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Async
    @Override
    public CompletableFuture<Boolean> sendEmail(Email email) {
        try {
            // int i = 1/0;
            int randomInt = (int)Math.floor(Math.random() * 100 + 1);
            log.info("=== Sending Email Id " + randomInt + " Start ===");
            long start = System.currentTimeMillis();
            MimeMessage message = mailSender.createMimeMessage();
            Properties properties = PropertiesLoader.loadProperties("/smtp.properties");

            message.setFrom(new InternetAddress(properties.getProperty("spring.mail.username"), properties.getProperty("spring.mail.displayName")));
            List<String> toAddresses = email.getTo();
            InternetAddress[] recipientAddresses = new InternetAddress[toAddresses.size()];
            for (int i = 0; i < toAddresses.size(); i++) {
                recipientAddresses[i] = new InternetAddress(toAddresses.get(i));
            }
            message.addRecipients(MimeMessage.RecipientType.TO, recipientAddresses);

            message.setRecipients(MimeMessage.RecipientType.BCC, email.getBcc());
            message.setSubject(email.getSubject() + "hello");
            message.setContent(email.getContent(), "text/html; charset=utf-8");
            mailSender.send(message);
            long end = System.currentTimeMillis();
            log.info("Sending Email Id " + randomInt + " Finish: Totally Used {} Milliseconds", end - start);
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(false);
        }
    }
}
