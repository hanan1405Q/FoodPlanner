package com.example.foodplanner.search.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.NetworkCallback;
import com.example.foodplanner.search.view.SearchViewInterface;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter implements NetworkCallback {
    Repository repo ;
    SearchViewInterface view;
    Boolean flageDetailedMealResponse=false;
    public SearchPresenter(SearchViewInterface view, Repository repo)
    {
        this.view=view;
        this.repo=repo;
    }

    /*****Mapping to the suitable API call*****/
    public void getMeals(int searchType,String searchString)
    {
        if (searchString.isEmpty()) {
            view.showMeals(new ArrayList<>()); // Clear the list if the search string is empty
            return; // Exit the method to prevent further processing
        }
      if(searchType==R.id.radioMealName){
            repo.getMealByName(this,searchString);
      }

      else if (searchType==R.id.radioCategory&&!searchString.isEmpty()) {
          repo.filterByCategory(this,searchString);
      }

      else if (searchType==R.id.radioIngredient&&!searchString.isEmpty()) {
          repo.filterByIngredient(this,searchString);

      }
      else if (searchType==R.id.radioCountry&&!searchString.isEmpty()) {
         repo.filterByArea(this,searchString);
      }

    }

    public  void getMealByName(String mealName)
        {
            flageDetailedMealResponse=true;
           Log.i("GetMealByName","Request from Repo to get a Detailed Meal Response");
           repo.getMealByName(this,mealName);
    }
    @Override
    public void onSuccessFilterByCategory(List<Meal> meals) {

        view.showMeals(meals);
    }

    @Override
    public void onFailureFilterByCategory(String errMSG) {

    }

    @Override
    public void onSuccessFilterByIngredient(List<Meal> meals) {
        view.showMeals(meals);
    }

    @Override
    public void onFailureFilterByIngredient(String errMSG) {

    }

    @Override
    public void onSuccessFilterByArea(List<Meal> meals) {

         view.showMeals(meals);
    }

    @Override
    public void onFailureFilterByArea(String errMSG) {

    }

    @Override
    public void onSuccessSearchByNameResult(List<Meal> meals) {
        if(flageDetailedMealResponse==true)
        {
            view.showDetailedMeal(meals);
        }
        else
        {
            view.showMeals(meals);
            flageDetailedMealResponse=false;
        }


    }

    @Override
    public void onFailureSearchByNameResult(String errMSG) {

    }
/*******************************************************************************************/
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
