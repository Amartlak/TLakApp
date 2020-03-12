package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.PoiItem;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DateDialogAdpter extends ArrayAdapter<PoiItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<PoiItem> mGridData = new ArrayList<PoiItem>();
    String travelDocId;
    String category;
    String createdate;
    String uploadstatus,note,viewUrl;
    public DateDialogAdpter(Context mContext, int layoutResourceId, ArrayList<PoiItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public DateDialogAdpter(Context applicationContext, int contact_list, PoiItem listItem) {
        super(applicationContext,contact_list, (List<PoiItem>) listItem);
    }
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
          //  holder.location = (TextView) row.findViewById(R.id.citys);
            holder.day = (TextView) row.findViewById(R.id.day);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        PoiItem item = mGridData.get(position);
        //    Picasso.with(mContext).load(item.getTypeIcon()).into(holder.circleImageView);
        holder.location.setText("" + item.getLocationName());
        holder.day.setText("Day " + item.getDayNumber());

        return row;
    }
    static class ViewHolder {
        TextView location,day,createdDate,uploadStatus;
        LinearLayout linerLayoutss;
        TextView docname;
        CircleImageView circleImageView;
    }
}