package com.watconsult.tlakapp.ui.poi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.maps.model.LatLng;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.model.PoiItem;
import com.watconsult.tlakapp.ui.map.MapFragment;

import java.util.ArrayList;


import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import androidx.appcompat.app.AlertDialog;
        import androidx.fragment.app.FragmentActivity;
        import androidx.fragment.app.FragmentTransaction;

        import com.github.chrisbanes.photoview.PhotoView;
        import com.google.android.gms.maps.model.LatLng;
        import com.makeramen.roundedimageview.RoundedImageView;
        import com.squareup.picasso.Picasso;
        import com.watconsult.tlakapp.R;
        import com.watconsult.tlakapp.SharedPrefs;
        import com.watconsult.tlakapp.model.PoiItem;
        import com.watconsult.tlakapp.ui.map.MapFragment;
        import com.watconsult.tlakapp.ui.poi.POIDetailActivity;

        import java.util.ArrayList;
import java.util.List;


public class NewFilterAdpter extends ArrayAdapter<Pous> {
    private Context mContext;
    private int layoutResourceId;
    String finalpath,secondfullpath,images;
    String path,sendPath,poipath,poiImg;
    private List<Pous> mGridData = new ArrayList<Pous>();
    String ques;
    public NewFilterAdpter(Context mContext, int layoutResourceId, List<Pous> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }


