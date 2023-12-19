package com.eddy.Car_Rental_Management_System.service.implementation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class mailconfig {
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Set mail properties (host, port, username, password, etc.)
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("regis.rw123@gmail.com");
        mailSender.setPassword("oiniuvsdesvprchs");

        return mailSender;
    }
}
