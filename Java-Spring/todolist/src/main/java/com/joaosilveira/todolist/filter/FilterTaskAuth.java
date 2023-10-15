package com.joaosilveira.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.joaosilveira.todolist.users.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

// Verificar o login do usuario
// Toda requisição antes de chegar no controller irá passar no filtro
@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if(servletPath.equals("/tasks/")) {
            // Pegar a autenticação (usuário e senha)
            var authorization = request.getHeader("Auhorization");
            System.out.println("Authorization: " + authorization);

            // tirando o basic do inicio do codigo
            var authEncoded = authorization.substring("Basic".length()).trim();
            // decodifica a senha
            byte[] authDecoded  = Base64.getDecoder().decode(authEncoded);
            // senha como string
            var authString = new String(authDecoded);

            // divide usuario:senha
            String[] credential = authString.split(":");
            String username = credential[0];
            String password = credential[1];

            System.out.println(username);
            System.out.println(password);

            // validar usuário

            var user = userRepository.findByUsername(username);
            if (user == null) {
                // usuario nao encontrado
                response.sendError(401);
            } else {
                // validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                // verifica se a senha é verdadeira
                if(passwordVerify.verified) {
                    // pegando o Id do usuario e passando para a request
                    request.setAttribute("userId", user.getId());
                    // Chegou no filtro
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }


            }



        } else
            filterChain.doFilter(request, response);


    }
}
