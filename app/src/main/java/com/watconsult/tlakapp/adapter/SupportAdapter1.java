package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.CSupportItem;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class SupportAdapter1 extends ArrayAdapter<CSupportItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<CSupportItem> mGridData = new ArrayList<CSupportItem>();
    String travelDocId;
    String category;
    String createdate;
    String uploadstatus,note,viewUrl;
    public SupportAdapter1(Context mContext, int layoutResourceId, ArrayList<CSupportItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public SupportAdapter1(Context applicationContext, int contact_list, CSupportItem listItem) {
        super(applicationContext,contact_list, (List<CSupportItem>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<CSupportItem> mGridData) {
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
            holder.guide = (TextView) row.findViewById(R.id.guide);
            Typeface custom_font2 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Bold.ttf");
            holder.guide.setTypeface(custom_font2);
            holder.guide.setPaintFlags(holder.guide.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
           holder.tname = (TextView) row.findViewById(R.id.tour_name);
            Typeface custom_font3 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.tname.setTypeface(custom_font3);
            holder.tlocation = (TextView) row.findViewById(R.id.tour_location);
            Typeface custom_font4 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.tlocation.setTypeface(custom_font4);
            holder.tphone = (TextView) row.findViewById(R.id.tour_phone);
            Typeface custom_font5 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.tphone.setTypeface(custom_font5);
           /*  holder.Oname = (TextView) row.findViewById(R.id.operator_name);
            holder.Oemail = (TextView) row.findViewById(R.id.operator_email);
            holder.Ophone = (TextView) row.findViewById(R.id.operator_phone);*/
            // holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.lineaer);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        CSupportItem item = mGridData.get(position);

        holder.tname.setText("" + item.getDepGuideName());
        holder.tlocation.setText("" + item.getDepGuideLocation());
        holder.tphone.setText("" + item.getDepGuidePhone());
        holder.tphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + item.getDepGuidePhone());
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    mContext.startActivity(i);
                }
                catch (SecurityException s)
                {
                    s.printStackTrace();
                }
            }
        });
       /* holder.tname.setText("" + item.getDepGuideName());
        holder.tlocation.setText("" + item.getDepGuideLocation());
        holder.tphone.setText("" + item.getDepGuidePhone());
        holder.Oname.setText("" + item.getCompanyPersonName());
        holder.Oemail.setText("" + item.getCompanyPersonEmail());
        holder.Ophone.setText("" + item.getCompanyPersonPhone());*/
      /*  holder.city.setText("" + item.getDepartureIata());
        holder.dest_city.setText("" + item.getArrivalIata());
        holder.time.setText("" + item.getTotalTime());
        holder.flighttime.setText("" + item.getDepartureTime());
        holder.flight_dest_time.setText("" + item.getArrivalTime());
        holder.flightno.setText("" + item.getAirlinCode()+" "+item.getFlightNumber());*/
       /*
        holder.createdDate.setText("" + item.getCreatedDate().trim());
        holder.uploadStatus.setText("" + item.getStatus());*/
/*
        holder.linerLayoutss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travelDocId = mGridData.get(position).getTravelDocId();//run karo??o

                DocumentDetailFragment a2Fragment = new DocumentDetailFragment();
                Bundle args = new Bundle();
                args.putString("travelDocId", travelDocId);
                FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
                a2Fragment.setArguments(args);
                transaction.replace(R.id.docss, a2Fragment).commit();
                // now you get url title what is next ???
                //   openDetailActivity(mContext, travelDocId);
            }
        });*/

        return row;
    }
    static class ViewHolder {
        TextView mname,memail,mphone,guide;
        TextView tname,tlocation,tphone;
        TextView Oname,Oemail,Ophone;
        LinearLayout linerLayoutss;
        TextView docname;
        CircleImageView circleImageView;
    }
   /* private void openDetailActivity(Context mContext, String travelDocId)
    {
        enterNextFragment();
    }

      private void enterNextFragment() {
          DocumentDetailFragment a2Fragment = new DocumentDetailFragment();
          FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();

          Bundle args = new Bundle();
          args.putString("travelDocId", travelDocId);
          a2Fragment.setArguments(args);
          transaction.replace(R.id.doc_detail, a2Fragment).commit();

      }*/
}