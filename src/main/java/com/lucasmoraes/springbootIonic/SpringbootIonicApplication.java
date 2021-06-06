package com.lucasmoraes.springbootIonic;

import com.lucasmoraes.springbootIonic.domain.Category;
import com.lucasmoraes.springbootIonic.domain.Product;
import com.lucasmoraes.springbootIonic.repositories.CategoryRepository;
import com.lucasmoraes.springbootIonic.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringbootIonicApplication implements CommandLineRunner
{
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		Product p1 = new Product(null,"Computador", 2000.0);
		Product p2 = new Product(null,"Impressora", 800.0);
		Product p3 = new Product(null,"Mouse", 80.0);


		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
		productRepository.flush();
		categoryRepository.flush();


		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
	}
}
