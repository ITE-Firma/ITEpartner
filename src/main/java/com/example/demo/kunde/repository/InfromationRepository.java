package com.example.demo.kunde.repository;

import com.example.demo.kunde.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfromationRepository  extends JpaRepository<Information, Long> {
}
