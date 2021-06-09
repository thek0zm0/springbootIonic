package com.lucasmoraes.springbootIonic.services;

import com.lucasmoraes.springbootIonic.domain.Order;
import com.lucasmoraes.springbootIonic.repositories.OrderRepository;
import com.lucasmoraes.springbootIonic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService
{

    @Autowired
    private OrderRepository repository;


    public Order find(Integer id)
    {
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Object not found! Id: " + id + " Type: " + Order.class.getName()));
    }
}
