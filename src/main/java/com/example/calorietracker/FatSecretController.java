package com.example.calorietracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class FatSecretController {

    @Autowired
    private FatSecretService fatSecretService;

    @GetMapping("/searchMeals")
    public String searchMeals(@RequestParam String query, Model model) {
        try {
            List<Meal> meals = fatSecretService.searchMeals(query);
            model.addAttribute("meals", meals);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to fetch meal data.");
        }
        return "profile";
    }
}

