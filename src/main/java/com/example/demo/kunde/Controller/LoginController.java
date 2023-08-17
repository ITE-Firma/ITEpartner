package com.example.demo.kunde.Controller;



import com.example.demo.kunde.regestrieren.RegistrationRequest;
import com.example.demo.kunde.regestrieren.token.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final RegistrationService registrationService;
    private final SpringTemplateEngine templateEngine;
    public LoginController(AuthenticationManager authenticationManager,RegistrationService registrationService,SpringTemplateEngine templateEngine) {
        this.authenticationManager = authenticationManager;
        this.registrationService= registrationService;
        this. templateEngine= templateEngine;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login-admin";
    }


    @GetMapping("/api/v1/user/register")
    public String getRegister()
    {
        return "registerAdmin";
    }

    @PostMapping(path = "/api/v1/user/registration")
    public ResponseEntity<String> registerAdmin(@ModelAttribute @RequestBody RegistrationRequest registrationRequest, HttpServletRequest request) {
        String confirmationResult = registrationService.registerAdmin(registrationRequest, request);
        Context context = new Context();
        context.setVariable("confirmationResult", confirmationResult);
        String renderedHtml = templateEngine.process("registerSucsses", context);
        return ResponseEntity.ok().body(renderedHtml);
    }

}