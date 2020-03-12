package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.watconsult.tlakapp.model.DocListitem;
import com.watconsult.tlakapp.model.WeatherItem;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ArrayAdapter<WeatherItem> {

    private Context mContext;
    private Activity activity;
    private ArrayList<WeatherItem> mGridData;
    private int layoutResourceId;;
/*
    public POI_CityAdapter(Context context, int textViewResourceId,
                       ArrayList<String> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.cityList = values;
    }*/

    public CityAdapter(Context mContext, int layoutResourceId, ArrayList<WeatherItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }
    public CityAdapter(Context applicationContext, int contact_list, WeatherItem listItem) {
        super(applicationContext,contact_list, (List<WeatherItem>) listItem);
    }

/*public POI_CityAdapter(Context context, int textViewResourceId,
                   ArrayList<WeatherItem> values) {
    super(context, textViewResourceId, values);
    this.context = context;
    this.cityList = values;
}*/


   /* public POI_CityAdapter(FragmentActivity activity, int simple_spinner_dropdown_item, ArrayList<String> list) {
        super(activity, simple_spinner_dropdown_item, list);
        this.activity = activity;
        this.cityList = list;
    }*/

    public int getCount(){
        return mGridData.size();
    }

    public WeatherItem getItem(int position){
        return mGridData.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(mContext);
        view.setTextColor(Color.BLACK);
        view.setGravity(Gravity.CENTER);
        view.setText((CharSequence) mGridData.get(position).getLocationName());

        return view;
    }

    //View of Spinner on dropdown Popping

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView view = new TextView(mContext);
        view.setTextColor(Color.BLACK);
        view.setText((CharSequence) mGridData.get(position).getLocationName());
        view.setHeight(160);

        return view;
    }
}