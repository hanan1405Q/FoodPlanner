package com.example.foodplanner.network;

import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Meals;

import java.util.ArrayList;
import java.util.List;

public interface NetworkCallback {

        public void onSuccessRandomResult(Meal meal);
        public void onFailureRandomResult(String errMSG);

        public void onSuccessSearchByNameResult(List<Meal> meals);
        public void onFailureSearchByNameResult(String errMSG);
//
//        public void onSuccessSearchByID(Meal meal);
//        public void onFailureSearchByID(String errMSG);
//
//        public void onSuccessSearchByLetter(Meals meals);
//        public  void  onFailureSearchByLetter(String errMSG);
//
        public  void onSuccessListMealCategories(CategoryResponse categories);
        public  void onFailureListMealCategories(String errMSG);

        public void onSuccessFilterByCategory(List<Meal> meals);
        public void onFailureFilterByCategory(String errMSG);

        public void onSuccessFilterByIngredient(List<Meal> meals);
        public void onFailureFilterByIngredient(String errMSG);


        public void onSuccessFilterByArea(List<Meal> meals);
        public void onFailureFilterByArea(String errMSG);

}
