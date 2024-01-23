package com.joaosilveira.dslearn.dto;

import com.joaosilveira.dslearn.entities.User;
import com.joaosilveira.dslearn.services.validation.UserUpdateValid;

// CLASSE USADA SOMENTE PARA A ATUALIZAÇÃO DE UM USER, POIS TERÁ O BEN @UserUpdateValid
// @UserUpdateValid é o Bean que eu mesmo criei
@UserUpdateValid
public class UserUpdateDTO extends UserDTO {

    public UserUpdateDTO() {
        
    }

    public UserUpdateDTO(User entity) {
        super(entity);
    }

}
