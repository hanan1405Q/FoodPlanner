package com.example.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "meals_table")
public class Meal {

    @PrimaryKey
    @NonNull
    @SerializedName("idMeal")
    public String  id;

    @SerializedName("strMeal")
    public String  name;

    @SerializedName("strDrinkAlternate")
    public Object  drink;

    @SerializedName("strCategory")
    public String  category;

    @SerializedName("strArea")
    public String area;

    @SerializedName("strInstructions")
    public String instructions;

    @SerializedName("strMealThumb")
    public String imgSource;

    @SerializedName("strTags")
    public String tags;
    @SerializedName("strYoutube")
    public String youtubeSource;

    @SerializedName("strIngredient1")
    public String ingredient1;

    @SerializedName("strIngredient2")
    public String ingredient2;

    @SerializedName("strIngredient3")
    public String ingredient3;

    @SerializedName("strIngredient4")
    public String ingredient4;

    @SerializedName("strIngredient5")
    public String ingredient5;

    @SerializedName("strIngredient6")
    public String ingredient6;

    @SerializedName("strIngredient7")
    public String ingredient7;

    @SerializedName("strIngredient8")
    public String ingredient8;

    @SerializedName("strIngredient9")
    public String ingredient9;

    @SerializedName("strIngredient10")
    public String ingredient10;

    @SerializedName("strIngredient11")
    public String ingredient11;

    @SerializedName("strIngredient12")
    public String ingredient12;

    @SerializedName("strIngredient13")
    public String ingredient13;

    @SerializedName("strIngredient14")
    public String ingredient14;

    @SerializedName("strIngredient15")
    public String ingredient15;

    @SerializedName("strIngredient16")
    public String ingredient16;

    @SerializedName("strIngredient17")
    public String ingredient17;

    @SerializedName("strIngredient18")
    public String ingredient18;

    @SerializedName("strIngredient19")
    public String ingredient19;

    @SerializedName("strIngredient20")
    public String ingredient20;


    @SerializedName("strMeasure1")
    public String measure1;

    @SerializedName("strMeasure2")
    public String measure2;

    @SerializedName("strMeasure3")
    public String measure3;

    @SerializedName("strMeasure4")
    public String measure4;

    @SerializedName("strMeasure5")
    public String measure5;

    @SerializedName("strMeasure6")
    public String measure6;

    @SerializedName("strMeasure7")
    public String measure7;

    @SerializedName("strMeasure8")
    public String measure8;

    @SerializedName("strMeasure9")
    public String measure9;

    @SerializedName("strMeasure10")
    public String measure10;

    @SerializedName("strMeasure11")
    public String measure11;

    @SerializedName("strMeasure12")
    public String measure12;

    @SerializedName("strMeasure13")
    public String measure13;

    @SerializedName("strMeasure14")
    public String measure14;

    @SerializedName("strMeasure15")
    public String measure15;

    @SerializedName("strMeasure16")
    public String measure16;

    @SerializedName("strMeasure17")
    public String measure17;

    @SerializedName("strMeasure18")
    public String measure18;

    @SerializedName("strMeasure19")
    public String measure19;

    @SerializedName("strMeasure20")
    public String measure20;

    @SerializedName("strSource")
    public String webSource;
/*******************************************************/

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDrink() {
        return drink;
    }

    public void setDrink(Object drink) {
        this.drink = drink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    public String getYoutubeSource() {
        return youtubeSource;
    }

    public void setYoutubeSource(String youtubeSource) {
        this.youtubeSource = youtubeSource;
    }



    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public void setIngredient5(String ingredient5) {
        this.ingredient5 = ingredient5;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public void setIngredient6(String ingredient6) {
        this.ingredient6 = ingredient6;
    }

    public String getIngredient7() {
        return ingredient7;
    }

    public void setIngredient7(String ingredient7) {
        this.ingredient7 = ingredient7;
    }

    public String getIngredient8() {
        return ingredient8;
    }

    public void setIngredient8(String ingredient8) {
        this.ingredient8 = ingredient8;
    }

    public String getIngredient9() {
        return ingredient9;
    }

    public void setIngredient9(String ingredient9) {
        this.ingredient9 = ingredient9;
    }

    public String getMeasure1() {
        return measure1;
    }

    public void setMeasure1(String measure1) {
        this.measure1 = measure1;
    }

    public String getMeasure2() {
        return measure2;
    }

    public void setMeasure2(String measure2) {
        this.measure2 = measure2;
    }

    public String getMeasure3() {
        return measure3;
    }

    public void setMeasure3(String measure3) {
        this.measure3 = measure3;
    }

    public String getMeasure4() {
        return measure4;
    }

    public void setMeasure4(String measure4) {
        this.measure4 = measure4;
    }

    public String getMeasure5() {
        return measure5;
    }

    public void setMeasure5(String measure5) {
        this.measure5 = measure5;
    }

    public String getMeasure6() {
        return measure6;
    }

    public void setMeasure6(String measure6) {
        this.measure6 = measure6;
    }

    public String getMeasure7() {
        return measure7;
    }

    public void setMeasure7(String measure7) {
        this.measure7 = measure7;
    }

    public String getMeasure8() {
        return measure8;
    }

    public void setMeasure8(String measure8) {
        this.measure8 = measure8;
    }

    public String getMeasure9() {
        return measure9;
    }

    public void setMeasure9(String measure9) {
        this.measure9 = measure9;
    }

    public String getWebSource() {
        return webSource;
    }

    public void setWebSource(String webSource) {
        this.webSource = webSource;
    }

       /*******************************************************************/
/********************************************************************************/
    public List<IngredientData> getIngredientAndMeasure() {
        List<IngredientData> ingredientDataList = new ArrayList<>();

        // Check each ingredient and measure for null/empty values and add valid pairs to the list
        addIngredientMeasure(ingredientDataList, ingredient1, measure1);
        addIngredientMeasure(ingredientDataList, ingredient2, measure2);
        addIngredientMeasure(ingredientDataList, ingredient3, measure3);
        addIngredientMeasure(ingredientDataList, ingredient4, measure4);
        addIngredientMeasure(ingredientDataList, ingredient5, measure5);
        addIngredientMeasure(ingredientDataList, ingredient6, measure6);
        addIngredientMeasure(ingredientDataList, ingredient7, measure7);
        addIngredientMeasure(ingredientDataList, ingredient8, measure8);
        addIngredientMeasure(ingredientDataList, ingredient9, measure9);
        addIngredientMeasure(ingredientDataList, ingredient10, measure10);
        addIngredientMeasure(ingredientDataList, ingredient11, measure11);
        addIngredientMeasure(ingredientDataList, ingredient12, measure12);
        addIngredientMeasure(ingredientDataList, ingredient13, measure13);
        addIngredientMeasure(ingredientDataList, ingredient14, measure14);
        addIngredientMeasure(ingredientDataList, ingredient15, measure15);
        addIngredientMeasure(ingredientDataList, ingredient16, measure16);
        addIngredientMeasure(ingredientDataList, ingredient17, measure17);
        addIngredientMeasure(ingredientDataList, ingredient18, measure18);
        addIngredientMeasure(ingredientDataList, ingredient19, measure19);
        addIngredientMeasure(ingredientDataList, ingredient20, measure20);

        return ingredientDataList;
    }

    // Helper method to check and add non-null and non-empty ingredients and measures
    private void addIngredientMeasure(List<IngredientData> list, String ingredient, String measure) {
        if (ingredient != null && !ingredient.isEmpty() && measure != null && !measure.isEmpty()) {
            list.add(new IngredientData(ingredient, measure));
        }
    }



}

