package com.example.demo.kunde.Controller;

import com.example.demo.kunde.model.Bilder;
import com.example.demo.kunde.model.Feedback;
import com.example.demo.kunde.repository.BilderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.io.IOException;
import java.util.List;

@Controller
public class BilderController {
    @Autowired
    private final BilderRepository mrEntityRepository;
    private Bilder bilder;
    public BilderController(BilderRepository mrEntityRepository) {
        this.mrEntityRepository = mrEntityRepository;
        List<Bilder> bilderList = mrEntityRepository.findAll();
        if (!bilderList.isEmpty()) {
            bilder = bilderList.get(0);
        } else {
            // Erstellen Sie ein neues Bilder-Objekt und speichern Sie es in der Datenbank
            bilder = new Bilder();
            mrEntityRepository.save(bilder);
            bilder = bilder;
        }
    }
    public BilderRepository getMrEntityRepository() {
        return mrEntityRepository;
    }
    public Bilder getBilder() {
        return bilder;
    }

    @GetMapping("/admin/bilder")
    public String index(Model model) {
        model.addAttribute("mrEntity", bilder);
        System.out.println(bilder.getId());

        // Logo
        if (bilder.getLogo() != null) {
            model.addAttribute("logoImage", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getLogo()));
        } else {
            model.addAttribute("logoImage", null); // Oder eine Standardabbildung
        }

        // Welcome
        if (bilder.getWelcome() != null) {
            model.addAttribute("welcome", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getWelcome()));
        } else {
            model.addAttribute("welcome", null); // Oder eine Standardabbildung
        }

        // aboutus
        if (bilder.getAboutus() != null) {
            model.addAttribute("aboutus", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getAboutus()));
        } else {
            model.addAttribute("aboutus", null); // Oder eine Standardabbildung
        }

        // Akustik
        if (bilder.getAkustik() != null) {
            model.addAttribute("akustik", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getAkustik()));
        } else {
            model.addAttribute("akustik", null); // Oder eine Standardabbildung
        }

        // Auditierung
        if (bilder.getAuditierung() != null) {
            model.addAttribute("auditierung", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getAuditierung()));
        } else {
            model.addAttribute("auditierung", null); // Oder eine Standardabbildung
        }

        // Beratung
        if (bilder.getBeratung() != null) {
            model.addAttribute("beratung", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getBeratung()));
        } else {
            model.addAttribute("beratung", null); // Oder eine Standardabbildung
        }

        // qus
        if (bilder.getQus() != null) {
            model.addAttribute("qus", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getQus()));
        } else {
            model.addAttribute("qus", null); // Oder eine Standardabbildung
        }

        // shuk
        if (bilder.getShuk() != null) {
            model.addAttribute("shuk", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getShuk()));
        } else {
            model.addAttribute("shuk", null); // Oder eine Standardabbildung
        }

        // referenzen
        if (bilder.getReferenzen() != null) {
            model.addAttribute("referenzen", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getReferenzen()));
        } else {
            model.addAttribute("referenzen", null); // Oder eine Standardabbildung
        }

        // projecte
        if (bilder.getProjecte() != null) {
            model.addAttribute("projecte", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getProjecte()));
        } else {
            model.addAttribute("projecte", null); // Oder eine Standardabbildung
        }

        // programierung
        if (bilder.getProgramierung() != null) {
            model.addAttribute("programierung", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getProgramierung()));
        } else {
            model.addAttribute("programierung", null); // Oder eine Standardabbildung
        }

        // engineering
        if (bilder.getEngineering() != null) {
            model.addAttribute("engineering", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getEngineering()));
        } else {
            model.addAttribute("engineering", null); // Oder eine Standardabbildung
        }

        // hust
        if (bilder.getHust() != null) {
            model.addAttribute("hust", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilder.getHust()));
        } else {
            model.addAttribute("hust", null); // Oder eine Standardabbildung
        }

        return "test1";
    }

    @GetMapping("/showlogo")
    public ResponseEntity<byte[]> getImage() {
        byte[] imageData = bilder.getWelcome();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type based on your image format
        System.out.println(bilder.toString());

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @PostMapping("/admin/welocm")
    public String uploadWelocm(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setWelcome(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/aboutus")
    public String uploadAboutus(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setAboutus(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/referenzen")
    public String uploadreferenzen(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setReferenzen(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/akustik")
    public String uploadAkustik(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setAkustik(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/auditierung")
    public String uploadAuditierung(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setAuditierung(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/beratung")
    public String uploadBeratung(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setBeratung(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/eng")
    public String uploadEng(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setEngineering(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/hust")
    public String uploadhust(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setHust(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/prog")
    public String uploadProg(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setProgramierung(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/qus")
    public String Qus(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setQus(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/shuk")
    public String uploadshuk(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setShuk(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/projecte")
    public String uploadProjecte(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setProjecte(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }

    @PostMapping("/admin/logo")
    public String uploadLogo(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setLogo(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }


}