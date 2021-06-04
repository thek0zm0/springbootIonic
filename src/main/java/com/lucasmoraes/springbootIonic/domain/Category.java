package com.lucasmoraes.springbootIonic.domain;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable
{
    // Atributos básicos
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

    //Associações

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
