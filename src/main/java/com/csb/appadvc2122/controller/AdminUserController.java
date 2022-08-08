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
import com.csb.appadvc2122.repository.UserRepository;
import com.csb.appadvc2122.services.UserService;

@Controller
@RequestMapping("admin/user")

public class AdminUserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired	
	private UserService userService;
	
    @GetMapping
    private String index(Model model) {
    	model.addAttribute("userList", userRepository.findAll());
    	
        return "admin/user/index";
    }
    
    @GetMapping("/create")
    private String create(Model model) {
    	model.addAttribute("user", new UserDTO());
    	
    	return "admin/user/create";
    }
    
    @PostMapping
    private String store(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDTO);
            
            return "admin/user/create";
        }
    	
    	userDTO.setRole("ROLE_USER");
        userService.add(userDTO);

        return "redirect:/admin/user";
    }
    
    @GetMapping("/{id}/edit")
    private String get(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.get(id));
        
        return "admin/user/edit";
    }
    
    @PutMapping
    private String update(UserDTO user, Model model) {
    	userService.update(user);
    	
        return index(model);
    }
    
    @DeleteMapping
    private String delete(UserDTO userDTO, Model model) {
    	userService.delete(userDTO.getId());
        return index(model);
    }
}
