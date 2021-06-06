package com.lucasmoraes.springbootIonic.repositories;

import com.lucasmoraes.springbootIonic.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Operações de acesso à dados
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
}
