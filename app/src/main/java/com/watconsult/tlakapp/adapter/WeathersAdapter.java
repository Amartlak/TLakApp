package com.watconsult.tlakapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.DocListitem;
import com.watconsult.tlakapp.model.WeatherItem;

import java.util.ArrayList;
import java.util.List;

public class WeathersAdapter extends BaseAdapter implements Filterable {
    private LayoutInflater mLayoutInflater;
    private List<WeatherItem> addressList;
    private List<WeatherItem> addressFilterList;
    private WeathersAdapter.WeatherFilter addressFilter;
    private Context context;




    public WeathersAdapter(Context context, List data){
        addressList = data;
        addressFilterList=data;
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public WeatherItem getItem(int position) {
        return addressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View updateView;
        final ViewHolder viewHolder;
        if (convertView == null) {
            updateView = mLayoutInflater.inflate(R.layout.weather_layout_item, null);
            viewHolder = new WeathersAdapter.ViewHolder();

          /*  viewHolder.tvName = (TextView) updateView.findViewById(R.id.nameTV);
            viewHolder.tvArea = (TextView) updateView.findViewById(R.id.areaTV);
            viewHolder.tvStreet = (TextView) updateView.findViewById(R.id.streetTv);*/


            viewHolder.date = (TextView) updateView.findViewById(R.id.date);
            viewHolder.date.setPaintFlags(viewHolder.date.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            Typeface custom_font1 = Typeface.createFromAsset(context.getAssets(),  "fonts/Roboto-Bold.ttf");
            viewHolder.date.setTypeface(custom_font1);
            viewHolder.celsiusTempmin = (TextView) updateView.findViewById(R.id.mintemperature);
            Typeface custom_font3 = Typeface.createFromAsset(context.getAssets(),  "fonts/Roboto-Medium.ttf");
            viewHolder.celsiusTempmin.setTypeface(custom_font3);
            viewHolder.celsiusTempmax = (TextView) updateView.findViewById(R.id.maxtemperature);
            Typeface custom_font7 = Typeface.createFromAsset(context.getAssets(),  "fonts/Roboto-Medium.ttf");
            viewHolder.celsiusTempmax.setTypeface(custom_font7);
            viewHolder.name = (TextView) updateView.findViewById(R.id.name);
            Typeface custom_font2 = Typeface.createFromAsset(context.getAssets(),  "fonts/Roboto-Medium.ttf");
            viewHolder.name.setTypeface(custom_font2);
            viewHolder.wind = (TextView) updateView.findViewById(R.id.wind);
            Typeface custom_font6 = Typeface.createFromAsset(context.getAssets(),  "fonts/Roboto-Regular.ttf");
            viewHolder.wind.setTypeface(custom_font6);
            viewHolder.iconType = (TextView) updateView.findViewById(R.id.type);
            viewHolder.windtxt = (TextView) updateView.findViewById(R.id.wind_txt);
            Typeface custom_font5 = Typeface.createFromAsset(context.getAssets(),  "fonts/Roboto-Regular.ttf");
            viewHolder.windtxt.setTypeface(custom_font5);
            viewHolder.cloudImageView = (ImageView) updateView.findViewById(R.id.cloudImg);
            viewHolder.celcius = (TextView) updateView.findViewById(R.id.cel);
            viewHolder.feranhight = (TextView) updateView.findViewById(R.id.celaaa);
            viewHolder.feranhight.setTextColor(Color.BLUE);
            viewHolder.celcius1 = (TextView) updateView.findViewById(R.id.min_cel);
            viewHolder.feranhight1 = (TextView) updateView.findViewById(R.id.min_cel1);
            viewHolder.feranhight1.setTextColor(Color.BLUE);


            updateView.setTag(viewHolder);

        } else {
            updateView = convertView;
            viewHolder = (WeathersAdapter.ViewHolder) updateView.getTag();
        }

        final WeatherItem item = getItem(position);

      /*  viewHolder.tvName.setText(item.getBuildingName());
        viewHolder.tvArea.setText(item.getArea());
        viewHolder.tvStreet.setText(String.valueOf(item.getStreet()));*/
        Picasso.with(context).load(item.getIcon()).into(viewHolder.cloudImageView);
        viewHolder.date.setText("" + item.getDate()+", "+item.getDay());
        viewHolder.name.setText("" + item.getLocationName());
        viewHolder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
        viewHolder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
        viewHolder.feranhight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // holder.celcius.setTextColor(Color.BLUE);
                viewHolder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
                viewHolder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
                viewHolder.celsiusTempmax.setText("" + item.getFahrenheitTemMax());
                viewHolder.celsiusTempmin.setText("" + item.getFahrenheitTemMin());
                viewHolder.celcius.setTextColor(Color.BLUE);
                viewHolder.feranhight.setTextColor(Color.BLACK);
            }
        });

        viewHolder.celcius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // holder.celcius.setTextColor(Color.BLUE);
                viewHolder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
                viewHolder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
                viewHolder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
                viewHolder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
                viewHolder.celcius.setTextColor(Color.BLACK);
                viewHolder.feranhight.setTextColor(Color.BLUE);
            }
        });

        //  tempValue.setText((resultemp) + " \u2103");
        viewHolder.wind.setText("" + item.getWind());
        viewHolder.iconType.setText("" + item.getIconType());
        viewHolder.celcius1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello=========");
                viewHolder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
                viewHolder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
                viewHolder.celsiusTempmax.setText("" + item.getFahrenheitTemMax());
                viewHolder.celsiusTempmin.setText("" + item.getFahrenheitTemMin());
                viewHolder.celcius1.setTextColor(Color.BLACK);
                viewHolder.feranhight1.setTextColor(Color.BLUE);
            }
        });
        viewHolder.feranhight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
                viewHolder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
                viewHolder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
                viewHolder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
                viewHolder.celcius1.setTextColor(Color.BLUE);
                viewHolder.feranhight1.setTextColor(Color.BLACK);
            }
        });
        return updateView;
    }

    @Override
    public Filter getFilter() {
        if (addressFilter == null) {
            addressFilter = new WeathersAdapter.WeatherFilter();
        }
        return addressFilter;
    }
    static class ViewHolder{
        TextView tvName;
        TextView tvArea;
        TextView tvStreet;
        TextView name,date,day,celsiusTempmin,celsiusTempmax,wind,windtxt,iconType,celcius,feranhight,celcius1,feranhight1;
        ImageView cloudImageView;
    }
    private class WeatherFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

//below checks the match for the cityId and adds to the filterlist
          //  long cityId= Long.parseLong(constraint.toString());
            String locationName = constraint.toString();
            FilterResults results = new FilterResults();

            if (locationName == null) {
                ArrayList<WeatherItem> filterList = new ArrayList<WeatherItem>();
                for (int i = 0; i < addressFilterList.size(); i++) {

                    if ( (addressFilterList.get(i).getLocationName().equals(locationName) )) {

                        WeatherItem address = addressFilterList.get(i);
                        filterList.add(address);
                    }
                }

                results.count = filterList.size();
                results.values = filterList;

            } else {

                results.count = addressFilterList.size();
                results.values = addressFilterList;

            }
            return results;
        }
        //Publishes the matches found, i.e., the selected cityids
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            addressList = (ArrayList<WeatherItem>)results.values;
            notifyDataSetChanged();
        }
    }
}
