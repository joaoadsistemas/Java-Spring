package com.joaosilveira.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StartupController {


    @GetMapping
    public String sayHello() {
        return "HELLO WORLD";
    }

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return  "Run a hard";
    }

    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day";
    }

}
