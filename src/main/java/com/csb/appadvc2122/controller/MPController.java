package com.csb.appadvc2122.controller;

import com.csb.appadvc2122.model.MPModel;
import com.csb.appadvc2122.services.impl.MPService;
import com.csb.appadvc2122.services.impl.PlanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MPController {

    @Autowired
    private MPService service;

    @GetMapping("/mealPlan")
    public String showMealPlanList(Model model) {
        List<MPModel> listMealPlan = service.listAll();
        model.addAttribute("listMealPlan", listMealPlan);

        return "mealPlan-page";
    }

    @GetMapping("/mealPlan/new")
    public String showNewForm(Model model) {
        model.addAttribute("mealPlan", new MPModel());
        model.addAttribute("pageTitle", "Add New Plan");
        return "mealPlan-form";
    }

    @PostMapping("/mealPlan/save")
    public String savePlan(MPModel mealPlan, RedirectAttributes ra) {
        service.save(mealPlan);
        ra.addFlashAttribute("message", "The plan has been saved successfully.");

        return "redirect:/mealPlan";
    }

    @GetMapping("/mealPlan/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            MPModel mpModel = service.get(id);
            model.addAttribute("mealPlan", mpModel);
            model.addAttribute("pageTitle", "Edit Plan (ID: )" + id + ")");
            return "mealPlan-form";
        } catch (PlanNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/mealPlan";
        }
    }

    @GetMapping("/mealPlan/delete/{id}")
    public String deletePlan(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
        } catch (PlanNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/mealPlan";
        }
        return "redirect:/mealPlan";
    }
}
