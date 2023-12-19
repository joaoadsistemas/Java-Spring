package com.joaosilveira.authorizationbaseproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    // CRIANDO UM NOVO COMPONENTE EM FORMA DE MÉTODO
    // COM O @BEAN FICA POSSÍVEL VOCE DAR UM @AUTOWIRED BCryptPasswordEncoder POR EXEMPLO
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
