/*
package com.watconsult.tlakapp.ui.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.watconsult.tlakapp.R;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final View mWindow;
    private Context context;

    public CustomInfoWindowAdapter(Context context) {
        this.context = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window,null);
    }
   private void rendowWindowText(Marker marker,View view){
        String title = marker.getTitle();
       TextView tvTitle = (TextView) view.findViewById(R.id.title);

       if(!title.equals("")){
           tvTitle.setText(title);
       }
       String snippet = marker.getSnippet();
   }
    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker,mWindow);
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker,mWindow);
        return null;
    }
}
*/
