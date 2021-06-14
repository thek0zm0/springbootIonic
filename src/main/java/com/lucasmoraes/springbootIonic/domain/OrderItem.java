package com.lucasmoraes.springbootIonic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.aspectj.weaver.ast.Or;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderItem implements Serializable
{
    // Atributos básicos
    private static final long serialVersionUID = 1L;
    @JsonIgnore
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

    @JsonIgnore
    public Order getOrder()
    {
        return id.getOrder();
    }

    public Product getProduct()
    {
        return id.getProduct();
    }

    public void setOrder(Order order)
    {
        id.setOrder(order);
    }

    public void setProduct(Product product)
    {
        id.setProduct(product);
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

    public Double getSubTotal()
    {
        return (price-discount)*quantity;
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
