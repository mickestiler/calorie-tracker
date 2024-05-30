package com.example.calorietracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/meals")
public class MealController {
    @Autowired
    private MealRepository mealRepository;
    @GetMapping("/add")
    public String showAddMealForm(Model model) {
        model.addAttribute("meal", new Meal());  // Adding an empty Meal object to the model
        return "addMeal";  // Name of the HTML file (without .html extension if it's under /resources/templates)
    }


    @PostMapping("/add")
    public String processAddMealForm(Meal meal) {
        // Save the meal using a service or repository
        mealRepository.save(meal);
        return "redirect:/meals/success";  // Redirect to another page after saving the meal
    }

}
