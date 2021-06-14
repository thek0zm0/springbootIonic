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
	public static void main(String[] args) {
		SpringApplication.run(SpringbootIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
	}
}
