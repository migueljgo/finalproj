package com.csb.appadvc2122.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csb.appadvc2122.dto.UserDTO;
import com.csb.appadvc2122.repository.LogRepository;
import com.csb.appadvc2122.repository.UserRepository;
import com.csb.appadvc2122.services.UserService;

@Controller
@RequestMapping("admin/logs")

public class AdminLogController {

	@Autowired
	LogRepository logRepository;
	
    @GetMapping
    private String index(Model model) {
    	model.addAttribute("logList", logRepository.findAll());
    	
        return "admin/log/index";
    }
}
