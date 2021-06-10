package com.lucasmoraes.springbootIonic.resources;

import com.lucasmoraes.springbootIonic.domain.Category;
import com.lucasmoraes.springbootIonic.dto.CategoryDto;
import com.lucasmoraes.springbootIonic.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Anotações são diretivas do SpringBoot.
// Em aspas em RequestMapping, colocamos o endpoint do controlador REST
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource
{
    @Autowired
    private CategoryService service;

    // Deletando Categoria
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> find(@PathVariable Integer id)
    {
        Category obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    // Implementando POST categoria (criar nova categoria)
    // @RequestBody faz o Json ser convertido para objeto java automaticamente
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDto objDto)
    {
        Category obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // Implementando Put (atualizar categoria)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDto objDto,@PathVariable Integer id)
    {
        Category obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    // Mostrar todas as categorias
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDto> > findAll()
    {
        List<Category> list = service.findAll();
        List<CategoryDto> listDto = list.stream().map(obj -> new CategoryDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    // Mostrar categorias com paginacao
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<CategoryDto>> findPage(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name") String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC") String direction)
    {
        Page<Category> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<CategoryDto> listDto = list.map(obj -> new CategoryDto(obj));
        return ResponseEntity.ok().body(listDto);
    }


}
