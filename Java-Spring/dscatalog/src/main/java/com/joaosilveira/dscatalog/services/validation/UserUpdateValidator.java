package com.joaosilveira.dscatalog.services.validation;

import com.joaosilveira.dscatalog.dtos.FieldMessage;
import com.joaosilveira.dscatalog.dtos.UserInsertDTO;
import com.joaosilveira.dscatalog.dtos.UserUpdateDTO;
import com.joaosilveira.dscatalog.entities.User;
import com.joaosilveira.dscatalog.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

// Validar a inserção de um novo usuário
// Aqui diz que a minha classe UserUpdateDTO vai responder ao UserUpdateValid que é o Bean criado
public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista

        // procura se um Usuário existe com base no email passado, se existir ele irá instanciar o objeto
        // se o objeto for instanciado, quer dizer que existe um usuário com esse email, entao adicionamos um erro
        // a lista de FieldMessage
        User user = userRepository.findByEmail(dto.getEmail());
        if (user!= null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }


        // aqui está percorrendo minha lista, e adicionando cada eventual erro dentro da lista de erros do
        // próprio BensValidation para ser acessado depois no FOREACH do ControllerExceptionHandler adicionando
        // os erros ao DTO
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }


}
