package com.example.demo.kunde.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;

@Controller
public class HomeController {
    private final BilderController bilderController;
    @Autowired
    public HomeController(BilderController bilderController){
        this.bilderController=bilderController;
    }
    @GetMapping("/")
    public String home(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("welcome",model);
        model=getAttribut("aboutus",model);
        model= getAttribut("slide1",model);
        model= getAttribut("slide2",model);
        model= getAttribut("slide3",model);
        return "index";
    }
    @GetMapping("/api/v1/user/")
    public String HomeIndex(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("welcome",model);
        model=getAttribut("aboutus",model);
        model= getAttribut("slide1",model);
        model= getAttribut("slide2",model);
        model= getAttribut("slide3",model);
        return "index";
    }
    public Model getAttribut(String s, Model model){
        switch(s) {
            case "index":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getLogo());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getLogo()) ? "jpeg" : "png";
                    model.addAttribute("logoImage", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("logoImage", null); // Oder eine Standardabbildung
                break;

            case "welcome":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    model.addAttribute("welcome", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getWelcome()));
                else
                    model.addAttribute("welcome", null); // Oder eine Standardabbildung
                break;

            case "aboutus":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getAboutus() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getAboutus());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getAboutus()) ? "jpeg" : "png";
                    model.addAttribute("logoImage", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("aboutus", null); // Oder eine Standardabbildung
                break;

            case "akustik":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getAkustik() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getAkustik());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getAkustik()) ? "jpeg" : "png";
                    model.addAttribute("akustik", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("akustik", null); // Oder eine Standardabbildung
                break;

            case "auditierung":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getAuditierung() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getAuditierung());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getAuditierung()) ? "jpeg" : "png";
                    model.addAttribute("auditierung", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("auditierung", null); // Oder eine Standardabbildung
                break;

            case "beratung":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getBeratung() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getBeratung());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getBeratung()) ? "jpeg" : "png";
                    model.addAttribute("beratung", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("beratung", null); // Oder eine Standardabbildung
                break;

            case "qus":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getQus() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getQus());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getQus()) ? "jpeg" : "png";
                    model.addAttribute("qus", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("qus", null); // Oder eine Standardabbildung
                break;

            case "shuk":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getShuk() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getShuk());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getShuk()) ? "jpeg" : "png";
                    model.addAttribute("shuk", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("shuk", null); // Oder eine Standardabbildung
                break;

            case "referenzen":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getReferenzen() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getReferenzen());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getReferenzen()) ? "jpeg" : "png";
                    model.addAttribute("referenzen", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("referenzen", null); // Oder eine Standardabbildung
                break;

            case "projecte":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getProjecte() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getProjecte());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getProjecte()) ? "jpeg" : "png";
                    model.addAttribute("projecte", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("projecte", null); // Oder eine Standardabbildung
                break;

            case "programierung":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getProgramierung() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getProgramierung());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getProgramierung()) ? "jpeg" : "png";
                    model.addAttribute("programierung", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("programierung", null); // Oder eine Standardabbildung
                break;

            case "engineering":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getEngineering() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getEngineering());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getEngineering()) ? "jpeg" : "png";
                    model.addAttribute("engineering", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("engineering", null); // Oder eine Standardabbildung
                break;
            case "hust":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getHust() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getHust());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getHust()) ? "jpeg" : "png";
                    model.addAttribute("hust", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("hust", null); // Oder eine Standardabbildung
                break;
            case "slide1":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getSlide1() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getSlide1());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getSlide1()) ? "jpeg" : "png";
                    model.addAttribute("slide2", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("slide1", null); // Oder eine Standardabbildung
                break;
            case "slide2":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getSlide2() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getSlide2());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getSlide2()) ? "jpeg" : "png";
                    model.addAttribute("slide2", "data:image/" + imageFormat + ";base64," + base64Image);
                }
                else
                    model.addAttribute("slide2", null); // Oder eine Standardabbildung
                break;
            case "slide3":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getSlide3() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getSlide3());
                    String imageFormat = bilderController.isJpeg(bilderController.getBilder().getSlide3()) ? "jpeg" : "png";
                    model.addAttribute("slide2", "data:image/" + imageFormat + ";base64," + base64Image);
                }
                else
                    model.addAttribute("slide3", null); // Oder eine Standardabbildung
                break;

        }
        return model;
    }
    public Model setSlider( Model m){
        m= getAttribut("slide1",m);
        m= getAttribut("slide2",m);
        m= getAttribut("slide3",m);
        return m;
    }
}