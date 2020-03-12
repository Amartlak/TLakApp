package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.DocListitem;
import com.watconsult.tlakapp.model.PoiItem;
import com.watconsult.tlakapp.ui.document.DocumentDetailFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;




public class CityDialogAdapter extends ArrayAdapter<PoiItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<PoiItem> mGridData = new ArrayList<PoiItem>();
    String travelDocId;
    String category;
    String createdate;
    String uploadstatus,note,viewUrl;
    public CityDialogAdapter(Context mContext, int layoutResourceId, ArrayList<PoiItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

   /* public CityDialogAdapter(Context applicationContext, int contact_list, PoiItem listItem) {
        super(applicationContext,contact_list, (ArrayList<PoiItem>) listItem);
    }*/
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<PoiItem> mGridData) {
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

            holder.location = (TextView) row.findViewById(R.id.city);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        PoiItem item = mGridData.get(position);
        //    Picasso.with(mContext).load(item.getTypeIcon()).into(holder.circleImageView);
        holder.location.setText("" + item.getLocationName());

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
        TextView location,category,createdDate,uploadStatus;
        LinearLayout linerLayoutss;
        TextView docname;
        CircleImageView circleImageView;
    }
}