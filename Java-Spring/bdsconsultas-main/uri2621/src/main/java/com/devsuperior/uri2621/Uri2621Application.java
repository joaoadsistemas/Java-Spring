package com.devsuperior.uri2621;

import com.devsuperior.uri2621.dtos.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<ProductMinProjection> projections = repository.search1("p");
		List<ProductMinDTO> result = projections.stream().map(ProductMinDTO::new).collect(Collectors.toList());

		System.out.println("\n SQL RAIZ");
		for (ProductMinDTO obj: result) {
			System.out.println(obj);
		}

		
	}
}
