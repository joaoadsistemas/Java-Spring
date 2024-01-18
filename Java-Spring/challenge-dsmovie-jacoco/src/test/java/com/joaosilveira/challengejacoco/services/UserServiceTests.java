package com.joaosilveira.challengejacoco.services;

import com.joaosilveira.challengejacoco.entities.UserEntity;
import com.joaosilveira.challengejacoco.projections.UserDetailsProjection;
import com.joaosilveira.challengejacoco.repositories.UserRepository;
import com.joaosilveira.challengejacoco.tests.UserDetailsFactory;
import com.joaosilveira.challengejacoco.tests.UserFactory;
import com.joaosilveira.challengejacoco.utils.CustomUserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class UserServiceTests {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomUserUtil userUtil;

    private UserEntity user;

    private List<UserDetailsProjection> userDetailsProjections;

    @BeforeEach
    void setUp() throws Exception {

        user = UserFactory.createUserEntity();
        userDetailsProjections = UserDetailsFactory.createCustomClientUser(user.getUsername());
    }

    @Test
    public void authenticatedShouldReturnUserEntityWhenUserExists() {
        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
        Mockito.when(userUtil.getLoggedUsername()).thenReturn(user.getUsername());

        UserEntity result = service.authenticated();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), user.getId());

    }

    @Test
    public void authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {
        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        Mockito.when(userUtil.getLoggedUsername()).thenReturn(user.getUsername());

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            UserEntity result = service.authenticated();
        });

    }

    @Test
    public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {

        Mockito.when(userRepository.searchUserAndRolesByUsername(user.getUsername())).thenReturn(userDetailsProjections);

        UserDetails result = service.loadUserByUsername(user.getUsername());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getUsername(), user.getUsername());

    }

    @Test
    public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {

        Mockito.when(userRepository.searchUserAndRolesByUsername(any())).thenReturn(new ArrayList<>());


        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
           UserDetails result = service.loadUserByUsername(user.getUsername());
       });



    }
}
