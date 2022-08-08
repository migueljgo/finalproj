package com.csb.appadvc2122.controller;

import com.csb.appadvc2122.dto.UserDTO;
import com.csb.appadvc2122.services.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("forgot-password")
public class ForgotPassword {

	@Autowired
	UserService userService;
  
    @GetMapping
    private String index(Model model) {
        model.addAttribute("user", new UserDTO());
        return "forgot-password/index";
    }
    
    @PostMapping
    private String update(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {
    	
    	if (! user.getPassword().equals(user.getForgotPassword()) || user.getPassword().isEmpty())
    	{
    		bindingResult.addError(new ObjectError("password", "password and confirm password must be equal")); 
    		
    		 return "forgot-password/index";
    	}
    	
    	userService.updatePassword(user);
    	
    	return "login/index";
    }
 
}
