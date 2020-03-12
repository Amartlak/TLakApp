package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.github.barteksc.pdfviewer.PDFView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.FlightItem;
import com.watconsult.tlakapp.model.HotelDetailItem;
import com.watconsult.tlakapp.ui.hotel.HotelPdfViewActivty;

import java.util.ArrayList;
import java.util.List;

public class HotelDetailAdapter extends ArrayAdapter<HotelDetailItem>  {
    private static final Object TAG = "";
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<HotelDetailItem> mGridData = new ArrayList<HotelDetailItem>();
    String topic;
    String category;
    String createdate;
    String hotelId;
    String pdfFileName;
    PDFView pdfView;
    Integer pageNumber = 0;
    public static String SAMPLE_FILE = "";

    String uploadstatus,note,viewUrl;
    public HotelDetailAdapter(Context mContext, int layoutResourceId, ArrayList<HotelDetailItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public HotelDetailAdapter(Context applicationContext, int contact_list, FlightItem listItem) {
        super(applicationContext,contact_list, (List<HotelDetailItem>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<HotelDetailItem> mGridData) {
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

            holder.people_name = (TextView) row.findViewById(R.id.name);
            Typeface custom_font3 = Typeface.createFromAsset(mContext.getAssets(),  "fonts/Roboto-Medium.ttf");
            holder.people_name.setTypeface(custom_font3);
            holder.ticket = (Button) row.findViewById(R.id.ticketpass);
           /* holder.web_view = (WebView) row.findViewById(R.id.web_view);
            holder.web_view.requestFocus();
            holder.web_view.getSettings().setJavaScriptEnabled(true);*/

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        HotelDetailItem item = mGridData.get(position);
        String hotels = item.getHotelId();
        //  System.out.println("hotel======="+hotels);
        holder.people_name.setText("" + item.getPeopleName());
        String pdffile = item.getHotelPass();
        System.out.println("pdffile======="+pdffile);
        SAMPLE_FILE = pdffile;
               holder.ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String hotelPass = mGridData.get(position).getHotelPass();
                Intent intent = new Intent(mContext, HotelPdfViewActivty.class);
                intent.putExtra("hotelPass", hotelPass);
                mContext.startActivity(intent);
               /* HotelPdfViewFragment a2Fragment = new HotelPdfViewFragment();
                Bundle args = new Bundle();
                args.putString("hotelPass", hotelPass);

                FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
                a2Fragment.setArguments(args);
                transaction.replace(R.id.hotels, a2Fragment).commit();*/

            }
        });
        return row;
    }
    private float getPixelsFromDPs(int i) {
        Resources r = mContext.getResources();
        int  px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, i, r.getDisplayMetrics()));
        return px;
    }

    static class ViewHolder {
        TextView people_name;
        LinearLayout linerLayoutss;
        TextView hotelname,hotelplace,location;
        RoundedImageView hotelImg;
        Button ticket;
        CardView cardView;
        WebView web_view;
        String pdfFileName;
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


