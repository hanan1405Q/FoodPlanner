package com.example.foodplanner.model;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class Repository {
    private MealRemoteDataSource mealRemoteDataSource;
    private MealLocalDataSource mealLocalDataSource;
    private static Repository repository= null;

    private Repository(MealRemoteDataSource mealRemoteDataSource,MealLocalDataSource mealLocalDataSource) {
       this.mealLocalDataSource=mealLocalDataSource;
       this.mealRemoteDataSource=mealRemoteDataSource;
    }
    public static Repository getInstance (MealRemoteDataSource mealRemoteDataSource ,MealLocalDataSource mealLocalDataSource)
    {
        if (repository == null )
        {
            repository = new Repository(mealRemoteDataSource,mealLocalDataSource);
        }
        return repository;
    }
    public void getRandomMeal (NetworkCallback networkCallBack)

    {
        Log.i("RepoStatus","I am in Repo For Making Network Call");
        mealRemoteDataSource.makeRandomMealNetworkCallback(networkCallBack);
    }

    public void getCategoryMeal(NetworkCallback networkCallBack)
    {
        mealRemoteDataSource.makeListMealCategoriesNetworkCallback(networkCallBack);
    }

    public void getMealByName(NetworkCallback networkCallback,String mealName)
    {
        mealRemoteDataSource.makeSearchByNameNetworkCallback(networkCallback,mealName);
    }

    public void filterByArea(NetworkCallback networkCallback,String mealName)
    {
        mealRemoteDataSource.makeFilterByAreaNetworkCallback(networkCallback,mealName);
    }

    public void filterByCategory(NetworkCallback networkCallback,String mealName)
    {
        mealRemoteDataSource.makeFilterByCategoryNetworkCallback(networkCallback,mealName);
    }

    public void filterByIngredient(NetworkCallback networkCallback,String mealName)
    {
        mealRemoteDataSource.makeFilterByIngredientNetworkCallback(networkCallback,mealName);
    }

    /*****  LocalDataSource (RooM)     ********/
    public void insertMeal(Meal meal)
    {
        mealLocalDataSource.insertMeal(meal);
    }
    public  void deleteMeal(Meal meal)
    {
       mealLocalDataSource.removeMeal(meal);
    }
    public LiveData<List<Meal>> getStoredMeals()
    {
       return  mealLocalDataSource.getAllMeals();
    }

    public  void insertPlannedMeal(PlannedMeal meal){mealLocalDataSource.insertPlannedMeal(meal);}
    public  void deletePlannedMeal(PlannedMeal meal){mealLocalDataSource.removePlannedMeal(meal);}
    public LiveData<List<PlannedMeal>> getStoredPlannedMeals()
    {

        return  mealLocalDataSource.getAllPlannedMeals();
    }

    public LiveData<List<PlannedMeal>> getPlannedMealsByDate(String date)
    {
        Log.i("REPO", "getStoredPlannedMealsBYDATE: ");
        return  mealLocalDataSource.getMealsByDate(date);
    }

    /****************** HandMade DataSource *******************/

    public List<Country> getArea() {
        CountryList countries = CountryList.getInstance(); // Ensure the class name is correct
        List<Country> c = countries.getAllAreas(); // Retrieve the list of countries
        return c;
    }


}
