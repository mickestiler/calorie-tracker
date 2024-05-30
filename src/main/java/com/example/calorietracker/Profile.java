package com.example.calorietracker;

import jakarta.persistence.*;

@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int dailyCalorieGoal;

    private int dailyProteinGoal;
    private int dailyFatGoal;
    private int dailyCarbohydrateGoal;

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
}
