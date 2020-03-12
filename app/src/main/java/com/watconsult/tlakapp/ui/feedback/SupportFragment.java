package com.watconsult.tlakapp.ui.feedback;

import android.app.ProgressDialog;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.adapter.SupportAdapter;
import com.watconsult.tlakapp.adapter.SupportAdapter1;
import com.watconsult.tlakapp.adapter.SupportAdapter2;
import com.watconsult.tlakapp.model.CSupportItem;
import com.watconsult.tlakapp.ui.support.SupportViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SupportFragment extends Fragment {
RelativeLayout relativeLayout;
    private SupportViewModel toolsViewModel;
    CSupportItem cSupportItem;
    SharedPrefs sharedPrefs;
    private ArrayList<CSupportItem> CSupportListItem = new ArrayList<CSupportItem>();
    private ArrayList<CSupportItem> CSupportListItem1 = new ArrayList<CSupportItem>();
    private ArrayList<CSupportItem> CSupportListItem2 = new ArrayList<CSupportItem>();

    ListView supportList,guideList,operatorList;
    SupportAdapter supportAdapter;
    SupportAdapter1 supportAdapter1;
    SupportAdapter2 supportAdapter2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(SupportViewModel.class);
        View root = inflater.inflate(R.layout.support_fragment, container, false);
        sharedPrefs = new SharedPrefs(getActivity());
        supportList = (ListView) root.findViewById(R.id.supportList);
        guideList = (ListView) root.findViewById(R.id.guideList);
        operatorList = (ListView) root.findViewById(R.id.operatorList);
        JSONObject object = new JSONObject();
        try {
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new SupportTask().execute(object);
        return root;
    }
    public class SupportTask extends AsyncTask<JSONObject, Void, String> {
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
            return ServiceProviderClass.getContactSupport(param[0]);
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
                String message = jsonObject.getString("message");
                JSONObject jObj = jsonObject.getJSONObject("traveler");
                int pkgId = jObj.getInt("pkgId");
                String tenantId = jObj.getString("tenantId");
                String token = jObj.getString("token");
                System.out.println("token==="+token);
                JSONArray jsonArray = jsonObject.getJSONArray("departureManager");
                final int numberofitems = jsonArray.length();
                for (int i = 0; i < numberofitems; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    CSupportItem item = new CSupportItem();
                    item.setDepManagerName(jsonObj.getString("depManagerName"));
                    item.setDepManagerEmail(jsonObj.getString("depManagerEmail"));
                    item.setDepManagerPhone(jsonObj.getString("depManagerPhone"));
                    CSupportListItem.add(item);
                }
               JSONArray jsonArray1 = jsonObject.getJSONArray("departureGuide");
                System.out.println("jsonArray1"+jsonArray1);
                final int numberofitem = jsonArray1.length();
                for (int i = 0; i < numberofitem; i++) {
                    JSONObject jsonObj = jsonArray1.getJSONObject(i);
                    CSupportItem item = new CSupportItem();
                    item.setDepGuideName(jsonObj.getString("depGuideName"));
                    item.setDepGuideLocation(jsonObj.getString("depGuideLocation"));
                    item.setDepGuidePhone(jsonObj.getString("depGuidePhone"));
                    CSupportListItem1.add(item);
                }
                 JSONArray jsonArray2 = jsonObject.getJSONArray("comanyContact");

                final int numberofitemss = jsonArray2.length();
                for (int i = 0; i < numberofitemss; i++) {
                    JSONObject jsonObj = jsonArray2.getJSONObject(i);
                    CSupportItem item = new CSupportItem();
                    item.setCompanyPersonName(jsonObj.getString("companyPersonName"));
                    item.setCompanyPersonEmail(jsonObj.getString("companyPersonEmail"));
                    item.setCompanyPersonPhone(jsonObj.getString("companyPersonPhone"));
                    CSupportListItem2.add(item);
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
            }supportAdapter = new SupportAdapter(getActivity(), R.layout.support_detail, CSupportListItem);
          supportList.setAdapter(supportAdapter);
          supportAdapter1 = new SupportAdapter1(getActivity(), R.layout.support_detail1, CSupportListItem1);
          guideList.setAdapter(supportAdapter1);
          supportAdapter2 = new SupportAdapter2(getActivity(), R.layout.support_detail2, CSupportListItem2);
          operatorList.setAdapter(supportAdapter2);
        }
    }
}