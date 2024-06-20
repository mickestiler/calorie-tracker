package com.example.calorietracker;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int dailyCalorieGoal;

    private int caloriesToday;

    private int dailyProteinGoal;
    private int dailyFatGoal;
    private int dailyCarbohydrateGoal;

    private int breakfastCalories;

    private int lunchCalories;

    private int dinnerCalories;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Meal> meals;  // List to hold multiple meals

    private double weight;

    private double goalWeight;

    private int height;

    // Associations
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }

    public void setDailyCalorieGoal(int dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public int getDailyProteinGoal() {
        return dailyProteinGoal;
    }

    public void setDailyProteinGoal(int dailyProteinGoal) {
        this.dailyProteinGoal = dailyProteinGoal;
    }

    public int getDailyFatGoal() {
        return dailyFatGoal;
    }

    public void setDailyFatGoal(int dailyFatGoal) {
        this.dailyFatGoal = dailyFatGoal;
    }

    public int getDailyCarbohydrateGoal() {
        return dailyCarbohydrateGoal;
    }

    public void setDailyCarbohydrateGoal(int dailyCarbohydrateGoal) {
        this.dailyCarbohydrateGoal = dailyCarbohydrateGoal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCaloriesToday() {
        return caloriesToday;
    }

    public void setCaloriesToday(int caloriesToday) {
        this.caloriesToday = caloriesToday;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
