package com.example.foodplanner.favourite.view;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface FavView {
    void setMeals (List<Meal> meals);
    void  showError (String str);
}
