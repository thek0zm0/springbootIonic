package com.lucasmoraes.springbootIonic.services;

import com.lucasmoraes.springbootIonic.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService
{
    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;


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

    protected String htmlFromTemplateOrder(Order obj)
    {
        Context context = new Context();
        context.setVariable("order", obj);
        return templateEngine.process("email/orderConfirm", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Order obj)
    {
        try
        {
            MimeMessage mm = prepareMimeMessageFromOrder(obj);
            sendHtmlEmail(mm);
        }
        catch(MessagingException e)
        {
            sendOrderConfirmationEmail(obj);
        }

    }

    protected MimeMessage prepareMimeMessageFromOrder(Order obj) throws MessagingException
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(obj.getClient().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Order confirmed: " + obj.getId());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplateOrder(obj), true);
        return mimeMessage;
    }
}
