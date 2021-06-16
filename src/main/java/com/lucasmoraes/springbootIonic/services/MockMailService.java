package com.lucasmoraes.springbootIonic.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;


public class MockMailService extends AbstractEmailService
{
    private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg)
    {
        LOG.info("Simulaint email");
        LOG.info(msg.toString());
        LOG.info("Sent");
    }
}
