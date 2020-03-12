package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.FlightItem;
import com.watconsult.tlakapp.model.ItinearyItem;

import java.util.ArrayList;
import java.util.List;

public class ItinearyAdapterss extends ArrayAdapter<ItinearyItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<ItinearyItem> mGridData = new ArrayList<ItinearyItem>();
    String topic;
    String category;
    String createdate;
    String uploadstatus,note,viewUrl;
    public ItinearyAdapterss(Context mContext, int layoutResourceId, ArrayList<ItinearyItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public ItinearyAdapterss(Context applicationContext, int contact_list, ItinearyItem listItem) {
        super(applicationContext,contact_list, (List<ItinearyItem>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<ItinearyItem> mGridData) {
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
            //     holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.itemclick);
          holder.avatarImageView = (ImageView) row.findViewById(R.id.iv_avatar);
            holder.tv_name = (TextView) row.findViewById(R.id.tv_name);
            Typeface custom_font1 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Medium.ttf");
            holder.tv_name.setTypeface(custom_font1);
            holder.welcome_name = (TextView) row.findViewById(R.id.welcome_name);
            Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Medium.ttf");
            holder.welcome_name.setTypeface(custom_font);
            holder.tv_sub1 = (TextView) row.findViewById(R.id.tv_sub1);
            Typeface custom = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.tv_sub1.setTypeface(custom);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ItinearyItem item = mGridData.get(position);
      //  String ItinearyPath = item.getItinearyImagePath()+"/"+item.getItinearyImage();
      //  Picasso.with(mContext).load(ItinearyPath).into(holder.avatarImageView);
        holder.tv_name.setText("" + item.getDayNumber());
        holder.welcome_name.setText("" + item.getDayHeading());
        holder.tv_sub1.setText("" + item.getDescription());
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
    static class ViewHolder {
        LinearLayout linerLayoutss;
        TextView tv_name,welcome_name,tv_sub1;
        ImageView avatarImageView;
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

