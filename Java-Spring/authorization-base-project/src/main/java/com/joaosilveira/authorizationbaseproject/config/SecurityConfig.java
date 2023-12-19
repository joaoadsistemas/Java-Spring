package com.joaosilveira.authorizationbaseproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// CLASSE DE CONFIGURAÇÃO GLOBAL DA APLICAÇÃO DE SEGURANÇA
@Configuration
public class SecurityConfig {


    // CONFIGURAÇÃO PARA DESATIVAR A VALIDAÇÃO DE ENDPOINT
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }


}
