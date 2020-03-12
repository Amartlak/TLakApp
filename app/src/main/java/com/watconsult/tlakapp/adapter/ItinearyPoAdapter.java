package com.watconsult.tlakapp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.ItinearyPOIItem;

import java.util.ArrayList;
import java.util.List;
public class ItinearyPoAdapter extends RecyclerView.Adapter<ItinearyPoAdapter.MyViewHolder> {
    Context c;
    private List<ItinearyPOIItem> moviesList;
   // ArrayList<ItinearyPOIItem> moviesList;
    Context mContext;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, year, genre;
        RoundedImageView roundedImageView;
        MyViewHolder(View view) {
            super(view);
            roundedImageView = view.findViewById(R.id.poi_images);

        }
    }
    public ItinearyPoAdapter(List<ItinearyPOIItem> moviesList) {
        //this.c = c;
        this.moviesList = moviesList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iti_poi_items, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Context context = holder.roundedImageView.getContext();
        ItinearyPOIItem movie = moviesList.get(position);
        String poiPath = "https://account.tlakapp.com/tlak/images/uploads/poibanner/";
        String poiImg = movie.getPoiImage();
        String poiData = movie.getPoiName();
        System.out.println("poiData-----"+poiData);
        System.out.println("poiImg-----"+poiImg);
        String finalPath = poiPath+poiImg;

        Picasso.with(context).load(movie.getPoiImage()).into(holder.roundedImageView);
       // Picasso.get().load(movie.getPoiImage()).into(holder.roundedImageView);
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}