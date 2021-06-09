package com.lucasmoraes.springbootIonic.dto;

import com.lucasmoraes.springbootIonic.domain.Category;

import java.io.Serializable;

public class CategoryDto implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

    public CategoryDto(){}

    public CategoryDto(Category obj)
    {
        id = obj.getId();
        name = obj.getName();
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
}
