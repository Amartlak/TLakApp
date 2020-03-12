package com.watconsult.tlakapp.ui.hotel;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.WIFIInternetConnectionDetector;
import com.watconsult.tlakapp.adapter.HotelAdapter;
import com.watconsult.tlakapp.model.HotelItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HotelsFragment extends Fragment {
Context context;
    private HotelsViewModel toolsViewModel;
    Button viewname;
    TextView hotelname,hotelplace;
    WIFIInternetConnectionDetector cd;
    String message;
    Boolean isConnectionExist = false;
    HotelAdapter hotelAdapter;
    ListView hotellistView;
    SharedPrefs sharedPrefs;
    private ArrayList<HotelItem> hotelListItems = new ArrayList<HotelItem>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.hotel_list, container, false);
        hotellistView = (ListView) root.findViewById(R.id.hotellist);
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
     //   CardView cardView = (CardView) root.findViewById(R.id.card);
      //  context = getContext();
      /*  hotelname = (TextView) root.findViewById(R.id.hotel_name);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Bold.ttf");

        hotelname.setTypeface(custom_font);
        hotelplace = (TextView) root.findViewById(R.id.hotel_place);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");

        hotelplace.setTypeface(custom_font1);*/
     /*  Button viewname = root.findViewById(R.id.viewname);
        viewname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              HotelDetailFragment a2Fragment = new HotelDetailFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.change_hotel, a2Fragment).commit();

                System.out.println("bjkdfkjdsbfkjdskjfdskjfdkjshfkjdshkfh");
            }
        });*/
    /* final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
      //  cardView.setCardElevation(getPixelsFromDPs(8));
        JSONObject object = new JSONObject();
        try {
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new HotelTask().execute(object);
        return root;
    }
  /*  protected int getPixelsFromDPs(int dps){
        Resources r = context.getResources();
        int  px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
    }*/
    public class HotelTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
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
            return ServiceProviderClass.getHotel(param[0]);
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
                JSONArray jsonArray = jsonObject.getJSONArray("hotels");
                System.out.println("jsonArray =="+jsonArray);
                final int numberofitems = jsonArray.length();
                for (int i = 0; i < numberofitems; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    HotelItem item = new HotelItem();
                    item.setHotelId(jsonObj.getString("hotelId"));
                    item.setHotelName(jsonObj.getString("hotelName"));
                    item.setLocation(jsonObj.getString("location"));
                    item.setHotelImage(jsonObj.getString("hotelImage"));
                    hotelListItems.add(item);
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
            hotelAdapter = new HotelAdapter(getActivity(), R.layout.hotel_layout, hotelListItems);
            hotellistView.setAdapter(hotelAdapter);
        }
    }
}