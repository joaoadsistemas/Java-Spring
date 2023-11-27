package com.devsuperior.uri2611;

import com.devsuperior.uri2611.dtos.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {


	@Autowired
	private MovieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<MovieMinProjection> projections = repository.search1("action");
		List<MovieMinDTO> result = projections.stream().map(MovieMinDTO::new).collect(Collectors.toList());


		System.out.println("\n*** RESULTADO SQL RAIZ");
		for (MovieMinDTO obj : result) {
			System.out.println(obj);
		}
		System.out.println("\n\n\n");




		List<MovieMinDTO> result2 = repository.search2("action");

		System.out.println("\n*** RESULTADO JPQL");
		for (MovieMinDTO obj : result2) {
			System.out.println(obj);
		}

	}
}
