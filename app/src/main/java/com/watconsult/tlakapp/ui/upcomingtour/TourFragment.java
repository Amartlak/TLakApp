package com.watconsult.tlakapp.ui.upcomingtour;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.viewpagerindicator.CirclePageIndicator;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.adapter.SlidingImage_Adapter;
import com.watconsult.tlakapp.model.OptionalTourItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TourFragment extends Fragment {
    SharedPrefs sharedPrefs;
    private TourViewModel toolsViewModel;
    OptionalTourItem optionalTourItem;
    private ArrayList<OptionalTourItem> optionalListItem = new ArrayList<OptionalTourItem>();
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    CirclePageIndicator indicator;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(TourViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tour, container, false);
        sharedPrefs = new SharedPrefs(getActivity());
        mPager = (ViewPager) root.findViewById(R.id.pager);
        indicator = (CirclePageIndicator)
                root.findViewById(R.id.indicator);

      /*  final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);



        NUM_PAGES =optionalListItem.length;*/
        JSONObject object = new JSONObject();
        try {
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new TourTask().execute(object);
        return root;
    }
    public class TourTask extends AsyncTask<JSONObject, Void, String> {
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
            return ServiceProviderClass.getOptionalDeparture(param[0]);
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
                JSONObject jsonObject1 = jsonObject.getJSONObject("traveler");
                String token = jsonObject1.getString("token");
              /*  String tourpackageid = String.valueOf(jsonObject1.getInt("pkgId"));
                String token = jsonObject1.getString("token");*/
                JSONArray jsonArray = jsonObject.getJSONArray("optionalDeparture");
                final int numberofitems = jsonArray.length();
                for (int i = 0; i < numberofitems; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    OptionalTourItem item = new OptionalTourItem();
                    item.setDayNumberOptionDepShow(jsonObj.getString("dayNumberOptionDepShow"));
                    item.setOptionalDepartureNotifiaction(jsonObj.getString("optionalDepartureNotifiaction"));
                    item.setOptionalDepartureId(jsonObj.getString("optionalDepartureId"));
                    item.setOptionalDepartureName(jsonObj.getString("optionalDepartureName"));
                    item.setPromoContent(jsonObj.getString("promoContent"));
                    item.setEmail(jsonObj.getString("email"));
                    item.setPhone(jsonObj.getString("phone"));
                    item.setDescription(jsonObj.getString("description"));
                    item.setOptionalDepartureImage(jsonObj.getString("optionalDepartureImage"));
                    optionalListItem.add(item);

                    final float density = getResources().getDisplayMetrics().density;
                    indicator.setRadius(5 * density);
                    NUM_PAGES =jsonArray.length();
                    final Handler handler = new Handler();
                    final Runnable Update = new Runnable() {
                        public void run() {
                            if (currentPage == NUM_PAGES) {
                                currentPage = 0;
                            }
                            mPager.setCurrentItem(currentPage++, true);
                        }
                    };
                }
            }catch(JSONException e)
            {
                e.printStackTrace();
            }
            mPager.setAdapter(new SlidingImage_Adapter(getActivity(),optionalListItem));
            indicator.setViewPager(mPager);

            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }
                @Override
                public void onPageScrolled(int pos, float arg1, int arg2) {
                }
                @Override
                public void onPageScrollStateChanged(int pos) {
                }
            });
        }
    }
}
