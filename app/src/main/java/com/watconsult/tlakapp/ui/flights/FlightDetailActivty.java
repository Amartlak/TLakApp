package com.watconsult.tlakapp.ui.flights;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.adapter.FlightDetailAdapter;
import com.watconsult.tlakapp.model.FlightDetailItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FlightDetailActivty extends AppCompatActivity {
    TextView bn,times;
    String flightId;
    SharedPrefs sharedPrefs;
    private static String sResponse = null;
    View view_showhide;
    ListView flightList;
    private ArrayList<FlightDetailItem> flightListItems = new ArrayList<FlightDetailItem>();
    FlightDetailAdapter flightDetailAdapter;
    ImageView back_btnx_txt;
    TextView city,flighttime,flightno,flightinfo,dest_city,flight_dest_time,price,time,nonstop,name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newflightdetail_layout);
        sharedPrefs = new SharedPrefs(this);
        flightList = (ListView) findViewById(R.id.flightdetails);
        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);

        Bundle args = getIntent().getExtras();
        if (args != null) {
            flightId = String.valueOf(args.get("flightId"));
            System.out.println("flightId---s--f-----"+flightId);
        }
        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);
        back_btnx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        JSONObject object = new JSONObject();
        try {
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new FlightDetailTask().execute(object);
    }
    public class FlightDetailTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(FlightDetailActivty.this, R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return getFlightDetails(param[0]);
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
                //   message = jsonObject.getString("message");
                JSONObject jObj = jsonObject.getJSONObject("traveler");
                //  int pkgId = jObj.getInt("pkgId");
                //   String tenantId = jObj.getString("tenantId");
                //        String token = jObj.getString("token");
                //  System.out.println("token==="+token);
                JSONObject jsonObject1 = jsonObject.getJSONObject("flights");
                JSONArray jsonArray = jsonObject1.getJSONArray("flightTickets");
                for(int i=0;i< jsonArray.length();i++)
                {
                    JSONObject jsonObjs = jsonArray.getJSONObject(i);
                    FlightDetailItem items = new FlightDetailItem();
                    items.setTicket(jsonObjs.getString("ticket"));
                    String ticket = jsonObjs.getString("ticket");
                    System.out.println("ticket==========="+ticket);
                    flightListItems.add(items);
                }
               /* String hotelId = jsonObject1.getString("hotelId");
                String hotelName = jsonObject1.getString("hotelName");
                System.out.println("hotelName====="+hotelName);
                String location = jsonObject1.getString("location");
                hotel_name.setText(hotelName+", "+location);
                hotelplace.setText(hotelName+", "+location);*/
             /*   String totalRoom = jsonObject1.getString("totalRoom");
                String roomType = jsonObject1.getString("roomType");
                String tvAvailable = jsonObject1.getString("tvAvailable");
                String acAvailable = jsonObject1.getString("acAvailable");
                String address = jsonObject1.getString("address");
                addres.setText(address);
                String descriptions = jsonObject1.getString("description");
                description.setText(descriptions);
                String hotelImage = jsonObject1.getString("hotelImage");
                Picasso.with(getActivity()).load(hotelImage).into(roundedImageView);*/

             /*   String hotelPasses = jsonObject1.getString("hotelPass");
                System.out.println("hotelPasses==="+hotelPasses);*/
                JSONArray jsonArray1 = jsonObject1.getJSONArray("people");
                final int numberofitems = jsonArray1.length();
                for (int i = 0; i < numberofitems; i++) {
                    JSONObject jsonObj = jsonArray1.getJSONObject(i);
                    FlightDetailItem item = new FlightDetailItem();
                    item.setPeopleName(jsonObj.getString("peopleName"));
                    flightListItems.add(item);
                }

                /*JSONArray jsonArray1 = jsonObject1.getJSONArray("sockets");
                final int numberofitem = jsonArray.length();
                for (int i = 0; i < numberofitem; i++) {
                    JSONObject jsonObjs = jsonArray1.getJSONObject(i);
                    HotelDetailItem item = new HotelDetailItem();
                    item.setPeopleName(jsonObjs.getString("socketType"));
                }*/
               /* if(message.equalsIgnoreCase("true")){
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
                }*/
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
            flightDetailAdapter = new FlightDetailAdapter(this, R.layout.list_detail_layout, flightListItems);
            flightList.setAdapter(flightDetailAdapter);
        }
    }

    private String getFlightDetails(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/flightdetail/"+flightId);
            System.out.println("url==="+url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true); // this will tell that you will read "something"
            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestMethod("POST"); // http verb
            urlConnection.setRequestProperty("Content-Type", "application/json");
            OutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
//            wr.writeBytes(jsonObject.toString());
            wr.write(jsonObject.toString().getBytes());
            wr.flush();
            InputStream in = urlConnection.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            sResponse = total.toString();
            wr.close();
            in.close();
            urlConnection.disconnect();
        } catch (Exception e) {
//            return e.getMessage();
            Log.e("PostComment Exception", e.toString());
        }
        System.out.println("sssss"+sResponse);
        return sResponse;

    }
}
