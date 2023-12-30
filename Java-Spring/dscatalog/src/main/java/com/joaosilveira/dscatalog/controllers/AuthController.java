package com.joaosilveira.dscatalog.controllers;


import com.joaosilveira.dscatalog.dtos.EmailDTO;
import com.joaosilveira.dscatalog.dtos.UserDTO;
import com.joaosilveira.dscatalog.dtos.UserInsertDTO;
import com.joaosilveira.dscatalog.dtos.UserUpdateDTO;
import com.joaosilveira.dscatalog.services.AuthService;
import com.joaosilveira.dscatalog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/recover-token")
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailDTO body) {
        authService.createRecoverToken(body);
        return ResponseEntity.noContent().build();

    }
}
