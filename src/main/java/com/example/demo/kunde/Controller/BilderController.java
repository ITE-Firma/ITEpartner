package com.example.demo.kunde.Controller;

import com.example.demo.kunde.model.Bilder;
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
            String base64Image = Base64.getEncoder().encodeToString(bilder.getLogo());
            String imageFormat = isJpeg(bilder.getLogo()) ? "jpeg" : "png";
            model.addAttribute("logoImage", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("logoImage", null); // Oder eine Standardabbildung
        }

        // Welcome
        if (bilder.getWelcome() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getWelcome());
            String imageFormat = isJpeg(bilder.getWelcome()) ? "jpeg" : "png";
            model.addAttribute("welcome", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("welcome", null); // Oder eine Standardabbildung
        }

        // aboutus
        if (bilder.getAboutus() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getAboutus());
            String imageFormat = isJpeg(bilder.getAboutus()) ? "jpeg" : "png";
            model.addAttribute("about", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("about", null); // Oder eine Standardabbildung
        }

        // Akustik
        if (bilder.getAkustik() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getAkustik());
            String imageFormat = isJpeg(bilder.getAkustik()) ? "jpeg" : "png";
            model.addAttribute("akustik", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("akustik", null); // Oder eine Standardabbildung
        }

        // Auditierung
        if (bilder.getAuditierung() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getAuditierung());
            String imageFormat = isJpeg(bilder.getAuditierung()) ? "jpeg" : "png";
            model.addAttribute("auditierung", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("auditierung", null); // Oder eine Standardabbildung
        }

        // Beratung
        if (bilder.getBeratung() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getBeratung());
            String imageFormat = isJpeg(bilder.getBeratung()) ? "jpeg" : "png";
            model.addAttribute("beratung", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("beratung", null); // Oder eine Standardabbildung
        }

        // qus
        if (bilder.getQus() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getQus());
            String imageFormat = isJpeg(bilder.getQus()) ? "jpeg" : "png";
            model.addAttribute("qus", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("qus", null); // Oder eine Standardabbildung
        }

        // shuk
        if (bilder.getShuk() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getShuk());
            String imageFormat = isJpeg(bilder.getShuk()) ? "jpeg" : "png";
            model.addAttribute("shuk", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("shuk", null); // Oder eine Standardabbildung
        }

        // referenzen
        if (bilder.getReferenzen() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getReferenzen());
            String imageFormat = isJpeg(bilder.getReferenzen()) ? "jpeg" : "png";
            model.addAttribute("referenzen", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("referenzen", null); // Oder eine Standardabbildung
        }

        // projecte
        if (bilder.getShuk() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getShuk());
            String imageFormat = isJpeg(bilder.getShuk()) ? "jpeg" : "png";
            model.addAttribute("projecte", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("projecte", null); // Oder eine Standardabbildung
        }

        // programierung
        if (bilder.getProgramierung() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getProgramierung());
            String imageFormat = isJpeg(bilder.getProgramierung()) ? "jpeg" : "png";
            model.addAttribute("programierung", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("programierung", null); // Oder eine Standardabbildung
        }

        // engineering
        if (bilder.getEngineering() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getEngineering());
            String imageFormat = isJpeg(bilder.getEngineering()) ? "jpeg" : "png";
            model.addAttribute("engineering", "data:image/" + imageFormat + ";base64," + base64Image);
        } else {
            model.addAttribute("engineering", null); // Oder eine Standardabbildung
        }

        // hust
        if (bilder.getHust() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getHust());
            String imageFormat = isJpeg(bilder.getHust()) ? "jpeg" : "png";
            model.addAttribute("hust", "data:image/" + imageFormat + ";base64," + base64Image);        } else {
            model.addAttribute("hust", null); //
        }
        // sleider 1
        if (bilder.getSlide1() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getSlide1());
            String imageFormat = isJpeg(bilder.getSlide1()) ? "jpeg" : "png";
            model.addAttribute("slide1", "data:image/" + imageFormat + ";base64," + base64Image);        } else {
            model.addAttribute("slide1", null); //
        }
        // sleider 2
        if (bilder.getSlide2() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getSlide2());
            String imageFormat = isJpeg(bilder.getSlide2()) ? "jpeg" : "png";
            model.addAttribute("slide2", "data:image/" + imageFormat + ";base64," + base64Image);        } else {
            model.addAttribute("slide2", null); //
        }
        // sleider 3
        if (bilder.getSlide3() != null) {
            String base64Image = Base64.getEncoder().encodeToString(bilder.getSlide3());
            String imageFormat = isJpeg(bilder.getSlide3()) ? "jpeg" : "png";
            model.addAttribute("slide3", "data:image/" + imageFormat + ";base64," + base64Image);        } else {
            model.addAttribute("slide3", null); //
        }

        return "UploadPortal";
    }
    public boolean isJpeg(byte[] imageData) {
        // JPEG-Bilder beginnen typischerweise mit den Bytes 0xFF, 0xD8
        return (imageData.length >= 2 && imageData[0] == (byte) 0xFF && imageData[1] == (byte) 0xD8);
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
    @PostMapping("/admin/slide1")
    public String setSlide1(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setSlide1(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }
    @PostMapping("/admin/slide2")
    public String setSlide2(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setSlide2(pictureFile.getBytes());
        mrEntityRepository.save(bilder);
        System.out.println(bilder.getId());
        return "redirect:/admin/bilder";
    }
    @PostMapping("/admin/slide3")
    public String setSlide3(@RequestParam("pictureFile") MultipartFile pictureFile) throws Exception {
        System.out.println(pictureFile.getBytes());
        bilder.setSlide3(pictureFile.getBytes());
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