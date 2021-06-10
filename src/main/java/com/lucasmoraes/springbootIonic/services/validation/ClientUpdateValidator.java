package com.lucasmoraes.springbootIonic.services.validation;

import com.lucasmoraes.springbootIonic.domain.Client;
import com.lucasmoraes.springbootIonic.domain.enums.ClientType;
import com.lucasmoraes.springbootIonic.dto.ClientDto;
import com.lucasmoraes.springbootIonic.dto.ClientNewDto;
import com.lucasmoraes.springbootIonic.repositories.ClientRepository;
import com.lucasmoraes.springbootIonic.resources.exceptions.FieldMessage;
import com.lucasmoraes.springbootIonic.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDto>
{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository repository;

    @Override
    public void initialize(ClientUpdate ann)
    {
    }
    @Override
    public boolean isValid(ClientDto objDto, ConstraintValidatorContext context) {
        Map<String,String> map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Client aux = repository.findByEmail(objDto.getEmail());
        if (aux!=null && !aux.getId().equals(uriId))
        {
            list.add(new FieldMessage("email","Email already exist"));
        }


        // inclua os testes aqui, inserindo erros na lista

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}