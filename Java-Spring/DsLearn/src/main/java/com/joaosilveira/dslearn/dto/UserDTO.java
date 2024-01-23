package com.joaosilveira.dslearn.dto;

import com.joaosilveira.dslearn.entities.Notification;
import com.joaosilveira.dslearn.entities.Role;
import com.joaosilveira.dslearn.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    @Email(message = "Favor entrar com um email válido")
    private String email;

    private List<RoleDTO> roles = new ArrayList<>();
    private List<NotificationDTO> notifications = new ArrayList<>();

    public UserDTO() {

    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();


        for (Role role : entity.getRoles()) {
            roles.add(new RoleDTO(role));
        }

        for (Notification notification : entity.getNotifications()) {
            notifications.add(new NotificationDTO(notification));
        }
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public List<NotificationDTO> getNotifications() {
        return notifications;
    }
}
