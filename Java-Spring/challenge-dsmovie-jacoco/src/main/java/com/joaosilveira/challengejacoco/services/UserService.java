package com.joaosilveira.challengejacoco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joaosilveira.challengejacoco.entities.RoleEntity;
import com.joaosilveira.challengejacoco.entities.UserEntity;
import com.joaosilveira.challengejacoco.projections.UserDetailsProjection;
import com.joaosilveira.challengejacoco.repositories.UserRepository;
import com.joaosilveira.challengejacoco.utils.CustomUserUtil;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CustomUserUtil userUtil;

    public UserEntity authenticated() {
        try {
            String username = userUtil.getLoggedUsername();
            return repository.findByUsername(username).get();
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = repository.searchUserAndRolesByUsername(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("Email not found");
        }

        UserEntity user = new UserEntity();
        user.setUsername(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new RoleEntity(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }
}