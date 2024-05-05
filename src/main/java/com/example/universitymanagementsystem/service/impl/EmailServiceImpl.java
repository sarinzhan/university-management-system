package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.service.EmailService;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailServiceImpl implements EmailService {

    //как подтянуть smtp настройки
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

    public class SmtpSettings{
        private String host;
        private int port;
        private String login;
        private String password;
        private String senderName;


        public String getHost(){
            return host;
        }

        public int getPort(){
            return port;
        }

        public String getLogin(){
            return login;
        }

        public String getPassword(){
            return password;
        }

        public  String getSenderName(){
            return senderName;
        }
    }
}
