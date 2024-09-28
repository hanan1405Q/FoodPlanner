package com.example.foodplanner.features.detailed_meal.view;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Meals;

import java.util.List;

public interface DetailedMealView {
    public void showMeal(List<Meal> meals);
    public void  showError (String str);
    public void playYouTubeVideo(String videoId);
    public void showInvalidUrl();

}
