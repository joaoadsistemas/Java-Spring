package com.joaosilveira.dscommerce.services;

import com.joaosilveira.dscommerce.dto.UserDTO;
import com.joaosilveira.dscommerce.entities.Role;
import com.joaosilveira.dscommerce.entities.User;
import com.joaosilveira.dscommerce.projections.UserDetailsProjection;
import com.joaosilveira.dscommerce.repositories.UserRepository;
import com.joaosilveira.dscommerce.util.CustomUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserUtil customUserUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Email not found");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());

        for (UserDetailsProjection projection: result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }

    // pega o usuário logado
    public User authenticated() {
        try {
            String username = customUserUtil.getLoggedUsername();
            return userRepository.findByEmail(username).get();

        } catch (Exception e) {
            throw new UsernameNotFoundException("Email not found");
        }
    }

    @Transactional(readOnly = true)
    public UserDTO getMe() {
        User user = authenticated();
        return new UserDTO(user);
    }
}
