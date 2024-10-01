package com.example.foodplanner.network;

import android.util.Log;

import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Meals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {
    public static final String BASE_URL = "https://themealdb.com/api/json/v1/1/";
    private MealService mealService;
    private  static MealRemoteDataSource client=null;
    public static String TAG="NetworkCallback";

    private static MealRemoteDataSource mealRemoteDataSource= null;

    private MealRemoteDataSource()
    {
        //that is my builder Pattern
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        //create an object from class that implements the (MealService)interface
        mealService = retrofit.create(MealService.class);
    }

    public static MealRemoteDataSource getInstance(){
        if(mealRemoteDataSource == null)
        {
            mealRemoteDataSource = new MealRemoteDataSource();
        }
        return mealRemoteDataSource;

    }


    public void makeRandomMealNetworkCallback(NetworkCallback networkCallback)
    {
        Call<Meals> call = mealService.getRandomMeal();
        Log.i(TAG, "Request URL: " + call.request().url());
        //enqueue-> make asynch call
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful())
                {
                    Log.i(TAG, "onResponseCallback: " +response.raw()+ response.body());
                    networkCallback.onSuccessRandomResult(response.body().getMeals().get(0));
                }
                else {
                    Log.e(TAG, "Response Error: Code " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.i(TAG, "onFailureCallback" );
                networkCallback.onFailureRandomResult(t.getMessage());
                t.printStackTrace();

            }
        });
    }
/*******************************************************************************************************/

    public void makeSearchByNameNetworkCallback(NetworkCallback networkCallback,String mealName)
    {
        Call<Meals> call = mealService.searchMealByName(mealName);
        Log.i(TAG, "Request URL: " + call.request().url());
        //enqueue-> make asynch call
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // Get the list of meals from the response body
                        List<Meal> meals = response.body().getMeals();

                        Log.i(TAG, "onResponseCallback: Meals retrieved: " + meals);
                        if (networkCallback != null) {
                            networkCallback.onSuccessSearchByNameResult(meals);
                        } else {
                            Log.e(TAG, "Network callback is not initialized.");
                        }
                    } else {
                        Log.e(TAG, "Response body is null.");
                    }
                } else {
                    Log.e(TAG, "Response Error: Code " + response.code() + " Message: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.i(TAG, "onFailureCallback" );
                networkCallback.onFailureSearchByNameResult(t.getMessage());
                t.printStackTrace();

            }
        });
    }

    /******************************************************************************************/
//
//    public void makeSearchByIDNetworkCallback(NetworkCallback networkCallback,String mealId)
//    {
//        Call<Meals> call = mealService.SearchMealById(mealId);
//        Log.i(TAG, "Request URL: " + call.request().url());
//        //enqueue-> make asynch call
//        call.enqueue(new Callback<Meals>() {
//            @Override
//            public void onResponse(Call<Meals> call, Response<Meals> response) {
//                if(response.isSuccessful())
//                {
//                    Log.i(TAG, "onResponseCallback: " +response.raw()+ response.body().getMeals());
//                    networkCallback.onSuccessSearchByID(response.body().getMeals().get(0));
//                }
//                else {
//                    Log.e(TAG, "Response Error: Code " + response.code() + " Message: " + response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Meals> call, Throwable t) {
//                Log.i(TAG, "onFailureCallback" );
//                networkCallback.onFailureSearchByID(t.getMessage());
//                t.printStackTrace();
//
//            }
//        });
//    }
//
//    public void makeSearchByLetterNetworkCallback(NetworkCallback networkCallback,char chr)
//    {
//        Call<Meals> call = mealService.searchMealsByFirstLetter(chr);
//        Log.i(TAG, "Request URL: " + call.request().url());
//        //enqueue-> make asynch call
//        call.enqueue(new Callback<Meals>() {
//            @Override
//            public void onResponse(Call<Meals> call, Response<Meals> response) {
//                if(response.isSuccessful())
//                {
//                    Log.i(TAG, "onResponseCallback: " +response.raw()+ response.body().getMeals());
//                    networkCallback.onSuccessSearchByLetter(response.body());
//                }
//                else {
//                    Log.e(TAG, "Response Error: Code " + response.code() + " Message: " + response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Meals> call, Throwable t) {
//                Log.i(TAG, "onFailureCallback" );
//                networkCallback.onFailureSearchByLetter(t.getMessage());
//                t.printStackTrace();
//
//            }
//        });
//    }

    public void makeListMealCategoriesNetworkCallback(NetworkCallback networkCallback)
    {
        Call<CategoryResponse> call = mealService.ListMealCategories();
        Log.i(TAG, "Request URL: " + call.request().url());
        //enqueue-> make asynch call
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful())
                {
                    Log.i(TAG, "onResponseCallback: " +response.raw()+ response.body().getCategories());
                    networkCallback.onSuccessListMealCategories(response.body());
                }
                else {
                    Log.e(TAG, "Response Error: Code " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.i(TAG, "onFailureCallback" );
                networkCallback.onFailureListMealCategories(t.getMessage());
                t.printStackTrace();

            }
        });
    }
