package com.joaosilveira.springboot.demo.mycoolapp;


import com.joaosilveira.springboot.demo.mycoolapp.exceptions.UnsuportedMathOperationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    Operations operation = new Operations();

    @GetMapping(value = "/plus/{numberOne}/{numberTwo}")
    public String sum(@PathVariable(value = "numberOne") String n1, @PathVariable(value = "numberTwo") String n2) {

        return operation.formatExpression(operation.operation(n1) + operation.operation(n2));
    }

    @GetMapping("/minus/{numberOne}/{numberTwo}")
    public String minus(
            @PathVariable(value = "numberOne") String n1,
            @PathVariable(value = "numberTwo") String n2) {

        return operation.formatExpression(operation.operation(n1) - operation.operation(n2));

    }

    @GetMapping("/multi/{numberOne}/{numberTwo}")
    public String mult(@PathVariable(value = "numberOne") String n1, @PathVariable(value = "numberTwo") String n2) {

        return operation.formatExpression(operation.operation(n1) * operation.operation(n2));
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public String division(@PathVariable(value = "numberOne") String n1, @PathVariable(value = "numberTwo") String n2) {


        return operation.formatExpression(operation.operation(n1) / operation.operation(n2));
    }

    @GetMapping(value = "/media/{numberOne}/{numberTwo}")
    public String media(@PathVariable(value = "numberOne") String n1, @PathVariable(value = "numberTwo") String n2) {

        return operation.formatExpression((operation.operation(n1) + operation.operation(n2)) / 2);


    }

    @GetMapping(value = "/raiz/{numberOne}")
    public String raiz(@PathVariable(value = "numberOne") String n1) {

        return operation.formatExpression(Math.sqrt(operation.operation(n1)));


    }




}
