package com.lucasmoraes.springbootIonic.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;


public class MockMailService extends AbstractEmailService
{
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg)
    {
        LOG.info("Sending mail");
        LOG.info(msg.toString());
        LOG.info("Sent");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg)
    {
        LOG.info("Sending HTML mail");
        LOG.info(msg.toString());
        LOG.info("Sent");
    }

}
