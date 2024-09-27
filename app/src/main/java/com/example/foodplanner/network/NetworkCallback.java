package com.example.foodplanner.network;

import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Meals;

import java.util.ArrayList;

public interface NetworkCallback {

        public void onSuccessRandomResult(Meal meal);
        public void onFailureRandomResult(String errMSG);

//        public void onSuccessSearchByNameResult(Meals meals);
//        public void onFailureSearchByNameResult(String errMSG);
//
//        public void onSuccessSearchByID(Meal meal);
//        public void onFailureSearchByID(String errMSG);
//
//        public void onSuccessSearchByLetter(Meals meals);
//        public  void  onFailureSearchByLetter(String errMSG);
//
        public  void onSuccessListMealCategories(CategoryResponse categories);
        public  void onFailureListMealCategories(String errMSG);

}
