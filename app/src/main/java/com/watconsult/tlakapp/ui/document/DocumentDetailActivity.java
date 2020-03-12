package com.watconsult.tlakapp.ui.document;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.adapter.DocDetailAdapter;
import com.watconsult.tlakapp.model.DocDetailListItem;

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

public class DocumentDetailActivity extends AppCompatActivity {
    SharedPrefs sharedPrefs;
    private static String sResponse = null;
    String message;
    String travelDocId;
    ListView DocList;
    ArrayList<DocDetailListItem> documentList = new ArrayList<DocDetailListItem>();
    DocDetailAdapter docDetailAdapter;
    ImageView back_btnx_txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_list_detailss);
        sharedPrefs = new SharedPrefs(this);
        DocList = (ListView) findViewById(R.id.doc_item);

        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);
        back_btnx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle args = getIntent().getExtras();
        if (args != null) {
            travelDocId = String.valueOf(args.get("travelDocId"));
            System.out.println("travelDocId---s-------"+travelDocId);
        }
        JSONObject object = new JSONObject();
        try {
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new DocumentDetailTask().execute(object);
    }
    public class DocumentDetailTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(DocumentDetailActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return getDocumentDetail(param[0]);
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
                System.out.println("token=s=="+token);
                JSONArray jsonArray = jsonObject.getJSONArray("travelDocuments");
                System.out.println("jsonArray=========="+jsonArray);
                final int numberofitems = jsonArray.length();
                for (int i = 0; i < numberofitems; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    DocDetailListItem item = new DocDetailListItem();
                    item.setTravelDocId(jsonObj.getString("travelDocId"));
                    item.setTravelDocName(jsonObj.getString("travelDocName"));
                    item.setDocumrntPath(jsonObj.getString("documrntPath"));
                    item.setDocType(jsonObj.getString("docType"));
                    item.setTypeIcon(jsonObj.getString("typeIcon"));
                    documentList.add(item);
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
            docDetailAdapter = new DocDetailAdapter(this, R.layout.doc_list_item, documentList);
            DocList.setAdapter(docDetailAdapter);
        }
    }
    private String getDocumentDetail(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/travel-document-detail/"+travelDocId);

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
