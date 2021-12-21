package com.example.demo.Controllers;

import com.example.demo.Models.driver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    private String All(Model model){
        return "/login/login";
    }
}
