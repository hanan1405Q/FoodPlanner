package com.example.foodplanner.favourite.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner.favourite.view.FavView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;

import java.util.List;

public class FavPresenter {
    Repository repo ;
    FavView   view;

    public FavPresenter(FavView view,Repository repo)
    {
        this.view=view;
        this.repo=repo;
    }
    public void observeFavMeals(LifecycleOwner lifecycleOwner){
        Observer<List<Meal> >observer = new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                view.setMeals(meals);

            }
        };
        LiveData<List<Meal>> liveData =repo.getStoredMeals();
        liveData.observe(lifecycleOwner,observer);
    }
    public void deleteMeal (Meal meal)
    {
        repo.deleteMeal(meal);
    }



}
