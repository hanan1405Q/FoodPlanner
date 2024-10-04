//package com.example.foodplanner.model;
//
//import androidx.annotation.NonNull;
//import androidx.room.Embedded;
//import androidx.room.Entity;
//
//import java.io.Serializable;
//
//
//@Entity(tableName = "planned_meals_table",primaryKeys = {"plannedMealId,date"})
//public class PlannedMeal implements Serializable{
//
//
//    @Embedded
//    private Meal meal;
//    @NonNull
//    private String date;
//    @NonNull
//    private String plannedMealId;
//
//    public PlannedMeal(Meal meal, String date) {
//        this.meal = meal;
//        this.date = date;
//        plannedMealId=meal.getId();
//    }
//
//    public Meal getMeal() {
//        return meal;
//    }
//
//    public void setMeal(Meal meal) {
//        this.meal = meal;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//    public String getPlannedMealId() {
//        return plannedMealId;
//    }
//
//
//    public void setPlannedMealId(String plannedMealId) {
//        this.plannedMealId = plannedMealId;
//    }
//}
package com.example.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "planned_meals_table", primaryKeys = {"plannedMealId", "date"})
public class PlannedMeal implements Serializable {

    @Embedded
    private Meal meal;

    @NonNull
    private String date;

    @NonNull
    private String plannedMealId;

   public PlannedMeal()
   {

   }
    public PlannedMeal(Meal meal, @NonNull String date) {
        this.meal = meal;
        this.date = date;
        this.plannedMealId = meal.getId();  // Assuming meal.getId() returns a unique identifier for the meal.
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @NonNull
    public String getPlannedMealId() {
        return plannedMealId;
    }

    public void setPlannedMealId(@NonNull String plannedMealId) {  // Fixed method name
        this.plannedMealId = plannedMealId;
    }
}
