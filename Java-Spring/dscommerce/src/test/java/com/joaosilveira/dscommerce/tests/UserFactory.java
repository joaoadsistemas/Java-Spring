package com.joaosilveira.dscommerce.tests;

import com.joaosilveira.dscommerce.entities.Role;
import com.joaosilveira.dscommerce.entities.User;

import java.time.LocalDate;

public class UserFactory {

    public static User createUserClient() {
        User user = new User(1L, "Maria", "maria@gmail.com", "15532432",
                LocalDate.parse("2001-07-25"), "$2a$10$yF8kfPJAcjnljzTLj2dgs.LEOLbN1K1fqoFtoFRoq68JoRtkAHTXm");
        user.addRole(new Role(1L, "ROLE_CLIENT"));

        return user;
    }

    public static User createUserAdmin() {
        User user = new User(2L, "Alex", "alex@gmail.com", "9799769",
                LocalDate.parse("1987-12-13"), "$2a$10$yF8kfPJAcjnljzTLj2dgs.LEOLbN1K1fqoFtoFRoq68JoRtkAHTXm");
        user.addRole(new Role(2L, "ROLE_ADMIN"));

        return user;
    }

    public static User createCustomAdminUser(Long id, String username) {
        User user = new User(id, "Admin", username, "9799769",
                LocalDate.parse("1987-12-13"), "$2a$10$yF8kfPJAcjnljzTLj2dgs.LEOLbN1K1fqoFtoFRoq68JoRtkAHTXm");
        user.addRole(new Role(2L, "ROLE_ADMIN"));

        return user;
    }

    public static User createCustomClientUser(Long id, String username) {
        User user = new User(id, "Client", username, "9799769",
                LocalDate.parse("1987-12-13"), "$2a$10$yF8kfPJAcjnljzTLj2dgs.LEOLbN1K1fqoFtoFRoq68JoRtkAHTXm");
        user.addRole(new Role(2L, "ROLE_CLIENT"));

        return user;
    }

}
