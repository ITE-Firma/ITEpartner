package com.example.demo.kunde.Controller;
import com.example.demo.kunde.email.EmailSender;
import com.example.demo.kunde.email.EmailService;
import com.example.demo.kunde.model.Customer;
import com.example.demo.kunde.model.Feedback;
import com.example.demo.kunde.model.Information;
import com.example.demo.kunde.model.Projekt;
import com.example.demo.kunde.regestrieren.token.RegistrationService;
import com.example.demo.kunde.service.CustomerService;
import com.example.demo.kunde.service.FeedbackService;
import com.example.demo.kunde.service.InformationService;
import com.example.demo.kunde.service.ProjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


@Controller

@RequestMapping("/api/v1/user/")
public class ContactController {
    private final BilderController bilderController;
    private final EmailSender emailSender;
    private final EmailService emailService;
    private final RegistrationService registrationService;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private ProjektService projektService;
    @Autowired
    private InformationService informationService;
    public ContactController(RegistrationService registrationService, SpringTemplateEngine templateEngine, EmailSender emailSender,EmailService emailService,BilderController bilderController) {
        this.registrationService = registrationService;
        this.templateEngine = templateEngine;
        this.emailSender=emailSender;
        this.emailService=emailService;
        this.bilderController =bilderController;
    }