/*******************************************************************************************/
public void makeFilterByCategoryNetworkCallback(NetworkCallback networkCallback,String mealName)
{
    Call<Meals> call = mealService.filterByCategory(mealName);
    Log.i(TAG, "Request URL: " + call.request().url());
    //enqueue-> make asynch call
    call.enqueue(new Callback<Meals>() {
        @Override
        public void onResponse(Call<Meals> call, Response<Meals> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    // Get the list of meals from the response body
                    List<Meal> meals = response.body().getMeals();

                    Log.i(TAG, "onResponseCallback: Meals retrieved: " + meals);
                    if (networkCallback != null) {
                        networkCallback.onSuccessFilterByCategory(meals);
                    } else {
                        Log.e(TAG, "Network callback is not initialized.");
                    }
                } else {
                    Log.e(TAG, "Response body is null.");
                }
            } else {
                Log.e(TAG, "Response Error: Code " + response.code() + " Message: " + response.message());
            }

        }

        @Override
        public void onFailure(Call<Meals> call, Throwable t) {
            Log.i(TAG, "onFailureCallback" );
            networkCallback.onFailureFilterByCategory(t.getMessage());
            t.printStackTrace();

        }
    });
}
/******************************************************************************/

public void makeFilterByIngredientNetworkCallback(NetworkCallback networkCallback,String mealName)
{
    Call<Meals> call = mealService.filterByMainIngredient(mealName);
    Log.i(TAG, "Request URL: " + call.request().url());
    //enqueue-> make asynch call
    call.enqueue(new Callback<Meals>() {
        @Override
        public void onResponse(Call<Meals> call, Response<Meals> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    // Get the list of meals from the response body
                    List<Meal> meals = response.body().getMeals();

                    Log.i(TAG, "onResponseCallback: Meals retrieved: " + meals);
                    if (networkCallback != null) {
                        networkCallback.onSuccessFilterByIngredient(meals);
                    } else {
                        Log.e(TAG, "Network callback is not initialized.");
                    }
                } else {
                    Log.e(TAG, "Response body is null.");
                }
            } else {
                Log.e(TAG, "Response Error: Code " + response.code() + " Message: " + response.message());
            }

        }

        @Override
        public void onFailure(Call<Meals> call, Throwable t) {
            Log.i(TAG, "onFailureCallback" );
            networkCallback.onFailureFilterByIngredient(t.getMessage());
            t.printStackTrace();

        }
    });
}
/********************************************************************************************/

public void makeFilterByAreaNetworkCallback(NetworkCallback networkCallback,String mealName)
{
    Call<Meals> call = mealService.filterByArea(mealName);
    Log.i(TAG, "Request URL: " + call.request().url());
    //enqueue-> make asynch call
    call.enqueue(new Callback<Meals>() {
        @Override
        public void onResponse(Call<Meals> call, Response<Meals> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    // Get the list of meals from the response body
                    List<Meal> meals = response.body().getMeals();

                    Log.i(TAG, "onResponseCallback: Meals retrieved: " + meals);
                    if (networkCallback != null) {
                        networkCallback.onSuccessFilterByArea(meals);
                    } else {
                        Log.e(TAG, "Network callback is not initialized.");
                    }
                } else {
                    Log.e(TAG, "Response body is null.");
                }
            } else {
                Log.e(TAG, "Response Error: Code " + response.code() + " Message: " + response.message());
            }

        }

        @Override
        public void onFailure(Call<Meals> call, Throwable t) {
            Log.i(TAG, "onFailureCallback" );
            networkCallback.onFailureFilterByArea(t.getMessage());
            t.printStackTrace();

        }
    });
}
/********************************************************************************************/
}