    public void setGridData(ArrayList<Pous> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }
    public void setdata(String ques)
    {
        this.ques = ques;

    }
    /*@Override
        public int getCount() {
            return 6;
        }
   */
    //  public void setdata(String ques)
    {
        //  this.checkvalue = ques;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.viewmap = (LinearLayout) row.findViewById(R.id.one);
            holder.poiDetail = (RoundedImageView) row.findViewById(R.id.grid_poi);
            holder.distance = (LinearLayout) row.findViewById(R.id.two);
            holder.zoom = (ImageView) row.findViewById(R.id.zoom);
           /* holder.question = (TextView) row.findViewById(R.id.question);
            holder.reply = (TextView) row.findViewById(R.id.reply);
            holder.timeStr = (TextView) row.findViewById(R.id.timestr);*/
            holder.km = row.findViewById(R.id.kilometer);
            Typeface custom_fonts = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Medium.ttf");
            holder.km.setTypeface(custom_fonts);
            holder.name = row.findViewById(R.id.poi_address);
            Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Medium.ttf");
            holder.name.setTypeface(custom_font);
            holder.poi_sub_name = row.findViewById(R.id.poi_sub_text);
            Typeface custom_font2 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
            holder.poi_sub_name.setTypeface(custom_font2);


            holder.poi_type_txt = row.findViewById(R.id.poitype_text);
            Typeface custom_font3 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
            holder.poi_type_txt.setTypeface(custom_font3);
            holder.poitype = row.findViewById(R.id.poitype);
           /* holder.poi_sub_name = row.findViewById(R.id.poi_sub_text);
            Typeface custom_font2 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
            holder.poi_sub_name.setTypeface(custom_font2);

            holder.kms = row.findViewById(R.id.kms);
            Typeface custom1 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
            holder.kms.setTypeface(custom1);
            holder.direction = row.findViewById(R.id.direction);
            Typeface custom2 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
            holder.direction.setTypeface(custom2);
            holder.length = row.findViewById(R.id.length);
            Typeface custom3 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Light.ttf");
            holder.length.setTypeface(custom3);
            holder.length_text = row.findViewById(R.id.length_text);
            Typeface custom4 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
            holder.length_text.setTypeface(custom4);
            holder.hour_text = row.findViewById(R.id.hour_text);
            Typeface custom5 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
            holder.hour_text.setTypeface(custom5);
            holder.hour = row.findViewById(R.id.hour);
            Typeface custom6 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Light.ttf");
            holder.hour.setTypeface(custom6);
            holder.poi_detail = row.findViewById(R.id.poi_detail);
            Typeface custom7 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
            holder.poi_detail.setTypeface(custom7);*/
            holder.imageView = (RoundedImageView) row.findViewById(R.id.grid_poi);
         /*   Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nature);
            RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            mDrawable.setCircular(true);*/
            //   mIcon.setImageDrawable(mDrawable);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        Pous item = mGridData.get(position);

        Double Lati = Double.valueOf(item.getLatitude());
        System.out.println("Lati"+Lati);
        Double Longitude = Double.valueOf(item.getLongitude());
        System.out.println("Longitude"+Longitude);
//        holder.titleTextView.setText(Html.fromHtml(item.getTitle()));
        SharedPrefs shareprefs = new SharedPrefs(mContext);
       /* Double lat = Double.valueOf(shareprefs.getLat());
        Double longs = Double.valueOf(shareprefs.getLongi());*/
        // LatLng from = new LatLng(lat,longs);
        LatLng to = new LatLng(Lati,Longitude);

        //Calculating the distance in meters
        //Double distance = SphericalUtil.computeDistanceBetween(from, to);
        //System.out.println("distance=="+distance);
        //  Double distances = SphericalUtil.
        // Double kilometer = distance/ 1000;
        //  DecimalFormat df = new DecimalFormat("#.#");
        // String formatted = df.format(kilometer);
        //  System.out.println("formatted=========="+formatted);
        //  holder.km.setText("" + formatted +" km");
        holder.poi_type_txt.setText(""+ item.getPoiType());
        //holder.km.setText("" + customkilometer +" km");
        //  holder.km.setText(new DecimalFormat("##.##").format(customkilometer)+" km");
        holder.name.setText("" + item.getLocationName()+" "+item.getCountryName());
        //   holder.poi_sub_name.setText(0,+Math.min( item.getPoiName().length(),10));
        String str = item.getPoiName().substring(0, Math.min(item.getPoiName().length(), 20));
        holder.poi_sub_name.setText(""+ str.trim());
        path = "https://account.tlakapp.com/tlak/images/uploads/poibanner/";
        poipath = "https://account.tlakapp.com/tlak/images/uploads/poiicons/";
        finalpath = path + item.getPoiImage();
        Picasso.with(mContext).load(finalpath).into(holder.imageView);
        poiImg = poipath + item.getTypeIcon();
        Picasso.with(mContext).load(poiImg).into(holder.poitype);
        holder.poiDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendImg = mGridData.get(position).getPoiImage();
                sendPath = path + sendImg;
                Intent intent = new Intent(mContext, POIDetailActivity.class);
                intent.putExtra("sendPath", sendPath);
                intent.putExtra("poiName", item.getPoiName());
                intent.putExtra("locationName", item.getLocationName());
                intent.putExtra("countryName", item.getCountryName());
                intent.putExtra("poiDescription", item.getPoiDescription());
                intent.putExtra("typeIcon", item.getTypeIcon());
                mContext.startActivity(intent);
                /*POIDetail_Fragment a2Fragment = new POIDetail_Fragment();
                Bundle args = new Bundle();
                args.putString("sendPath", sendPath);
                args.putString("poiName", item.getPoiName());
                args.putString("locationName", item.getLocationName());
                args.putString("countryName", item.getCountryName());
                args.putString("poiDescription",item.getPoiDescription());
                args.putString("typeIcon",item.getTypeIcon());
                FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                a2Fragment.setArguments(args);
                transaction.replace(R.id.mapview, a2Fragment).commit();*/
            }
        });

        holder.viewmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lati = mGridData.get(position).getLatitude();//run karo??o
                String longi = mGridData.get(position).getLongitude();
                String poiName = mGridData.get(position).getPoiName();
                // now you get url title what is next ???
                //   openDetailActivity(mContext, lati, longi,poiName);
                String strUri = "http://maps.google.com/maps?saddr="+shareprefs.getLat()+","+shareprefs.getLongi()+"&daddr="+lati+","+longi;
                //  String strUri= "http://maps.google.com/maps?q=loc:" + lati + "," + longi + " (" + "Akshardham" + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                mContext.startActivity(intent);
            }
        });

        holder.zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images = mGridData.get(position).getPoiImage();
                secondfullpath = path + images;
                System.out.println("secondfullpath===="+secondfullpath);
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
                View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                holder.photoView = (PhotoView) view.findViewById(R.id.zoomView);
                ImageView cancel = (ImageView) view.findViewById(R.id.ic_cancel);
                Picasso.with(mContext).load(secondfullpath).into(holder.photoView);
                builder.setView(view);
                AlertDialog mDialog = builder.create();
                mDialog.setCancelable(false);
                mDialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
            }
        });
        return row;
    }

    private void openDetailActivity(Context mContext, String lati, String longi,String poiName) {
        MapFragment a2Fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString("latitude", lati);
        args.putString("longitude", longi);
        args.putString("poiName",poiName);
        FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        a2Fragment.setArguments(args);
        transaction.replace(R.id.mapview, a2Fragment).commit();
    }

    static class ViewHolder {
        TextView question, timeStr, reply;
        TextView km, open, text, hour_text, hour, length_text, length, direction, kms, poi_type_txt,name,poi_sub_name;
        LinearLayout directions,distance,viewmap,museum;
        RoundedImageView imageView,poiDetail;
        PhotoView photoView;
        ImageView zoom,cancel,poitype;

    }
}