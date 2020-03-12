package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.FlightItem;
import com.watconsult.tlakapp.model.HotelItem;
import com.watconsult.tlakapp.ui.hotel.HotelDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends ArrayAdapter<HotelItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<HotelItem> mGridData = new ArrayList<HotelItem>();
    String topic;
    String category;
    String createdate;
    String hotelId;
            String uploadstatus,note,viewUrl;
    public HotelAdapter(Context mContext, int layoutResourceId, ArrayList<HotelItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public HotelAdapter(Context applicationContext, int contact_list, FlightItem listItem) {
        super(applicationContext,contact_list, (List<HotelItem>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<HotelItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

@Override
public int getPosition(@Nullable HotelItem item) {
    return super.getPosition(item);
   // int lastpostion =getPosition -1;
}


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            //     holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.itemclick);

           /* holder.city = (TextView) row.findViewById(R.id.city);
            Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.city.setTypeface(custom_font);
            holder.dest_city = (TextView) row.findViewById(R.id.dest_city);
            Typeface custom_font4 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.dest_city.setTypeface(custom_font4);
            holder.time = (TextView) row.findViewById(R.id.timess);
            Typeface custom_font7 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.time.setTypeface(custom_font7);
            holder.flighttime = (TextView) row.findViewById(R.id.flight_time);
            Typeface custom_font1 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Bold.ttf");
            holder.flighttime.setTypeface(custom_font1);
            holder.flight_dest_time = (TextView) row.findViewById(R.id.flight_dest_time);
            Typeface custom_font5 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Bold.ttf");
            holder.flight_dest_time.setTypeface(custom_font5);
            holder.flightno = (TextView) row.findViewById(R.id.flight_no);
            Typeface custom_font2 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.flightno.setTypeface(custom_font2);*/
          /*  holder.category = (TextView) row.findViewById(R.id.category);
            holder.uploadStatus = (TextView) row.findViewById(R.id.status);
            holder.createdDate = (TextView) row.findViewById(R.id.date);*/
            holder.hotelname = (TextView) row.findViewById(R.id.hotel_name);
            Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Bold.ttf");
            holder.hotelname.setTypeface(custom_font);
            holder.location = (TextView) row.findViewById(R.id.hotel_place);
            Typeface custom_font1 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.location.setTypeface(custom_font1);
            holder.hotelImg = (RoundedImageView) row.findViewById(R.id.hImage);
            holder.viewname = (Button) row.findViewById(R.id.viewname);
            holder.cardView = (CardView) row.findViewById(R.id.card);

            mContext = getContext();
            holder.cardView.setCardElevation(getPixelsFromDPs(8));


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        HotelItem item = mGridData.get(position);
        String hotels = item.getHotelId();


        holder.hotelname.setText("" + item.getHotelName()+", "+ item.getLocation());
       holder.location.setText("" + item.getHotelName()+", "+ item.getLocation());
        Picasso.with(mContext).load(item.getHotelImage()).into(holder.hotelImg);
        holder.viewname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotelId = item.getHotelId();
                Intent intent = new Intent(mContext, HotelDetailActivity.class);
                intent.putExtra("hotelId", hotels);
                mContext.startActivity(intent);
              /*  HotelDetailFragment a2Fragment = new HotelDetailFragment();
                Bundle args = new Bundle();
                args.putString("hotelId", hotels);
                FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
                a2Fragment.setArguments(args);
                transaction.replace(R.id.hotalfram, a2Fragment).commit();*/

            }
        });
       /* holder.city.setText("" + item.getDepartureIata());
        holder.dest_city.setText("" + item.getArrivalIata());
        holder.time.setText("" + item.getTotalTime());
        holder.flighttime.setText("" + item.getDepartureTime());
        holder.flight_dest_time.setText("" + item.getArrivalTime());
        holder.flightno.setText("" + item.getAirlinCode()+" "+item.getFlightNumber());*/
       /*
        holder.createdDate.setText("" + item.getCreatedDate().trim());
        holder.uploadStatus.setText("" + item.getStatus());*/

        /*holder.linerLayoutss.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                 topic = mGridData.get(position).getTopic();//run karo??o
                 category = mGridData.get(position).getCategory();
                 createdate = mGridData.get(position).getCreatedDate();//run karo??o
                 uploadstatus = mGridData.get(position).getStatus();
                 note = mGridData.get(position).getNotes();
                 viewUrl = mGridData.get(position).getViewUrl();

                // now you get url title what is next ???
                openDetailActivity(mContext, topic,category);
            }
        });*/

        return row;
    }

    private float getPixelsFromDPs(int i) {
        Resources r = mContext.getResources();
        int  px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, i, r.getDisplayMetrics()));
        return px;
    }

    static class ViewHolder {
        TextView topic,category,createdDate,uploadStatus;
        LinearLayout linerLayoutss;
       TextView hotelname,hotelplace,location;
       RoundedImageView hotelImg;
       Button viewname;
       CardView cardView;
    }
  /*  private void openDetailActivity(Context mContext, String createTime, String createdBy)
    {
        enterNextFragment();
    }*/

     /* private void enterNextFragment() {
          DocListDetail a2Fragment = new DocListDetail();
          FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();

          Bundle args = new Bundle();
          args.putString("topic", topic);
          args.putString("category", category);
          args.putString("createdate", createdate);
          args.putString("uploadstatus", uploadstatus);
          args.putString("note", note);
          args.putString("viewUrl", viewUrl);
          a2Fragment.setArguments(args);
          transaction.replace(R.id.docSec_page, a2Fragment).commit();


      }*/
}

