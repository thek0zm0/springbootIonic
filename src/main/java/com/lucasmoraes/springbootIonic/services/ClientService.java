package com.lucasmoraes.springbootIonic.services;

import com.lucasmoraes.springbootIonic.domain.Client;
import com.lucasmoraes.springbootIonic.dto.ClientDto;
import com.lucasmoraes.springbootIonic.repositories.ClientRepository;
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
public class ClientService
{

    @Autowired
    private ClientRepository repository;


    public Client find(Integer id)
    {
        Optional<Client> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Object not found! Id: " + id + " Type: " + Client.class.getName()));
    }

    public Client update(Client obj)
    {
        Client newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(Client newObj, Client obj)
    {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
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
            throw new DataIntegrityException("Cant delete this client because entities related");
        }
    }

    public List<Client> findAll()
    {
        return repository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction)
    {
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Client fromDto(ClientDto objDto)
    {
        return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }
}
