package com.example.universitymanagementsystem.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class SmtpSettings {
    @Value("${smtpSettings.host}")
    private String host;

    @Value("${smtpSettings.port}")
    private int port;

    @Value("${smtpSettings.login}")
    private String login;

    @Value("${smtpSettings.password}")
    private String password;

    @Value("${smtpSettings.senderName}")
    private String senderName;
}
