package com.joaosilveira.dslearn.dto;

import com.joaosilveira.dslearn.entities.User;
import com.joaosilveira.dslearn.services.validation.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// CLASSE USADA SOMENTE PARA A CRIAÇÃO DE UM NOVO USER, POIS IRÁ PASSAR A SENHA
// @UserInsertValid é o Bean que eu mesmo criei
@UserInsertValid
public class UserInsertDTO extends UserDTO {

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 8, message = "Deve ter no mínimo 8 caracteres")
    private String password;

    public UserInsertDTO() {

    }

    // SALVA A SENHA NO DTO E PASSA O RESTO PARA A CLASSE PAI (UserDTO)
    public UserInsertDTO(User entity) {
        super(entity);
        this.password = entity.getPassword();
    }


    public String getPassword() {
        return password;
    }
}
