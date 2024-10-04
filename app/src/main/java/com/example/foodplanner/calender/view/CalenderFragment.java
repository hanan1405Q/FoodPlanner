package com.example.foodplanner.calender.view;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.calender.presenter.CalenderPresenter;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.favourite.presenter.FavPresenter;
import com.example.foodplanner.favourite.view.FavAdapter;

import com.example.foodplanner.features.detailed_meal.view.DetailedMeal;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class CalenderFragment extends Fragment implements CalenderViewInterface, OnClickCalenderListener {

    RecyclerView recyclerView;
    CalenderAdapter adt;
    List<PlannedMeal> meals;
    CalenderPresenter presenter;
    CalendarView calendarView;

    AlertDialog.Builder builder;
    public static final String mealObject="MEAL_OBJECT";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Creating the Presenter*/
        presenter=new CalenderPresenter(this,
                Repository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(requireContext())));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_calender, container, false);
        calendarView=v.findViewById(R.id.calenderViewMeal);

        recyclerView = v.findViewById(R.id.rvCalenderMeals);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mgr = new LinearLayoutManager(requireContext());
        mgr.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mgr);

        meals = new ArrayList<PlannedMeal>(){};
        adt=new CalenderAdapter(requireContext(),meals,this);
        recyclerView.setAdapter(adt);

        //alert dialog
        builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Alert !");
        builder.setCancelable(false);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String selectedDate = year + "." + (month + 1) + "." + day;
                Toast.makeText(requireContext(),"Leaving Fragment with selectd Date"
                +selectedDate,Toast.LENGTH_SHORT).show();
               presenter.observeMealsByDate(CalenderFragment.this,selectedDate);
            }
        });


        return v;
    }



    @Override
    public void showMeals(List<PlannedMeal> meals) {
        if(meals.isEmpty())
            Toast.makeText(requireContext(),"No Available Plane in this Date",Toast.LENGTH_SHORT).show();
        adt.setValues(meals);

    }

    @Override
    public void showError(String str)
    {
        Toast.makeText(requireContext(),"Can not Load Planned meals",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(PlannedMeal meal) {
        alert(meal);
    }

    @Override
    public void onCardClick(PlannedMeal meal) {
        Intent intent=new Intent(getActivity(), DetailedMeal.class);
        intent.putExtra(mealObject,meal.getMeal());
        startActivity(intent);
    }


    void alert (PlannedMeal meal)
    {
        builder.setMessage("Are you sure you want to remove "+meal.getMeal().getName()+" from Plane?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            presenter.deletePlannedMeal(meal);
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

