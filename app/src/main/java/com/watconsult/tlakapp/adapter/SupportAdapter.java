package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
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
import android.widget.Toast;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.CSupportItem;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class SupportAdapter extends ArrayAdapter<CSupportItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<CSupportItem> mGridData = new ArrayList<CSupportItem>();
    String travelDocId;
    String category;
    String createdate;
    String uploadstatus,note,viewUrl;
    public SupportAdapter(Context mContext, int layoutResourceId, ArrayList<CSupportItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public SupportAdapter(Context applicationContext, int contact_list, CSupportItem listItem) {
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
            // holder.circleImageView = (CircleImageView) row.findViewById(R.id.docImg);

            holder.mname = (TextView) row.findViewById(R.id.manager_name);
            Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.mname.setTypeface(custom_font);
            holder.manager = (TextView) row.findViewById(R.id.manager);
            holder.manager.setPaintFlags(holder.manager.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            Typeface custom_fon = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Bold.ttf");
            holder.manager.setTypeface(custom_fon);
            holder.memail = (TextView) row.findViewById(R.id.manager_email);
            Typeface custom_font1 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.memail.setTypeface(custom_font1);
            holder.mphone = (TextView) row.findViewById(R.id.manager_phone);
            Typeface custom_font2 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.mphone.setTypeface(custom_font2);


          /*  holder.tname = (TextView) row.findViewById(R.id.tour_name);
            holder.tlocation = (TextView) row.findViewById(R.id.tour_location);
            holder.tphone = (TextView) row.findViewById(R.id.tour_phone);
            holder.Oname = (TextView) row.findViewById(R.id.operator_name);
            holder.Oemail = (TextView) row.findViewById(R.id.operator_email);
            holder.Ophone = (TextView) row.findViewById(R.id.operator_phone);*/
           // holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.lineaer);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        CSupportItem item = mGridData.get(position);
        holder.mname.setText("" + item.getDepManagerName());
        holder.memail.setText("" + item.getDepManagerEmail());
        holder.memail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + item.getDepManagerEmail()));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "");
                    intent.putExtra(Intent.EXTRA_TEXT, "");
                    mContext.startActivity(intent);
                }catch(ActivityNotFoundException e){
                    //TODO smth
                }
            }
        });
        holder.mphone.setText("" + item.getDepManagerPhone());
        holder.mphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + item.getDepManagerPhone());
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
        TextView mname,memail,mphone,manager;
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