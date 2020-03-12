package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.FlightItem;
import com.watconsult.tlakapp.ui.flights.FlightDetailActivty;

import java.util.ArrayList;
import java.util.List;

public class FlightAdapter extends ArrayAdapter<FlightItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<FlightItem> mGridData = new ArrayList<FlightItem>();
    String topic;
    String flightId;
    String createdate;
    String uploadstatus,note,viewUrl;
    public FlightAdapter(Context mContext, int layoutResourceId, ArrayList<FlightItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public FlightAdapter(Context applicationContext, int contact_list, FlightItem listItem) {
        super(applicationContext,contact_list, (List<FlightItem>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<FlightItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
         holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.link);

            holder.city = (TextView) row.findViewById(R.id.city);
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
            holder.flightno.setTypeface(custom_font2);
          /*  holder.category = (TextView) row.findViewById(R.id.category);
            holder.uploadStatus = (TextView) row.findViewById(R.id.status);
            holder.createdDate = (TextView) row.findViewById(R.id.date);*/
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        FlightItem item = mGridData.get(position);
        holder.city.setText("" + item.getDepartureIata());
        holder.dest_city.setText("" + item.getArrivalIata());
        holder.time.setText("" + item.getTotalTime());
        holder.flighttime.setText("" + item.getDepartureTime());
        holder.flight_dest_time.setText("" + item.getArrivalTime());
        holder.flightno.setText("" + item.getAirlinCode()+" "+item.getFlightNumber());
       /*
        holder.createdDate.setText("" + item.getCreatedDate().trim());
        holder.uploadStatus.setText("" + item.getStatus());*/

        holder.linerLayoutss.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                flightId = mGridData.get(position).getFlightId();//run karo??o
                Intent intent = new Intent(mContext, FlightDetailActivty.class);
                intent.putExtra("flightId", flightId);
                mContext.startActivity(intent);
              /*  FlightDetail_Fragment a2Fragment = new FlightDetail_Fragment();
                Bundle args = new Bundle();
                args.putString("flightId", flightId);
                FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
                a2Fragment.setArguments(args);
                transaction.replace(R.id.flight, a2Fragment).commit();*/

                // now you get url title what is next ???
                //openDetailActivity(mContext, topic,category);
            }
        });

        return row;
    }
    static class ViewHolder {
        TextView topic,category,createdDate,uploadStatus;
        LinearLayout linerLayoutss;

        TextView city,flighttime,flightno,flightinfo,dest_city,flight_dest_time,price,time,nonstop;
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

