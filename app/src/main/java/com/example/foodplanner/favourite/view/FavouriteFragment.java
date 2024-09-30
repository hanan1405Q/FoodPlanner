package com.example.foodplanner.favourite.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.favourite.presenter.FavPresenter;
import com.example.foodplanner.features.detailed_meal.view.DetailedMeal;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements FavView,FavFragmentOnclick {

    RecyclerView recyclerView;
    FavAdapter adt;
    List<Meal> meals;
    FavPresenter favPresenter;
    AlertDialog.Builder builder;
    public static final String mealObject="MEAL_OBJECT";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Creating the Presenter*/
        favPresenter=new FavPresenter(this,
        Repository.getInstance(MealRemoteDataSource.getInstance(),
                MealLocalDataSource.getInstance(requireContext())));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_favourite, container, false);

        recyclerView = v.findViewById(R.id.rvMyFavMeals);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mgr = new LinearLayoutManager(requireContext());
        mgr.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mgr);

        meals = new ArrayList<Meal>(){};
        adt=new FavAdapter(requireContext(),meals,this);
        recyclerView.setAdapter(adt);

        favPresenter.observeFavMeals(this);

        //alert dialog
        builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Alert !");
        builder.setCancelable(false);

        return v;
    }

    @Override
    public void setMeals(List<Meal> meals) {
         adt.setValues(meals);
    }

    @Override
    public void showError(String str)
    {
        Toast.makeText(requireContext(),"Can not Load Favorite Meals",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(Meal meal) {
        alert(meal);

    }

    @Override
    public void onCardClick(Meal meal) {
        Intent intent=new Intent(getActivity(), DetailedMeal.class);
        intent.putExtra(mealObject,meal);
        startActivity(intent);
    }


    void alert (Meal meal)
    {
        builder.setMessage("Are you sure you want to remove "+meal.getName()+" from favorites?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
       // When the user click yes button then app will close
           favPresenter.deleteMeal(meal);
        });
        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}