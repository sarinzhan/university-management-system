package com.example.universitymanagementsystem.configuration;

import com.example.universitymanagementsystem.repository.PersonRepository;
import jakarta.servlet.FilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(Customizer.withDefaults())
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        httpSecurity
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/applicant/**").permitAll()
                        .requestMatchers("/swagger-ui/**","/v3/api-docs").permitAll()
                        .requestMatchers("/applicant/register-applicant").permitAll()
                        .requestMatchers("/person/find-by-pn").permitAll()
                        .anyRequest().authenticated());
        return httpSecurity.build();
    }

    @Bean
    public SmtpSettings smtpSettings() {
        return new SmtpSettings();
    }

    @Bean
    public Properties smtpProperties() {
        SmtpSettings smtpSettings = smtpSettings();

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpSettings.getHost());
        props.put("mail.smtp.socketFactory.port", smtpSettings.getPort());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", smtpSettings.getPort());

        return props;
    }
}
