package com.joaosilveira.authorizationbaseproject.service;

import com.joaosilveira.authorizationbaseproject.dto.RoleDTO;
import com.joaosilveira.authorizationbaseproject.dto.UserDTO;
import com.joaosilveira.authorizationbaseproject.entities.Role;
import com.joaosilveira.authorizationbaseproject.entities.User;
import com.joaosilveira.authorizationbaseproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
// Implementa a interface UserDetailsService para fornecer funcionalidades de autenticação.
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Método obrigatório da interface UserDetailsService para carregar o usuário com base no nome de usuário.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Busca no banco de dados o usuário e suas roles associadas pelo e-mail.
        List<UserDTO> result = userRepository.searchUserAndRolesByEmail(username);

        // Lança uma exceção se o usuário não for encontrado.
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        // Cria um novo objeto User e popula com as informações do usuário e suas roles.
        User user = new User();
        seedUserWithRoles(user, username, result);
        return user;
    }

    // Popula o objeto User com informações do usuário e suas roles.
    private void seedUserWithRoles(User user, String username, List<UserDTO> result) {
        // Adiciona o e-mail do usuário.
        user.setEmail(username);

        // Adiciona a senha do usuário obtida da lista de UserDTO.
        user.setPassword(result.get(0).getPassword());

        // Cria uma lista de RoleDTOs para armazenar as roles associadas ao usuário.
        List<RoleDTO> roleDTOS = new ArrayList<>();

        // Itera sobre a lista de UserDTOs para obter as roles associadas.
        for (UserDTO userDTO : result) {
            roleDTOS.addAll(userDTO.getRoles());
        }

        // Adiciona ao usuário cada role obtida.
        for (RoleDTO role : roleDTOS) {
            user.addRole(new Role(role.getId(), role.getAuthority()));
        }
    }
}
