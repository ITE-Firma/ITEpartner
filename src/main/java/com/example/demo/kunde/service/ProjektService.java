package com.example.demo.kunde.service;

import com.example.demo.kunde.model.Customer;
import com.example.demo.kunde.model.Projekt;
import com.example.demo.kunde.repository.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjektService {
    @Autowired
    private ProjektRepository projektRepository;

    public List<Projekt> getAllProjekte() {
        return projektRepository.findAll();
    }

    public Projekt getProjektById(Long id) throws Exception {
        return projektRepository.findById(id).orElseThrow(() -> new Exception());
    }

    public Projekt createProjekt(Projekt projekt) {
        return projektRepository.save(projekt);
    }


    public void deleteProjekt(Long id) {
        projektRepository.deleteById(id);
    }
    public void saveProjekt(Projekt projekt) {
        projektRepository.save(projekt);
    }


    public void updateProjekt(Long id, Projekt projekt) throws Exception {
        Projekt existingProjekt = projektRepository.findById(id)
                .orElseThrow(() -> new Exception("Projekt not found with id: " + id));
        existingProjekt.setName(projekt.getName());
        existingProjekt.setBeschreibung(projekt.getBeschreibung());
        existingProjekt.setLogo(projekt.getLogo());
        existingProjekt.setStatus(projekt.getStatus());
        existingProjekt.setDetails(projekt.getDetails());
        projektRepository.save(existingProjekt);
    }
}
