package com.example.foodplanner.calender.view;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.favourite.view.FavAdapter;
import com.example.foodplanner.favourite.view.FavFragmentOnclick;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;

import java.util.ArrayList;
import java.util.List;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.ViewHolder> {
    private final Context context;
    private List<PlannedMeal> values;
    private static final String TAG = "CalenderRecyclerView";
    private OnClickCalenderListener  listener;


    public CalenderAdapter(Context context, List<PlannedMeal>values,OnClickCalenderListener listener) {
        this.context = context;
        // Initialize with an empty list if `values` is null
        this.values = (values != null) ? values : new ArrayList<>();
        this.listener=listener;
    }

    public void setValues(List<PlannedMeal> values)
    {
        this.values = (values != null) ? values : new ArrayList<>();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imgView;
        TextView txtMealName;
        TextView txtMealCountry;
        ImageButton btnDelete;
        public ViewHolder(@NonNull View v) {
            super(v);

        cardView=v.findViewById(R.id.cardFavMeal);
        imgView = v.findViewById(R.id.imgFavMeal);
        txtMealName=v.findViewById(R.id.txtFavMealName);
        txtMealCountry=v.findViewById(R.id.txtFavMealCountry);
        btnDelete=v.findViewById(R.id.btnDelete);

        }

    }


    @NonNull
    @Override
    public CalenderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.row_favmealcard,recycler,false);
        CalenderAdapter.ViewHolder vh = new CalenderAdapter.ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: ");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderAdapter.ViewHolder holder, int position) {
        holder.txtMealName.setText(values.get(position).getMeal().getName());
        holder.txtMealCountry.setText(values.get(position).getMeal().getArea());
        Glide.with(context).load(values.get(position).getMeal().getImgSource()).apply(new RequestOptions().override(150,150)
                        .placeholder(R.drawable.loadingimag_animation))
                .into(holder.imgView);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteClick(values.get(position));
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCardClick(values.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (values != null) ? values.size() : 0;
    }
}
