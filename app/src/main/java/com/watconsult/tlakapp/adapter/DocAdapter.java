package com.watconsult.tlakapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.DocListitem;
import com.watconsult.tlakapp.ui.document.DocumentDetailActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DocAdapter extends ArrayAdapter<DocListitem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<DocListitem> mGridData = new ArrayList<DocListitem>();
    String travelDocId;
    String category;
    String createdate;
    String uploadstatus,note,viewUrl;
    public DocAdapter(Context mContext, int layoutResourceId, ArrayList<DocListitem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public DocAdapter(Context applicationContext, int contact_list, DocListitem listItem) {
        super(applicationContext,contact_list, (List<DocListitem>) listItem);
    }
    @Override
    public int getCount()
    {
        return mGridData.size();
    }

    public void setGridData(ArrayList<DocListitem> mGridData) {
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

            holder.docname = (TextView) row.findViewById(R.id.docname);
            holder.linerLayoutss = (LinearLayout) row.findViewById(R.id.lineaer);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        DocListitem item = mGridData.get(position);
    //    Picasso.with(mContext).load(item.getTypeIcon()).into(holder.circleImageView);
        holder.docname.setText("" + item.getTravelDocName());


        holder.linerLayoutss.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                travelDocId = mGridData.get(position).getTravelDocId();//run karo??o
                Intent intent = new Intent(mContext, DocumentDetailActivity.class);
                intent.putExtra("travelDocId", travelDocId);
                mContext.startActivity(intent);
              /*  DocumentDetailFragment a2Fragment = new DocumentDetailFragment();
                Bundle args = new Bundle();
                args.putString("travelDocId", travelDocId);
                FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
                a2Fragment.setArguments(args);
                transaction.replace(R.id.docss, a2Fragment).commit();*/
                // now you get url title what is next ???
             //   openDetailActivity(mContext, travelDocId);
            }
        });
        return row;
    }
    static class ViewHolder {
        TextView topic,category,createdDate,uploadStatus;
        LinearLayout linerLayoutss;
        TextView docname;
        CircleImageView circleImageView;
    }
}