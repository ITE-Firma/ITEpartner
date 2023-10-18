package com.example.demo.kunde.Controller;

import com.example.demo.kunde.model.Information;
import com.example.demo.kunde.model.Projekt;
import com.example.demo.kunde.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class InformationController {
    @Autowired
    InformationService informationService;
    @PostMapping("/admin/add/information")
    public String addInfo(@ModelAttribute Information information) throws Exception {
        if(informationService.getAllInformationen().size()==0)
            informationService.createInformation(information);
        else
            informationService.updateInformation(informationService.getAllInformationen().get(0).getId(),information);

        return "redirect:/admin/information";
    }
    @GetMapping("/admin/information/delete/{id}")
    public String deleteInformation(@PathVariable Long id) {
        informationService.deleteInformation(id);
        return "redirect:/admin/information";
    }
    @GetMapping("/admin/information")
    public String showInformationen(Model model) {
        List<Information> information = informationService.getAllInformationen();
        model.addAttribute("information", information);
        return "InfoPortal";
    }

    @PostMapping("/admin/information/edit/{id}")
    public String editProject(@PathVariable Long id,@ModelAttribute Information information) throws Exception {
        informationService.updateInformation(id ,information);
        return "redirect:/admin/information";
    }
}
