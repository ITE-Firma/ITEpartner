package com.example.demo.kunde.Controller;
import com.example.demo.kunde.email.EmailSender;
import com.example.demo.kunde.email.EmailService;
import com.example.demo.kunde.model.Customer;
import com.example.demo.kunde.model.Feedback;
import com.example.demo.kunde.regestrieren.RegistrationRequest;
import com.example.demo.kunde.regestrieren.token.RegistrationService;
import com.example.demo.kunde.service.CustomerService;
import com.example.demo.kunde.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Controller

@RequestMapping("/api/v1/user/")
public class ContactController {
    private final EmailSender emailSender;
    private final EmailService emailService;
    private final RegistrationService registrationService;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private FeedbackService feedbackService;

    public ContactController(RegistrationService registrationService, SpringTemplateEngine templateEngine, EmailSender emailSender,EmailService emailService) {
        this.registrationService = registrationService;
        this.templateEngine = templateEngine;
        this.emailSender=emailSender;
        this.emailService=emailService;
    }

    @GetMapping("/index")
    public String showForm(Model model) {
        System.out.println("showForm sucess ");
        return "index";
    }
    @GetMapping("/projects")
    public String showProjects(Model model) {
        System.out.println("showProjects sucess ");
        return "Projekte";
    }
    @GetMapping("/QuS")
    public String showQuS(Model model) {
        System.out.println("showQuS sucess");
        return "QuS";
    }
    @GetMapping("/Auditierung")
    public String showAuditierung(Model model) {
        System.out.println("showAuditierung sucess");
        return "Auditierung";
    }
    @GetMapping("/Beratung")
    public String showBeratung(Model model) {
        System.out.println("showBeratung sucess");
        return "Beratung";
    }
    @GetMapping("/HuST")
    public String showHuST(Model model) {
        System.out.println("showHuST sucess");
        return "HuST";
    }
    @GetMapping("/Engineering")
    public String showEngineering(Model model) {
        System.out.println("showEngineering sucess");
        return "Engineering";
    }
    @GetMapping("/Programierung")
    public String showProgramierung(Model model) {
        System.out.println("showProgramierung sucess");
        return "Programierung";
    }
    @GetMapping("/Akustik")
    public String showAkustik(Model model) {
        System.out.println("showAkustik sucess");
        return "Akustik";
    }
    @GetMapping("/SHuK")
    public String showSHuK(Model model) {
        System.out.println("showSHuK sucess");
        return "SHuK";
    }
    @GetMapping("/Kontakt")
    public String showKontakt(Model model) {
        System.out.println("showKontakt sucess");
        return "Kontakt";
    }
    @GetMapping("/impressum")
    public String showImpressum(Model model) {
        System.out.println("showImpressum sucess");
        return "impressum";
    }
    @GetMapping("/referenzen")
    public String showReferenzen(Model model) {
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
}