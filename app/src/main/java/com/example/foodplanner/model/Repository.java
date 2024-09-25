//package com.example.foodplanner.model;
//
//import androidx.lifecycle.LiveData;
//
//import com.example.foodplanner.db.MealLocalDataSource;
//import com.example.foodplanner.network.MealRemoteDataSource;
//import com.example.foodplanner.network.NetworkCallback;
//
//import java.util.List;
//
//public class Repository {
//    private MealRemoteDataSource mealRemoteDataSource;
//    private MealLocalDataSource mealLocalDataSource;
//    private static Repository repository= null;
//
//    private Repository(MealRemoteDataSource mealRemoteDataSource ,MealLocalDataSource mealLocalDataSource) {
//       this.mealLocalDataSource=mealLocalDataSource;
//       this.mealRemoteDataSource=mealRemoteDataSource;
//    }
//    public static Repository getInstance (MealRemoteDataSource mealRemoteDataSource ,MealLocalDataSource mealLocalDataSource)
//    {
//        if (repository == null )
//        {
//            repository = new Repository(mealRemoteDataSource,mealLocalDataSource);
//        }
//        return repository;
//    }
//    public void getAllProducts (NetworkCallback networkCallBack)
//    {
//        mealRemoteDataSource.makeNetworkCallback(networkCallBack);
//    }
////    public void insertProduct (Product products)
////    {
////        productsLocalDataSource.insert(products);
////    }
////    public void deleteProduct (Product products)
////    {
////        productsLocalDataSource.delete(products);
////    }
////    public LiveData<List<Product>> getStoredProducts (){return productsLocalDataSource.getStoredProducts();}
//}
