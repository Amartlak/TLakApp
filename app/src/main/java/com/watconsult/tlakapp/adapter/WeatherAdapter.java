package com.watconsult.tlakapp.adapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
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
import com.watconsult.tlakapp.model.DayWiseweather;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends ArrayAdapter<DayWiseweather> {
    private Context mContext;
    private int layoutResourceId;
    private List<DayWiseweather> mGridData = new ArrayList<DayWiseweather>();
    String travelDocId;
    String category;
    String createdate;
    String uploadstatus,note,viewUrl;
    public WeatherAdapter(Context mContext, int layoutResourceId, List<DayWiseweather> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public WeatherAdapter(Context applicationContext, int contact_list, DayWiseweather listItem) {
        super(applicationContext,contact_list, (List<DayWiseweather>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<DayWiseweather> mGridData) {
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
            holder.weakday = (TextView) row.findViewById(R.id.weakday);
      //      holder.date.setPaintFlags(holder.date.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            Typeface weakday = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Bold.ttf");
            holder.weakday.setTypeface(weakday);
            holder.date = (TextView) row.findViewById(R.id.date);
            holder.date.setPaintFlags(holder.date.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            Typeface custom_font1 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Bold.ttf");
            holder.date.setTypeface(custom_font1);
            holder.celsiusTempmin = (TextView) row.findViewById(R.id.mintemperature);
            Typeface custom_font3 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Medium.ttf");
            holder.celsiusTempmin.setTypeface(custom_font3);
            holder.celsiusTempmax = (TextView) row.findViewById(R.id.maxtemperature);
            Typeface custom_font7 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Medium.ttf");
            holder.celsiusTempmax.setTypeface(custom_font7);
           /* holder.name = (TextView) row.findViewById(R.id.name);
            Typeface custom_font2 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Medium.ttf");
            holder.name.setTypeface(custom_font2);*/
            holder.wind = (TextView) row.findViewById(R.id.wind);
            Typeface custom_font6 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.wind.setTypeface(custom_font6);
            holder.iconType = (TextView) row.findViewById(R.id.type);
            holder.windtxt = (TextView) row.findViewById(R.id.wind_txt);
            Typeface custom_font5 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Regular.ttf");
            holder.windtxt.setTypeface(custom_font5);
            holder.cloudImageView = (ImageView) row.findViewById(R.id.cloudImg);
            holder.celcius = (TextView) row.findViewById(R.id.cel);
            holder.feranhight = (TextView) row.findViewById(R.id.celaaa);
            holder.feranhight.setTextColor(Color.BLUE);
            holder.celcius1 = (TextView) row.findViewById(R.id.min_cel);
            holder.feranhight1 = (TextView) row.findViewById(R.id.min_cel1);
            holder.feranhight1.setTextColor(Color.BLUE);
           /*
            holder.feranhight1.setTextColor(Color.BLUE);*/
       //    holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.lineaer);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        DayWiseweather item = mGridData.get(position);
        Picasso.with(mContext).load(item.getIcon()).into(holder.cloudImageView);
       /* Calendar cal= Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("d, MMM YYYY");
        String month_name = month_date.format(item.getDate());
        System.out.println("month_name==========="+month_name);*/

       /* DateFormat outputFormat = new SimpleDateFormat("d, MMM YYYY");
       // DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);

        String inputText = mGridData.get(position).getDate();
        Date date = null;
        try {
            date = outputFormat.parse(inputText);
            String outputText = outputFormat.format(date);
            holder.date.setText("" + outputText);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        holder.date.setText("" + item.getDate());
        holder.weakday.setText("" + item.getDay());
      //  holder.name.setText("" + item.getLocationName());
        holder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
        holder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
holder.feranhight.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // holder.celcius.setTextColor(Color.BLUE);
        holder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
        holder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
        holder.celsiusTempmax.setText("" + item.getFahrenheitTemMax());
        holder.celsiusTempmin.setText("" + item.getFahrenheitTemMin());
        holder.celcius.setTextColor(Color.BLUE);
        holder.feranhight.setTextColor(Color.BLACK);
    }
});

        holder.celcius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // holder.celcius.setTextColor(Color.BLUE);
        holder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
        holder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
                holder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
                holder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
                holder.celcius.setTextColor(Color.BLACK);
                holder.feranhight.setTextColor(Color.BLUE);
            }
        });

      //  tempValue.setText((resultemp) + " \u2103");

       String s = item.getWind();

        String str = item.getWind();
        String[] arrOfStr = str.split("@", 2);

        for (String a : arrOfStr)
            System.out.println("a-------------"+a);
     //   System.out.println("a===="+a);

        holder.wind.setText("" + item.getWind());

        holder.iconType.setText("" + item.getIconType());
holder.celcius1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        System.out.println("Hello=========");
        holder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
        holder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
        holder.celsiusTempmax.setText("" + item.getFahrenheitTemMax());
        holder.celsiusTempmin.setText("" + item.getFahrenheitTemMin());
        holder.celcius1.setTextColor(Color.BLACK);
        holder.feranhight1.setTextColor(Color.BLUE);
}
});
holder.feranhight1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        holder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
        holder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
        holder.celsiusTempmax.setText("" + item.getCelsiusTempMax());
        holder.celsiusTempmin.setText("" + item.getCelsiusTempMin());
        holder.celcius1.setTextColor(Color.BLUE);
        holder.feranhight1.setTextColor(Color.BLACK);
    }
});
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
        TextView topic,category,createdDate,uploadStatus,weakday;
        LinearLayout linerLayoutss;
        TextView name,date,day,celsiusTempmin,celsiusTempmax,wind,windtxt,iconType,celcius,feranhight,celcius1,feranhight1;
        ImageView cloudImageView;

    }
}