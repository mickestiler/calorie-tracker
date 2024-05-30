package com.example.calorietracker;

import jakarta.persistence.Embeddable;

/**
 * Macronutrient class consisting of the protein, fat, and carbohydrates of a meal.
 */
@Embeddable
public class Macronutrient {

    private int protein;
    private int fat;
    private int carbohydrates;
    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = Math.max(protein, 0);
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = Math.max(protein, 0);
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = Math.max(carbohydrates, 0);
    }

    public int getCalories() {
        return 4 * protein + 4 * fat + 9 * carbohydrates;
    }
}
