package com.watconsult.tlakapp.ui.chat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.watconsult.tlakapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MessageAdapter extends ArrayAdapter<ChatBubble> {

    private Activity activity;
    private List<ChatBubble> messages;

    public MessageAdapter(Activity context, int resource, List<ChatBubble> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.messages = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource = 0; // determined by view type
        ChatBubble ChatBubble = getItem(position);
        int viewType = getItemViewType(position);

        if (ChatBubble.myMessage()) {
            layoutResource = R.layout.left_chat_bubble;
        } else {
            layoutResource = R.layout.right_chat_bubble;
        }

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        //set message content
        holder.msg.setText(ChatBubble.getContent());

       /* Glide.with(activity).load(ChatBubble.getImageupload())
                .into(holder.imageupload);
        holder.imageupload.setColorFilter(ContextCompat.getColor(activity, android.R.color.transparent));*/
        DateFormat dateFormat = new SimpleDateFormat("hh.mm a");
        String dateString = dateFormat.format(new Date()).toString().toLowerCase();
     //   String currentTime = new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());
        holder.time.setText(dateString);
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime. Value 2 is returned because of left and right views.
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 2;
    }

    private class ViewHolder {
        private TextView msg;
        private TextView time;
        ImageView imageupload;

        public ViewHolder(View v) {
            msg = (TextView) v.findViewById(R.id.txt_msg);
            time = (TextView) v.findViewById(R.id.time);
           // imageupload = (ImageView) v.findViewById(R.id.image_msg);
        }
    }
}
