package com.watconsult.tlakapp.ui.hotel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.adapter.HotelDetailAdapter;
import com.watconsult.tlakapp.model.HotelDetailItem;

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

public class HotelDetailActivity extends AppCompatActivity {
    Context context;
    private static String sResponse = null;
    String message;
    SharedPrefs sharedPrefs;
    String hotelid;
    ListView numofUser;
    private ArrayList<HotelDetailItem> hotelListItems = new ArrayList<HotelDetailItem>();

    RoundedImageView roundedImageView;
    HotelDetailAdapter hotelDetailAdapter;
    TextView hotel_name,hotelplace,review,name,wifi,bed,backup,tv,ac,geyser,adress_text,addres,description;
ImageView back_btnx_txt;
 
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotelcards_layout);
        sharedPrefs = new SharedPrefs(this);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            hotelid = String.valueOf(args.get("hotelId"));
            System.out.println("hotelid---s-------"+hotelid);
        }
        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);
        back_btnx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        roundedImageView = (RoundedImageView)findViewById(R.id.hotelImg);
        numofUser = (ListView)findViewById(R.id.numofuser);
        hotel_name = (TextView)findViewById(R.id.hotel_name);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");
        hotel_name.setTypeface(custom_font);
        hotelplace = (TextView)findViewById(R.id.hotel_place);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        hotelplace.setTypeface(custom_font1);


        wifi = (TextView)findViewById(R.id.wifi);
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        wifi.setTypeface(custom_font4);

        bed = (TextView)findViewById(R.id.bed);
        Typeface custom_font5 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        bed.setTypeface(custom_font5);

        backup = (TextView)findViewById(R.id.backup);
        Typeface custom_font6 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        backup.setTypeface(custom_font6);

        tv = (TextView)findViewById(R.id.tv);
        Typeface custom_font7 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        tv.setTypeface(custom_font7);

        ac = (TextView)findViewById(R.id.ac);
        Typeface custom_font8 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        ac.setTypeface(custom_font8);

        geyser = (TextView)findViewById(R.id.geyser);
        Typeface custom_font9 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        geyser.setTypeface(custom_font9);

        adress_text = (TextView)findViewById(R.id.address_text);
        Typeface custom_font10 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Medium.ttf");
        adress_text.setTypeface(custom_font10);

        addres = (TextView)findViewById(R.id.addres);
        Typeface custom_font11 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        addres.setTypeface(custom_font11);

        description = (TextView)findViewById(R.id.hotel_descrp);
        Typeface custom_font12 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        description.setTypeface(custom_font12);
        JSONObject object = new JSONObject();
        try {
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new HotelDetailTask().execute(object);
    }
    public class HotelDetailTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(HotelDetailActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return getHotelDetails(param[0]);
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
                JSONObject jsonObject1 = jsonObject.getJSONObject("hotels");
                String hotelId = jsonObject1.getString("hotelId");
                String hotelName = jsonObject1.getString("hotelName");
                System.out.println("hotelName====="+hotelName);
                String location = jsonObject1.getString("location");
                hotel_name.setText(hotelName+", "+location);
                hotelplace.setText(hotelName+", "+location);
                String totalRoom = jsonObject1.getString("totalRoom");
                String roomType = jsonObject1.getString("roomType");
                String tvAvailable = jsonObject1.getString("tvAvailable");
                String acAvailable = jsonObject1.getString("acAvailable");
                String address = jsonObject1.getString("address");
                addres.setText(address);
                String descriptions = jsonObject1.getString("description");
                description.setText(descriptions);
                String hotelImage = jsonObject1.getString("hotelImage");
                Picasso.with(this).load(hotelImage).into(roundedImageView);

                // String hotelPasses = jsonObject1.getString("hotelPass");
                //   System.out.println("hotelPasses==="+hotelPasses);
                JSONArray jsonArray = jsonObject1.getJSONArray("peoples");
                final int numberofitems = jsonArray.length();
                for (int i = 0; i < numberofitems; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    HotelDetailItem item = new HotelDetailItem();
                    item.setPeopleName(jsonObj.getString("peopleName"));
                    //    item.setHotelPass(jsonObj.getString("hotelPass"));
                    hotelListItems.add(item);
                }

                JSONArray jsonArray1 = jsonObject1.getJSONArray("hotelPasses");
                final int numberofitem = jsonArray1.length();
                for (int i = 0; i < numberofitem; i++) {
                    JSONObject jsonObjs = jsonArray1.getJSONObject(i);
                    HotelDetailItem items = new HotelDetailItem();
                    items.setHotelPass(jsonObjs.getString("hotelPass"));
                    hotelListItems.add(items);
                }
                if(message.equalsIgnoreCase("true")){
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
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
            hotelDetailAdapter = new HotelDetailAdapter(this, R.layout.list_detail_layout, hotelListItems);
            numofUser.setAdapter(hotelDetailAdapter);
        }
    }
    private String getHotelDetails(JSONObject jsonObject) {

        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/hoteldetail/"+hotelid);
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
