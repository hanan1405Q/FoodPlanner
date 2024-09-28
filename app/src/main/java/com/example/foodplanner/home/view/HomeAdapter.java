package com.example.foodplanner.home.view;

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
import com.example.foodplanner.model.Category;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    private final Context context;
    private List<Category> values;
    //private OnFavouriteClickListener listener;
    private static final String TAG="HomeRecyclerView";

    public  void setData(List<Category> categories)
    {
        this.values=categories;
    }
    //Provide a suitable Constructor
    public HomeAdapter(Context context, List<Category>  categories)
    {
        this.context=context;
        values=categories;
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        //should describe the row layout
        public  TextView txtTitle;
        public  TextView txtCategory;
        public ImageView imgView;
        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(View v)
        {
            super(v);
            layout =v;
            txtTitle=v.findViewById(R.id.txtname);
            //txtCategory=v.findViewById(R.id.txtCategory);
            imgView=v.findViewById(R.id.imgSource);
           // constraintLayout=v.findViewById(R.id.homeFrage);

        }


    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        //create a new view
        LayoutInflater inflater=LayoutInflater.from(recycler.getContext());
        View v=inflater.inflate(R.layout.row_category,recycler,false);
        ViewHolder vh=new ViewHolder(v);
        Log.i(TAG, "****** onCreateViewHolder ***********");
        return vh;

    }

    //replace the contents of the view (invoked by Layout Manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtTitle.setText(values.get(position).getName());
       // holder.txtCategory.setText(values.get(position).getStrCategory());
        Glide.with(context).load(values.get(position).getImgSource()).apply(new RequestOptions().override(150,150)
                        .placeholder(R.drawable.loadingimag_animation))
                .into(holder.imgView);

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
        Log.i(TAG, "*********** onBindViewHolder ********** ");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
