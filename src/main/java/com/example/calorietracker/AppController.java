package com.example.calorietracker;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    private final static int DEFAULT_DAILY_CALORIE_GOAL = 2000;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    /**
     * Registers a new user. Adds a new user attribute to the model.
     * @param model
     * @return signup_form.html
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }


    /**
     * Processes the user registrations.
     * @param user
     * @return register_success.html
     */
    @Transactional
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        Profile profile = new Profile();
        user.setProfile(profile);
        profile.setUser(user); // Set the user for the profile
        profile.setDailyCalorieGoal(DEFAULT_DAILY_CALORIE_GOAL);
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication) {
        String email = authentication.getName();  // Get the username (email) from the authentication object
        User user = userRepo.findByEmail(email);  // Fetch user details from the database
        if (user != null && user.getProfile() != null) {
            model.addAttribute("profile", user.getProfile());  // Ensure the profile is not null
        } else {
            // Handle null cases or redirect to an error page or a page that handles missing profiles
            model.addAttribute("error", "Profile not found or not set up");
            return "error_page";  // Redirect to an error handling page or setup page
        }
        return "profile";
    }


}