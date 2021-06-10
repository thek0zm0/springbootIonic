package com.lucasmoraes.springbootIonic.dto;

import com.lucasmoraes.springbootIonic.domain.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryDto implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotEmpty(message = "Uncompleted Field")
    @Length(min = 5, max = 80, message = "Size needs to be between 5 and 80 characters")
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
