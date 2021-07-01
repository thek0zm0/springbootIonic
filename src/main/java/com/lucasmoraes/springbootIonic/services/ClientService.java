package com.lucasmoraes.springbootIonic.services;

import com.lucasmoraes.springbootIonic.domain.Address;
import com.lucasmoraes.springbootIonic.domain.Category;
import com.lucasmoraes.springbootIonic.domain.City;
import com.lucasmoraes.springbootIonic.domain.Client;
import com.lucasmoraes.springbootIonic.domain.enums.ClientType;
import com.lucasmoraes.springbootIonic.domain.enums.Profile;
import com.lucasmoraes.springbootIonic.dto.ClientDto;
import com.lucasmoraes.springbootIonic.dto.ClientNewDto;
import com.lucasmoraes.springbootIonic.repositories.AdressRepository;
import com.lucasmoraes.springbootIonic.repositories.ClientRepository;
import com.lucasmoraes.springbootIonic.security.UserSS;
import com.lucasmoraes.springbootIonic.services.exceptions.AuthorizationException;
import com.lucasmoraes.springbootIonic.services.exceptions.DataIntegrityException;
import com.lucasmoraes.springbootIonic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService
{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ClientRepository repository;

    @Autowired
    private AdressRepository adressRepository;


    public Client find(Integer id)
    {
        UserSS user = UserService.authenticated();

        if(user==null || !user.hasRole(Profile.ADMIN) && id.equals(user.getId()))
        {
            throw new AuthorizationException("Forbidden");
        }

        Optional<Client> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Object not found! Id: " + id + " Type: " + Client.class.getName()));
    }

    public Client insert(Client obj)
    {
        obj.setId(null);
        obj = repository.save(obj);
        adressRepository.saveAll(obj.getAdresses());
        return obj;
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
        return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null,null);
    }

    public Client fromDto(ClientNewDto objDto)
    {
        Client cli = new Client(null,objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getType()), bCryptPasswordEncoder.encode(objDto.getPassword()));
        City cid = new City(objDto.getCityId(), null, null);
        Address adr = new Address(null,objDto.getPublicPlace(),objDto.getNumber(),objDto.getAdjunct(),objDto.getDistrict(),objDto.getCep(),cli,cid);
        cli.getAdresses().add(adr);
        cli.getPhones().add(objDto.getPhone1());
        if(objDto.getPhone2()!=null)
        {
            cli.getPhones().add(objDto.getPhone2());
        }
        if(objDto.getPhone3()!=null)
        {
            cli.getPhones().add(objDto.getPhone3());
        }
        return cli;
    }
}
