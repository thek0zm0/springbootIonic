package com.lucasmoraes.springbootIonic.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Anotações são diretivas do SpringBoot.
// Em aspas em RequestMapping, colocamos o endpoint do controlador REST
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource
{

    @RequestMapping(method = RequestMethod.GET)
    public String listar()
    {
        return "Rest está funcionando";
    }
}
