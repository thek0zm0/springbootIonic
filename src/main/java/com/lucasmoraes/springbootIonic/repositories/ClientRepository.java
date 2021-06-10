package com.lucasmoraes.springbootIonic.repositories;

import com.lucasmoraes.springbootIonic.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


// Operações de acesso à dados
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>
{
    // Busca no banco de dados um cliente passando como argumento um email
    @Transactional(readOnly = true)
    Client findByEmail(String email);
}
