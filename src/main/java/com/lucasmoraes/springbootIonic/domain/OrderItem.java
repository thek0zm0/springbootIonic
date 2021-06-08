package com.lucasmoraes.springbootIonic.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderItem implements Serializable
{
    // Atributos básicos
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private OrderItemPk id = new OrderItemPk();
    private Double discount;
    private Integer quantity;
    private Double price;

    public OrderItem()
    {}

    public OrderItem(Order order, Product product, Double discount, Integer quantity, Double price)
    {
        id.setOrder(order);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder()
    {
        return id.getOrder();
    }

    public Product getProduct()
    {
        return id.getProduct();
    }

    public OrderItemPk getId()
    {
        return id;
    }

    public void setId(OrderItemPk id)
    {
        this.id = id;
    }

    public Double getDiscount()
    {
        return discount;
    }

    public void setDiscount(Double discount)
    {
        this.discount = discount;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
