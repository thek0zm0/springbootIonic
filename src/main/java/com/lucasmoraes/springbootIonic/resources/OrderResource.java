package com.lucasmoraes.springbootIonic.resources;

import com.lucasmoraes.springbootIonic.domain.Order;
import com.lucasmoraes.springbootIonic.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Anotações são diretivas do SpringBoot.
// Em aspas em RequestMapping, colocamos o endpoint do controlador REST
@RestController
@RequestMapping(value = "/orders")
public class OrderResource
{
    @Autowired
    private OrderService service;

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id)
    {
        Order obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
