package academy.devjoao.springbootposgre.controllers;

import academy.devjoao.springbootposgre.domain.Product;
import academy.devjoao.springbootposgre.domain.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/product")
public class ProductController {


    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allproducts = repository.findAll();
        return ResponseEntity.ok(allproducts);
    }
}
