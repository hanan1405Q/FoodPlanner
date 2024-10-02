package com.example.foodplanner.features.all_meals_in_category.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealsInCategoryView {
    public void showMeals(List<Meal> meals);
    public void  showError(String errMessage);
    public void showDetailedMeal(List<Meal> meals);
}
