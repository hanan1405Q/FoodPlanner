package com.example.foodplanner.features.detailed_meal.view;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.features.detailed_meal.presenter.DetailedMealPresenter;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.home.view.HomeAdapter;
import com.example.foodplanner.home.view.HomeFragment;
import com.example.foodplanner.model.IngredientData;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class DetailedMeal extends AppCompatActivity implements DetailedMealView{

    DetailedMealPresenter mealPresenter;

    Meal targetMeal;

    TextView txtCountry;
    TextView txtMealName;
    ImageView imgMeal;

    FloatingActionButton fabFavourite;
    FloatingActionButton fabCalender;

   YouTubePlayerView vidMeal;
   TextView  txtInstruction;

   RecyclerView rvIngredient;
   DetailedMealAdapter adt;
   List<IngredientData>  ingredientData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_meal);

        /*Initialize The Presenter ***(SO IMPORTANT)****/
        mealPresenter=new DetailedMealPresenter(this,Repository.getInstance(MealRemoteDataSource.getInstance(), MealLocalDataSource.getInstance(getApplicationContext())));

        /*assign an inflated view object to the reference*/
        txtMealName=findViewById(R.id.txtDetailedMealName);
        txtCountry=findViewById(R.id.txtMealCountry);
        imgMeal=findViewById(R.id.imgDetailedMeal);

        fabFavourite=findViewById(R.id.fab_favorite);
        fabCalender=findViewById(R.id.fab_calendar);

        vidMeal=findViewById(R.id.vidMeal);
        txtInstruction=findViewById(R.id.txtInstruction);


        /*setup the recycler view */
        rvIngredient=findViewById(R.id.rvIngredient);
        rvIngredient.setHasFixedSize(true);
        LinearLayoutManager mgr = new LinearLayoutManager(getApplicationContext());
        mgr.setOrientation(RecyclerView.HORIZONTAL);
        rvIngredient.setLayoutManager(mgr);
        adt = new DetailedMealAdapter(getApplicationContext(), new ArrayList<>());
        rvIngredient.setAdapter(adt);

        /*extract the info from intent (which is brought me here)*/
        //Intent intent = getIntent();
        // Retrieve the data from the Intent
        targetMeal = (Meal) getIntent().getSerializableExtra("MEAL_OBJECT");
        showMeal(targetMeal);

         /*Debuging Purpose */
//        if(targetMeal==null)
//        {
//            Toast.makeText(getApplicationContext(),"extract Null obj from intent ", LENGTH_SHORT).show();
//        }
//        Toast.makeText(getApplicationContext(),"I am in Detailed meal Activity  after extract obj from intent ", LENGTH_SHORT).show();
//        Log.i("MealName = ",targetMeal.getName());

       // Log.i("MealName = ",mealName);
        // mealPresenter.getMealByName(mealName);



    }

    public void showMeal(Meal meal)
    {
        Glide.with(getApplicationContext()).load(meal.getImgSource())
                .apply(new RequestOptions().override(300, 300)
                .placeholder(R.drawable.loadingimag_animation))
                .into(imgMeal);
        txtMealName.setText(meal.getName());
        txtCountry.setText(meal.getArea());

        adt.setData(meal.getIngredientAndMeasure());
        mealPresenter.checkYouTubeURL(meal.getYoutubeSource());
        txtInstruction.setText(meal.getInstructions());

        fabFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mealPresenter.addToFav(meal);
            }
        });
    }

//    @Override
//    public void showMeal(List<Meal> meals) {
//        Meal meal=meals.get(0);
//
//        Glide.with(getApplicationContext()).load(meal.getImgSource())
//                .apply(new RequestOptions().override(300, 300)
//                .placeholder(R.drawable.loadingimag_animation))
//                .into(imgMeal);
//        txtMealName.setText(meal.getName());
//        txtCountry.setText(meal.getArea());
//
//        adt.setData(meal.getIngredientAndMeasure());
//        mealPresenter.checkYouTubeURL(meal.getYoutubeSource());
//        txtInstruction.setText(meal.getInstructions());
//
//        fabFavourite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mealPresenter.addToFav(meal);
//            }
//        });
//
//    }
//
//    @Override
//    public void showError(String str) {
//        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void playYouTubeVideo(String videoId) {
           vidMeal.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
               @Override
               public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                   youTubePlayer.cueVideo(videoId,0);
               }
           });

    }

    @Override
    public void showInvalidUrl() {
        Toast.makeText(this, "Invalid YouTube URL", LENGTH_SHORT).show();
    }
}