package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.DocDetailListItem;
import com.watconsult.tlakapp.ui.document.CSVActivity;
import com.watconsult.tlakapp.ui.document.PDFActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DocDetailAdapter extends ArrayAdapter<DocDetailListItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<DocDetailListItem> mGridData = new ArrayList<DocDetailListItem>();
    String travelDocId;
    String documrntPath;
    String createdate;
    String uploadstatus,note,viewUrl;
    public DocDetailAdapter(Context mContext, int layoutResourceId, ArrayList<DocDetailListItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public DocDetailAdapter(Context applicationContext, int contact_list, DocDetailAdapter listItem) {
        super(applicationContext,contact_list, (List<DocDetailListItem>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<DocDetailListItem> mGridData) {
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

             holder.circleImageView = (CircleImageView) row.findViewById(R.id.docImg);

            holder.docname = (TextView) row.findViewById(R.id.docname);
            holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.lineaer);


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        DocDetailListItem item = mGridData.get(position);
          Picasso.with(mContext).load(item.getTypeIcon()).into(holder.circleImageView);
        holder.docname.setText("" + item.getTravelDocName());
      /*  holder.city.setText("" + item.getDepartureIata());
        holder.dest_city.setText("" + item.getArrivalIata());
        holder.time.setText("" + item.getTotalTime());
        holder.flighttime.setText("" + item.getDepartureTime());
        holder.flight_dest_time.setText("" + item.getArrivalTime());
        holder.flightno.setText("" + item.getAirlinCode()+" "+item.getFlightNumber());*/
       /*
        holder.createdDate.setText("" + item.getCreatedDate().trim());
        holder.uploadStatus.setText("" + item.getStatus());*/

        holder.linerLayoutss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                travelDocId = mGridData.get(position).getTravelDocId();
                documrntPath = mGridData.get(position).getDocumrntPath();
               String docType = mGridData.get(position).getDocType();
               if(docType.equalsIgnoreCase("PDF")) {
                   Intent intent = new Intent(mContext, PDFActivity.class);
                   intent.putExtra("travelDocId", travelDocId);
                   intent.putExtra("documrntPath", documrntPath);
                   mContext.startActivity(intent);
                /*   PdfFragment a2Fragment = new PdfFragment();
                   Bundle args = new Bundle();
                   args.putString("travelDocId", travelDocId);
                   args.putString("documrntPath", documrntPath);
                   FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                   a2Fragment.setArguments(args);
                   transaction.replace(R.id.doc_detailss, a2Fragment).commit();*/
               } else if(docType.equalsIgnoreCase("DOC")){
                   Intent intent = new Intent(mContext, CSVActivity.class);
                   intent.putExtra("travelDocId", travelDocId);
                   intent.putExtra("documrntPath", documrntPath);
                   mContext.startActivity(intent);
                /*   CSVFragment a2Fragment = new CSVFragment();
                   Bundle args = new Bundle();
                   args.putString("travelDocId", travelDocId);
                   args.putString("documrntPath", documrntPath);
                   FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                   a2Fragment.setArguments(args);
                   transaction.replace(R.id.doc_detailss, a2Fragment).commit();*/
               }


            }
        });

        return row;
    }



    static class ViewHolder {
        TextView topic,category,createdDate,uploadStatus;
        LinearLayout linerLayoutss;
        TextView docname;
        CircleImageView circleImageView;
        WebView  webView;
    }
   /* private void openDetailActivity(Context mContext, String travelDocId)
    {
        enterNextFragment();
    }

      private void enterNextFragment() {
          DocumentDetailFragment a2Fragment = new DocumentDetailFragment();
          FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();

          Bundle args = new Bundle();
          args.putString("travelDocId", travelDocId);
          a2Fragment.setArguments(args);
          transaction.replace(R.id.doc, a2Fragment).commit();

      }*/
}