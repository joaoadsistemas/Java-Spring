package com.joaosilveira.springbootgmail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.joaosilveira.springbootgmail.dto.EmailDTO;
import com.joaosilveira.springbootgmail.services.exceptions.EmailException;

@Service
public class EmailService {

    // AQUI ELE PEGA O VALOR QUE CONFIGUREI DE EMAIL NA VARIÁVEL DE AMBIENTE E COLOCA ELE COMO REMETENTE
    // NO MEU CASO, O MEU EMAIL MESMO joaoadsistemas
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(EmailDTO obj) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(obj.getTo());
            message.setSubject(obj.getSubject());
            message.setText(obj.getBody());
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new EmailException("Failed to send email");
        }
    }
}
