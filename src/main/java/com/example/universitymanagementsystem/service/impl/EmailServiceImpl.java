package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.configuration.SmtpSettings;
import com.example.universitymanagementsystem.service.EmailService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    private final SmtpSettings smtpSettings;
    private final Properties smtpProperties;

    public EmailServiceImpl(SmtpSettings smtpSettings, Properties smtpProperties) {
        this.smtpSettings = smtpSettings;
        this.smtpProperties = smtpProperties;
    }

    @Override
    public void sendMessage(String email, String subject, String messageText) throws MessagingException {
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpSettings.getLogin(), smtpSettings.getPassword());
            }
        };

        Session session = Session.getInstance(smtpProperties, auth);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(smtpSettings.getLogin()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(subject);
        message.setText(messageText);

        Transport.send(message);
    }
}
