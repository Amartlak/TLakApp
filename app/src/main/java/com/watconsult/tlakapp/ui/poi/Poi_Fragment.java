package com.watconsult.tlakapp.ui.poi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.WIFIInternetConnectionDetector;
import com.watconsult.tlakapp.adapter.CityDialogAdapter;
import com.watconsult.tlakapp.adapter.DateDialogAdpter;
import com.watconsult.tlakapp.adapter.PoiDetailAdapter;
import com.watconsult.tlakapp.model.MainPOI;
import com.watconsult.tlakapp.model.PoiItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import static com.watconsult.tlakapp.LoginConfirmActivity.token;

public class Poi_Fragment extends Fragment {
    JSONObject poidata;
   // String tokens = token;
    String message;
    private ArrayList<PoiItem> poiListItems = new ArrayList<PoiItem>();
    private ArrayList<PoiItem> locationItems = new ArrayList<PoiItem>();
    private ArrayList<String> locationList = new ArrayList<String>();
    private ArrayList<String> dayList = new ArrayList<String>();
    ListView cityList,dateList;
   NewOnePOIMain newOnePOIMain;
  //  ListView poiList;
  ProgressDialog progressDialog;
    SharedPrefs sharedPrefs;
    private static String poiimage=null;
    PoiDetailAdapter poIDetailAdapter;
    CityDialogAdapter cityDialogAdapter;
    POI_CityAdapter poi_cityAdapter;
    TextView km, open, text, hour_text, hour, length_text, length, direction, kms, poi_detail;
    WIFIInternetConnectionDetector cd;
    Boolean isConnectionExist = false;
    LinearLayout sortcity,sortdate;
    View root;
    DateDialogAdpter dateDialogAdpter;
    public static String PoiName;
    AlertDialog alertDialog;
    GridView poiList;
    String location;
    POI_DayAdapter poi_dayAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       if(savedInstanceState!=null){
           progressDialog = new ProgressDialog(getActivity());
           progressDialog.dismiss();
       }
       /* slideshowViewModel =
                ViewModelProviders.of(this).get(PoiViewModel.class);*/
        View root = inflater.inflate(R.layout.poilist_item, container, false);
        cd = new WIFIInternetConnectionDetector(getActivity());
        isConnectionExist = cd.checkMobileInternetConn();
        poiList = (GridView) root.findViewById(R.id.poi_list);
        sharedPrefs = new SharedPrefs(getActivity());
        sortcity =(LinearLayout) root.findViewById(R.id.sortcity);
        sortdate =(LinearLayout) root.findViewById(R.id.sortdate);
       sortingCity();
       sortingDate();
        if (!isConnectionExist) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
            a_builder.setMessage(R.string.NoInternet)
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //  name.setText(null);
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Error");
            alert.show();
        }
        try {
            poidata = new JSONObject();
            System.out.println("HI" + sharedPrefs.getToken());
            poidata.put("token", sharedPrefs.getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new PoIDataTask().execute(poidata);
      /*  TextView text = root.findViewById(R.id.poi_text);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        text.setTypeface(custom_font);
        TextView poi_sub_text = root.findViewById(R.id.poi_sub_text);
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        poi_sub_text.setTypeface(custom_font2);
        kms = root.findViewById(R.id.kms);
        Typeface custom1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        kms.setTypeface(custom1);
        direction = root.findViewById(R.id.direction);
        Typeface custom2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        direction.setTypeface(custom2);
        length = root.findViewById(R.id.length);
        Typeface custom3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        length.setTypeface(custom3);
        length_text = root.findViewById(R.id.length_text);
        Typeface custom4 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        length_text.setTypeface(custom4);
        hour_text = root.findViewById(R.id.hour_text);
        Typeface custom5 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        hour_text.setTypeface(custom5);
        hour = root.findViewById(R.id.hour);
        Typeface custom6 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        hour.setTypeface(custom6);
        poi_detail = root.findViewById(R.id.poi_detail);
        Typeface custom7 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        poi_detail.setTypeface(custom7);*/
       /* final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
    public class PoIDataTask extends AsyncTask<JSONObject, Void, String> {


        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(JSONObject... param) {
            return ServiceProviderClass.getPOIData(param[0]);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss--------" + result);
            progressDialog.dismiss();
            parseResultAsPerOrgStandards(result);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void parseResultAsPerOrgStandards(String result) {
        if (result != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(result);
                Gson obj = new Gson();
                newOnePOIMain = obj.fromJson(result,NewOnePOIMain.class);

                String error = String.valueOf(jsonObject.getBoolean("error"));
               //if(jsonObject.getString("error") == false){

                message = jsonObject.getString("message");
                String typeImagePath = jsonObject.getString("typeImagePath");
               // System.out.println("image"+image);
                JSONObject jsonObject1 = jsonObject.getJSONObject("traveler");
                System.out.println("jsonObject1-----------"+jsonObject1);
                String token = jsonObject1.getString("token");
                String tpackageId = String.valueOf(jsonObject1.getInt("tour_package_id"));
                JSONArray jsonArray = jsonObject.getJSONArray("locationPoi");
                final int numberofitems = jsonArray.length();
                for (int i = 0; i < numberofitems; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    PoiItem poiItemss = new PoiItem();
                    poiItemss.setLocationName(jsonObj.getString("locacionName"));
                    poiItemss.setDayNumber(jsonObj.getString("dayNumber"));
                    System.out.println("jsonObj=========="+jsonObj);
                    location = jsonObj.getString("locacionName");
                    String dayNumber = jsonObj.getString("dayNumber");
                    locationItems.add(poiItemss);
                    System.out.println("data=="+jsonObj.getString("locacionName"));
                    if(locationList.contains(jsonObj.getString("locacionName"))){

                    }else{
                        System.out.println("data1=="+jsonObj.getString("locacionName"));
                        locationList.add(jsonObj.getString("locacionName"));
                    }

                    if(dayList.contains(jsonObj.getString("dayNumber"))){

                    }else{
                        System.out.println("Day Number=="+jsonObj.getString("dayNumber"));
                        dayList.add(jsonObj.getString("dayNumber"));
                    }
                    JSONArray jsonArr = jsonObj.getJSONArray("poi");
                    final int numberofitem = jsonArr.length();
                    for (int j = 0; j < numberofitem; j++) {
                        PoiItem poiItem = new PoiItem();
                        JSONObject jsonObjs = jsonArr.getJSONObject(j);
                        System.out.println("jsonObjs====="+jsonObjs);
                        PoiName = jsonObjs.getString("poiName");
                        poiItem.setPoiName(jsonObjs.getString("poiName"));
                        poiItem.setLocationName(jsonObjs.getString("locationName"));
                        poiItem.setCountryName(jsonObjs.getString("countryName"));
                        poiItem.setPoiImage(jsonObjs.getString("poiImage"));
                        poiItem.setLatitude(jsonObjs.getString("latitude"));
                        poiItem.setLongitude(jsonObjs.getString("longitude"));
                        poiItem.setPoiDescription(jsonObjs.getString("poiDescription"));
                    //    String pois = jsonObj.getString("poiAddress");
                        poiItem.setTypeIcon(jsonObjs.getString("typeIcon"));
                        poiItem.setPoiType(jsonObjs.getString("poiType"));
                        poiListItems.add(poiItem);
                    }

                   /* poiItem.setPoiName(jsonObj.getString("poiName"));
                    poiItem.setPoiAddress(jsonObj.getString("poiAddress"));
                    poiItem.setTypeIcon(jsonObj.getString("typeIcon"));
                    poiListItems.add(poiItem);*/
                }
               /* JSONArray jsonArray = jsonObject.getJSONArray("poi");
                final int numberofitems = jsonArray.length();
                for (int i = 0; i < numberofitems; i++) {
                    PoiItem poiItem = new PoiItem();
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    System.out.println("json---"+jsonObject1);
                    poiItem.setPkgId(Integer.parseInt(String.valueOf(jsonObj.getInt("pkgId"))));
                    poiItem.setCountryName(jsonObj.getString("countryName"));
                    poiItem.setLocationName(jsonObj.getString("locationName"));
                    poiItem.setPoiName(jsonObj.getString("poiName"));
                    poiItem.setLatitude(jsonObj.getString("latitude"));
                    poiItem.setLongitude(jsonObj.getString("longitude"));
                    poiItem.setPoiDescription(jsonObj.getString("poiDescription"));
                    poiItem.setPoiAddress(jsonObj.getString("poiAddress"));
                    poiItem.setPoiType(jsonObj.getString("poiType"));
                    poiItem.setPoiImage(jsonObj.getString("poiImage"));
                    poiItem.setTypeIcon(jsonObj.getString("typeIcon"));
                   *//* poiItem.setLocationName(jsonObj.getString("locationName"));
                    poiItem.setPkgId(Integer.parseInt(String.valueOf(jsonObj.getInt("pkgId"))));
                    poiItem.setPoiName(jsonObj.getString("poiName"));
                    poiItem.setCountryName(jsonObj.getString("countryName"));
                    poiItem.setLatitude(jsonObj.getString("latitude"));
                    poiItem.setLongitude(jsonObj.getString("longitude"));
                    poiItem.setPoiDescription(jsonObj.getString("poiDescription"));
                    poiItem.setPoiImage(jsonObj.getString("poiImage"));
                    poiItem.setPoiAddress(jsonObj.getString("poiAddress"));
                    poiItem.setPoiType(jsonObj.getString("poiType"));
                //    poiItem.setRadius(jsonObj.getString("radius"));
                    poiItem.setTypeIcon(jsonObj.getString("typeIcon"));
*//*
                    poiListItems.add(poiItem);
                }*/
                if(message.equalsIgnoreCase("true"))
                {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
                    a_builder.setMessage(message)
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                 //   passcode.setText(null);
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Error");
                    alert.show();
                    alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            poIDetailAdapter = new PoiDetailAdapter(getActivity(), R.layout.poi_layout, poiListItems);
            poiList.setAdapter(poIDetailAdapter);
        }
    }
    public void sortingCity(){
        sortcity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.city_layout, viewGroup, false);
                TextView city = (TextView) dialogView.findViewById(R.id.city);
                ImageView cancel = (ImageView) dialogView.findViewById(R.id.cancel);
                Button submit = (Button) dialogView.findViewById(R.id.sort);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     //   alertDialog.dismiss();
                        List<Pous> poiItemArrayList =new ArrayList<>();
                        System.out.println("first-------"+newOnePOIMain.getLocationPoi().size());

                        for (int i=0;i<newOnePOIMain.getLocationPoi().size();i++){
                            System.out.println("first----dd-sssss--"+newOnePOIMain.getLocationPoi().get(i).getLocacionName());

                            if(poi_cityAdapter.getFilterGridData().contains(newOnePOIMain.getLocationPoi().get(i).getLocacionName())){

                                poiItemArrayList.addAll(newOnePOIMain.getLocationPoi().get(i).getPoi());
                                System.out.println("first-----sssss--");

                            }
                        }
                        NewFilterAdpter newfilter = new NewFilterAdpter(getActivity(), R.layout.poi_layout, poiItemArrayList);
                        poiList.setAdapter(newfilter);
                     //   poi_cityAdapter = new POI_CityAdapter(getActivity(), R.layout.sort_city_layout, poiItemArrayList);
                       // cityList = (ListView) dialogView.findViewById(R.id.citylist);
                        //cityList.setAdapter(poi_cityAdapter);
                        alertDialog.dismiss();
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(dialogView);
                 alertDialog = builder.create();
                alertDialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.setCancelable(false);
              /*  List<ArrayList<PoiItem>> list = Arrays.asList(locationItems);
                        //Arrays.asList(locationItems);
                list.stream().distinct().forEach(System.out::println);*/
            /*  Gson onGson = new Gson();
              Type listType = new TypeToken<HashSet<LocationPous>>(){}.getType();
                Set<LocationPous> lpList = new Gson().fromJson(String.valueOf(onGson), listType);*/
                poi_cityAdapter = new POI_CityAdapter(getActivity(), R.layout.sort_city_layout, locationList);
                cityList = (ListView) dialogView.findViewById(R.id.citylist);
                cityList.setAdapter(poi_cityAdapter);
            }
        });
    }
    public void sortingDate(){
        sortdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.date_layout, viewGroup, false);
             //   TextView city = (TextView) dialogView.findViewById(R.id.citys);
                ImageView cancel = (ImageView) dialogView.findViewById(R.id.cancel);
                Button submit = (Button) dialogView.findViewById(R.id.sorts);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //   alertDialog.dismiss();


                        List<Pous> poiItemArrayList =new ArrayList<>();
                        System.out.println("first-------"+newOnePOIMain.getLocationPoi().size());

                        for (int i=0;i<newOnePOIMain.getLocationPoi().size();i++){
                            System.out.println("first---d-dd-sssss--"+newOnePOIMain.getLocationPoi().get(i).getLocacionName());

                            if(poi_dayAdapter.getFilterGridData().contains(newOnePOIMain.getLocationPoi().get(i).getDayNumber())){

                                poiItemArrayList.add((Pous) newOnePOIMain.getLocationPoi().get(i).getPoi());
                                System.out.println("first-----sssss--");

                            }
                        }
alertDialog.dismiss();
                        //   poi_cityAdapter = new POI_CityAdapter(getActivity(), R.layout.sort_city_layout, poiItemArrayList);
                        // cityList = (ListView) dialogView.findViewById(R.id.citylist);
                        //cityList.setAdapter(poi_cityAdapter);
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setView(dialogView);
                 alertDialog = builder.create();
                alertDialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.setCancelable(false);
               /* dateDialogAdpter = new DateDialogAdpter(getActivity(), R.layout.sort_date_layout, locationItems);
                cityList = (ListView) dialogView.findViewById(R.id.datelist);
                cityList.setAdapter(dateDialogAdpter);*/

                poi_dayAdapter = new POI_DayAdapter(getActivity(), R.layout.sort_date_layout, dayList);
                cityList = (ListView) dialogView.findViewById(R.id.datelist);
                cityList.setAdapter(poi_dayAdapter);


            }
        });
    }
}
