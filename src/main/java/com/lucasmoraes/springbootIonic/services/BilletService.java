package com.lucasmoraes.springbootIonic.services;

import com.lucasmoraes.springbootIonic.domain.BilletPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BilletService
{
    public void fillBilletPayment(BilletPayment billetPayment, Date orderInstant)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderInstant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        billetPayment.setDueDate(calendar.getTime());
    }
}
