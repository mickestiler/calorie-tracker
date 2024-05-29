package com.example.expensetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Get username (email) from authentication

        User user = userRepository.findByEmail(email);
        if (user != null && user.getProfile() != null) {
            model.addAttribute("profile", user.getProfile());
        } /**else {
            // Handle the case where the user or profile is not found
            return "error";
        } */

        return "profile"; // Name of the Thymeleaf template for the profile page
    }
}

