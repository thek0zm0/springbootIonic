package com.lucasmoraes.springbootIonic.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasmoraes.springbootIonic.domain.enums.ClientType;
import com.lucasmoraes.springbootIonic.domain.enums.Profile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
    // Isto já resolve o problema de integridade, porém não controlamos o lançamento da excessão
    @Column(unique = true)
    private String email;
    private String cpfOrCnpj;
    private Integer type;
    @JsonIgnore
    private String password;

    // Cascada all significa que toda a modificação em cliente será refletida em endereços

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONES")
    private Set<String> phones = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public Client()
    {
        addProfile(Profile.CLIENT);
    }

    public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType type, String password)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = (type==null) ? null : type.getCod();
        this.password = password;
        addProfile(Profile.CLIENT);
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Set<Profile> getProfile()
    {
        return profiles.stream().map(x-> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile)
    {
        profiles.add(profile.getCod());
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
