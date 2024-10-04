package com.example.foodplanner.features.detailed_meal.presenter;

import android.util.Log;

import com.example.foodplanner.features.detailed_meal.view.DetailedMealView;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.PlannedMeal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class DetailedMealPresenter {
    Repository repo;
    DetailedMealView view;

    public DetailedMealPresenter(DetailedMealView view, Repository repo)
    {
        this.repo=repo;
        this.view=view;

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

    public void addToFav(Meal meal)
    {
        repo.insertMeal(meal);
    }

    public void addToPlane(PlannedMeal meal)
    {
        Log.i("INSERT" ,"addToPlane: Iam leaving to repo to insert "+meal.getMeal().getName());
        repo.insertPlannedMeal(meal);
    }


/************************************************************************************/

}
