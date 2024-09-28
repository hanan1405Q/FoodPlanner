package com.example.foodplanner.model;

public class IngredientData {

    String ingredient;
    String measure;
    public IngredientData(String ingredient, String measure) {
        this.ingredient = ingredient;
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    @Override
    public String toString() {
        return "Ingredient: " + ingredient + ", Measure: " + measure;
    }
}
