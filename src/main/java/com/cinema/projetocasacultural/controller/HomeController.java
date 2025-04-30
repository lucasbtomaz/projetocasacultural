package com.cinema.projetocasacultural.controller;

// Página principal mostrando a casa cultural e suas ações.

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }

}