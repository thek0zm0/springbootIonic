package com.lucasmoraes.springbootIonic.config;

import com.lucasmoraes.springbootIonic.services.DbService;
import com.lucasmoraes.springbootIonic.services.EmailService;
import com.lucasmoraes.springbootIonic.services.MockMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig
{
    @Autowired
    private DbService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException
    {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService()
    {
        return new MockMailService();
    }
}
