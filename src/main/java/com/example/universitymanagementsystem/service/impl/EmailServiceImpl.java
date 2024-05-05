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

    public EmailServiceImpl(SmtpSettings smtpSettings) {
        this.smtpSettings = smtpSettings;
    }

    @Override
    public void sendMessage(String email, String subject, String messageText) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpSettings.getHost());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", smtpSettings.getHost());
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpSettings.getLogin(), smtpSettings.getPassword());
            }
        });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(smtpSettings.getLogin()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
    }
}
