package com.example.foodplanner.calender.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner.calender.view.CalenderViewInterface;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;
import com.example.foodplanner.model.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalenderPresenter  {
    Repository repo ;
    CalenderViewInterface view;

    public CalenderPresenter(CalenderViewInterface view, Repository repo)
    {
        this.view=view;
        this.repo=repo;
    }
    /*Attaching the observer to love data */
    public void observePlannedMeals(LifecycleOwner lifecycleOwner){
        Observer<List<PlannedMeal>> observer = new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> plannedMeals) {

                view.showMeals(plannedMeals);
            }
        };
        LiveData<List<PlannedMeal>> liveData =repo.getStoredPlannedMeals();
        liveData.observe(lifecycleOwner,observer);
    }
    public void observeMealsByDate(LifecycleOwner lifecycleOwner,String str){
        Observer<List<PlannedMeal>> observer = new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> plannedMeals) {

                view.showMeals(plannedMeals);
            }
        };
        LiveData<List<PlannedMeal>> liveData =repo.getPlannedMealsByDate(str);
        liveData.observe(lifecycleOwner,observer);
    }


    public void deletePlannedMeal (PlannedMeal meal)
    {
        repo.deletePlannedMeal(meal);
    }


//    public List<Meal> extractMeals(List<PlannedMeal> plannedMeals) {
//        List<Meal> meals = new ArrayList<>();
//        for (PlannedMeal plannedMeal : plannedMeals) {
//            Meal meal = plannedMeal.getMeal();
//            meals.add(meal);
//        }
//        return meals;
//    }


}



