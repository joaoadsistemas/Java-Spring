package com.joaosilveira.projectOrders.resources;


import com.joaosilveira.projectOrders.entities.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResources {

    @GetMapping
    public ResponseEntity<User> findALl() {
        User u = new User(1L, "Maria", "maria@gmail.com", "0999999", "12345");
        return ResponseEntity.ok().body(u);
    }


}
