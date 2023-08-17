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
        model=setSlider(model);
        return "index";
    }
    public Model getAttribut(String s, Model m){
        switch(s) {
            case "index":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("logoImage", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getLogo()));
                else
                    m.addAttribute("logoImage", null); // Oder eine Standardabbildung
                break;

            case "welcome":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("welcome", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getWelcome()));
                else
                    m.addAttribute("welcome", null); // Oder eine Standardabbildung
                break;

            case "aboutus":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("aboutus", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getAboutus()));
                else
                    m.addAttribute("aboutus", null); // Oder eine Standardabbildung
                break;

            case "akustik":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("akustik", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getAkustik()));
                else
                    m.addAttribute("akustik", null); // Oder eine Standardabbildung
                break;

            case "auditierung":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("auditierung", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getAuditierung()));
                else
                    m.addAttribute("auditierung", null); // Oder eine Standardabbildung
                break;

            case "beratung":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("beratung", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getBeratung()));
                else
                    m.addAttribute("beratung", null); // Oder eine Standardabbildung
                break;

            case "qus":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("qus", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getQus()));
                else
                    m.addAttribute("qus", null); // Oder eine Standardabbildung
                break;

            case "shuk":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("shuk", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getShuk()));
                else
                    m.addAttribute("shuk", null); // Oder eine Standardabbildung
                break;

            case "referenzen":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("referenzen", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getReferenzen()));
                else
                    m.addAttribute("referenzen", null); // Oder eine Standardabbildung
                break;

            case "projecte":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("projecte", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getProjecte()));
                else
                    m.addAttribute("projecte", null); // Oder eine Standardabbildung
                break;

            case "programierung":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("programierung", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getProgramierung()));
                else
                    m.addAttribute("programierung", null); // Oder eine Standardabbildung
                break;

            case "engineering":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("engineering", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getEngineering()));
                else
                    m.addAttribute("engineering", null); // Oder eine Standardabbildung
                break;
            case "hust":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("hust", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getHust()));
                else
                    m.addAttribute("hust", null); // Oder eine Standardabbildung
                break;
            case "slide1":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("slide1", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getHust()));
                else
                    m.addAttribute("slide1", null); // Oder eine Standardabbildung
                break;
            case "slide2":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("slide2", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getHust()));
                else
                    m.addAttribute("slide2", null); // Oder eine Standardabbildung
                break;
            case "slide3":
                m.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null)
                    m.addAttribute("slide3", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getHust()));
                else
                    m.addAttribute("slide3", null); // Oder eine Standardabbildung
                break;

        }
        return m;
    }
    public Model setSlider( Model m){
        m= getAttribut("slide1",m);
        m= getAttribut("slide2",m);
        m= getAttribut("slide3",m);
        return m;
    }
}