package com.csb.appadvc2122.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csb.appadvc2122.model.Recipe;
import com.csb.appadvc2122.repository.RecipeRepository;


@Controller
@RequestMapping("recipe")
public class RecipeController {
	
	@Autowired
	RecipeRepository recipeRepository;
	
    @GetMapping
    private String index(Model model) {
    	model.addAttribute("recipeList", recipeRepository.findAll());
    	
        return "recipe/index";
    } 
    
    @GetMapping("/create")
    private String create(Model model) {   
    	model.addAttribute("recipe", new Recipe());
    	
        return "recipe/create";
    } 
    
    @PostMapping
    private String store(Model model, Recipe recipe) {   
    	recipeRepository.save(recipe);
    	
        return index(model);
    } 
    
    @GetMapping("/edit/{id}")
    private String show(@PathVariable("id") Long id, Model model) {   
    	model.addAttribute("recipe", recipeRepository.findById(id));
    	
        return "recipe/edit";
    } 
    
    @PostMapping("/update")
    private String update(Recipe recipe, Model model) {   
    	recipeRepository.save(recipe);
    	
    	return index(model);
    } 
    
    @GetMapping("/delete/{id}")
    private String delete(@PathVariable("id") Long id, Model model) {   
    	recipeRepository.deleteById(id);
    	
        return index(model);
    } 
}
