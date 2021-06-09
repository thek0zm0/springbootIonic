package com.lucasmoraes.springbootIonic.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Category implements Serializable
{
    // Atributos básicos
    private static final long serialVersionUID = 1L;
    @Id
    // Definindo estratégia de geração automática para chaves primárias
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    // Associações
    // Uma categoria tem vários produtos, conforme diagrama

    @ManyToMany(mappedBy = "categories")
    private List<Product> products = new ArrayList<>();

    // Construtor vazio
    public Category(){}

    // Construtor com argumentos
    public Category(Integer id, String name)
    {
        this.id = id;
        this.name = name;
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

    public List<Product> getProducts()
    {
        return products;
    }

    public void setProducts(List<Product> products)
    {
        this.products = products;
    }

    // Para que dois objetos possam ser comparados pelo seu conteúdo e não sua posição na memória.
    // Utilizamos Hash code equals
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
