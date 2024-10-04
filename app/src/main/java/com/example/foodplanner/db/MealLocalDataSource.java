package com.example.foodplanner.db;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;

import java.util.List;


public class MealLocalDataSource  {
    private MealDAO mealDAO ;
    private PlannedMealDAO plannedMealDAO;
    private static MealLocalDataSource mealLocalDataSource=null ;
    private LiveData<List<Meal>> storedMeals;
    private LiveData<List<PlannedMeal>> storedPlannedMeals;

    private MealLocalDataSource(Context context) {
        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO= appDataBase.mealDAO();
        storedMeals=mealDAO.getAllMeal();

        plannedMealDAO=appDataBase.plannedMealDAO();
        storedPlannedMeals=plannedMealDAO.getAllPlannedMeal();
    }
    public static MealLocalDataSource getInstance(Context context)
    {
        if (mealLocalDataSource== null )
        {
            mealLocalDataSource = new MealLocalDataSource(context);
        }
        return mealLocalDataSource;
    }

    public void insertPlannedMeal(PlannedMeal meal) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                plannedMealDAO.insertPlannedMeal(meal);
            }
        }.start();
    }

    public void removePlannedMeal(PlannedMeal meal) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                plannedMealDAO.deletePlannedMeal(meal);
            }
        }.start();
    }

    public LiveData<List<PlannedMeal>> getAllPlannedMeals() {
        return storedPlannedMeals;
    }


    public LiveData<List<PlannedMeal>> getMealsByDate(String str) {
         return plannedMealDAO.getMealsByDate(str);
    }


    public void insertMeal(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mealDAO.insertMeal(meal);
            }
        }.start();
    }

    public void removeMeal(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mealDAO.deleteMeal(meal);
            }
        }.start();
    }

    public LiveData<List<Meal>> getAllMeals() {
        return storedMeals;
    }


}
