package com.example.universitymanagementsystem.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class AppConfiguration {
    @Bean
    static GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }
    @Bean
    public SmtpSettings smtpSettings() {
        return new SmtpSettings();
    }
    @Bean
    public Properties smtpProperties(SmtpSettings smtpSettings) {

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpSettings.getHost());
        props.put("mail.smtp.socketFactory.port", smtpSettings.getPort());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", smtpSettings.getPort());

        return props;
    }
}
