package com.joaosilveira.empregadosauthchallenge.services;

import com.joaosilveira.empregadosauthchallenge.dtos.RoleDTO;
import com.joaosilveira.empregadosauthchallenge.dtos.UserDetailsDTO;
import com.joaosilveira.empregadosauthchallenge.entities.Role;
import com.joaosilveira.empregadosauthchallenge.entities.User;
import com.joaosilveira.empregadosauthchallenge.repositories.UserReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserReporitory userReporitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsDTO> result = userReporitory.searchUserAndRolesByEmail(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = new User();
        seedUserWithRoles(user, username, result);
        return user;
    }


    private void seedUserWithRoles(User user, String username, List<UserDetailsDTO> result) {
        user.setEmail(username);

        user.setPassword(result.get(0).getPassword());

        List<RoleDTO> roleDTOS = new ArrayList<>();

        for (UserDetailsDTO userDTO : result) {
            roleDTOS.addAll(userDTO.getRoles());
        }

        for (RoleDTO role : roleDTOS) {
            user.getRoles().add(new Role(role.getId(), role.getAuthority()));
        }
    }
}
