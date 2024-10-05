package com.example.foodplanner.home.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.home.view.HomeView;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Country;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements NetworkCallback {

    private Repository repository;
    private HomeView homeView;

    public HomePresenter(HomeView homeView, Repository repository) {
        this.homeView=homeView;
        this.repository = repository;

    }

    public void getRandomMeal ()
    {
        Log.i("MealReq","Request from Repo to get Random Image Meal");
        repository.getRandomMeal(this);
    }
    public void listCategoryMeal()
    {
        Log.i("MealReq","Request from Repo to get category");
        repository.getCategoryMeal(this);
    }

    @Override
    public void onSuccessRandomResult(Meal meal) {
        Log.i("MealRes","Success to Fetch Image");
            homeView.showRandomMeal(meal);
    }

    public List<Country> listArea()
    {
        return repository.getArea();
    }

    @Override
    public void onFailureRandomResult(String errMSG) {

        Log.i("MealRes","Failed to Fetch Image");
       // homeView.showError("Failed to Fetch Image");
    }

    @Override
    public void onSuccessListMealCategories(CategoryResponse categories) {

        Log.i("CatRes","Success to Fetch Category List");
        homeView.showCategoryMeal(categories.getCategories());
    }

    @Override
    public void onFailureListMealCategories(String errMSG) {
        Log.i("CatRes","Failed to Fetch Category list");
        //homeView.showError("Failed to Fetch Category");
    }
/*********************************************************************************/
    @Override
    public void onSuccessFilterByCategory(List<Meal> meals) {

    }

    @Override
    public void onFailureFilterByCategory(String errMSG) {

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

    @Override
    public void onSuccessSearchByNameResult(List<Meal> meals) {

    }

    @Override
    public void onFailureSearchByNameResult(String errMSG) {

    }

//    public void addToFav(Product product)
//    {
//        repository.insertProduct(product);
//    }
//
//    @Override
//    public void onSuccessResult(ArrayList<Product> products) {
//        allProductsView.setProducts(products);
//    }
//
//    @Override
//    public void onFailureResult(String errMSG) {
//        allProductsView.showError(errMSG);
//    }

}
