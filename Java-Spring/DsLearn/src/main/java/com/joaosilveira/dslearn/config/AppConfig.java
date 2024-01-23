package com.joaosilveira.dslearn.config;

// CLASSE GERAL DE CONFIGURAÇÃO

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    // cria um componente do spring, como o @Service por exemplo
    // A diferença é que ele é uma anotation de método e não de classe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
