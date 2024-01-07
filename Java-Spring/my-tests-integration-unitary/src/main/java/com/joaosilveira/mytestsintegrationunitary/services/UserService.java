package com.joaosilveira.mytestsintegrationunitary.services;


import com.joaosilveira.mytestsintegrationunitary.dtos.*;
import com.joaosilveira.mytestsintegrationunitary.entities.Role;
import com.joaosilveira.mytestsintegrationunitary.entities.RoleDTO;
import com.joaosilveira.mytestsintegrationunitary.entities.User;
import com.joaosilveira.mytestsintegrationunitary.repositories.RoleRepository;
import com.joaosilveira.mytestsintegrationunitary.repositories.UserRepository;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.DatabaseException;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.mytestsintegrationunitary.repositories.RoleRepository;
import com.joaosilveira.mytestsintegrationunitary.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserInsertDTO> result = userRepository.searchUserAndRolesByEmail(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = new User();
        seedUserWithRoles(user, username, result);
        return user;
    }


    private void seedUserWithRoles(User user, String username, List<UserInsertDTO> result) {
        user.setEmail(username);

        user.setPassword(result.get(0).getPassword());

        List<RoleDTO> roleDTOS = new ArrayList<>();

        for (UserInsertDTO userDTO : result) {
            roleDTOS.addAll(userDTO.getRoles());
        }

        for (RoleDTO role : roleDTOS) {
            user.getRoles().add(new Role(role.getId(), role.getAuthority()));
        }
    }
}
