//package com.example.foodplanner.network;
//
//import android.util.Log;
//
//import com.example.foodplanner.model.Meal;
//import com.example.foodplanner.model.Meals;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class MealRemoteDataSource {
//    public static final String BASE_URL = "https://themealdb.com/";
//    private MealService mealService;
//    private  static MealRemoteDataSource client=null;
//    public static String TAG="NetworkCallback";
//
//    private static MealRemoteDataSource mealRemoteDataSource= null;
//
//    private MealRemoteDataSource()
//    {
//        //that is my builder Pattern
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL).build();
//        //create an object from class that implements the (MealService)interface
//        mealService = retrofit.create(MealService.class);
//    }
//
//    public static MealRemoteDataSource getInstance(){
//        if(mealRemoteDataSource == null)
//        {
//            mealRemoteDataSource = new MealRemoteDataSource();
//        }
//        return mealRemoteDataSource;
//
//    }
//
//
//    public void makeNetworkCallback(NetworkCallback networkCallback)
//    {
//        Call<Meals> call = mealService.getRandomMeal();
//        //enqueue-> make asynch call
//        call.enqueue(new Callback<Meals>() {
//            @Override
//            public void onResponse(Call<Meals> call, Response<Meals> response) {
//                if(response.isSuccessful())
//                {
//                    Log.i(TAG, "onResponseCallback: " +response.raw()+ response.body());
//                    networkCallback.onSuccessResult(response.body().meals);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Meals> call, Throwable t) {
//                Log.i(TAG, "onFailureCallback" );
//                networkCallback.onFailureResult(t.getMessage());
//                t.printStackTrace();
//
//            }
//        });
//    }
//
//}
