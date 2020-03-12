package com.watconsult.tlakapp.ui.Itineary;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;

import com.watconsult.tlakapp.ServiceProviderClass;
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


public class ItnearyFragmentDetail extends Fragment {
    TextView tour_heading,sub_heading,day,descrip,descriptions,inclusion,inclusiondetail,exclusion,exclusion_detail;
    String DOP;
    int night;
    String itinearyImagePath=null;
    String ItinearyImage = null;
    int days;
    ImageView imageView;
    SharedPrefs sharedPrefs;
    private static String sResponse = null;
    String finalImgPaths=null;
    String itinearyids;
    String daynumbers=null;
    String pkgName;
    WIFIInternetConnectionDetector cd;
    int itinearyId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.itinery_detail_layout, container, false);
        sharedPrefs=new SharedPrefs(getActivity());
        Bundle args = getArguments();
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
       /* Bundle args = getArguments();
        if (args != null) {
            night =  args.getInt("tourtnight");
             days = args.getInt("tourday");

            finalImgPaths = args.getString("finalImgPath");
            System.out.println("hdfsgjds==="+finalImgPaths);
            *//* String imagepath = args.getString("itinearyImagePath");
             fullImagepath = imagepath+"/"+imagep;
            System.out.println("hdfsgjds==="+imagepath+imagep);*//*
            System.out.println("daynight ss===="+night   +  days);*/
        tour_heading = (TextView) root.findViewById(R.id.tour_heading);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Bold.ttf");
        tour_heading.setTypeface(custom_font1);
      /* imageView = (ImageView) root.findViewById(R.id.img);
        Picasso.with(getContext()).load(finalImgPaths).into(imageView);
        day = (TextView) root.findViewById(R.id.day);
        if (args != null) {
            day.setText(args.getString("dayNumber"));
        }
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        day.setTypeface(custom_font);*/
        imageView = (ImageView) root.findViewById(R.id.img);
        sub_heading = (TextView) root.findViewById(R.id.sub_heading);
        Typeface custom_fon = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        sub_heading.setTypeface(custom_fon);
        inclusiondetail = (TextView) root.findViewById(R.id.inclusion_detail);
        Typeface custom2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        inclusiondetail.setTypeface(custom2);
        day = (TextView) root.findViewById(R.id.day);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        day.setTypeface(custom_font);
        descrip = (TextView) root.findViewById(R.id.descriptionHeading);
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        descrip.setTypeface(custom_font2);
        descriptions = (TextView) root.findViewById(R.id.description);
        Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        descriptions.setTypeface(custom_font3);
        inclusion = (TextView) root.findViewById(R.id.inclusion);
        Typeface custom_font4 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        inclusion.setTypeface(custom_font4);
        exclusion = (TextView) root.findViewById(R.id.exclusion);
        Typeface custom_font5 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        exclusion.setTypeface(custom_font5);
        exclusion_detail = (TextView) root.findViewById(R.id.exclusion_detail);
        Typeface custom_font6 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        exclusion_detail.setTypeface(custom_font6);
       /*  if (args != null) {
           //jj.setText(args.getString(totalNight+" Night "+ "/ "+ totalDays +" Days tour Package"));
            jj.setText(args.getString(night+"/"+ days + "Days tour Package"));
        }
        description = (TextView) root.findViewById(R.id.description);
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        exclusive.setTypeface(custom_font2);
        inclusion = (TextView) root.findViewById(R.id.inclusion);
        Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        inclusion.setTypeface(custom_font3);
        first = (TextView) root.findViewById(R.id.first);
        Typeface custom = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        first.setTypeface(custom);
        second = (TextView) root.findViewById(R.id.second);
        Typeface custom1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        second.setTypeface(custom1);
        inclusiondetail = (TextView) root.findViewById(R.id.inclusion_detail);
        Typeface custom2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        inclusiondetail.setTypeface(custom2);
        if (args != null) {
            inclusiondetail.setText(args.getString("inclusion"));
        }*/
      new ItinearyDetailTask().execute(object);
        return root;
    }
    public class ItinearyDetailTask extends AsyncTask<JSONObject, Void, String> {
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
                sub_heading.setText(totalNight+" Night "+ "/ "+ totalDays +" Days tour Package");
                JSONObject jsonObj = jsonObject.getJSONObject("itinearies");
                itinearyId = jsonObj.getInt("itinearyId");
                System.out.println("itinearyId===="+itinearyId);
                String dayNumber = jsonObj.getString("dayNumber");
                day.setText("Day "+dayNumber);
                String dayHeading = jsonObj.getString("dayHeading");
                String description = jsonObj.getString("description");
                descriptions.setText(description);
                String inclusions = jsonObj.getString("inclusions");
                inclusiondetail.setText(inclusions);
                String exclusions = jsonObj.getString("exclusions");
                exclusion_detail.setText(exclusions);
                String itinearyImage = jsonObj.getString("itinearyImage");
                Picasso.with(getContext()).load(itinearyImage).into(imageView);
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
