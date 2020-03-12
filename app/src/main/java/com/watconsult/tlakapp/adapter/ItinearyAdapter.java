package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.ItinearyItem;
import com.watconsult.tlakapp.model.PoiItem;
import com.watconsult.tlakapp.ui.Itineary.ItnearyFragmentDetail;
import com.watconsult.tlakapp.ui.Itineary.RoundImageView;

import java.util.ArrayList;




public class ItinearyAdapter extends ArrayAdapter<ItinearyItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<ItinearyItem> mGridData = new ArrayList<ItinearyItem>();
    String ques;
    public ItinearyAdapter(Context mContext, int layoutResourceId, ArrayList<ItinearyItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }


    public void setGridData(ArrayList<ItinearyItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }
   /* public void setdata(String ques)
    {
        this.ques = ques;

    }*/
    /*@Override
        public int getCount() {
            return 6;
        }
   */
    //  public void setdata(String ques)
    {
        //  this.checkvalue = ques;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            //   holder.relativeLayout = (RelativeLayout) row.findViewById(R.id.clicks);

           /* holder.question = (TextView) row.findViewById(R.id.question);
            holder.reply = (TextView) row.findViewById(R.id.reply);
            holder.timeStr = (TextView) row.findViewById(R.id.timestr);*/
            holder.tv_name = (TextView) row.findViewById(R.id.tv_name);

            Typeface custom_font1 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Medium.ttf");
            holder.tv_name.setTypeface(custom_font1);
            holder.welcome_name = (TextView) row.findViewById(R.id.welcome_name);
            Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Medium.ttf");
            holder.welcome_name.setTypeface(custom_font);
            holder.tv_sub1 = (TextView) row.findViewById(R.id.tv_sub1);
            Typeface custom = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.tv_sub1.setTypeface(custom);
            holder.more = (Button) row.findViewById(R.id.more);
           /* holder.tv_sub2 = (TextView) row.findViewById(R.id.tv_sub2);
            Typeface custom1 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.tv_sub2.setTypeface(custom1);
            holder.tv_sub3 = (TextView) row.findViewById(R.id.tv_sub3);
            Typeface custom2 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.tv_sub3.setTypeface(custom2);*/


           holder.imageView =(RoundImageView) row.findViewById(R.id.iv_avatar);

        } else {
            holder = (ViewHolder) row.getTag();
        }
        ItinearyItem item = mGridData.get(position);
        holder.welcome_name.setText("" + item.getDayHeading());
        holder.tv_sub1.setText("" + item.getDescription());


        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItnearyFragmentDetail a2Fragment = new ItnearyFragmentDetail();
               // FragmentTransaction transaction = mContext.get
                FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.changeframe, a2Fragment).commit();
            }
        });
//        holder.titleTextView.setText(Html.fromHtml(item.getTitle()));
        // holder.question.setText("" + item.getQuestion());
       /* holder.poi_sub_name.setText("" + item.getLocationName());
        holder.poi_detail.setText("" + item.getPoiDescription());
        String poiImg = poiimage;
        System.out.println("poiImg--"+poiImg);
        String poiImgpath= item.getPoiImage();
        String finalImgPath = poiImg+"/"+poiImgpath;
        System.out.println("finalImgPath--"+finalImgPath);
        Picasso.with(mContext).load(finalImgPath).into(holder.imageView);*/
      /*  holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String createdBy = mGridData.get(position).getCreatedBy();//run karo??o
                String createTime = mGridData.get(position).getCreateTime();
                // now you get url title what is next ???
                openDetailActivity(mContext, createdBy, createTime);
        });*/
        return row;
    }
    static class ViewHolder {
        TextView question, timeStr, reply;
       // TextView km, open, text, hour_text, hour, length_text, length, direction, kms, poi_detail,name,poi_sub_name;
        RelativeLayout relativeLayout;
        TextView tv_name,welcome_name,tv_sub1,tv_sub2,tv_sub3;
        RoundImageView imageView;
        Button more;
    }

  /*  private void openDetailActivity(Context mContext, String createTime, String createdBy) {
        Intent intent = new Intent(mContext, ContactCounter.class);
        intent.putExtra("createTime", createTime);
        intent.putExtra("createdBy", createdBy);
        mContext.startActivity(intent);
    }*/
}
