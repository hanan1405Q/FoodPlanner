package com.example.foodplanner.calender.view;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;

public interface OnClickCalenderListener {

    public  void onDeleteClick(PlannedMeal meal);
    public  void onCardClick(PlannedMeal meal);

}
