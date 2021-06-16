package com.lucasmoraes.springbootIonic.services;

import com.lucasmoraes.springbootIonic.domain.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService
{
    @Value("${default.sender}")
    private String sender;


    @Override
    public void sendOrderConfirmationEmail(Order obj)
    {
        SimpleMailMessage sm = prepareSimpleMailMessage(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessage(Order obj)
    {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Order Confirmed");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }
}
