package com.watconsult.tlakapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.watconsult.tlakapp.R;

import java.util.ArrayList;
import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter {
    private List scoreList = new ArrayList();

    static class ItemViewHolder {
       /* TextView name;
        TextView score;*/
       TextView id;

        TextView exp_id;
        TextView activity_name;
    }

    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    /*@Override
    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }*/

    @Override
    public void add(@Nullable Object object) {
        scoreList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public String[] getItem(int index) {
        return (String[]) this.scoreList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.csv_item_layout, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.id = (TextView) row.findViewById(R.id.name);

            viewHolder.exp_id = (TextView) row.findViewById(R.id.score);
            viewHolder.activity_name = (TextView) row.findViewById(R.id.activity_name);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)row.getTag();
        }
        String[] stat = getItem(position);
        viewHolder.exp_id.setText(stat[0]);

        viewHolder.exp_id.setText(stat[1]);
        viewHolder.activity_name.setText(stat[2]);
        return row;
    }
}