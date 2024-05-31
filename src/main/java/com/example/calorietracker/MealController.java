package com.example.calorietracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/meals")
public class MealController {

    @Autowired
    private MealRepository mealRepository; // Assume you have a Meal repository

    @GetMapping("/meals/add")
    public String showAddMealForm(Model model) {
        model.addAttribute("meal", new Meal()); // Adds a new Meal to the model
        return "meal"; // Displays the meal.html form
    }

    @PostMapping("/meals/add")
    public String processAddMeal(Meal meal, BindingResult result) {
        if (result.hasErrors()) {
            return "meal";
        }
        mealRepository.save(meal);
        return "redirect:/profile"; // Redirect after successful addition
    }
}
