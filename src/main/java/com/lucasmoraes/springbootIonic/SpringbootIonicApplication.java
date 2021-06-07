package com.lucasmoraes.springbootIonic;

import com.lucasmoraes.springbootIonic.domain.Category;
import com.lucasmoraes.springbootIonic.domain.City;
import com.lucasmoraes.springbootIonic.domain.Product;
import com.lucasmoraes.springbootIonic.domain.State;
import com.lucasmoraes.springbootIonic.repositories.CategoryRepository;
import com.lucasmoraes.springbootIonic.repositories.CityRepository;
import com.lucasmoraes.springbootIonic.repositories.ProductRepository;
import com.lucasmoraes.springbootIonic.repositories.StateRepository;
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

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

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

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City ct1 = new City(null, "Uberlândia", st1);
		City ct2 = new City( null, "São Paulo", st2);
		City ct3 = new City(null, "Campinas", st2);

		stateRepository.saveAll(Arrays.asList(st1,st2));
		cityRepository.saveAll(Arrays.asList(ct1,ct2,ct3));
		cityRepository.flush();
		stateRepository.flush();

		st1.getCities().addAll(Arrays.asList(ct1));
		st2.getCities().addAll(Arrays.asList(ct2,ct3));

		stateRepository.saveAll(Arrays.asList(st1,st2));
		cityRepository.saveAll(Arrays.asList(ct1,ct2,ct3));
	}
}
