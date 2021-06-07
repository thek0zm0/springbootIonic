package com.lucasmoraes.springbootIonic.repositories;

import com.lucasmoraes.springbootIonic.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Operações de acesso à dados
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>
{
}
