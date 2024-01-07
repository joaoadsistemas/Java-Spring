package com.joaosilveira.mytestsintegrationunitary.dtos;

import com.joaosilveira.mytestsintegrationunitary.entities.User;
import com.joaosilveira.mytestsintegrationunitary.services.validation.UserUpdateValid;
import com.joaosilveira.mytestsintegrationunitary.services.validation.UserUpdateValid;

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
