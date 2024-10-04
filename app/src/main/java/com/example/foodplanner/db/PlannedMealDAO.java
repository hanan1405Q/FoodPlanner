package com.example.foodplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;

import java.util.List;

@Dao
public interface PlannedMealDAO {

    @Query("Select * FROM planned_meals_table")
    LiveData<List<PlannedMeal>> getAllPlannedMeal ();

    @Query("Select * FROM planned_meals_table WHERE  date = :specificDate")
    LiveData<List<PlannedMeal>> getMealsByDate(String specificDate);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlannedMeal (PlannedMeal meal);
    @Delete
    void deletePlannedMeal(PlannedMeal meal);
}
