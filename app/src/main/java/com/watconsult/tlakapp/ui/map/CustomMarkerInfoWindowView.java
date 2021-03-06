/*
package com.watconsult.tlakapp.ui.map;

import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.watconsult.tlakapp.R;

public class CustomMarkerInfoWindowView implements GoogleMap.InfoWindowAdapter {
    private final View markerItemView;
    public CustomMarkerInfoWindowView() {
        View layoutInflater = null;
        markerItemView = layoutInflater.inflate(R.layout.marker_info_window, null);  // 1
    }
    @Override
    public View getInfoWindow(Marker marker) { // 2
        User user = (User) marker.getTag();  // 3
        if (user == null) return clusterItemView;
        TextView itemNameTextView = markerItemView.findViewById(R.id.itemNameTextView);
        TextView itemAddressTextView = markerItemView.findViewById(R.id.itemAddressTextView);
        itemNameTextView.setText(marker.getTitle());
        itemAddressTextView.setText(user.getAddress());
        return markerItemView;  // 4
    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}*/
