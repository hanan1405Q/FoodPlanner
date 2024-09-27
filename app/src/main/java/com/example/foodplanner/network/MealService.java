package com.example.foodplanner.network;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Meals;

import  retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

      // Lookup a single random meal
       @GET("random.php")
       Call<Meals> getRandomMeal();

       // Search meal by name
        @GET("search.php")
        Call<Meals> searchMealByName(@Query("s") String mealName);

        // List all meals by first letter
        @GET("search.php")
        Call<Meals> searchMealsByFirstLetter(@Query("f") char firstLetter);

        // Lookup full meal details by id
        @GET("lookup.php")
        Call<Meals> SearchMealById(@Query("i") String mealId);



        // List all meal categories
        @GET("categories.php")
        Call<CategoryResponse> ListMealCategories();

//        // List all categories
//        @GET("list.php")
//        Call<CategoryList> listCategories(@Query("c") String categoryList);
//
//        // List all areas
//        @GET("list.php")
//        Call<AreaList> listAreas(@Query("a") String areaList);
//
//        // List all ingredients
//        @GET("list.php")
//        Call<IngredientList> listIngredients(@Query("i") String ingredientList);
//
//        // Filter meals by main ingredient
//        @GET("filter.php")
//        Call<MealList> filterByMainIngredient(@Query("i") String mainIngredient);
//
//
//        // Filter meals by area
//        @GET("filter.php")
//        Call<MealList> filterByArea(@Query("a") String area);

    }


