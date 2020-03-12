package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.DocListitem;
import com.watconsult.tlakapp.model.OptionalTourItem;
import com.watconsult.tlakapp.ui.document.DocumentDetailFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;




public class UpcomingAdapter extends ArrayAdapter<OptionalTourItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<OptionalTourItem> mGridData = new ArrayList<OptionalTourItem>();
    String travelDocId;
    String category;
    String createdate;
    String uploadstatus,note,viewUrl;
    public UpcomingAdapter(Context mContext, int layoutResourceId, ArrayList<OptionalTourItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public UpcomingAdapter(Context applicationContext, int contact_list, OptionalTourItem listItem) {
        super(applicationContext,contact_list, (List<OptionalTourItem>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<OptionalTourItem> mGridData) {
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
            holder.name = (TextView) row.findViewById(R.id.name);
            holder.promoContent = (TextView) row.findViewById(R.id.promoContent);
            holder.name = (TextView) row.findViewById(R.id.description);
            holder.optionalImage = (ImageView) row.findViewById(R.id.img);

     //       holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.lineaer);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        OptionalTourItem item = mGridData.get(position);
        holder.name.setText("" + item.getOptionalDepartureName());
        holder.promoContent.setText("" + item.getPromoContent());
        holder.description.setText("" + item.getDescription());
        Picasso.with(mContext).load(item.getOptionalDepartureImage()).into(holder.optionalImage);
        //    Picasso.with(mContext).load(item.getTypeIcon()).into(holder.circleImageView);
      //  holder.docname.setText("" + item.getTravelDocName());

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
        TextView name,promoContent,description,uploadStatus;
        LinearLayout linerLayoutss;
        TextView docname;
        ImageView optionalImage;
    }
}