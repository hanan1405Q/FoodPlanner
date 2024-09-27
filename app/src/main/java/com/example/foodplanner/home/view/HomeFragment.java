package com.example.foodplanner.home.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeView {

    RecyclerView recyclerView;
    HomeAdapter adt;
    List<Category> categories;
    HomePresenter homePresenter;
    ImageView imgMeal;
    TextView txMealName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(this, Repository.getInstance(MealRemoteDataSource.getInstance()));
        //, MealLocalDataSource.getInstance(getApplicationContext())));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        /*Take Refrence To Card Components */
        imgMeal = view.findViewById(R.id.imgMeal);
        txMealName = view.findViewById(R.id.txtMealName);

        /*setup the recycler view */
        recyclerView = view.findViewById(R.id.recycler_randomCategory);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mgr = new LinearLayoutManager(requireContext());
        mgr.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(mgr);
        adt = new HomeAdapter(requireContext(), new ArrayList<>());
        recyclerView.setAdapter(adt);

        /*Request the Data*/
        homePresenter.getRandomMeal();
        homePresenter.getCategoryMeal();

        return view;
    }

    @Override
    public void showRandomMeal(Meal meal) {
        //Toast.makeText(requireContext(),meal.getName(),Toast.LENGTH_SHORT).show();
        txMealName.setText(meal.getName());
        Glide.with(requireContext()).load(meal.getImgSource())
                .apply(new RequestOptions().override(300, 300)
                        .placeholder(R.drawable.ic_launcher_background))
                .into(imgMeal);

    }

    @Override
    public void showCategoryMeal(List<Category> categories) {
        adt.setData(categories);
        adt.notifyDataSetChanged();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show();
    }
}