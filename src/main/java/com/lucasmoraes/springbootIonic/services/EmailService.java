package com.lucasmoraes.springbootIonic.services;

import com.lucasmoraes.springbootIonic.domain.Order;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService
{
    void sendOrderConfirmationEmail(Order obj);

    void sendEmail(SimpleMailMessage msg);

}
