package com.joaosilveira.dslearn.services;


import com.joaosilveira.dslearn.entities.User;
import com.joaosilveira.dslearn.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    // testar se o usuário logado é admin, e se ele é o dono do pedido
    public void validateSelfOrAdmin(Long userId) {
        User me = userService.authenticated();

        if (me.hasRole("ROLE_ADMIN")) {
            return;
        }

        if (!me.getId().equals(userId)) {
            throw new ForbiddenException("Access denied. Should be self or admin");
        }
    }

}
