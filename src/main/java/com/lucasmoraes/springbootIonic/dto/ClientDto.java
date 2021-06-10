package com.lucasmoraes.springbootIonic.dto;

import com.lucasmoraes.springbootIonic.domain.Client;
import com.lucasmoraes.springbootIonic.services.validation.ClientUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientUpdate
public class ClientDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "Field not completed")
    @Length(min = 5, max = 120, message = "Invalid name, min 5 characters and maximum 120 characters")
    private String name;
    @NotEmpty(message = "Field not completed")
    @Email(message = "Invalid email")
    private String email;

    public ClientDto(){}

    public ClientDto(Client obj)
    {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
