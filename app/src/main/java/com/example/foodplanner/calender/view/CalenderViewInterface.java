package com.example.foodplanner.calender.view;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;

import java.util.List;

public interface CalenderViewInterface {
    void  showMeals (List<PlannedMeal> meals);
    void  showError (String str);
}
