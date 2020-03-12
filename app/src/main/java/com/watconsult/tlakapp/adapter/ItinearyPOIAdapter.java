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

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.DocListitem;
import com.watconsult.tlakapp.model.ItinearyPOIItem;
import com.watconsult.tlakapp.ui.document.DocumentDetailFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


import android.app.Activity;
        import android.content.Context;
        import android.graphics.Typeface;
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
        import com.watconsult.tlakapp.ui.document.DocumentDetailFragment;
        import java.util.ArrayList;
        import java.util.List;

        import de.hdodenhof.circleimageview.CircleImageView;

public class ItinearyPOIAdapter extends ArrayAdapter<ItinearyPOIItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<ItinearyPOIItem> mGridData = new ArrayList<ItinearyPOIItem>();
    String travelDocId;
    String category;
    String createdate;
    String uploadstatus,note,viewUrl;
    public ItinearyPOIAdapter(Context mContext, int layoutResourceId, ArrayList<ItinearyPOIItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public ItinearyPOIAdapter(Context applicationContext, int contact_list, ItinearyPOIItem listItem) {
        super(applicationContext,contact_list, (List<ItinearyPOIItem>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<ItinearyPOIItem> mGridData) {
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

            holder.roundedImageView = (RoundedImageView) row.findViewById(R.id.poi_images);
            holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.lineaer);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ItinearyPOIItem item = mGridData.get(position);
        Picasso.with(mContext).load(item.getPoiImage()).into(holder.roundedImageView);
     //   holder.docname.setText("" + item.getTravelDocName());
       /* holder.linerLayoutss.setOnClickListener(new View.OnClickListener() {
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
        TextView topic,category,createdDate,uploadStatus;
        LinearLayout linerLayoutss;
        TextView docname;
        RoundedImageView roundedImageView;

    }
}