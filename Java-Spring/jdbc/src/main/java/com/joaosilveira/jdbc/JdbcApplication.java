package com.joaosilveira.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication implements CommandLineRunner {

	@Autowired
	PersonJdbcDAO personJdbcDAO;

	private Logger logger = LoggerFactory.getLogger(JdbcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All persons: {}", personJdbcDAO.findAll());
	}
}
