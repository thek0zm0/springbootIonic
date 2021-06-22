package com.lucasmoraes.springbootIonic.dto;

import com.lucasmoraes.springbootIonic.services.validation.ClientInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientInsert
public class ClientNewDto implements Serializable
{
    // Atributos básicos
    private static final long serialVersionUID = 1L;
    @NotEmpty(message = "Field not completed")
    @Length(min = 5, max = 120, message = "Invalid name, min 5 characters and maximum 120 characters")
    private String name;
    @NotEmpty(message = "Field not completed")
    @Email(message = "Invalid email")
    private String email;
    @NotEmpty(message = "Field not completed")
    private String cpfOrCnpj;
    private Integer type;
    @NotEmpty(message = "Field not completed")
    private String password;

    @NotEmpty(message = "Field not completed")
    private String publicPlace;
    @NotEmpty(message = "Field not completed")
    private String number;

    private String adjunct;

    private String district;
    @NotEmpty(message = "Field not completed")
    private String cep;

    @NotEmpty(message = "Field not completed")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public ClientNewDto(){}

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

    public String getCpfOrCnpj()
    {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj)
    {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getPublicPlace()
    {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace)
    {
        this.publicPlace = publicPlace;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getAdjunct()
    {
        return adjunct;
    }

    public void setAdjunct(String adjunct)
    {
        this.adjunct = adjunct;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public String getPhone1()
    {
        return phone1;
    }

    public void setPhone1(String phone1)
    {
        this.phone1 = phone1;
    }

    public String getPhone2()
    {
        return phone2;
    }

    public void setPhone2(String phone2)
    {
        this.phone2 = phone2;
    }

    public String getPhone3()
    {
        return phone3;
    }

    public void setPhone3(String phone3)
    {
        this.phone3 = phone3;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
