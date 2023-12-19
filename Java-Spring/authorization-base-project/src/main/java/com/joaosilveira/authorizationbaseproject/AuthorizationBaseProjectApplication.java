package com.joaosilveira.authorizationbaseproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthorizationBaseProjectApplication implements CommandLineRunner {

    // CHAMANDO O BEAN QUE CRIEI NA CLASSE SECURITYCONFIG
    @Autowired
    private PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(AuthorizationBaseProjectApplication.class, args);
    }


    // MOSTRANDO CRIPTOGRAFANDO UMA SENHA POR BAIXO DOS PANOS
    @Override
    public void run(String... args) throws Exception {
        System.out.println("SENHA CRIPTOGRAFADA = " + passwordEncoder.encode("123456"));

        // VERIFICANDO SE ESSE HASH BATE COM A SENHA
        boolean result = passwordEncoder.matches("123456",
                "$2a$10$4tjNMRDx0NxP5DS0hxUtdud8ow31Tvyz5jTDSk2RwalE5tyZwjVHu");

        System.out.println("RESULTADO DO MATCH = " + result);

    }
}
