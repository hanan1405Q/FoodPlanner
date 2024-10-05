package com.example.foodplanner.search.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.favourite.view.FavAdapter;
import com.example.foodplanner.favourite.view.FavFragmentOnclick;
import com.example.foodplanner.features.detailed_meal.view.DetailedMeal;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.example.foodplanner.search.presenter.SearchPresenter;
import com.example.foodplanner.utils.Connection;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchViewInterface, FavFragmentOnclick {

    RecyclerView recyclerView;
    FavAdapter adt;
    List<Meal> meals;
    SearchPresenter  searchPresenter;

    int searchType;

    SearchView searchView;
    RadioGroup  radioGroup;
    RadioButton btnMeal;
    RadioButton  btnCategory;
    RadioButton  btnIngredient;
    RadioButton btnCountry;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*Creating the Presenter*/
        searchPresenter=new SearchPresenter(this,
                Repository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(requireContext())));

    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Connection.checkConnectionAndAlert(getContext());
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_search, container, false);


         /*Recycler View and Adapter Set up*/
        recyclerView = v.findViewById(R.id.recyclerViewSearchResults);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mgr = new LinearLayoutManager(requireContext());
        mgr.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mgr);

        meals = new ArrayList<Meal>(){};
        adt=new FavAdapter(requireContext(),meals,this,false);
        recyclerView.setAdapter(adt);
        /*****************************************************/

        searchView=v.findViewById(R.id.searchViewFragment);

        radioGroup=v.findViewById(R.id.radioGroupSearch);
        btnMeal=v.findViewById(R.id.radioMealName);
        btnCategory=v.findViewById(R.id.radioCategory);
        btnIngredient=v.findViewById(R.id.radioIngredient);
        btnCountry=v.findViewById(R.id.radioCountry);
        /*set default value of radio button to the search Type*/
        searchType=R.id.radioCategory;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                searchType=i;
                //Toast.makeText(requireContext()," option"+searchType+"is selected",Toast.LENGTH_LONG).show();
            }
        });


        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchPresenter.getMeals(searchType,newText);
                return true;
            }
        });

        return  v;
    }


    @Override
    public void showMeals(List<Meal> meals) {
        adt.setValues(meals);
    }

    @Override
    public void showDetailedMeal(List<Meal> meals) {
        Intent intent=new Intent(getActivity(), DetailedMeal.class);
        intent.putExtra("MEAL_OBJECT",meals.get(0));
        startActivity(intent);
    }

    @Override
    public void ShowNotValidSearch() {
        Toast.makeText(requireContext(),
                "Sorry, we couldn't find any matches. Try adjusting your search terms!",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(Meal meal) {

    }

    @Override
    public void onCardClick(Meal meal) {
        searchPresenter.getMealByName(meal.getName());
    }
}