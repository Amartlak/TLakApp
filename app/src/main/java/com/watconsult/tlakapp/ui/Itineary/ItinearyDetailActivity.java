package com.watconsult.tlakapp.ui.Itineary;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.WIFIInternetConnectionDetector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ItinearyDetailActivity extends AppCompatActivity {
    SharedPrefs sharedPrefs;
    TextView tour_heading,sub_heading,day,descrip,descriptions,inclusion,inclusiondetail,exclusion,exclusion_detail;
    String DOP;
    int night;
    String itinearyImagePath=null;
    String ItinearyImage = null;
    int days;
   ImageView imageView;
//RoundedImageView imageView;
    private static String sResponse = null;
    String finalImgPaths=null;
    String itinearyids;
    String daynumbers=null;
    String pkgName;
    WIFIInternetConnectionDetector cd;
    int itinearyId;
    ImageView back_btnx_txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        sharedPrefs=new SharedPrefs(this);
        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            itinearyids = String.valueOf(args.get("itinearyId"));
            System.out.println("itineary---s-------"+itinearyids);
        }
        JSONObject object = new JSONObject();
        try {
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        back_btnx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tour_heading = (TextView) findViewById(R.id.tour_heading);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");
        tour_heading.setTypeface(custom_font1);
        imageView = (ImageView) findViewById(R.id.img);
        sub_heading = (TextView) findViewById(R.id.sub_heading);
        Typeface custom_fon = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        sub_heading.setTypeface(custom_fon);
        inclusiondetail = (TextView) findViewById(R.id.inclusion_detail);
        Typeface custom2 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        inclusiondetail.setTypeface(custom2);
        day = (TextView) findViewById(R.id.day);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Medium.ttf");
        day.setTypeface(custom_font);
        descrip = (TextView) findViewById(R.id.descriptionHeading);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Medium.ttf");
        descrip.setTypeface(custom_font2);

        descriptions = (TextView) findViewById(R.id.description);
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
       // descriptions.setTypeface(custom_font3);
        //descriptions.setTypeface(Html.fromHtml(getString(custom_font3));
        descriptions.setText(Html.fromHtml(String.valueOf(custom_font3)));
        inclusion = (TextView) findViewById(R.id.inclusion);
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Medium.ttf");
        inclusion.setTypeface(custom_font4);
        exclusion = (TextView) findViewById(R.id.exclusion);
        Typeface custom_font5 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Medium.ttf");
        exclusion.setTypeface(custom_font5);
        exclusion_detail = (TextView) findViewById(R.id.exclusion_detail);
        Typeface custom_font6 = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        exclusion_detail.setTypeface(custom_font6);
        new ItinearyDetailTask().execute(object);
    }
    public class ItinearyDetailTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ItinearyDetailActivity.this, R.style.MyAlertDialogStyle);

            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return getItinearyDetail(param[0]);
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
                //      itinearyImagePath = jsonObject.getString("itinearyImagePath");
                JSONObject jsonObject1 = jsonObject.getJSONObject("traveler");
                String tourpackageid = String.valueOf(jsonObject1.getInt("pkgId"));
                String token = jsonObject1.getString("token");
                JSONObject jsonObject2 = jsonObject.getJSONObject("tourPackage");
                pkgName = jsonObject2.getString("pkgName");
                System.out.println("pkgName=="+pkgName);
                tour_heading.setText(pkgName);
                String companyName = jsonObject2.getString("companyName");
                int totalDays = jsonObject2.getInt("totalDays");
                int totalNight = jsonObject2.getInt("totalNight");
                sub_heading.setText(totalNight+" Nights "+ "/ "+ totalDays +" Days");
                JSONObject jsonObj = jsonObject.getJSONObject("itinearies");
                itinearyId = jsonObj.getInt("itinearyId");
                System.out.println("itinearyId===="+itinearyId);
                String dayNumber = jsonObj.getString("dayNumber");
                day.setText("Day "+dayNumber);
                String dayHeading = jsonObj.getString("dayHeading");
                String description = jsonObj.getString("description");
                descriptions.setText(description);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    descriptions.setText(Html.fromHtml(description,Html.FROM_HTML_MODE_COMPACT));
                } else {
                    descriptions.setText(Html.fromHtml(description,Html.FROM_HTML_MODE_COMPACT));
                }


                String inclusions = jsonObj.getString("inclusions");
                inclusiondetail.setText(inclusions);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    inclusiondetail.setText(Html.fromHtml(inclusions,Html.FROM_HTML_MODE_COMPACT));
                } else {
                    inclusiondetail.setText(Html.fromHtml(inclusions,Html.FROM_HTML_MODE_COMPACT));
                }

                String exclusions = jsonObj.getString("exclusions");
                exclusion_detail.setText(exclusions);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    exclusion_detail.setText(Html.fromHtml(exclusions,Html.FROM_HTML_MODE_COMPACT));
                } else {
                    exclusion_detail.setText(Html.fromHtml(exclusions,Html.FROM_HTML_MODE_COMPACT));
                }
                String itinearyImagess = jsonObj.getString("itinearyImage");
                System.out.println("==="+itinearyImagess);
                Log.e("XYZ",itinearyImagess);
               Picasso.with(this).load(itinearyImagess).into(imageView);
            }catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
    public String getItinearyDetail(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/detailitineary/"+itinearyids);
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
