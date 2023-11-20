package com.joaosilveira.injectiondependencychallange;

import com.joaosilveira.injectiondependencychallange.entities.Order;
import com.joaosilveira.injectiondependencychallange.services.OderService;
import com.joaosilveira.injectiondependencychallange.services.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InjectionDependencyChallangeApplication implements CommandLineRunner {

    @Autowired
    OderService service;

    public static void main(String[] args) {
        SpringApplication.run(InjectionDependencyChallangeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {



        Order o1 = new Order(1034, 800, 10.0);

        System.out.println(service.total(o1));



    }
}
