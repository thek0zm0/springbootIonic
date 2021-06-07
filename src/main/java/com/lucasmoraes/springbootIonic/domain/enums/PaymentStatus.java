package com.lucasmoraes.springbootIonic.domain.enums;

public enum PaymentStatus
{
    PAYED(1, "Payed"),
    SETTLED(2, "Settled"),
    CANCELED(3, "Canceled"),
    PENDENT(4,"Pendent");

    private int cod;
    private String description;

    PaymentStatus(int cod, String description)
    {
        this.cod = cod;
        this.description = description;
    }

    public int getCod()
    {
        return cod;
    }

    public String getDescription()
    {
        return description;
    }

    public static PaymentStatus toEnum(Integer cod)
    {
        if(cod==null)
        {
            return null;
        }
        for( PaymentStatus x : PaymentStatus.values())
        {
            if(cod.equals(x.getCod()))
            {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id cod:" +cod);
    }
}
