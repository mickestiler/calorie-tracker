package com.example.calorietracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
    // Additional methods if required
}
