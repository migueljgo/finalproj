package com.csb.appadvc2122.controller;

import com.csb.appadvc2122.model.IModel;
import com.csb.appadvc2122.services.impl.IService;
import com.csb.appadvc2122.services.impl.IngredientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class IController {

    @Autowired
    private IService service;

    @GetMapping("/ingredients")
    public String showIngredientsList(Model model){
        List<IModel> listIngredients = service.listAll();
        model.addAttribute("listIngredients", listIngredients);

        return "ingredients-page";
    }

    @GetMapping("/ingredients/new")
    public String showNewForm(Model model) {
        model.addAttribute("ingredient", new IModel());
        model.addAttribute("pageTitle", "Add New Ingredient");
        return "ingredient-form";
    }

    @PostMapping("/ingredients/save")
    public String saveIngredient(IModel ingredient, RedirectAttributes ra){
        service.save(ingredient);
        ra.addFlashAttribute("message", "The ingredient has been saved successfully");
        return "redirect:/ingredients";
    }

    @GetMapping("/ingredients/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            IModel ingredient = service.get(id);
            model.addAttribute("ingredient", ingredient);
            model.addAttribute("pageTitle", "Edit Ingredient (ID: " + id + ")");

            return "ingredient-form";
        } catch (IngredientNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/ingredients";
        }
    }

    @GetMapping("/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The ingredient ID " + id + " has been deleted.");
        } catch (IngredientNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/ingredients";
    }
}
