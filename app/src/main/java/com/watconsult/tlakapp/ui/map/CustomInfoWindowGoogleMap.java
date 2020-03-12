package com.watconsult.tlakapp.ui.map;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.InfoWindowData;
import com.watconsult.tlakapp.model.PoiItem;

import static com.watconsult.tlakapp.ui.poi.Poi_Fragment.PoiName;


public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.map_custom_info, null);

        TextView name_tv = (TextView) view.findViewById(R.id.name);
        name_tv.setText(marker.getTitle());
      InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();
       String name = infoWindowData.getPoiName();
        System.out.println("name---"+name);
        name_tv.setText(PoiName);
        return view;
    }
}
