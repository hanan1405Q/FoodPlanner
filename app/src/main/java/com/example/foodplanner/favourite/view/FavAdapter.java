package com.example.foodplanner.favourite.view;

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
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private final Context context;
    private List<Meal> values;
    private static final String TAG = "FavRecyclerView";
    private FavFragmentOnclick  listener;
    private boolean showDeleteButton;

    public FavAdapter(Context context, List<Meal>values, FavFragmentOnclick listener,Boolean showDeleteButton) {
        this.context = context;
        // Initialize with an empty list if `values` is null
        this.values = (values != null) ? values : new ArrayList<>();

        this.listener=listener;
        this.showDeleteButton=showDeleteButton;
    }

    public void setValues(List<Meal> values)
    {

        this.values = (values != null) ? values : new ArrayList<>();
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView  cardView;
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
            // Show or hide the delete button based on the parameter
            btnDelete.setVisibility(showDeleteButton ? View.VISIBLE : View.GONE);

        }

    }

    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.row_favmealcard,recycler,false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: ");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {
        holder.txtMealName.setText(values.get(position).getName());
        holder.txtMealCountry.setText(values.get(position).getArea());
        Glide.with(context).load(values.get(position).getImgSource()).apply(new RequestOptions().override(150,150)
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
