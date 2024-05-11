package com.example.universitymanagementsystem.configuration;


import com.example.universitymanagementsystem.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class AppConfiguration {
    private final JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(Customizer.withDefaults())
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        httpSecurity
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/applicant/**").permitAll()
                        .requestMatchers("/swagger-ui/**","/v3/api-docs").permitAll()
                        .requestMatchers("/applicant/register-applicant").permitAll()
                        .requestMatchers("/person/find-by-pn").permitAll()
                        .anyRequest().authenticated());
        httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return httpSecurity.build();
    }

    @Bean
    public SmtpSettings smtpSettings() {
        return new SmtpSettings();
    }

    @Bean
    static GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }



//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return rawPassword.toString().equals(encodedPassword);
//            }
//        };
//    }

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
