package com.watconsult.tlakapp.ui.document.weather;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.WIFIInternetConnectionDetector;
import com.watconsult.tlakapp.adapter.WeatherAdapter;
import com.watconsult.tlakapp.model.MainWeather;
import com.watconsult.tlakapp.model.WeatherItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private ArrayList<WeatherItem> weatherList = new ArrayList<WeatherItem>();
    private ArrayList<WeatherItem> weatherLists = new ArrayList<WeatherItem>();
    private WeatherViewModel slideshowViewModel;
    WeatherAdapter weatherAdapter;
    ListView weatherlistView;
    SharedPrefs sharedPrefs;
    private Spinner city_spinner;
    ArrayList<String> List = new ArrayList<String>();
    JSONObject weatherdata;
    TextView validate;
    WIFIInternetConnectionDetector cd;
    int flag = 0;
    String peopleNamee="Select Location Name";
    int myposition;
    String locationname;
    Boolean isConnectionExist = false;
    ArrayAdapter<String> spinnerMenu;
    String Selecteditem;
    String Items;
    String name;
    MainWeather mainWeather;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
        ViewModelProviders.of(this).get(WeatherViewModel.class);
        View root = inflater.inflate(R.layout.weather_layout, container, false);

        sharedPrefs = new SharedPrefs(getActivity());
        weatherlistView = (ListView) root.findViewById(R.id.weatherlistview);
        validate = (TextView) root.findViewById(R.id.validatespin);
        city_spinner = (Spinner) root.findViewById(R.id.spinner_city);
        try {
           weatherdata = new JSONObject();
            System.out.println("HI" + sharedPrefs.getToken());
            weatherdata.put("token", sharedPrefs.getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new WeatherTask().execute(weatherdata);
   //     inItSpinner();
        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Selecteditem = parent.getItemAtPosition(position).toString();
                System.out.println("Selecteditem==s="+Selecteditem);
                Selecteditem = Items;
                       /* if(Selecteditem.equalsIgnoreCase(name))
                        {
                            weatherlistView.setVisibility(View.VISIBLE);
                           // parseResultAsPerOrgStandards(result);

                        }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        System.out.println("SItems="+Items);
     return root;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if ((!peopleNamee.equalsIgnoreCase("Select  Location Name"))) {
            if(!peopleNamee.equalsIgnoreCase("Select Location Name")){
            //    senddata();
            }
            cd = new WIFIInternetConnectionDetector(getActivity());
            isConnectionExist = cd.checkMobileInternetConn();
            //   names = name.getText().toString();

            //addName = addname.getText().toString();
            if (isConnectionExist) {
                //inItSpinner();
                // senddata();
                       /* Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                        startActivity(intent);*/
                System.out.println("----------CONNECTION AVAILABLE-------------");
            } else {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
                a_builder.setMessage(R.string.NoInternet)
                        .setCancelable(false)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //  name.setText("");
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Error");
                alert.show();
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        }else{
                       /* Toast toast=Toast.makeText(getApplicationContext(),"please select atleast",Toast.LENGTH_SHORT);
                        toast.show();*/
            AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
            a_builder.setMessage("The people name field is required.")
                    .setCancelable(false)
                    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // name.setText(null);
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Error");
            alert.show();
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    public class WeatherTask extends AsyncTask<JSONObject, Void, String> {
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
            return ServiceProviderClass.getWeatherDetail(param[0]);
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
            Gson obGson = new Gson();
            mainWeather = obGson.fromJson(result, MainWeather.class);
            List.add(0,"Select Location Name");
            for (int i=0;i<mainWeather.getWeather().size();i++){
                List.add(mainWeather.getWeather().get(i).getLocationName());
            }

            spinnerMenu = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List);
            spinnerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            city_spinner.setAdapter(spinnerMenu);
            city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedName = (String)parent.getAdapter().getItem(position);
                    for (int i=0;i<mainWeather.getWeather().size();i++){
                        if(selectedName.equals(mainWeather.getWeather().get(i).getLocationName() )){
                            System.out.println("Selected");
                            weatherAdapter = new WeatherAdapter(getActivity(), R.layout.weather_layout_item, mainWeather.getWeather().get(i).getDayWiseweather());
                             weatherlistView.setAdapter(weatherAdapter);

                        }else {
                            System.out.println("not Selected");
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //weatherAdapter = new WeatherAdapter(getActivity(), R.layout.weather_layout_item, weatherList);
           //// weatherlistView.setAdapter(weatherAdapter);

            if (Selecteditem != null) {
                int spinnerPosition = spinnerMenu.getPosition(Selecteditem);
                System.out.println("spinnerPosition==="+spinnerPosition);
                city_spinner.setSelection(spinnerPosition);
            }

        }
    }
}