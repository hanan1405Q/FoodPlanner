package com.example.foodplanner.features.all_meals_in_category.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.favourite.view.FavAdapter;
import com.example.foodplanner.favourite.view.FavFragmentOnclick;
import com.example.foodplanner.features.all_meals_in_Country.presenter.MealsInCountryPresenter;
import com.example.foodplanner.features.all_meals_in_category.presenter.MealsInCategoryPresenter;
import com.example.foodplanner.features.detailed_meal.view.DetailedMeal;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.example.foodplanner.utils.Connection;

import java.util.ArrayList;
import java.util.List;

public class MealsInCategoryFragment extends Fragment implements MealsInCategoryView, FavFragmentOnclick {

    String selectedCategory;
    MealsInCategoryPresenter presenter;

    RecyclerView recyclerView;
    FavAdapter adt;
    List<Meal> meals;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedCategory = getArguments().getString("selectedCategory");
        }

        /*Toast.makeText(requireContext(),"I am in MealsInCountryFragment",
                Toast.LENGTH_SHORT).show();*/

        /*Creating the Presenter*/
        presenter=new MealsInCategoryPresenter(this,
                Repository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(requireContext())));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Connection.checkConnectionAndAlert(getContext());
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_list_category, container, false);

        TextView txtView=v.findViewById(R.id.txtAllCategoryMeal);
        if(selectedCategory!=null && !selectedCategory.isEmpty())
        {
            txtView.setText(selectedCategory + " Meals");
        }
        else
        {
            txtView.setText(" Meals ");
        }

        /*Recycler View and Adapter Set up*/
        recyclerView = v.findViewById(R.id.rvListMealsCategory);
        recyclerView.setHasFixedSize(true);
        // GridLayoutManager mgr = new GridLayoutManager(requireContext(), 2);
        LinearLayoutManager mgr=new LinearLayoutManager(requireContext());
        mgr.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mgr);

        meals = new ArrayList<Meal>(){};
        adt=new FavAdapter(requireContext(),meals,this,false);
        recyclerView.setAdapter(adt);

        presenter.getMealsInCategoty(selectedCategory);

        return  v;
    }

    @Override
    public void onDeleteClick(Meal meal) {

    }

    @Override
    public void onCardClick(Meal meal) {
       presenter.getMealByName(meal.getName());
    }

    @Override
    public void showMeals(List<Meal> meals) {
        adt.setValues(meals);
    }

    @Override
    public void showError(String errMessage) {

    }

    @Override
    public void showDetailedMeal(List<Meal> meals) {
        Intent intent=new Intent(getActivity(), DetailedMeal.class);
        intent.putExtra("MEAL_OBJECT",meals.get(0));
        startActivity(intent);
    }
}