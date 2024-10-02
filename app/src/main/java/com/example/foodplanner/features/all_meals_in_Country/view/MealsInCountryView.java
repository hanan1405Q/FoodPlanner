package com.example.foodplanner.features.all_meals_in_Country.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealsInCountryView {
    public void showMeals(List<Meal> meals);
    public void  showError(String errMessage);
    public void showDetailedMeal(List<Meal> meals);
}
