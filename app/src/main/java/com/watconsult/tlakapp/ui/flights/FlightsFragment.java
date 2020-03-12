package com.watconsult.tlakapp.ui.flights;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.WIFIInternetConnectionDetector;
import com.watconsult.tlakapp.adapter.FlightAdapter;
import com.watconsult.tlakapp.login.LoginActivity;
import com.watconsult.tlakapp.model.FlightItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlightsFragment extends Fragment {
RelativeLayout relativeLayout;
    private Context mContext;
    private FlightsViewModel toolsViewModel;
    CardView cardView;
    TextView bn,times;
    Context context;
    SharedPrefs sharedPrefs;
    TextView city,flighttime,flightno,flightinfo,dest_city,flight_dest_time,price,time,nonstop;
    WIFIInternetConnectionDetector cd;
    Boolean isConnectionExist = false;
    ListView flightList;
    FlightAdapter flightAdapter;
    String message;
    private ArrayList<FlightItem> flightListItems = new ArrayList<FlightItem>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(FlightsViewModel.class);
        View root = inflater.inflate(R.layout.flight_list, container, false);
        mContext = getContext();
        cd = new WIFIInternetConnectionDetector(getActivity());
        isConnectionExist = cd.checkMobileInternetConn();
        sharedPrefs = new SharedPrefs(getActivity());
        if(!isConnectionExist){
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
        flightList = (ListView) root.findViewById(R.id.flightlist);
       /*
        city = (TextView) root.findViewById(R.id.city);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        city.setTypeface(custom_font);

        flighttime = (TextView) root.findViewById(R.id.flight_time);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Bold.ttf");
        flighttime.setTypeface(custom_font1);

        flightno = (TextView) root.findViewById(R.id.flight_no);
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        flightno.setTypeface(custom_font2);

        flightinfo = (TextView) root.findViewById(R.id.flight_info);
        Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        flightinfo.setTypeface(custom_font3);

        dest_city = (TextView) root.findViewById(R.id.dest_city);
        Typeface custom_font4 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        dest_city.setTypeface(custom_font4);

        flight_dest_time = (TextView) root.findViewById(R.id.flight_dest_time);
        Typeface custom_font5 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Bold.ttf");
        flight_dest_time.setTypeface(custom_font5);

        price = (TextView) root.findViewById(R.id.price);
        Typeface custom_font6 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Bold.ttf");
        price.setTypeface(custom_font6);

        time = (TextView) root.findViewById(R.id.timess);
        Typeface custom_font7 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        time.setTypeface(custom_font7);

        nonstop = (TextView) root.findViewById(R.id.nonstop);
        Typeface custom_font8 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        nonstop.setTypeface(custom_font8);*/

        /*cardView = (CardView) root.findViewById(R.id.card);
        context = getContext();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlightDetail_Fragment a2Fragment = new FlightDetail_Fragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.flight_change, a2Fragment).commit();

            }
        });*/
       /* relativeLayout = (RelativeLayout) root.findViewById(R.id.click);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              *//*  FlightDetail_Fragment a2Fragment = new FlightDetail_Fragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.docDetailList, a2Fragment).commit();*//*
            }
        });*/
    /* final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
      //  cardView.setCardElevation(getPixelsFromDPs(10));
        JSONObject object = new JSONObject();
        try {
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new FlightTask().execute(object);
        return root;
    }
   /* protected int getPixelsFromDPs(int dps){
        Resources r = context.getResources();
        int  px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
    }*/
    public class FlightTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity(),R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return ServiceProviderClass.getFlight(param[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss---dd-----"+result);
            progressDialog.dismiss();
            parseResultAsPerOrgStandards(result);
        }
    }
    private void parseResultAsPerOrgStandards(String result) {
        if (result != null) {
            JSONObject jsonObject = null;
            try {
             jsonObject = new JSONObject(result);
             String error = String.valueOf(jsonObject.getBoolean("error"));
             message = jsonObject.getString("message");
             JSONObject jObj = jsonObject.getJSONObject("traveler");
             int pkgId = jObj.getInt("pkgId");
             String tenantId = jObj.getString("tenantId");
             String token = jObj.getString("token");
                System.out.println("token==="+token);

                JSONArray jsonArray = jsonObject.getJSONArray("flight");
                System.out.println("jsonArray =="+jsonArray);
               final int numberofitems = jsonArray.length();
                for (int i = 0; i < numberofitems; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    FlightItem item = new FlightItem();
                    item.setFlightId(jsonObj.getString("flightId"));
                    item.setFlightNumber(jsonObj.getInt("flightNumber"));
                    item.setAirlinCode(jsonObj.getString("airlinCode"));
                    item.setDepartureDate(jsonObj.getString("departureDate"));
                    item.setDepartureTime(jsonObj.getString("departureTime"));
                    item.setDepartureIata(jsonObj.getString("departureIata"));
                    item.setDepartureTerminal(jsonObj.getString("departureTerminal"));
                    item.setArrivalDate(jsonObj.getString("arrivalDate"));
                    item.setArrivalTime(jsonObj.getString("arrivalTime"));
                    item.setArrivalIata(jsonObj.getString("arrivalIata"));
                    item.setTotalTime(jsonObj.getString("totalTime"));
                    flightListItems.add(item);
                }
                if(message.equalsIgnoreCase("true")){
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
                    a_builder.setMessage(message)
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Error");
                    alert.show();
                    alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                }
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
            flightAdapter = new FlightAdapter(getActivity(), R.layout.flight_layout, flightListItems);
            flightList.setAdapter(flightAdapter);
            }
    }
}
