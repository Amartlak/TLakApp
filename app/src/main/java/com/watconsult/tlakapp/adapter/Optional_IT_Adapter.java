package com.watconsult.tlakapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.ItinearyPOIItem;
import com.watconsult.tlakapp.model.Optional_IT_Item;

import java.util.List;


public class Optional_IT_Adapter extends RecyclerView.Adapter<Optional_IT_Adapter.MyViewHolder> {
    private List<Optional_IT_Item> moviesList;
    Context mContext;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, year, genre;
        RoundedImageView roundedImageView;
      //  ImageView roundedImageView;
        MyViewHolder(View view) {
            super(view);
            roundedImageView = view.findViewById(R.id.poi_img);
            roundedImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            roundedImageView.setAdjustViewBounds(true);

        }
    }
    public Optional_IT_Adapter(List<Optional_IT_Item> moviesList) {
        this.moviesList = moviesList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iti_optional_item, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Context context = holder.roundedImageView.getContext();
        Optional_IT_Item movie = moviesList.get(position);
        Picasso.with(context).load(movie.getPoiImage()).into(holder.roundedImageView);

    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}