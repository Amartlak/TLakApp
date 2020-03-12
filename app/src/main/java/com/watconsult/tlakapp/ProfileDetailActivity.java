package com.watconsult.tlakapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfileDetailActivity extends AppCompatActivity {
String totalDeparture,travelerId,travelerName,travelerEmail,travelerPhone,travelerDOB,travelerAddress;
    TextView  place, number, tour_text, dest_number, dest_text,
            explore_number, explre_text, bio, user_name, biodata, email, dob, address;
    EditText logout,emails,dobs,adreees;
    EditText names,phon,name;
    Button update;
    ImageView back_btnx_txt;

    TextView prfile_name,tour_number;
    private static String sResponse = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_detail);
        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);

        back_btnx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        travelerId = getIntent().getStringExtra("travelerId");
        totalDeparture = getIntent().getStringExtra("totalDeparture");
        travelerName = getIntent().getStringExtra("travelerName");
        travelerEmail = getIntent().getStringExtra("travelerEmail");
        travelerPhone = getIntent().getStringExtra("travelerPhone");
        travelerDOB = getIntent().getStringExtra("travelerDOB");
        travelerAddress = getIntent().getStringExtra("travelerAddress");

        System.out.println("totalDeparture---s-------"+totalDeparture);

        dobs = (EditText) findViewById(R.id.dobs);
        dobs.setText(travelerDOB);
        dobs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        adreees = (EditText) findViewById(R.id.adreees);
        adreees.setText(travelerAddress);
        adreees.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        names = (EditText) findViewById(R.id.names);
        names.setText(travelerName);
        names.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        prfile_name = (TextView) findViewById(R.id.user_profile_name);
        prfile_name.setText(travelerName);
        tour_number = (TextView) findViewById(R.id.tour_number);
        tour_number.setText(totalDeparture);
        emails = (EditText) findViewById(R.id.emails);
        emails.setText(travelerEmail);
        emails.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        phon = (EditText) findViewById(R.id.phone);
        phon.setText(travelerPhone);
        phon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        update =(Button) findViewById(R.id.edit);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tid = travelerId;
                String email = emails.getText().toString();
                String phone = phon.getText().toString();
                String dob = dobs.getText().toString();
                String addres = adreees.getText().toString();
                String picname = "emp.jpg";
                String picture = "";

                JSONObject object = new JSONObject();
                try {
                    object.put("travelerId",tid);
                    object.put("travelerEmail",email);
                    object.put("travelerPhone",phone);
                    object.put("travelerDOB",dob);
                    object.put("travelerAddress",addres);
                    object.put("picturename",picname);
                    object.put("picture","iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAABFgAAARYBhMcjdgAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAALzSURBVFiFvddNiFZVGAfw32M5I1M45cxkSUbW1C6nIggCaxqZSAIX7koQ0kWCILQwaNOmXZsWJZm0cTNEEW4Cp2zRxyKCIpCMog+KorKUSvIjBZ8W9xC31/G+972v9MDhveec5/k///ec5/zPvZGZulpE7MRUZj7bFWNZ5+yVXYNrhwGIYVYAIiJyCJDGFYiI+Yi4o8mnKXlETEfE5sYcTeQjYgHXZ+ZcE8gw8f0IjOHmzPy8I4Hb8Gtm/tmJQIsE2zGGvZ3rIDMHbqrKfwPncBqHsLoTVkcCL+M3bMBd+B6v/S8EVCfnFzxdG3scpzA2KF4XIboTq3G4NnZYVQsPDAp2EYGImI2IlQ0xX+AMZmpjM7iATy8VFBErImL+oome5b2pgD/VZxsO4hOswtV4B+/1idmB85hprAGVvkcfsHvwA77GZziG+9ucnt6xzjoQEatUp2EM2zPzWCecYYToctiw1/HQdmVbx4gYwb24FbfUGnxba9/go8w81wq33xZExAR2YhduQOKnWkI1MmsQ+Bl7sS8zTzQmaKjYSbyk0voz2I+7MdoQM1p89peY0wVjciApxhSO4DieUb33DSrZUyX2eMFaEqMp+XeY7pNkHdb18ZkuWEuS6HUeKY5fYm2Lf3kAB1r4rS2YRzDSRGBbKbL50p/F1gbgcYw3zG/FbHl+qGBvayKwiKO1/h6cxUTpL2B3bX4LNtf6u7FQnidK7J7a/FEs1nP26sDtWBYRi6U/Xir7UbyouoT+gojYhReQEfFEZr6Cr1RHUIkZxZaI2FjGrio5/rX/6ED50nm4h9QFPJ+ZH9T8Rkuigyox24Qb6+ITERvwpEoX6vZWZu5bkkCTRcQOPIaVOIk5rMdy1dX8Lq7D33g1M59rhduGQJHhk6o9/BgbVS+ma4rLj/hd9XK6XiXZ45l5vi/4AMLydkk0WforanPLy+8VeB+H2uIOsgVzeBMf4vVLuD2CB7GpXjOXZQVquvCH6jwv1U7gvkEw/wEiGjmfg2BwsgAAAABJRU5ErkJggg==");
                    Log.e("obJE=",object.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error",e.toString());
                }
                new ProfileTask().execute(object);
            }
        });
        }

    public class ProfileTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ProfileDetailActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return getProfileDetail(param[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss---dds-----"+result);
            progressDialog.dismiss();
            parseResultAsPerOrgStandardss(result);
        }
    }
    private void parseResultAsPerOrgStandardss(String result) {
        if (result != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(result);
                String error = String.valueOf(jsonObject.getBoolean("error"));
                String message = jsonObject.getString("message");
                String travelerId = jsonObject.getString("travelerId");
                String token = jsonObject.getString("token");
                if(error.equalsIgnoreCase("false")){
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(ProfileDetailActivity.this);
                    a_builder.setMessage(message)
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("ProfileUpdate");
                    alert.show();
                    alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);

                }else if(error.equalsIgnoreCase("true")){
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(ProfileDetailActivity.this);
                    a_builder.setMessage(message)
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Profile Update");
                    alert.show();
                    alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                }
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
            //   flightAdapter = new FlightAdapter(getActivity(), R.layout.flight_layout, flightListItems);
            //  flightList.setAdapter(flightAdapter);
        }
    }
    private String getProfileDetail(JSONObject jsonObject) {
        try {
            URL url = new URL("http://account.tlakapp.com/tlak/api/updateprofile");

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