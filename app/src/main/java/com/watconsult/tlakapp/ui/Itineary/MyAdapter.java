package com.watconsult.tlakapp.ui.Itineary;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.ItinearyItem;

import java.util.ArrayList;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {
    private Context mCtx;

    //we are storing all the products in a list
    private List<ItinearyItem> productList = new ArrayList<>();
    public MyAdapter() {
        this.mCtx = mCtx;
        this.productList = productList;
    }



    @NonNull
    @Override
    public MyAdapter.MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item, null);
        return new MyAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyAdapterViewHolder holder, int position) {
        ItinearyItem product = productList.get(position);
        holder.tv_name.setText(product.getDayNumber());
        holder.tv_sub1.setText(product.getDescription());
        holder.welcome_name.setText(product.getDayHeading());
      /*  String img = product.getItinearyImagePath();
        String poiImgpath= product.getItinearyImage();
         String finalImgPath = img+"/"+poiImgpath;
        System.out.println("finalImgPath"+finalImgPath);
        Picasso.with(mCtx).load(finalImgPath).into(holder.avatarImageView);*/

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,welcome_name,tv_sub1,tv_sub2,tv_sub3;
        ImageView avatarImageView;
        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            Typeface custom_font1 = Typeface.createFromAsset(mCtx.getAssets(),  "fonts/Roboto-Medium.ttf");
            tv_name.setTypeface(custom_font1);
            welcome_name = (TextView) itemView.findViewById(R.id.welcome_name);
            Typeface custom_font = Typeface.createFromAsset(mCtx.getAssets(),  "fonts/Roboto-Medium.ttf");
            welcome_name.setTypeface(custom_font);
            tv_sub1 = (TextView) itemView.findViewById(R.id.tv_sub1);
            Typeface custom = Typeface.createFromAsset(mCtx.getAssets(),  "fonts/Roboto-Regular.ttf");
            tv_sub1.setTypeface(custom);
           /*     tv_sub2 = (TextView) itemView.findViewById(R.id.tv_sub2);
            Typeface custom1 = Typeface.createFromAsset(mCtx.getAssets(),  "fonts/Roboto-Regular.ttf");
            tv_sub2.setTypeface(custom1);
            tv_sub3 = (TextView) itemView.findViewById(R.id.tv_sub3);
            Typeface custom2 = Typeface.createFromAsset(mCtx.getAssets(),  "fonts/Roboto-Regular.ttf");
            tv_sub3.setTypeface(custom2);*/
        }
    }
}
