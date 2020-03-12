package com.watconsult.tlakapp.ui.map;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.adapter.WeatherAdapter;
import com.watconsult.tlakapp.model.MainWeather;
import com.watconsult.tlakapp.model.POIMapItem;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class NewMapsActivity extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.InfoWindowAdapter {
    private static final String TAG = "";
    String placeName;
    String vicinity;
    private GoogleMap mMap;
    double latitude;
    double longitude;
    double lat;
    double longs;
    private ArrayList<POIMapItem> poiListItems = new ArrayList<POIMapItem>();
    private int PROXIMITY_RADIUS = 3000;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    String bottomNumber;
    SharedPrefs sharedPrefs;
    LatLng latLng;
    ArrayList<String> spinnerList = new ArrayList<String>();
    Spinner spinnerItem;
    MapPOI mapPOI;
    ArrayAdapter<String> spinnerMenu;
    Context mContext;
   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }*/
  /* private Context myContext = null;
    public NewMapsActivity(Context ctx){
        mContext = ctx;
    }*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        sharedPrefs = new SharedPrefs(getActivity());
        Bundle args = getArguments();
        if (args != null) {
            bottomNumber = String.valueOf(args.get("itemNumber"));
            System.out.println("bottomNumber---s-------"+bottomNumber);
    }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            getActivity().finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);*/
        mapFragment.getMapAsync(this);
        spinnerItem = (Spinner) root.findViewById(R.id.spinner_city);
        if(bottomNumber.equalsIgnoreCase("0"))
        {
            JSONObject object = new JSONObject();
            try {
                object.put("token",sharedPrefs.getToken());
                Log.e("obJE=",object.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("error",e.toString());
            }
            new POIMapTask().execute(object);
        }
        if(bottomNumber.equalsIgnoreCase("1"))
        {
            JSONObject object = new JSONObject();
            try {
                object.put("token",sharedPrefs.getToken());
                Log.e("obJE=",object.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("error",e.toString());
            }
            new OptionalMapTask().execute(object);
        }
        return root;
    }
    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(getActivity());
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(getActivity(), result,
                        0).show();
            }
            return false;
        }
        return true;
    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private boolean checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        }
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onLocationChanged(Location location) {
            Log.d("onLocationChanged", "entered");

            mLastLocation = location;
            if (mCurrLocationMarker != null) {
                mCurrLocationMarker.remove();
            }

            //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        Geocoder geoCoder = new Geocoder(getActivity(), Locale.getDefault()); //it is Geocoder
        StringBuilder builder = new StringBuilder();
        Geocoder gcd = new Geocoder(getActivity().getBaseContext(),
                Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude()
                    , 1);
            if (addresses.size() > 0)
                System.out.println(addresses.get(0).getLocality());
             String cityNames = addresses.get(0).getLocality();
            markerOptions.title(cityNames);
            System.out.println("cityNamessss="+cityNames);
        } catch (IOException e) {
            e.printStackTrace();
        }

       // markerOptions.title("Current Position");
       markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
       // markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.shoppingbag));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
            latitude = location.getLatitude();

            lat = latitude;
        System.out.println("lat===="+lat);
            longitude = location.getLongitude();
        longs = longitude;
        System.out.println("longs===="+longs);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
            Toast.makeText(getActivity(),"Your Current Location", Toast.LENGTH_LONG).show();

            Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f",latitude,longitude));

            if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
                Log.d("onLocationChanged", "Removing Location Updates");
            }
            Log.d("onLocationChanged", "Exit");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.style_json));
            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
         /*   boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json1));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }*/
        //Initialize Google Play Services
        mMap.setOnInfoWindowClickListener(this);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

       // Button btnRestaurant = (Button) findViewById(R.id.btnRestaurant);

       /* Button btnHospital = (Button) findViewById(R.id.btnHospital);
        btnHospital.setOnClickListener(new View.OnClickListener() {
            String Shopping = "shopping";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                mMap.clear();
                String url = getUrl(latitude, longitude, Shopping);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(MapsActivity.this,"Nearby Shopping", Toast.LENGTH_LONG).show();
            }
        });*/
       /* Button btnSchool = (Button) findViewById(R.id.btnSchool);
        btnSchool.setOnClickListener(new View.OnClickListener() {
            String School = "school";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                mMap.clear();
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }
                String url = getUrl(latitude, longitude, School);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(MapsActivity.this,"Nearby Schools", Toast.LENGTH_LONG).show();
            }
        });*/
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private String getUrl(double lat, double longs, String groceries) {
      //  StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.6300529,77.2256482");
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.6300529,77.2256482");

        https://maps.googleapis.com/maps/api/place/nearbysearch/output?parameters
       // googlePlacesUrl.append("location=" + lat + "," + longs);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + groceries);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyBpny7f4norxynsPJphb9x5miXPCMETyN8");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }
    @Override
    public void onInfoWindowClick(Marker marker) {
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        //return prepareInfoView(marker);
        LinearLayout info = new LinearLayout(getActivity());
        LinearLayout.LayoutParams infoViewParams = new LinearLayout.LayoutParams(
                700,LinearLayout.LayoutParams.WRAP_CONTENT);
        info.setOrientation(LinearLayout.HORIZONTAL);
        info.setHorizontalGravity(View.TEXT_ALIGNMENT_CENTER);
        info.setPadding(2,20,2,0);
        info.setLayoutParams(infoViewParams);
        info.setOrientation(LinearLayout.VERTICAL);
        TextView title = new TextView(getActivity());
        title.setTextColor(Color.BLACK);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(null, Typeface.NORMAL);
        title.setText(marker.getTitle());
        TextView snippet = new TextView(getActivity());
        snippet.setTextColor(Color.GRAY);
        snippet.setTextSize(35);
       // snippet.setText(marker.getSnippet());
        info.addView(title);
        info.addView(snippet);
        return info;
    }
    public class OptionalMapTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
           /* progressDialog = new ProgressDialog(getActivity(),R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();*/
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return ServiceProviderClass.getOptionalMapDetail(param[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss---dd-----"+result);
          //  progressDialog.dismiss();
            parseResultAsPerOrgStandard(result);
        }
    }
    private void parseResultAsPerOrgStandard(String result) {
        JSONObject jsonObjects = null;
        try {
            jsonObjects = new JSONObject(result);
            String error = String.valueOf(jsonObjects.getBoolean("error"));
            JSONArray jsonArrays = jsonObjects.getJSONArray("toptionalPoi");
            for (int i = 0; i < jsonArrays.length(); i++) {
                JSONObject jsonObj = jsonArrays.getJSONObject(i);
                MarkerOptions markerOptions = new MarkerOptions();
                POIMapItem item = new POIMapItem();
                String name = jsonObj.getString("poiName");
                Double latitudes = Double.valueOf(jsonObj.getString("latitude"));
                Double longitudes = Double.valueOf(jsonObj.getString("longitude"));
              /*  mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                        .title(jsonObj.getString("poiName")));*/
                latLng = new LatLng(latitudes, longitudes);
                markerOptions.position(latLng);
                markerOptions.title(name);
                markerOptions.icon(bitmapDescriptorFromVector(getActivity(), R.drawable.optional_poi_map_icon));
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(new LatLng(latitudes, longitudes), 13));
              /*  mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(13));*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public class POIMapTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
          /*  progressDialog = new ProgressDialog(getActivity(),R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();*/
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return ServiceProviderClass.getPoIMapDetail(param[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss---dd-----"+result);
          //  progressDialog.dismiss();
            parseResultAsPerOrgStandards(result);
        }
    }
    private void parseResultAsPerOrgStandards(String result) {
        if (result != null) {
        try {

            Gson obGson = new Gson();
            mapPOI = obGson.fromJson(result, MapPOI.class);

            spinnerList.add(0,"Select Location Name");
            //   System.out.println("HI---"+mainPOI.getPoiMap().size());
            spinnerList.clear();
            for(int i=0;i<mapPOI.getPoiMap().size();i++){
                spinnerList.add(mapPOI.getPoiMap().get(i).getLocacionName());
                Log.d("spinner",spinnerList.toString());
            }
           /* if (spinnerList != null && spinnerList.size() >= 0){*/

                spinnerMenu = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerList);
                spinnerMenu.setDropDownViewResource(android.R.layout.simple_list_item_1);
                spinnerItem.setAdapter(spinnerMenu);
                //  }

            spinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedName = (String)parent.getAdapter().getItem(position);
                    MarkerOptions markerOptions = new MarkerOptions();
                    for (int i=0;i<mapPOI.getPoiMap().size();i++){
                        if(selectedName.equals(mapPOI.getPoiMap().get(i).getLocacionName() )){
                            System.out.println("Selected");
                            mMap.clear();
                            for (int j=0;j<mapPOI.getPoiMap().get(i).getPoi().size();j++){
                                latLng = new LatLng(Double.parseDouble(mapPOI.getPoiMap().get(i).getPoi().get(j).getLatitude()), Double.parseDouble(mapPOI.getPoiMap().get(i).getPoi().get(j).getLongitude()));
                                markerOptions.position(latLng);
                                markerOptions.title(mapPOI.getPoiMap().get(i).getPoi().get(j).getPoiName());
                                if(mapPOI.getPoiMap().get(i).getPoi().get(j).getStatus().equals("1"))
                                {
                                    markerOptions.icon(bitmapDescriptorFromVector(getActivity(), R.drawable.poi_map_icon));
                                }
                                else {
                                    markerOptions.icon(bitmapDescriptorFromVector(getActivity(), R.drawable.optional_poi_map_icon));
                                }
                                mMap.addMarker(markerOptions);
                                mMap.moveCamera(CameraUpdateFactory
                                        .newLatLngZoom(new LatLng(Double.parseDouble(mapPOI.getPoiMap().get(i).getPoi().get(j).getLatitude()),Double.parseDouble(mapPOI.getPoiMap().get(i).getPoi().get(j).getLongitude())), 12));
                            }

                        }else {
                            System.out.println("not Selected");
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }



              /*   for (int i=0;i<mainPOI.getPoiMap().size();i++){
                    spinnerList.add(mainPOI.getPoiMap().get(i).getLocacionName());
                }*/
             /*   spinnerMenu = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, spinnerList);
                spinnerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerItem.setAdapter(spinnerMenu);*/

            //final int numberofitems = jsonArray.length();
              /*  for (int i=0;i<mainPOI.getPoiMap().size();i++){
                    spinnerList.add(mainPOI.getPoiMap().get(i).getLocacionName());
                }
                spinnerMenu = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, spinnerList);
                spinnerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerItem.setAdapter(spinnerMenu);
                spinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedName = (String) parent.getAdapter().getItem(position);
                        Log.e("selectedName1", selectedName);
                        //  final int numberofitems = mainPOI.getPoiMap().size()
                        //  ;
                        if (mainPOI.getPoiMap().size() == 0) {
                            for (int i = 0; i < mainPOI.getPoiMap().size(); i++) {
                                Log.e("selectedName2", mainPOI.getPoiMap().toString());
                                Double latitude = Double.valueOf(mainPOI.getPoiMap().get(i).getPoi().get(i).getLatitude());
                                System.out.println("latitudes=========="+latitude);
                            Double latitude = Double.valueOf(mainPOI.getPoiMap().get(i).getPoi().get(i).getLatitude());
                            Log.e("latitude",latitude.toString());
                                //      MarkerOptions markerOptions = new MarkerOptions();
                                // Double latitude = Double.valueOf(mainPOI.getPoiMap().get(i).getPoi().get(i).getLatitude());
                                //Log.e("latitudeNewmap3",latitude.toString());
                                //   Double longitude = Double.valueOf(mainPOI.getPoiMap().get(i).getPoi().get(i).getLongitude());
                                //  String poiName = mainPOI.getPoiMap().get(i).getPoi().get(i).getPoiName();
                                // System.out.println("latitudes=========="+latitude);

                                // Double longitude = Double.valueOf(jsonObj.getString("longitude"));
                                if (selectedName.equals(mainPOI.getPoiMap().get(i).getLocacionName())) {
                                    System.out.println("Selected");
                                latLng = new LatLng(latitude, longitude);
                                markerOptions.position(latLng);
                                markerOptions.title(poiName);
                                markerOptions.icon(bitmapDescriptorFromVector(getActivity(), R.drawable.poi_map_icon));
                                mMap.addMarker(markerOptions);
              weatherAdapter = new WeatherAdapter(getActivity(), R.layout.weather_layout_item, mainWeather.getWeather().get(i).getDayWiseweather());
                                weatherlistView.setAdapter(weatherAdapter);

                                } else {
                                    System.out.println("not Selected");
                                }
                            }
                        }
                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });*/
            //   final int numberofitems = jsonArray.length();
              /*  for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    MarkerOptions markerOptions = new MarkerOptions();
                    POIMapItem item = new POIMapItem();
                    String name = jsonObj.getString("poiName");
                    Double latitude = Double.valueOf(jsonObj.getString("latitude"));
                    Double longitude = Double.valueOf(jsonObj.getString("longitude"));
                    item.setPoiName(jsonObj.getString("poiName"));
                    item.setLatitude(jsonObj.getString("latitude"));
                    item.setLongitude(jsonObj.getString("longitude"));
                    poiListItems.add(item);
                    PolylineOptions polylineOptions = new PolylineOptions();
                    polylineOptions.color(Color.YELLOW);
                    polylineOptions.width(15);
                        polylineOptions.add(new LatLng(Double.parseDouble(jsonObj.getString("latitude")), Double.parseDouble(jsonObj.getString("longitude"))));
                    mMap.addPolyline(polylineOptions);
                    latLng = new LatLng(latitude, longitude);
                    markerOptions.position(latLng);
                    markerOptions.title(name);
                    markerOptions.icon(bitmapDescriptorFromVector(getActivity(), R.drawable.poi_map_icon));
                    mMap.addMarker(markerOptions);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                    mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                            .title(jsonObj.getString("poiName")));
                }*/
        }
    }
    /*private void parseResultAsPerOrgStandards(String result) {
        if(result!=null){
            Gson obGson = new Gson();
            mapPOI = obGson.fromJson(result, MapPOI.class);
            spinnerList.add(0,"Select Location Name");
            for(int i=0;i<mapPOI.getPoiMap().size();i++){
                spinnerList.add(mapPOI.getPoiMap().get(i).getLocacionName());
            }
            spinnerMenu = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, spinnerList);
            spinnerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerItem.setAdapter(spinnerMenu);
            spinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedName = (String)parent.getAdapter().getItem(position);
                    System.out.println("selectedName==="+selectedName);
                   // final int numberofitems = mapPOI.getPoiMap().size();

                    for (int i=0; i < mapPOI.getPoiMap().size();i++){
                        if(selectedName.equals(mapPOI.getPoiMap().get(i).getLocacionName())){
                            System.out.println("Selected");

                            MarkerOptions markerOptions = new MarkerOptions();
                            Double latitude = Double.valueOf(mapPOI.getPoiMap().get(i).getPoi().get(i).getLatitude());
                            System.out.println("latitudes=========="+latitude);
                            Double longitude = Double.valueOf(mapPOI.getPoiMap().get(i).getPoi().get(i).getLongitude());
                            String poiname = mapPOI.getPoiMap().get(i).getPoi().get(i).getPoiName();
                            latLng = new LatLng(latitude, longitude);
                            markerOptions.position(latLng);
                            markerOptions.title(poiname);
                            markerOptions.icon(bitmapDescriptorFromVector(getActivity(), R.drawable.poi_map_icon));
                            mMap.addMarker(markerOptions);
                        }else {
                            System.out.println("not Selected");
                        }
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }*/

    public class ViewAllTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
          /*  progressDialog = new ProgressDialog(getActivity(),R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();*/
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return ServiceProviderClass.getPoIMapDetail(param[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss---dd-----"+result);
            //  progressDialog.dismiss();
            parseResultAsPerOrgStandard(result);
        }
    }
   /* private void parseResultAsPerOrgStandards(String result) {
        if (result != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(result);
              *//*  String error = String.valueOf(jsonObject.getBoolean("error"));
                JSONArray jsonArray = jsonObject.getJSONArray("locationPoi");
                System.out.println("jsonArray =="+jsonArray);*//*
              *//*  Gson obGson = new Gson();
                mainPOI = obGson.fromJson(result, MainPOI.class);
                spinnerList.add(0,"Select Location Name");*//*
                Gson obGson = new Gson();
                mainPOI = obGson.fromJson(result, MainPOI.class);
                spinnerList.add(0,"Select Location Name");
                for(int i=0;i<mainPOI.getPoiMap().size();i++){
                 spinnerList.add(mainPOI.getPoiMap().get(i).getLocacionName());
                }
               *//* for (int i=0;i<mainPOI.getPoiMap().size();i++){
                    spinnerList.add(mainPOI.getPoiMap().get(i).getLocacionName());
                }*//*
                spinnerMenu = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, spinnerList);
                spinnerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerItem.setAdapter(spinnerMenu);

              *//*  //final int numberofitems = jsonArray.length();
                for (int i=0;i<mainPOI.getPoiMap().size();i++){
                    spinnerList.add(mainPOI.getPoiMap().get(i).getLocacionName());
                }
                spinnerMenu = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, spinnerList);
                spinnerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerItem.setAdapter(spinnerMenu);*//*
    *//*            spinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedName = (String) parent.getAdapter().getItem(position);
                        Log.e("selectedName1", selectedName);
                        //  final int numberofitems = mainPOI.getPoiMap().size()
                        //  ;
                        if (mainPOI.getPoiMap().size() == 0) {
                            for (int i = 0; i < mainPOI.getPoiMap().size(); i++) {
                                Log.e("selectedName2", mainPOI.getPoiMap().toString());
                                Double latitude = Double.valueOf(mainPOI.getPoiMap().get(i).getPoi().get(i).getLatitude());
                                System.out.println("latitudes=========="+latitude);
                          *//**//*  Double latitude = Double.valueOf(mainPOI.getPoiMap().get(i).getPoi().get(i).getLatitude());
                            Log.e("latitude",latitude.toString());*//**//*
                                //      MarkerOptions markerOptions = new MarkerOptions();
                                // Double latitude = Double.valueOf(mainPOI.getPoiMap().get(i).getPoi().get(i).getLatitude());
                                //Log.e("latitudeNewmap3",latitude.toString());
                                //   Double longitude = Double.valueOf(mainPOI.getPoiMap().get(i).getPoi().get(i).getLongitude());
                                //  String poiName = mainPOI.getPoiMap().get(i).getPoi().get(i).getPoiName();
                                // System.out.println("latitudes=========="+latitude);

                                // Double longitude = Double.valueOf(jsonObj.getString("longitude"));
                                if (selectedName.equals(mainPOI.getPoiMap().get(i).getLocacionName())) {
                                    System.out.println("Selected");
                              *//**//*  latLng = new LatLng(latitude, longitude);
                                markerOptions.position(latLng);
                                markerOptions.title(poiName);
                                markerOptions.icon(bitmapDescriptorFromVector(getActivity(), R.drawable.poi_map_icon));
                                mMap.addMarker(markerOptions);*//**//*
                               *//**//* weatherAdapter = new WeatherAdapter(getActivity(), R.layout.weather_layout_item, mainWeather.getWeather().get(i).getDayWiseweather());
                                weatherlistView.setAdapter(weatherAdapter);*//**//*

                                } else {
                                    System.out.println("not Selected");
                                }
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });*//*
             //   final int numberofitems = jsonArray.length();
            *//*    for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    MarkerOptions markerOptions = new MarkerOptions();
                    POIMapItem item = new POIMapItem();
                    String name = jsonObj.getString("poiName");
                    Double latitude = Double.valueOf(jsonObj.getString("latitude"));
                    Double longitude = Double.valueOf(jsonObj.getString("longitude"));
                    item.setPoiName(jsonObj.getString("poiName"));
                    item.setLatitude(jsonObj.getString("latitude"));
                    item.setLongitude(jsonObj.getString("longitude"));
                    poiListItems.add(item);
                    PolylineOptions polylineOptions = new PolylineOptions();
                    polylineOptions.color(Color.YELLOW);
                    polylineOptions.width(15);
                        polylineOptions.add(new LatLng(Double.parseDouble(jsonObj.getString("latitude")), Double.parseDouble(jsonObj.getString("longitude"))));
                    mMap.addPolyline(polylineOptions);
                    latLng = new LatLng(latitude, longitude);
                    markerOptions.position(latLng);
                    markerOptions.title(name);
                    markerOptions.icon(bitmapDescriptorFromVector(getActivity(), R.drawable.poi_map_icon));
                    mMap.addMarker(markerOptions);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                    mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                            .title(jsonObj.getString("poiName")));
                }*//*
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }*/
    public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {
        public Context getContext() {
            return context;
        }
        String googlePlacesData;
        GoogleMap mMap;
        String url;
        Context context;
        @Override
        protected String doInBackground(Object... params) {
            try {
                Log.d("GetNearbyPlacesData", "doInBackground entered");
                mMap = (GoogleMap) params[0];
                url = (String) params[1];
                DownloadUrl downloadUrl = new DownloadUrl();
                googlePlacesData = downloadUrl.readUrl(url);
                Log.d("GooglePlacesReadTask", "doInBackground Exit");
            } catch (Exception e) {
                Log.d("GooglePlacesReadTask", e.toString());
            }
            return googlePlacesData;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("GooglePlacesReadTask", "onPostExecute Entered");
            List<HashMap<String, String>> nearbyPlacesList = null;
            DataParser dataParser = new DataParser();
            nearbyPlacesList =  dataParser.parse(result);
            ShowNearbyPlaces(nearbyPlacesList);
            Log.d("GooglePlacesReadTask", "onPostExecute Exit");
        }

        private void ShowNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList) {
            for (int i = 0; i < nearbyPlacesList.size(); i++) {
                Log.d("onPostExecute","Entered into showing locations");
                MarkerOptions markerOptions = new MarkerOptions();
                HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
                double lat = Double.parseDouble(googlePlace.get("lat"));
                double lng = Double.parseDouble(googlePlace.get("lng"));
                placeName = googlePlace.get("place_name");
                System.out.println("placeName=="+placeName);
                vicinity = googlePlace.get("vicinity");
                System.out.println("vicinity=="+vicinity);
                String icons = googlePlace.get("icon");
                System.out.println("icon========="+icons);

              /*  LinearLayout subInfoView = new LinearLayout(getActivity());
                LinearLayout.LayoutParams subInfoViewParams = new LinearLayout.LayoutParams(
                        630, 170);
                subInfoView.setOrientation(LinearLayout.VERTICAL);
                subInfoView.setLayoutParams(subInfoViewParams);

                TextView subInfoLat = new TextView(getActivity());
               // subInfoLat.setText(placeName + " : " + vicinity);
                subInfoLat.setText(placeName);
                subInfoLat.setGravity(View.TEXT_ALIGNMENT_CENTER);
                subInfoLat.setTextColor(Color.BLACK);
                subInfoView.addView(subInfoLat);*/
               // infoView.addView(subInfoView);

                 latLng = new LatLng(lat, lng);
                markerOptions.position(latLng);
              // markerOptions.snippet(vicinity);
                markerOptions.title(placeName + " : " + vicinity);
          //      markerOptions.title(googlePlace.get("place_name").toString());
                 markerOptions.icon(bitmapDescriptorFromVector(getActivity(), R.drawable.resturant_map_icon));

                // markerOptions.icon(bitmapDescriptorFromVector(, R.drawable.your_vector_asset))
                mMap.addMarker(markerOptions);
                //  markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.shoppingbag));
                //move map camera
              /*  mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(13));*/
                mMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(new LatLng(lat, lng), 15));
            }
        }
    }
    public class GetNearbyShoppingPlacesData extends AsyncTask<Object, String, String> {
        public Context getContext() {
            return context;
        }
        String googlePlacesData;
        GoogleMap mMap;
        String url;
        Context context;
        @Override
        protected String doInBackground(Object... params) {
            try {
                Log.d("GetNearbyPlacesData", "doInBackground entered");
                mMap = (GoogleMap) params[0];
                url = (String) params[1];
                DownloadUrl downloadUrl = new DownloadUrl();
                googlePlacesData = downloadUrl.readUrl(url);
                Log.d("GooglePlacesReadTask", "doInBackground Exit");
            } catch (Exception e) {
                Log.d("GooglePlacesReadTask", e.toString());
            }
            return googlePlacesData;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("GooglePlacesReadTask", "onPostExecute Entered");
            List<HashMap<String, String>> nearbyPlacesList = null;
            DataParser dataParser = new DataParser();
            nearbyPlacesList =  dataParser.parse(result);
            ShowNearbyPlaces(nearbyPlacesList);
            Log.d("GooglePlacesReadTask", "onPostExecute Exit");
        }
        private void ShowNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList) {
            for (int i = 0; i < nearbyPlacesList.size(); i++) {
                Log.d("onPostExecute","Entered into showing locations");
                MarkerOptions markerOptions = new MarkerOptions();
                HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
                double lat = Double.parseDouble(googlePlace.get("lat"));
                double lng = Double.parseDouble(googlePlace.get("lng"));
                placeName = googlePlace.get("place_name");
                System.out.println("placeName=="+placeName);
                vicinity = googlePlace.get("vicinity");
                System.out.println("vicinity=="+vicinity);
                String icons = googlePlace.get("icon");
                System.out.println("icon========="+icons);

              /*  LinearLayout subInfoView = new LinearLayout(getActivity());
                LinearLayout.LayoutParams subInfoViewParams = new LinearLayout.LayoutParams(
                        630, 170);
                subInfoView.setOrientation(LinearLayout.VERTICAL);
                subInfoView.setLayoutParams(subInfoViewParams);

                TextView subInfoLat = new TextView(getActivity());
               // subInfoLat.setText(placeName + " : " + vicinity);
                subInfoLat.setText(placeName);
                subInfoLat.setGravity(View.TEXT_ALIGNMENT_CENTER);
                subInfoLat.setTextColor(Color.BLACK);
                subInfoView.addView(subInfoLat);*/
                // infoView.addView(subInfoView);
                latLng = new LatLng(lat, lng);
                markerOptions.position(latLng);
                // markerOptions.snippet(vicinity);
                markerOptions.title(placeName + " : " + vicinity);

                //      markerOptions.title(googlePlace.get("place_name").toString());
                markerOptions.icon(bitmapDescriptorFromVector(getActivity(), R.drawable.shopping_bag_map_icon));
                // markerOptions.icon(bitmapDescriptorFromVector(, R.drawable.your_vector_asset))
                mMap.addMarker(markerOptions);
                //  markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.shoppingbag));

                //move map camera
                mMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(new LatLng(lat, lng), 15));
            }
        }
    }

   private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
       try {
           Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
           vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
           Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
           Canvas canvas = new Canvas(bitmap);
           vectorDrawable.draw(canvas);
           return BitmapDescriptorFactory.fromBitmap(bitmap);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }

    private View prepareInfoView(Marker marker){
        //prepare InfoView programmatically
        LinearLayout infoView = new LinearLayout(getActivity());
        LinearLayout.LayoutParams infoViewParams = new LinearLayout.LayoutParams(
                630, 170);
        infoView.setOrientation(LinearLayout.HORIZONTAL);
        infoView.setHorizontalGravity(View.TEXT_ALIGNMENT_CENTER);
        infoView.setPadding(8,4,4,4);
        infoView.setLayoutParams(infoViewParams);

        LinearLayout subInfoView = new LinearLayout(getActivity());
        LinearLayout.LayoutParams subInfoViewParams = new LinearLayout.LayoutParams(
                630, 170);
        subInfoView.setOrientation(LinearLayout.VERTICAL);
        subInfoView.setLayoutParams(subInfoViewParams);
        TextView subInfoLat = new TextView(getActivity());
        subInfoLat.setText(placeName + " : " + vicinity);
        subInfoLat.setGravity(View.TEXT_ALIGNMENT_CENTER);
        subInfoLat.setTextColor(Color.BLACK);
        subInfoView.addView(subInfoLat);
        infoView.addView(subInfoView);
        return infoView;
    }
    public void tabClick(String i){
        if(i.equalsIgnoreCase("0"))
        {
            spinnerItem.setVisibility(View.VISIBLE);
            mMap.clear();
            JSONObject object = new JSONObject();
            try {
                object.put("token",sharedPrefs.getToken());
                Log.e("obJE=",object.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("error",e.toString());
            }
            new POIMapTask().execute(object);
        }
        if(i.equalsIgnoreCase("2")) {
            spinnerItem.setVisibility(View.GONE);
            String Restaurant = "restaurant";
            mMap.clear();
            String url = getUrl(lat, longs, Restaurant);
            Object[] DataTransfer = new Object[2];
            DataTransfer[0] = mMap;
            DataTransfer[1] = url;
            Log.d("onClick", url);
            GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
            getNearbyPlacesData.execute(DataTransfer);
          //  Toast.makeText(getActivity(), "Nearby Restaurants", Toast.LENGTH_LONG).show();
          /*  btnRestaurant.setOnClickListener(new View.OnClickListener() {
                String Restaurant = "restaurant";
                @Override
                public void onClick(View v) {
                    Log.d("onClick", "Button is Clicked");
                    mMap.clear();
                    String url = getUrl(latitude, longitude, Restaurant);
                    Object[] DataTransfer = new Object[2];
                    DataTransfer[0] = mMap;
                    DataTransfer[1] = url;
                    Log.d("onClick", url);
                    GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                    getNearbyPlacesData.execute(DataTransfer);
                    Toast.makeText(getActivity(), "Nearby Restaurants", Toast.LENGTH_LONG).show();
                }
            });*/
        }
        if(i.equalsIgnoreCase("3")) {
            spinnerItem.setVisibility(View.GONE);
            String Shopping = "supermarket";
            mMap.clear();
            String url = getUrl(lat, longs, Shopping);
            Object[] DataTransfer = new Object[2];
            DataTransfer[0] = mMap;
            DataTransfer[1] = url;
            Log.d("onClick", url);
            GetNearbyShoppingPlacesData getNearbyPlacesData = new GetNearbyShoppingPlacesData();
            getNearbyPlacesData.execute(DataTransfer);
           // Toast.makeText(getActivity(), "Nearby Shopping", Toast.LENGTH_LONG).show();
        }
      /*  if(i.equalsIgnoreCase("4")) {
            mMap.clear();
            spinnerItem.setVisibility(View.GONE);
            JSONObject object = new JSONObject();
            try {
                object.put("token",sharedPrefs.getToken());
                Log.e("obJE=",object.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("error",e.toString());
            }
            new POIMapTask().execute(object);

        }*/
        if(i.equalsIgnoreCase("4")) {
            SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
            if (isFirstRun)
            {
                mMap.clear();
                SharedPreferences.Editor editor = wmbPreference.edit();
                editor.putBoolean("FIRSTRUN", false);
                POI();
                Optional();
                restaurantAll();
                groceriesAll();
                editor.commit();
            }
            else{
                mMap.clear();
                SharedPreferences.Editor editor = wmbPreference.edit();
                editor.putBoolean("FIRSTRUN", true);
                editor.commit();
              //  Toast.makeText(getActivity(),"Clear Markers",Toast.LENGTH_SHORT).show();
            }
        }
        if(i.equalsIgnoreCase("1"))
        {
            spinnerItem.setVisibility(View.GONE);
            String Railway = "railway";
            mMap.clear();
            String url = getUrl(lat, longs, Railway);
            Object[] DataTransfer = new Object[2];
            DataTransfer[0] = mMap;
            DataTransfer[1] = url;
            Log.d("onClick", url);
            GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
            getNearbyPlacesData.execute(DataTransfer);
          /*  mMap.clear();
            spinnerItem.setVisibility(View.GONE);
            JSONObject object = new JSONObject();
            try {
                object.put("token",sharedPrefs.getToken());
                Log.e("obJE=",object.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("error",e.toString());
            }
            new OptionalMapTask().execute(object);*/
        }
    }
    public void restaurantAll(){
        String Restaurant = "restaurant";
        String url = getUrl(lat, longs, Restaurant);
        Object[] DataTransfer = new Object[2];
        DataTransfer[0] = mMap;
        DataTransfer[1] = url;
        Log.d("onClick", url);
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        getNearbyPlacesData.execute(DataTransfer);
    }

    public void groceriesAll(){
        String Groceries = "groceries";
        String url = getUrl(lat, longs, Groceries);
        Object[] DataTransfer = new Object[2];
        DataTransfer[0] = mMap;
        DataTransfer[1] = url;
        Log.d("onClick", url);
        GetNearbyShoppingPlacesData getNearbyPlacesData = new GetNearbyShoppingPlacesData();
        getNearbyPlacesData.execute(DataTransfer);
    }

    public void POI(){
        JSONObject object = new JSONObject();
        try {
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new POIMapTask().execute(object);
    }

    public void Optional(){
        JSONObject object = new JSONObject();
        try {
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new OptionalMapTask().execute(object);
    }
}
