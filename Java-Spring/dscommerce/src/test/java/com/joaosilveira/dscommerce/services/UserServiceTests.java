package com.joaosilveira.dscommerce.services;


import com.joaosilveira.dscommerce.dto.UserDTO;
import com.joaosilveira.dscommerce.entities.User;
import com.joaosilveira.dscommerce.projections.UserDetailsProjection;
import com.joaosilveira.dscommerce.repositories.UserRepository;
import com.joaosilveira.dscommerce.tests.UserDetailsFactory;
import com.joaosilveira.dscommerce.tests.UserFactory;
import com.joaosilveira.dscommerce.util.CustomUserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomUserUtil userUtil;

    private String existingUsername, nonExistingUsername;

    private User user;

    private List<UserDetailsProjection> userDetails;

    @BeforeEach
    void setUp() throws Exception {
        existingUsername = "maria@gmail.com";
        nonExistingUsername = "user@gmail.com";

        user = UserFactory.createCustomClientUser(1L, existingUsername);
        userDetails = UserDetailsFactory.createCustomAdminUser(existingUsername);

        Mockito.when(userRepository.searchUserAndRolesByEmail(existingUsername)).thenReturn(userDetails);
        Mockito.when(userRepository.searchUserAndRolesByEmail(nonExistingUsername)).thenReturn(new ArrayList<>());

        Mockito.when(userRepository.findByEmail(existingUsername)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByEmail(nonExistingUsername)).thenReturn(Optional.empty());

    }

    @Test
    public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {

        UserDetails result = userService.loadUserByUsername(existingUsername);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getUsername(), existingUsername);
    }

    @Test
    public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            UserDetails result = userService.loadUserByUsername(nonExistingUsername);
        });
    }

    @Test
    public void authenticatedShouldReturnUserWhenUserExists() {
        Mockito.when(userUtil.getLoggedUsername()).thenReturn(existingUsername);

        User result = userService.authenticated();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getUsername(), existingUsername);
    }

    @Test
    public void authenticatedShouldThrowUserNotFoundExceptionWhenUserDoesNotExists() {

        Mockito.doThrow(ClassCastException.class).when(userUtil).getLoggedUsername();

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            User result = userService.authenticated();
        });

    }

    @Test
    public void getMeShouldReturnUserDTOWhenUserAuthenticated() {

        UserService spyUserService = Mockito.spy(userService);
        Mockito.doReturn(user).when(spyUserService).authenticated();

        UserDTO result = spyUserService.getMe();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getEmail(), existingUsername);
    }

    @Test
    public void getMeShouldThrowUserNotFoundExceptionWhenUserNotAuthenticated() {

        UserService spyUserService = Mockito.spy(userService);
        Mockito.doThrow(UsernameNotFoundException.class).when(spyUserService).authenticated();

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            UserDTO result = spyUserService.getMe();
        });
    }

}
