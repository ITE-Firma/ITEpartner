package com.example.demo.kunde.repository;

import com.example.demo.kunde.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DAO class
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}


