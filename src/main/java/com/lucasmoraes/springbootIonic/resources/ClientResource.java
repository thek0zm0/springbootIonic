package com.lucasmoraes.springbootIonic.resources;

import com.lucasmoraes.springbootIonic.domain.Client;
import com.lucasmoraes.springbootIonic.dto.ClientDto;
import com.lucasmoraes.springbootIonic.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

// Anotações são diretivas do SpringBoot.
// Em aspas em RequestMapping, colocamos o endpoint do controlador REST
@RestController
@RequestMapping(value = "/clients")
public class ClientResource
{
    @Autowired
    private ClientService service;

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Client> find(@PathVariable Integer id)
    {
        Client obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Implementando Put (atualizar categoria)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDto objDto,@PathVariable Integer id)
    {
        Client obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    // Mostrar todas as categorias
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDto>> findAll()
    {
        List<Client> list = service.findAll();
        List<ClientDto> listDto = list.stream().map(obj -> new ClientDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    // Mostrar categorias com paginacao
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDto>> findPage(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name") String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC") String direction)
    {
        Page<Client> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<ClientDto> listDto = list.map(obj -> new ClientDto(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
