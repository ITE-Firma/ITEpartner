package com.example.demo.kunde.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/api/v1/user/")
    public String home() {
        return "index";
    }

}