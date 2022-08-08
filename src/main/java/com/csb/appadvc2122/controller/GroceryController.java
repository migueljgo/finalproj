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
import com.csb.appadvc2122.model.Grocery;
import com.csb.appadvc2122.repository.GroceryRepository;
import com.csb.appadvc2122.repository.UserRepository;
import com.csb.appadvc2122.services.UserService;

@Controller
@RequestMapping("grocery")

public class GroceryController {

	@Autowired
	GroceryRepository groceryRepository;
	
    @GetMapping
    private String index(Model model) {
    	model.addAttribute("groceryList", groceryRepository.findAll());
    	
        return "grocery/index";
    }
    
    @GetMapping("/create")
    private String create(Model model) {
    	model.addAttribute("grocery", new Grocery());
    	
    	return "grocery/create";
    }
    
    @PostMapping
    private String store(
    		@ModelAttribute("grocery") @Valid Grocery grocery, BindingResult bindingResult, Model model
    ) {
    	groceryRepository.save(grocery);

        return "redirect:/grocery";
    }
    
    @GetMapping("edit/{id}")
    private String get(@PathVariable Long id, Model model) {
        model.addAttribute("grocery", groceryRepository.findById(id).get());
        
        return "grocery/edit";
    }
    
    @PostMapping("/update")
    private String update(Grocery grocery, Model model) {
    	groceryRepository.save(grocery);
    	
        return index(model);
    }
    
    @GetMapping("/delete/{id}")
    private String delete(@PathVariable("id") Long id, Model model) {   
    	groceryRepository.deleteById(id);
        
    	return index(model);
    }
}
