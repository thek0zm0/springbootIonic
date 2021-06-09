package com.lucasmoraes.springbootIonic.services;

import com.lucasmoraes.springbootIonic.domain.Category;
import com.lucasmoraes.springbootIonic.repositories.CategoryRepository;
import com.lucasmoraes.springbootIonic.services.exceptions.DataIntegrityException;
import com.lucasmoraes.springbootIonic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{

    @Autowired
    private CategoryRepository repository;


    public Category find(Integer id)
    {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Object not found! Id: " + id + " Type: " + Category.class.getName()));
    }

    public Category insert(Category obj)
    {
        obj.setId(null);
        return repository.save(obj);
    }

    public Category update(Category obj)
    {
        find(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id)
    {
        find(id);
        try
        {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataIntegrityException("Cant delete this category with products related");
        }
    }

    public List<Category> findAll()
    {
        return repository.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction)
    {
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
}
