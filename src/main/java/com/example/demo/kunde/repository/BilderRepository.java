package com.example.demo.kunde.repository;

import com.example.demo.kunde.model.Bilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilderRepository extends JpaRepository<Bilder, Long> {
}