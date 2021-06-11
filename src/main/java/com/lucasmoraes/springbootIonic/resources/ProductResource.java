package com.lucasmoraes.springbootIonic.resources;

import com.lucasmoraes.springbootIonic.domain.Category;
import com.lucasmoraes.springbootIonic.domain.Product;
import com.lucasmoraes.springbootIonic.domain.Product;
import com.lucasmoraes.springbootIonic.dto.CategoryDto;
import com.lucasmoraes.springbootIonic.dto.ProductDto;
import com.lucasmoraes.springbootIonic.resources.utils.URL;
import com.lucasmoraes.springbootIonic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Anotações são diretivas do SpringBoot.
// Em aspas em RequestMapping, colocamos o endpoint do controlador REST
@RestController
@RequestMapping(value = "/products")
public class ProductResource
{
    @Autowired
    private ProductService service;

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id)
    {
        Product obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    // Mostrar categorias com paginacao
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDto>> findPage(
            @RequestParam(value = "name",defaultValue = "") String name,
            @RequestParam(value = "categories",defaultValue = "") String categories,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name") String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC") String direction)
    {
        String decodeName = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = service.search( decodeName, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDto> listDto = list.map(obj -> new ProductDto(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
