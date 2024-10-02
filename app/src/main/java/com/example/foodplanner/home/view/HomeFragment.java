package com.example.foodplanner.home.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
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
import com.example.foodplanner.features.detailed_meal.view.DetailedMeal;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Country;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeView,ClickListener {

    public static final String mealObject="MEAL_OBJECT";
    RecyclerView recyclerViewCategory;
    RecyclerView recyclerViewCountry;
    CategoryAdapter adt1;
    CounteryAdapter adt2;
    List<Category> categories;
    List<Country>  countries;
    HomePresenter homePresenter;

    ImageView imgMeal;
    TextView txMealName;
    CardView  cardView;

    Meal targetMeal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(this, Repository.getInstance(MealRemoteDataSource.getInstance(),MealLocalDataSource.getInstance(requireContext())));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        /*Take Reference To Card Components */
        imgMeal = view.findViewById(R.id.imgMeal);
        txMealName = view.findViewById(R.id.txtMealName);
        cardView=view.findViewById(R.id.cardMeal);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), DetailedMeal.class);
                intent.putExtra(mealObject,targetMeal);
                startActivity(intent);

                    /*Debug Purpose*/
//                Toast.makeText(requireContext(), "I am Leaving Home Fragment to Detailed Meal Activity  ", Toast.LENGTH_SHORT).show();
//                Toast.makeText(requireContext(), "Meal Name = "+ targetMeal.getName().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        /*setup the recycler view for Category */
        recyclerViewCategory = view.findViewById(R.id.rvListCategory);
        recyclerViewCategory.setHasFixedSize(true);
        LinearLayoutManager mgr = new LinearLayoutManager(requireContext());
        mgr.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewCategory.setLayoutManager(mgr);
        adt1 = new CategoryAdapter(requireContext(), new ArrayList<>());
        recyclerViewCategory.setAdapter(adt1);

        /*setup the recycler view for Country */
        recyclerViewCountry = view.findViewById(R.id.rvListCountry);
        recyclerViewCountry.setHasFixedSize(true);
        LinearLayoutManager mgr1 = new LinearLayoutManager(requireContext());
        mgr1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewCountry.setLayoutManager(mgr1);
        adt2 = new CounteryAdapter(requireContext(), new ArrayList<>());
        recyclerViewCountry.setAdapter(adt2);

        /*Request the Data*/
        homePresenter.getRandomMeal();
        homePresenter.listCategoryMeal();

        /*Synchorounce Request*/
        List<Country> countries=homePresenter.listArea();
        adt2.setData(countries);

        return view;
    }

    @Override
    public void showRandomMeal(Meal meal) {
        targetMeal=meal;
        //Toast.makeText(requireContext(),meal.getName(),Toast.LENGTH_SHORT).show();
        txMealName.setText(meal.getName());
        Glide.with(requireContext()).load(meal.getImgSource())
                .apply(new RequestOptions().override(300, 300)
                        .placeholder(R.drawable.loadingimag_animation))
                .into(imgMeal);

    }

    @Override
    public void showCategoryMeal(List<Category> categories) {
        adt1.setData(categories);
        adt1.notifyDataSetChanged();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void countryCardListener(Country country) {

    }

    @Override
    public void CategoryCardListener(Category category) {

    }
}