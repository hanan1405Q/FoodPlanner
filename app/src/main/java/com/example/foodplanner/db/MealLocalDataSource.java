package com.example.foodplanner.db;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;

import java.util.List;


public class MealLocalDataSource  {
    private MealDAO mealDAO ;
    private static MealLocalDataSource mealLocalDataSource=null ;
    private LiveData<List<Meal>> storedMeals;

    private MealLocalDataSource(Context context) {
        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO= appDataBase.mealDAO();
        storedMeals=mealDAO.getAllMeal();
    }
    public static MealLocalDataSource getInstance(Context context)
    {
        if (mealLocalDataSource== null )
        {
            mealLocalDataSource = new MealLocalDataSource(context);
        }
        return mealLocalDataSource;
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
