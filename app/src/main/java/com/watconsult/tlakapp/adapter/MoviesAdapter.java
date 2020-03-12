/*
package com.watconsult.tlakapp.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.ItinearyItem;
import com.watconsult.tlakapp.ui.Itineary.ItinearyFragments;

import java.util.List;

import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.OnSwipeListener;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<ItinearyItem> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        CardItemTouchHelperCallback cardCallback;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.welcome_name);
            genre = (TextView) view.findViewById(R.id.tv_sub1);

        }
    }


    public MoviesAdapter(List<ItinearyItem> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ItinearyItem movie = moviesList.get(position);

        holder.cardCallback.setOnSwipedListener(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                viewHolder = (MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);

            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                myHolder = (MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
            }

            @Override
            public void onSwipedClear() {

            }
        });


        holder.title.setText(movie.getDayHeading());
        holder.genre.setText(movie.getDescription());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}*/
