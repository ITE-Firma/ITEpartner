package com.example.demo.kunde.Controller;
import com.example.demo.kunde.email.EmailSender;
import com.example.demo.kunde.email.EmailService;
import com.example.demo.kunde.model.Customer;
import com.example.demo.kunde.model.Feedback;
import com.example.demo.kunde.regestrieren.token.RegistrationService;
import com.example.demo.kunde.service.CustomerService;
import com.example.demo.kunde.service.FeedbackService;
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
import java.util.Base64;


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
        model=getAttribut("aboutus",model);
        model=setSlider(model);
        System.out.println("showForm sucess ");
        return "index";
    }
    @GetMapping("/projects")
    public String showProjects(Model model) {
        model=getAttribut("index",model);
        model=setSlider(model);
        System.out.println("showProjects sucess ");
        return "Projekte";
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
