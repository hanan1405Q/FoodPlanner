package com.example.foodplanner.features.detailed_meal.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.home.view.HomeAdapter;
import com.example.foodplanner.model.IngredientData;
import com.example.foodplanner.model.Meal;

import java.util.List;

public class DetailedMealAdapter extends RecyclerView.Adapter<DetailedMealAdapter.ViewHolder> {
    private final Context context;
    private List<IngredientData> values;
    //private OnFavouriteClickListener listener;
    private static final String TAG = "DetailedMealRecyclerView";

    public void setData(List<IngredientData> data) {
        this.values = data;
        notifyDataSetChanged();
    }

    //Provide a suitable Constructor
    public DetailedMealAdapter(Context context, List<IngredientData> data) {
        this.context = context;
        values = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //should describe the row layout
        public TextView txtIngredientName;
        public TextView txtIngredientMeasure;
        public ImageView imgIngredient;
        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtIngredientName = v.findViewById(R.id.txtIngredientName);
            txtIngredientMeasure = v.findViewById(R.id.txtIngredientMeasure);
            imgIngredient = v.findViewById(R.id.imgIngredient);
        }


    }

    @NonNull
    @Override
    public DetailedMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        //create a new view
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.row_ingrediant_with_measure, recycler, false);
        DetailedMealAdapter.ViewHolder vh = new DetailedMealAdapter.ViewHolder(v);
        Log.i(TAG, "****** onCreateViewHolder ***********");
        return vh;

    }

    //replace the contents of the view (invoked by Layout Manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtIngredientName.setText(values.get(position).getIngredient());
        holder.txtIngredientMeasure.setText(values.get(position).getMeasure());
        Glide.with(context)
                .load(String.format(context.getString(R.string.ingredient_img), values.get(position).getIngredient()))
                .apply(new RequestOptions().override(150, 150)
                        .placeholder(R.drawable.loadingimag_animation))
                .into(holder.imgIngredient);


    }

    @Override
    public int getItemCount() {
        return values.size();
    }


}


//        holder.btnFavourite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.setOnFavouriteList(values.get(position));
//            }
//        });
//            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context,values.get(position).getTitle(),Toast.LENGTH_SHORT).show();
//                }
//            });




