package com.joaosilveira.dscommerce.services;

import com.joaosilveira.dscommerce.entities.User;
import com.joaosilveira.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    // testar se o usuário logado é admin, e se ele é o dono do pedido
    public void validateSelfOrAdmin(Long userId) {
        User me = userService.authenticated();
        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
            throw new ForbiddenException("Access denied");
        }
    }

}