    @GetMapping("/index")
    public String showForm(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("welcome",model);
        model=getAttribut("about",model);
        model= getAttribut("slide1",model);
        model= getAttribut("slide2",model);
        model= getAttribut("slide3",model);
        List<Information>information=informationService.getAllInformationen();
        model.addAttribute("information", information);
        List<Projekt> projekte = projektService.getAllProjekte();
        model.addAttribute("projekte", projekte);
        System.out.println("showForm sucess ");
        return "index";
    }
    @GetMapping("/projektAnsicht/{projectId}")
    public String showProjects(@PathVariable Long projectId,Model model) throws Exception {
        model=getAttribut("index",model);
        model=setSlider(model);
        Projekt projekt= projektService.getProjektById(projectId);
        model.addAttribute("projekt", projekt);
        List<Projekt> projekte = projektService.getAllProjekte();
        model.addAttribute("projekte", projekte);
        System.out.println("showProjects sucess ");
        return "projekt";
    }
    @GetMapping("/datenschutz")
    public String showDatenschutz(Model model) {
        model=getAttribut("index",model);
        model=setSlider(model);
        System.out.println("showDatenschutz sucess ");
        return "ITE-Datenschutz";
    }
    @GetMapping("/agb")
    public String showAgb(Model model) {
        model=getAttribut("index",model);
        model=setSlider(model);
        System.out.println("showAgb sucess ");
        return "AGB";
    }
    @GetMapping("/QuS")
    public String showQuS(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("qus",model);
        model=setSlider(model);
        System.out.println("showQuS sucess");
        return "QuS";
    }
    @GetMapping("/Auditierung")
    public String showAuditierung(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("auditierung",model);
        model=setSlider(model);
        System.out.println("showAuditierung sucess");
        return "Auditierung";
    }
    @GetMapping("/Beratung")
    public String showBeratung(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("beratung",model);
        model=setSlider(model);
        System.out.println("showBeratung sucess");
        return "Beratung";
    }
    @GetMapping("/HuST")
    public String showHuST(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("hust",model);
        model=setSlider(model);
        System.out.println("showHuST sucess");
        return "HuST";
    }
    @GetMapping("/Engineering")
    public String showEngineering(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("engineering",model);
        model=setSlider(model);
        System.out.println("showEngineering sucess");
        return "Engineering";
    }
    @GetMapping("/Programierung")
    public String showProgramierung(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("programierung",model);
        model=setSlider(model);
        System.out.println("showProgramierung sucess");
        return "Programierung";
    }
    @GetMapping("/Akustik")
    public String showAkustik(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("akustik",model);
        model=setSlider(model);
        System.out.println("showAkustik sucess");
        return "Akustik";
    }
    @GetMapping("/SHuK")
    public String showSHuK(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("shuk",model);
        model=setSlider(model);
        System.out.println("showSHuK sucess");
        return "SHuK";
    }
    @GetMapping("/Kontakt")
    public String showKontakt(Model model) {
        model=getAttribut("index",model);
        model=setSlider(model);
        System.out.println("showKontakt sucess");
        return "Kontakt";
    }
    @GetMapping("/impressum")
    public String showImpressum(Model model) {
        model=getAttribut("index",model);
        model=setSlider(model);
        System.out.println("showImpressum sucess");
        return "impressum";
    }
    @GetMapping("/referenzen")
    public String showReferenzen(Model model) {
        model=getAttribut("index",model);
        model=getAttribut("referenzen",model);
        model=setSlider(model);
        System.out.println("showReferenzen sucess");
        return "referenzen";
    }
    @GetMapping("/test")
    public String showTest(Model model) {
        System.out.println("showTest sucess");
        return "test";
    }
    @PostMapping("/submit-form")
    public String submitContactForm(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        try {
            customerService.saveCustomer(customer);
            redirectAttributes.addFlashAttribute("successMessage", "Your form has been successfully submitted!");
            emailSender.sendFeedback("Neue Kontakt Anfrage",buildEmailKontakt(customer.getAnrede(),
                    customer.getLastName(),
                    customer.getFirstName(),
                    customer.getEmail(),
                    customer.getCompany(),
                    customer.getPhoneNumber(),
                    customer.getInterest(),
                    customer.getMessage()
            ));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while submitting the form. Please try again later.");
        }
        return "redirect:/";
    }
    @PostMapping("/feedback-form")
    public String submitFeedbackForm(@ModelAttribute Feedback feedback, RedirectAttributes redirectAttributes) {
        try {
            feedbackService.saveFeedback(feedback);
            redirectAttributes.addFlashAttribute("successMessage", "Your form has been successfully submitted!");
            emailSender.sendFeedback(feedback.getSubject(),buildEmailFeedback(feedback.getSubject(), feedback.getMessage()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while submitting the form. Please try again later."+e.getMessage());
        }
        return "redirect:/";
    }
    private String buildEmailFeedback(String subject, String feedback)
    {
        String htmlTemplate = "";
        try {
            InputStream inputStream = getClass().getResourceAsStream("/templates/emailFeedback.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
            {
                htmlTemplate += line;
            }
            reader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        htmlTemplate = htmlTemplate.replace("{{subject}}", subject);
        htmlTemplate = htmlTemplate.replace("{{feedback}}", feedback);
        return htmlTemplate;
    }
    private String buildEmailKontakt(String anrede,
                                     String Name,
                                     String Vorname,
                                     String Email,
                                     String Firma,
                                     String telefon,
                                     String interesse,
                                     String frage
    )
    {
        String htmlTemplate = "";
        try {
            InputStream inputStream = getClass().getResourceAsStream("/templates/emailKontakt.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
            {
                htmlTemplate += line;
            }
            reader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        htmlTemplate = htmlTemplate.replace("{{anrede}}", anrede);
        htmlTemplate = htmlTemplate.replace("{{Name}}", Name);
        htmlTemplate = htmlTemplate.replace("{{Vorname}}", Vorname);
        htmlTemplate = htmlTemplate.replace("{{Email}}", Email);
        htmlTemplate = htmlTemplate.replace("{{Firma}}", Firma);
        htmlTemplate = htmlTemplate.replace("{{telefon}}", telefon);
        htmlTemplate = htmlTemplate.replace("{{interesse}}", interesse);
        htmlTemplate = htmlTemplate.replace("{{frage}}", frage);
        return htmlTemplate;
    }
    public Model getAttribut(String s, Model model){
        String base64Image ;
        String imageFormat ;
        switch(s) {
            case "index":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getLogo() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getLogo());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getLogo()) ? "jpeg" : "png";
                    model.addAttribute("logoImage", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("logoImage", null); // Oder eine Standardabbildung
                break;

            case "welcome":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getWelcome() != null)
                    model.addAttribute("welcome", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bilderController.getBilder().getWelcome()));
                else
                    model.addAttribute("welcome", null); // Oder eine Standardabbildung
                break;

            case "about":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getAboutus() != null) {
                    base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getAboutus());
                    imageFormat = bilderController.isJpeg(bilderController.getBilder().getAboutus()) ? "jpeg" : "png";
                    model.addAttribute("about", "data:image/" + imageFormat + ";base64," + base64Image);
                }
                else
                    model.addAttribute("about", null); // Oder eine Standardabbildung

                break;

            case "akustik":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getAkustik() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getAkustik());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getAkustik()) ? "jpeg" : "png";
                    model.addAttribute("akustik", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("akustik", null); // Oder eine Standardabbildung
                break;

            case "auditierung":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getAuditierung() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getAuditierung());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getAuditierung()) ? "jpeg" : "png";
                    model.addAttribute("auditierung", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("auditierung", null); // Oder eine Standardabbildung
                break;

            case "beratung":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getBeratung() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getBeratung());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getBeratung()) ? "jpeg" : "png";
                    model.addAttribute("beratung", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("beratung", null); // Oder eine Standardabbildung
                break;

            case "qus":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getQus() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getQus());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getQus()) ? "jpeg" : "png";
                    model.addAttribute("qus", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("qus", null); // Oder eine Standardabbildung
                break;

            case "shuk":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getShuk() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getShuk());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getShuk()) ? "jpeg" : "png";
                    model.addAttribute("shuk", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("shuk", null); // Oder eine Standardabbildung
                break;

            case "referenzen":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getReferenzen() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getReferenzen());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getReferenzen()) ? "jpeg" : "png";
                    model.addAttribute("referenzen", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("referenzen", null); // Oder eine Standardabbildung
                break;

            case "projecte":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getProjecte() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getProjecte());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getProjecte()) ? "jpeg" : "png";
                    model.addAttribute("projecte", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("projecte", null); // Oder eine Standardabbildung
                break;

            case "programierung":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getProgramierung() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getProgramierung());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getProgramierung()) ? "jpeg" : "png";
                    model.addAttribute("programierung", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("programierung", null); // Oder eine Standardabbildung
                break;

            case "engineering":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getEngineering() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getEngineering());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getEngineering()) ? "jpeg" : "png";
                    model.addAttribute("engineering", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("engineering", null); // Oder eine Standardabbildung
                break;
            case "hust":
                model.addAttribute("mrEntity", bilderController.getBilder());
                if (bilderController.getBilder().getHust() != null) {
                     base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getHust());
                     imageFormat = bilderController.isJpeg(bilderController.getBilder().getHust()) ? "jpeg" : "png";
                    model.addAttribute("hust", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("hust", null); // Oder eine Standardabbildung
                break;
            case "slide1":
                model.addAttribute("mrEntity", bilderController.getBilder());
                System.out.println("slide1"+ Arrays.toString(bilderController.getBilder().getSlide1()));
                if (bilderController.getBilder().getSlide1() != null) {
                    base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getSlide1());
                    imageFormat = bilderController.isJpeg(bilderController.getBilder().getSlide1()) ? "jpeg" : "png";
                    model.addAttribute("slide1", "data:image/" + imageFormat + ";base64," + base64Image);
                }else
                    model.addAttribute("slide1", null); // Oder eine Standardabbildung
                break;
            case "slide2":
                model.addAttribute("mrEntity", bilderController.getBilder());
                System.out.println("slide2"+ Arrays.toString(bilderController.getBilder().getSlide2()));
                if (bilderController.getBilder().getSlide2() != null) {
                    base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getSlide2());
                    imageFormat = bilderController.isJpeg(bilderController.getBilder().getSlide2()) ? "jpeg" : "png";
                    model.addAttribute("slide2", "data:image/" + imageFormat + ";base64," + base64Image);
                }
                else
                    model.addAttribute("slide2", null); // Oder eine Standardabbildung
                break;
            case "slide3":
                model.addAttribute("mrEntity", bilderController.getBilder());
                System.out.println("slide3"+ Arrays.toString(bilderController.getBilder().getSlide3()));
                if (bilderController.getBilder().getSlide3() != null) {
                    base64Image = Base64.getEncoder().encodeToString(bilderController.getBilder().getSlide3());
                    imageFormat = bilderController.isJpeg(bilderController.getBilder().getSlide3()) ? "jpeg" : "png";
                    model.addAttribute("slide3", "data:image/" + imageFormat + ";base64," + base64Image);
                }
                else
                    model.addAttribute("slide3", null); // Oder eine Standardabbildung
                break;

        }
        return model;
    }
    public Model setSlider( Model model){
        model= getAttribut("slide1",model);
        model= getAttribut("slide2",model);
        model= getAttribut("slide3",model);
        return model;
    }
}
