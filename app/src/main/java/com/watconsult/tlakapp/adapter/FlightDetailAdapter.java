package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.DocDetailListItem;
import com.watconsult.tlakapp.model.FlightDetailItem;
import com.watconsult.tlakapp.ui.document.DocumentDetailActivity;
import com.watconsult.tlakapp.ui.document.PdfFragment;
import com.watconsult.tlakapp.ui.flights.FlightPdf;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;




public class FlightDetailAdapter extends ArrayAdapter<FlightDetailItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<FlightDetailItem> mGridData = new ArrayList<FlightDetailItem>();
    String travelDocId;
    String documrntPath;
    String createdate;
    String uploadstatus,note,viewUrl;
    public FlightDetailAdapter(Context mContext, int layoutResourceId, ArrayList<FlightDetailItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public FlightDetailAdapter(Context applicationContext, int contact_list, FlightDetailAdapter listItem) {
        super(applicationContext,contact_list, (List<FlightDetailItem>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<FlightDetailItem> mGridData) {
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

            holder.circleImageView = (CircleImageView) row.findViewById(R.id.profile_image);
            holder.name = (TextView) row.findViewById(R.id.name);
            holder.ticketpass = (Button) row.findViewById(R.id.ticketpass);
         //   holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.lineaer);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        FlightDetailItem item = mGridData.get(position);
      //  Picasso.with(mContext).load(item.getTypeIcon()).into(holder.circleImageView);
        holder.name.setText("" + item.getPeopleName());
        String tic =  item.getTicket();
        System.out.println("tic=====dd======"+tic);

        holder.ticketpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String travelDocId = mGridData.get(position).getTicket();
                System.out.println("d=="+travelDocId);//run karo??o
                Intent intent = new Intent(mContext, FlightPdf.class);
                intent.putExtra("ticket", tic);
                mContext.startActivity(intent);
            }
        });

     /*   holder.linerLayoutss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                travelDocId = mGridData.get(position).getTravelDocId();
                documrntPath = mGridData.get(position).getDocumrntPath();


                PdfFragment a2Fragment = new PdfFragment();
                Bundle args = new Bundle();
                args.putString("travelDocId", travelDocId);
                args.putString("documrntPath", documrntPath);
                FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
                a2Fragment.setArguments(args);
                transaction.replace(R.id.doc_detailss, a2Fragment).commit();


            }
        });*/

        return row;
    }
    static class ViewHolder {
        TextView topic,category,createdDate,uploadStatus;
        LinearLayout linerLayoutss;
        TextView name;
        CircleImageView circleImageView;
        WebView webView;
        Button ticketpass;
        View view_showhide;
    }
}