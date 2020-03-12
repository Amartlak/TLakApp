package com.watconsult.tlakapp.ui.placard;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.watconsult.tlakapp.ui.hotel.HotelsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PlaCard extends Fragment{

    Button viewname;
    TextView hotelname, hotelplace;
    WIFIInternetConnectionDetector cd;
    String message;
    Boolean isConnectionExist = false;
    HotelAdapter hotelAdapter;
    ListView hotellistView;
    SharedPrefs sharedPrefs;
    private ArrayList<HotelItem> hotelListItems = new ArrayList<HotelItem>();
    private PlaCardViewModel placardViewModel;
    TextView txt_title, txt_desc;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        placardViewModel = ViewModelProviders.of(this).get(PlaCardViewModel.class);
        View root = inflater.inflate(R.layout.placard, container, false);

        txt_title = (TextView) root.findViewById(R.id.text_title);
        txt_desc = (TextView) root.findViewById(R.id.text_description);
        cd = new WIFIInternetConnectionDetector(getActivity());
        isConnectionExist = cd.checkMobileInternetConn();
        sharedPrefs = new SharedPrefs(getActivity());
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

        JSONObject object = new JSONObject();
        try {
            object.put("token", sharedPrefs.getToken());
            Log.e("obJE=", object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error", e.toString());
        }
        new HotelTask().execute(object);
        return root;
    }


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
            return ServiceProviderClass.getPlaCard(param[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss---dd-----" + result);
            progressDialog.dismiss();
            parseResultAsPerOrgStandards(result);
        }
    }

    private void parseResultAsPerOrgStandards(String result) {
        if (result != null) {
            JSONObject jsonObject = null;
          /*  "placard": {
                "placardName": "Dook printed Red T-shirt",
                        "placardDetail": "Welcome to Moscow by Dook Travels"*/

            try {
                jsonObject = new JSONObject(result);
               Log.e("jsonObject",jsonObject.toString());
                String error = String.valueOf(jsonObject.getBoolean("error"));
                message = jsonObject.getString("message");
                JSONObject jObj = jsonObject.getJSONObject("placard");
                Log.e("txt_title",jObj.toString());
                String placardName = jObj.getString("placardName");
                String placardDetail = jObj.getString("placardDetail");

                Log.e("txt_title",jObj.getString("placardName"));
                Log.e("txt_desc",jObj.getString("placardDetail"));
                Log.d("txt_desc",jObj.getString("placardDetail"));
                Log.d("txt_desc",jObj.getString("placardDetail"));

                txt_title.setText(placardName);
                txt_desc.setText(placardDetail);

                Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
                txt_title.setTypeface(custom_font1);

                Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
                txt_desc.setTypeface(custom_font2);

               /* JSONObject jObj = jsonObject.getJSONObject("traveler");
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
                }*/
                if (message.equalsIgnoreCase("true")) {
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //hotelAdapter = new HotelAdapter(getActivity(), R.layout.hotel_layout, hotelListItems);
            //hotellistView.setAdapter(hotelAdapter);
           // txt_title.setText(placardName);

        }
    }

}
