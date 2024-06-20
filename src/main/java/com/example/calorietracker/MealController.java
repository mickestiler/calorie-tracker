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
    private MealRepository mealRepository;

    @GetMapping("/create")
    public String showCreateMealForm(Model model) {
        model.addAttribute("meal", new Meal()); // Adds a new Meal to the model
        return "meal"; // Displays the meal.html form
    }

    @PostMapping("/create")
    public String processCreateMeal(Meal meal, BindingResult result) {
        // error handling in the form submission here
        if (result.hasErrors()) {
            return "meal";
        }

        mealRepository.save(meal);
        return "redirect:/profile"; // Redirect after successful addition
    }
    @PostMapping("/add-to-diet")
    public String addMealToDiet(Long mealId, String mealType, Model model) {
        Meal meal = mealRepository.findById(mealId).orElse(null);
        if (meal != null) {
            // Logic to add meal nutrients to user's daily intake
            // This could involve fetching the user's current profile and updating it
        } else {
            // Handle error: meal not found
            model.addAttribute("error", "Meal not found");
            return "meal";  // Redirect back to meal management page with error
        }
        return "redirect:/profile";  // Redirect to profile page where changes can be seen
    }


}
