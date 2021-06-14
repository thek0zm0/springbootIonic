package com.lucasmoraes.springbootIonic.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.lucasmoraes.springbootIonic.domain.enums.PaymentStatus;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
@JsonTypeName("creditCardPayment")
public class CreditCardPayment extends Payment
{
    private static final long serialVersionUID = 1L;
    private Integer installmentsNumber;

    public CreditCardPayment(){}

    public CreditCardPayment(Integer id, PaymentStatus status, Order order, Integer installmentsNumber)
    {
        super(id, status, order);
        this.installmentsNumber = installmentsNumber;
    }

    public Integer getInstallmentsNumber()
    {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(Integer installmentsNumber)
    {
        this.installmentsNumber = installmentsNumber;
    }

}
