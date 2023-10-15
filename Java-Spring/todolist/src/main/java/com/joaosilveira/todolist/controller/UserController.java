package com.joaosilveira.todolist.controller;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.joaosilveira.todolist.users.User;
import com.joaosilveira.todolist.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
//htpp://localhost:8080/------------- rota
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /*
        MÉTODOS DE ACESSO DO HTTP
    *  GET - Buscar uma informação
    *  POST - Adicionar um dado/informação
    *  PUT - Alterar um dado/informação
    *  DELETE - Remover um dado/informação
    *  PATCH - Alterar somente uma parte da info/dado
    */

    @PostMapping("/")
    public ResponseEntity create(@RequestBody User user) {
        var checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser != null) {
            System.out.println("Usuario já existente");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existente");
        }

        // CIRPTOGRAFAR SENHA
        var passwordHashred = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(passwordHashred);
        //
        var userCreated = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }



}
