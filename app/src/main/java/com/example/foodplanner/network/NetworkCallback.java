package com.example.foodplanner.network;

import com.example.foodplanner.model.Meal;

import java.util.ArrayList;

public interface NetworkCallback {

        public void onSuccessResult(Meal meal);
        public void onFailureResult(String errMSG);
}
