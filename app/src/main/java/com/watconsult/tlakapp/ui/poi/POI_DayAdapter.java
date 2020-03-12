package com.watconsult.tlakapp.ui.poi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.watconsult.tlakapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class POI_DayAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<String> filterGridData = new ArrayList<String>();
    private List<String> mGridData = new ArrayList<String>();
    String travelDocId;
    String category;
    String createdate;
    String uploadstatus,note,viewUrl;
    private Object List;

    public POI_DayAdapter(Context mContext, int layoutResourceId, List<String> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }


    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(List<String> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }
    public ArrayList<String> getFilterGridData() {
        return filterGridData;
    }

    public void setFilterGridData(ArrayList<String> filterGridData) {
        this.filterGridData = filterGridData;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.daynumber = (TextView) row.findViewById(R.id.dayss);
        //    holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.sortday);

          //  holder.location = (TextView) row.findViewById(R.id.citys);
          holder.checkBox = (CheckBox) row.findViewById(R.id.check);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        String item = mGridData.get(position);
        Collections.sort(mGridData);
       // int row = 0;
        for (String mark : mGridData) {
            System.out.println(": soting" + mark);
        }
        holder.daynumber.setText("Day " + item);
        holder.checkBox.setTag(item);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String a = (String) buttonView.getTag();

                System.out.println("data----"+ a);
                if(isChecked){
                    filterGridData.add(a);

                }
                else{
                    if(filterGridData.size()>0){
                        filterGridData.remove(a);
                    }

                }

            }
        });
       /* holder.linerLayoutss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clicked = String.valueOf(v.getTag());
                System.out.println("click-------"+clicked);
              if(v.isClickable()){
                  filterGridData.add(clicked);
              }else{
                  if(filterGridData.size()>0){
                      filterGridData.remove(clicked);
                  }

              }

            }
        });*/
       // holder.location.setText("" + item);
       /* holder.checkBox.setTag(item);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String a = (String) buttonView.getTag();

                System.out.println("data----"+ a);
                if(isChecked){
                    filterGridData.add(a);

                }
                else{
                    if(filterGridData.size()>0){
                        filterGridData.remove(a);
                    }

                }

            }
        });*/
        //    Picasso.with(mContext).load(item.getTypeIcon()).into(holder.circleImageView);
        // holder.docname.setText("" + item.getTravelDocName());



        return row;
    }
    static class ViewHolder {
        TextView location,daynumber,createdDate,uploadStatus;
        LinearLayout linerLayoutss;
        TextView docname;
        CheckBox checkBox;
        CircleImageView circleImageView;
    }
}