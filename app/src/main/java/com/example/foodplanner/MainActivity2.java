package com.example.foodplanner;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodplanner.calender.view.CalenderFragment;
import com.example.foodplanner.databinding.ActivityMain2Binding;
import com.example.foodplanner.favourite.view.FavouriteFragment;
import com.example.foodplanner.home.view.HomeFragment;
import com.example.foodplanner.search.view.SearchFragment;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.mnuHome)
            {
                setFragment(new HomeFragment());


            } else if (item.getItemId()==R.id.mnuSearch) {

                setFragment(new SearchFragment());

            } else if (item.getItemId()==R.id.mnuFavourite) {

                setFragment(new FavouriteFragment());

            } else if (item.getItemId()==R.id.mnuCalender) {

                setFragment(new CalenderFragment());
            }


            return  true;
        });

    }

    //create method that replace frame layout with a fragment
    private  void setFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
        // Show Toast message to confirm fragment replacement
        //Toast.makeText(this, "Fragment replaced", Toast.LENGTH_SHORT).show();
    }

}