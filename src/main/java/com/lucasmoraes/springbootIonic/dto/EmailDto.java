package com.lucasmoraes.springbootIonic.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDto implements Serializable
{
    // Atributos básicos
    private static final long serialVersionUID = 1L;
    @NotEmpty(message = "Field not completed")
    @Email(message = "Invalid email")
    private String email;

    public EmailDto(){}

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
