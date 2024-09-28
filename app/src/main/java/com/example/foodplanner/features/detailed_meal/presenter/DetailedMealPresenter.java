package com.example.foodplanner.features.detailed_meal.presenter;

import android.util.Log;

import com.example.foodplanner.features.detailed_meal.view.DetailedMealView;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class DetailedMealPresenter implements NetworkCallback {
    Repository repo;
    DetailedMealView view;

    public DetailedMealPresenter(DetailedMealView view, Repository repo)
    {
        this.repo=repo;
        this.view=view;

    }



    public  void getMealByName(String mealName)
    {
        Log.i("GetMealByName","Request from Repo to get a Specific Meal By Name");
        repo.getMealByName(this,mealName);
    }


    @Override
    public void onSuccessSearchByNameResult(List<Meal> meals) {
        Log.i("SearchRes","Success to Get Specific Meal By Name");
        view.showMeal(meals);
    }

    @Override
    public void onFailureSearchByNameResult(String errMSG) {
             view.showError(errMSG);
    }

    public void  checkYouTubeURL(String url)
    {
        if (url != null && !url.isEmpty()) {
            String[] split = url.split("=");
            if (split.length > 1) {
                String videoId = split[1];
                view.playYouTubeVideo(videoId);
            } else {
                view.showInvalidUrl();
            }
        } else {
            view.showInvalidUrl();
        }

    }


/************************************************************************************/
    @Override
    public void onSuccessRandomResult(Meal meal) {

    }

    @Override
    public void onFailureRandomResult(String errMSG) {

    }

    @Override
    public void onSuccessListMealCategories(CategoryResponse categories) {

    }

    @Override
    public void onFailureListMealCategories(String errMSG) {

    }
}
