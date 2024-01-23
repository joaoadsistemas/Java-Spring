package com.joaosilveira.dslearn.services.validation;

import com.joaosilveira.dslearn.dto.FieldMessage;
import com.joaosilveira.dslearn.dto.UserInsertDTO;
import com.joaosilveira.dslearn.entities.User;
import com.joaosilveira.dslearn.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Validar a inserção de um novo usuário
// Aqui diz que a minha classe UserInsertDTO vai responder ao UserInsertValid que é o Bean criado
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista

        // procura se um Usuário existe com base no email passado, se existir ele irá instanciar o objeto
        // se o objeto for instanciado, quer dizer que existe um usuário com esse email, entao adicionamos um erro
        // a lista de FieldMessage
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if (user.isEmpty()) {
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
