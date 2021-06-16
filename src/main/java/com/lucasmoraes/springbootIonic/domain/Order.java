package com.lucasmoraes.springbootIonic.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "TB_ORDER")
public class Order implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    // Definindo estratégia de geração automática para chaves primárias
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
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

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public Double getTotalValue()
    {
        Double sum = 0.0;
        for(OrderItem orderItem : items)
        {
            sum += orderItem.getSubTotal();
        }
        return sum;
    }

    @Override
    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        final StringBuilder sb = new StringBuilder("Order:: ");
        sb.append("Order id: ");
        sb.append(getId());
        sb.append(", Instant: ");
        sb.append(sdf.format(getInstant()));
        sb.append(", Client: ");
        sb.append(getClient().getName());
        sb.append(", Payment Status: ");
        sb.append(getPayment().getStatus().getDescription());
        sb.append("\nDetails\n");
        for (OrderItem ip : getItems())
        {
            sb.append(ip.toString());
        }
        sb.append("Total Value: ");
        sb.append(nf.format(getTotalValue()));
        return sb.toString();
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
