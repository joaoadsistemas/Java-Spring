package com.joaosilveira.dscatalog.dtos;

import com.joaosilveira.dscatalog.entities.User;
import com.joaosilveira.dscatalog.services.validation.UserInsertValid;

// CLASSE USADA SOMENTE PARA A CRIAÇÃO DE UM NOVO USER, POIS IRÁ PASSAR A SENHA
// @UserInsertValid é o Bean que eu mesmo criei
@UserInsertValid
public class UserInsertDTO extends UserDTO {

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