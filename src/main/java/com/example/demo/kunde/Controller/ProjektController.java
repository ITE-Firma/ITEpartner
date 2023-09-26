package com.example.demo.kunde.Controller;

import com.example.demo.kunde.model.Projekt;
import com.example.demo.kunde.service.ProjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProjektController {

    @Autowired
    ProjektService projektService;

    @PostMapping("/admin/projekte")
    public String addProject(@ModelAttribute Projekt projekt, @RequestParam("pictureFile") MultipartFile pictureFile) throws IOException {
        if (!pictureFile.isEmpty()) {
            projekt.setLogo(pictureFile.getBytes());
        }
        projektService.createProjekt(projekt);
        return "redirect:/admin/projekte";
    }
    @PostMapping("/admin/project/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projektService.deleteProjekt(id);
        return "redirect:/admin/projekte";
    }
    @PostMapping("/admin/project/edit/{id}")
    public String editProject(@PathVariable Long id,@ModelAttribute Projekt projekt) throws Exception {

        projektService.updateProjekt(id ,projekt);
        return "redirect:/admin/projekte";
    }
    @GetMapping("/admin/projekte")
    public String showProjekt(Model model) {
        List<Projekt> projekte = projektService.getAllProjekte();
        model.addAttribute("projekte", projekte);
        return "ProjektePortal";
    }
    @GetMapping("/admin/projekte/image/{projectId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long projectId) throws Exception {
        Projekt projekt = projektService.getProjektById(projectId);
        byte[] imageData = projekt.getLogo(); // Assuming your project entity has a 'getLogo' method to get the image data
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Change this to the appropriate image format
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}
