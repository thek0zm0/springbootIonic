package com.lucasmoraes.springbootIonic.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Adress implements Serializable
{
    // Atributos básicos
    private static final long serialVersionUID = 1L;
    @Id
    // Definindo estratégia de geração automática para chaves primárias
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String publicPlace;
    private String number;
    private String adjunct;
    private String district;
    private String cep;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Adress(){}

    public Adress(Integer id, String publicPlace, String number, String adjunct, String district, String cep, Client client, City city)
    {
        this.id = id;
        this.publicPlace = publicPlace;
        this.number = number;
        this.adjunct = adjunct;
        this.district = district;
        this.cep = cep;
        this.client = client;
        this.city = city;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return Objects.equals(id, adress.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
