package com.joaosilveira.dscatalog.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmailDTO {

    @NotBlank(message = "Campo obigatório")
    @Email(message = "Email inválido")
    @Size(min = 8)
    private String email;

    public EmailDTO() {

    }

    public EmailDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
