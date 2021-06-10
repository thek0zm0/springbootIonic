package com.lucasmoraes.springbootIonic.services.validation;

import com.lucasmoraes.springbootIonic.domain.enums.ClientType;
import com.lucasmoraes.springbootIonic.dto.ClientNewDto;
import com.lucasmoraes.springbootIonic.resources.exceptions.FieldMessage;
import com.lucasmoraes.springbootIonic.services.validation.utils.BR;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDto>
{
    @Override
    public void initialize(ClientInsert ann)
    {
    }
    @Override
    public boolean isValid(ClientNewDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getType().equals(ClientType.PHYSICALPERSON.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj()))
        {
            list.add(new FieldMessage("cpfOrCnpj","Invalid CPF"));
        }
        if(objDto.getType().equals(ClientType.LEGALPERSON.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj()))
        {
            list.add(new FieldMessage("cpfOrCnpj","Invalid CNPJ"));
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