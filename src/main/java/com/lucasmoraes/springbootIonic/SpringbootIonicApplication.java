package com.lucasmoraes.springbootIonic;

import com.lucasmoraes.springbootIonic.domain.Category;
import com.lucasmoraes.springbootIonic.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringbootIonicApplication implements CommandLineRunner
{
	@Autowired
	private CategoryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		repository.saveAll(Arrays.asList(cat1,cat2));
	}
}
