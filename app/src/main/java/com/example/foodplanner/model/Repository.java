package com.example.foodplanner.model;

import android.util.Log;

import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.example.foodplanner.network.NetworkCallback;

public class Repository {
    private MealRemoteDataSource mealRemoteDataSource;
    //private MealLocalDataSource mealLocalDataSource;
    private static Repository repository= null;

    private Repository(MealRemoteDataSource mealRemoteDataSource) {
       //this.mealLocalDataSource=mealLocalDataSource;
       this.mealRemoteDataSource=mealRemoteDataSource;
    }
    public static Repository getInstance (MealRemoteDataSource mealRemoteDataSource /*,MealLocalDataSource mealLocalDataSource*/)
    {
        if (repository == null )
        {
            repository = new Repository(mealRemoteDataSource);
                    //mealLocalDataSource
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



//    public void insertProduct (Product products)
//    {
//        productsLocalDataSource.insert(products);
//    }
//    public void deleteProduct (Product products)
//    {
//        productsLocalDataSource.delete(products);
//    }
//    public LiveData<List<Product>> getStoredProducts (){return productsLocalDataSource.getStoredProducts();}
}
