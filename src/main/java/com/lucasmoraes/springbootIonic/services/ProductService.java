package com.lucasmoraes.springbootIonic.services;

import com.lucasmoraes.springbootIonic.domain.Category;
import com.lucasmoraes.springbootIonic.domain.Product;
import com.lucasmoraes.springbootIonic.repositories.CategoryRepository;
import com.lucasmoraes.springbootIonic.repositories.ProductRepository;
import com.lucasmoraes.springbootIonic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;


    public Product find(Integer id)
    {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Object not found! Id: " + id + " Type: " + Product.class.getName()));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction)
    {
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        List<Category> categories = categoryRepository.findAllById(ids);

        return repository.search( name, categories, pageRequest);
    }
}
