package com.joaosilveira.dscatalog.services;

import com.joaosilveira.dscatalog.dtos.EmailDTO;
import com.joaosilveira.dscatalog.dtos.NewPasswordDTO;
import com.joaosilveira.dscatalog.entities.PasswordRecover;
import com.joaosilveira.dscatalog.entities.User;
import com.joaosilveira.dscatalog.repositories.PasswordRecoverRepository;
import com.joaosilveira.dscatalog.repositories.UserRepository;
import com.joaosilveira.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;

    // PEGA DAS VARIÁVEIS DE AMBIENTE O TEMPO DE EXPIRAÇÃO DO TOKEN
    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    // PEGA A URL DO SITE DE RECUPERAÇÃO DE SENHA (SITE DO FRONTEND)
    @Value("${email.password-recover.uri}")
    private String recoverUri;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void createRecoverToken(EmailDTO body) {
        // INSTANCIA UM USUÁRIO COM BASE NO EMAIL PASSADO, E JÁ VERIFICA SE ELE EXISTE, SE NÃO EXISTIR LANÇA A EXCEÇÃO
        User user = userRepository.findByEmail(body.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("Email não encontrado");
        }

        // CRIA UM TOKEN UUI ALEATORIO
        String token = UUID.randomUUID().toString();

        // CRIA UMA ENTIDADE DE PASSWORD RECOVER ISNTANCIANDO O EMAIL, TOKEN E A EXPERIÇÃO DELE
        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(body.getEmail());
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));

        // SALVA ESSAS INFORMAÇÕES NO BANCO DE DADOS
        passwordRecoverRepository.save(entity);

        // CRIA A MENSAGEM QUE SERÁ ENVIADA NO EMAIL
        String textBody = "Acesse o link para definir uma nova senha\n\n " +
                recoverUri + token + ". Validade de " + tokenMinutes + " minutos";

        // MANDA O EMAIL PRO USUÁRIO COM A MENSAGEM DE RECUPERAÇÃO DE SENHA E O LINK
        emailService.sendEmail(body.getEmail(), "Recuperação de senha", textBody);
    }

    @Transactional
    public void saveNewPassword(NewPasswordDTO body) {

        // VERIFICA SE O TOKEN PASSADO EXISTE E É VALIDO AINDA
        List<PasswordRecover> result = passwordRecoverRepository.searchValidTokens(body.getToken(), Instant.now());
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Token inválido");
        }

        // SALVA A NOVA SENHA DO USUÁRIO, E SALVA O USUÁRIO NO BANCO DE DADOS
        User user = userRepository.findByEmail(result.get(0).getEmail());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        user = userRepository.save(user);
    }


    // MÉTODO PARA PEGAR O USUÁRIO QUE ESTÁ LOGADO NA SESSÃO
    protected User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return userRepository.findByEmail(username);
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }

}
