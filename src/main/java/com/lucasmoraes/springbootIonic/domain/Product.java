package com.lucasmoraes.springbootIonic.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Product implements Serializable
{
    // Atributos básicos
    private static final long serialVersionUID = 1L;
    @Id
    // Definindo estratégia de geração automática para chaves primárias
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;

    // Associações
    // O produto tem 1 ou mais categorias

    // No JPA, quando temos lista nos dois lados, utilizamos ManyToMany
    // No jointable, iremos definir a tabela intermediária que fara o MuitosParaMuitos

    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORY",
               joinColumns = @JoinColumn(name = "product_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    public Product(){}

    public Product(Integer id, String name, Double price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public List<Category> getCategories()
    {
        return categories;
    }

    public void setCategories(List<Category> categories)
    {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
