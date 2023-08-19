package com.example.demo.kunde.repository;

import com.example.demo.kunde.model.Projekt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjektRepository extends JpaRepository<Projekt, Long> {

}
