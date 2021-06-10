package com.lucasmoraes.springbootIonic.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasmoraes.springbootIonic.domain.enums.ClientType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "TB_CLIENT")
public class Client implements Serializable
{
    // Atributos básicos
    private static final long serialVersionUID = 1L;
    @Id
    // Definindo estratégia de geração automática para chaves primárias
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer type;

    @OneToMany(mappedBy = "client")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONES")
    private Set<String> phones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public Client(){}

    public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType type)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = (type==null) ? null : type.getCod();
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

    public String getCpfOrCnpj()
    {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj)
    {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public ClientType getType()
    {
        return ClientType.toEnum(type);
    }

    public void setType(ClientType type)
    {
        this.type = type.getCod();
    }

    public List<Address> getAdresses()
    {
        return addresses;
    }

    public void setAdresses(List<Address> addresses)
    {
        this.addresses = addresses;
    }

    public Set<String> getPhones()
    {
        return phones;
    }

    public void setPhones(Set<String> phones)
    {
        this.phones = phones;
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
