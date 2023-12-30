package com.joaosilveira.dscatalog.services;

import com.joaosilveira.dscatalog.dtos.EmailDTO;
import com.joaosilveira.dscatalog.entities.PasswordRecover;
import com.joaosilveira.dscatalog.entities.User;
import com.joaosilveira.dscatalog.repositories.PasswordRecoverRepository;
import com.joaosilveira.dscatalog.repositories.UserRepository;
import com.joaosilveira.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;

    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Value("${email.password-recover.uri}")
    private String recoverUri;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void createRecoverToken(EmailDTO body) {

        User user = userRepository.findByEmail(body.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("Email não encontrado");
        }

        String token = UUID.randomUUID().toString();

        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(body.getEmail());
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));

        passwordRecoverRepository.save(entity);

        String textBody = "Acesse o link para definir uma nova senha\n\n " +
                recoverUri + token + ". Validade de " + tokenMinutes + " minutos";

        emailService.sendEmail(body.getEmail(), "Recuperação de senha", textBody);
    }
}
