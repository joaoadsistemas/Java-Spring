package com.joaosilveira.dscatalog.dtos;

import com.joaosilveira.dscatalog.entities.User;
import com.joaosilveira.dscatalog.services.validation.UserInsertValid;
import com.joaosilveira.dscatalog.services.validation.UserUpdateValid;

// CLASSE USADA SOMENTE PARA A ATUALIZAÇÃO DE UM USER, POIS TERÁ O BEN @UserUpdateValid
// @UserUpdateValid é o Bean que eu mesmo criei
@UserUpdateValid
public class UserUpdateDTO extends UserDTO {

}
