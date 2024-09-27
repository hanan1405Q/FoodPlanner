package com.example.foodplanner.home.view;

import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface HomeView {
     public void showRandomMeal(Meal meal);
     public void showCategoryMeal(List<Category> categories);
     void  showError (String str);

}
