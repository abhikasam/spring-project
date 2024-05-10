package com.spring.project;

import com.spring.project.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.project"})
public class ProjectApplication implements CommandLineRunner {

	@Autowired
	IPlayerRepository playerRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		//playerRepository.findAll().forEach(System.out::println);
	}
}
