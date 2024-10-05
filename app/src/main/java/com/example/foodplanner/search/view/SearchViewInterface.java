package com.example.foodplanner.search.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface SearchViewInterface {
    public void showMeals(List<Meal> meals);
    public void showDetailedMeal(List<Meal> meals);
    public void ShowNotValidSearch();
}
