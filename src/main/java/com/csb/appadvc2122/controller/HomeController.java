package com.csb.appadvc2122.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")

public class HomeController {

    @GetMapping
    private String home(Model model) {
        return "home/index";
    }

}
