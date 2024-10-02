package com.example.foodplanner.features.all_meals_in_category.presenter;

import com.example.foodplanner.features.all_meals_in_Country.view.MealsInCountryView;
import com.example.foodplanner.features.all_meals_in_category.view.MealsInCategoryView;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class MealsInCategoryPresenter implements NetworkCallback {
    Repository repo ;
    MealsInCategoryView view;


    public MealsInCategoryPresenter(MealsInCategoryView view, Repository repo) {
        this.view = view;
        this.repo = repo;
    }

    public void getMealsInCategoty(String categoryName)
    {
        repo.filterByCategory(this,categoryName);
    }
    public void getMealByName(String mealName)
    {
        repo.getMealByName(this,mealName);
    }



/******************* Network CallBack ************************************/

    @Override
    public void onSuccessFilterByCategory(List<Meal> meals) {
        view.showMeals(meals);
    }

    @Override
    public void onFailureFilterByCategory(String errMSG) {
        view.showError(errMSG);
    }


    @Override
    public void onSuccessSearchByNameResult(List<Meal> meals) {
        view.showDetailedMeal(meals);
    }

    @Override
    public void onFailureSearchByNameResult(String errMSG) {
       view.showError(errMSG);
    }

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


    @Override
    public void onSuccessFilterByIngredient(List<Meal> meals) {

    }

    @Override
    public void onFailureFilterByIngredient(String errMSG) {

    }

    @Override
    public void onSuccessFilterByArea(List<Meal> meals) {

    }

    @Override
    public void onFailureFilterByArea(String errMSG) {

    }
}
