package com.example.demo.kunde.service;

import com.example.demo.kunde.model.Information;
import com.example.demo.kunde.repository.InfromationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationService {
    @Autowired
    InfromationRepository infromationRepository;
    public List<Information> getAllInformationen() {
        return infromationRepository.findAll();
    }
    public Information getInformationenById(Long id) throws Exception {
        return infromationRepository.findById(id).orElseThrow(Exception::new);
    }
    public void createInformation(Information information) {
        infromationRepository.save(information);
    }
    public void deleteInformation(Long id) {
        infromationRepository.deleteById(id);
    }
    public void saveInformation(Information information) {
        infromationRepository.save(information);
    }
    public void updateInformation(Long id, Information information) throws Exception {
        Information existingInfo = infromationRepository.findById(id)
                .orElseThrow(() -> new Exception("infromation not found with id: " + id));
        if(information.getInfo()!=null)
            existingInfo.setInfo(information.getInfo());
        infromationRepository.save(existingInfo);
    }
}
