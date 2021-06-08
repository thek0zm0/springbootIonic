package com.lucasmoraes.springbootIonic.domain;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_ORDER")
public class Order implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    // Definindo estratégia de geração automática para chaves primárias
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date instant;

    // Erro de entidade transiente
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Order(){}

    public Order(Integer id, Date instant, Client client, Address deliveryAddress)
    {
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Date getInstant()
    {
        return instant;
    }

    public void setInstant(Date instant)
    {
        this.instant = instant;
    }

    public Payment getPayment()
    {
        return payment;
    }

    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }

    public Address getDeliveryAddress()
    {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<OrderItem> getItems()
    {
        return items;
    }

    public void setItems(Set<OrderItem> items)
    {
        this.items = items;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
