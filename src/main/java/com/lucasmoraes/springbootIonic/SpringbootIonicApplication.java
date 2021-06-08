package com.lucasmoraes.springbootIonic;

import com.lucasmoraes.springbootIonic.domain.*;
import com.lucasmoraes.springbootIonic.domain.enums.ClientType;
import com.lucasmoraes.springbootIonic.domain.enums.PaymentStatus;
import com.lucasmoraes.springbootIonic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private  AdressRepository adressRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		// Produto e Categoria

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

		// Cidade e estado

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

		// Cliente e endereço

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "47690859489", ClientType.PHYSICALPERSON);
		cli1.getPhones().addAll(Arrays.asList("44558892","34908722"));

		Address adr1 = new Address(null,"Rua flores","300","Apto 43","Jardim","09834020",cli1,ct1);
		Address adr2 = new Address(null, "Avenida Matos","105","Sala 80","Centro","02832049",cli1,ct2);

		clientRepository.saveAll(Arrays.asList(cli1));
		adressRepository.saveAll(Arrays.asList(adr1,adr2));
		clientRepository.flush();
		adressRepository.flush();

		cli1.getAdresses().addAll(Arrays.asList(adr1,adr2));

		clientRepository.saveAll(Arrays.asList(cli1));
		adressRepository.saveAll(Arrays.asList(adr1,adr2));

		// Pedido e pagamento

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Order ord1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, adr1);
		Order ord2 = new Order(null, sdf.parse("30/09/2017 19:32"), cli1, adr2);
		Payment pay1 = new CreditCardPayment(null, PaymentStatus.SETTLED, ord1, 6);
		ord1.setPayment(pay1);
		Payment pay2 = new BilletPayment(null, PaymentStatus.PENDENT, ord2, sdf.parse("10/09/2017 00:00"),null);
		ord2.setPayment(pay2);

		cli1.getOrders().addAll(Arrays.asList(ord1,ord2));

		orderRepository.saveAll(Arrays.asList(ord1,ord2));
		paymentRepository.saveAll(Arrays.asList(pay1,pay2));


		// Items de pedido

		OrderItem ip1 = new OrderItem(ord1,p1,0.00,1,2000.0);
		OrderItem ip2 = new OrderItem(ord1,p3, 0.00,2,80.0);
		OrderItem ip3 = new OrderItem(ord2,p2,100.00,1,800.0);

		ord1.getItems().addAll(Arrays.asList(ip1,ip2));
		ord2.getItems().addAll(Arrays.asList(ip3));

		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p3.getItems().addAll(Arrays.asList(ip2));

		orderItemRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
