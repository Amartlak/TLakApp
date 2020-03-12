package com.watconsult.tlakapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.watconsult.tlakapp.model.LoginItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.watconsult.tlakapp.login.LoginActivity.jsonArray;

public class LoginConfirmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText name,email,addname;
    WIFIInternetConnectionDetector cd;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button submit_btn;
    private PrefManager prefManager;
    String names,emails;
    Boolean isConnectionExist = false;
    private Spinner spinner;
    String peopleNamee="Select Name";
    String peopleid;
    String occupied;
    String getname_et;
    LoginItem loginItem;
    ArrayAdapter<String> adapter;
    ArrayList<String> List1 = new ArrayList<String>();
    ArrayList<HashMap<String, String>> productList;
    private ArrayList<LoginItem> listItems = new ArrayList<LoginItem>();
    String arrayString = String.valueOf(jsonArray);
    TextView validateMenuItem;
    int myposition;
    int flag = 0;
    public static String travelerId= null;
    SharedPrefs shareprefs;
    String Packageid;
    String addName;
    String tenentid;
    String addNewName,selectedName;
     String token=null;
    String message;
    ArrayAdapter<String> spinnerMenu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
        finish();
    }
        setContentView(R.layout.login_confirm);
        validateMenuItem = (TextView) findViewById(R.id.validatespin);
        Intent intent = getIntent();

        Packageid = intent.getStringExtra("packageid");
        tenentid = intent.getStringExtra("tenantid");
        selectedName =intent.getStringExtra("selectedName");
        shareprefs=new SharedPrefs(this);
        //System.out.println("jsonarray"+ jsonarray);
      /*  String arrayString = String.valueOf(jsonArray);
        System.out.println("arrayString==="+arrayString);*/
      //  Intent mIntent = getIntent();
        //ArrayList<? extends LoginItem> mUsers = mIntent.getParcelableArrayListExtra("UniqueKey");
     //   System.out.println("mUsers"+mUsers);

        //String products[] = {"Raj Kumar", "Dorjee", "Raj Kumar", "adityas"};
       try{
           JSONArray jsonArray = new JSONArray(arrayString);
           System.out.println("jsonArray=s="+jsonArray);
           for(int i=0;i< jsonArray.length();i++)
           {
               LoginItem item = new LoginItem();
               JSONObject jsonObject = jsonArray.getJSONObject(i);
               item.setPeopleName(jsonObject.getString("peopleName"));
               item.setPeopleId(jsonObject.getString("peopleId"));
               item.setOccupied(Integer.parseInt(jsonObject.getString("occupied")));
               String oocupied = String.valueOf(jsonObject.getInt("occupied"));
               listItems.add(item);
               List1.add(listItems.get(i).getPeopleName());
               if(oocupied.equalsIgnoreCase("1"))
               {
               //    ((TextView) parent.getChildAt(1)).setTextColor(Color.BLUE);
               }
           }
           List1.add(0,"Select Name");
       }catch(JSONException e){
           e.printStackTrace();
       }
//        spinner.setPrompt("Select Name");
//        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        email.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
      /*  email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    email.setCursorVisible(false);
                }else{
                    email.setCursorVisible(true);

                }
            }
        });*/
        addname = (EditText) findViewById(R.id.addname);
      /*  addname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                  addname.setCursorVisible(true);
                }else{
                  //  addname.setCursorVisible(true);

                }
            }
        });*/
        addname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        submit_btn = (Button) findViewById(R.id.login_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkToValidation()) {
                    addNewName= addname.getText().toString();
                    addname.setText("");
                    if ((!peopleNamee.equalsIgnoreCase("Select Name")) ||(!addNewName.isEmpty())) {
                        if(!peopleNamee.equalsIgnoreCase("Select Name")){
                            senddata();
                        }else if(!addNewName.isEmpty()){
                            senddataWithName();
                        }
                    cd = new WIFIInternetConnectionDetector(getApplicationContext());
                    isConnectionExist = cd.checkMobileInternetConn();
                    //   names = name.getText().toString();
                    emails = email.getText().toString();
                    //addName = addname.getText().toString();
                    if (isConnectionExist) {
                        inItSpinner();
                       // senddata();
                        checkValidation();
                       /* Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                        startActivity(intent);*/
                        System.out.println("----------CONNECTION AVAILABLE-------------");
                    } else {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginConfirmActivity.this);
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
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginConfirmActivity.this);
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
               /* Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);*/
            }
        });

        spinner = (Spinner) findViewById(R.id.name);
        LoginItem loginItem = new LoginItem();
        loginItem.setPeopleName("Select Category");
        loginItem.setPeopleId("Select Category");
        listItems.add(0,loginItem);
        inItSpinner();

    /*    name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LoginConfirmActivity.this.adapter.getFilter().filter(s);
                lv.setVisibility(View.VISIBLE);
                if (count == 0){
                    lv.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });*/
     /* adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        lv.setAdapter(adapter);*/

    }

    private void inItSpinner() {
        spinnerMenu = new ArrayAdapter<String>(LoginConfirmActivity.this, android.R.layout.simple_list_item_1, List1);
        spinnerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerMenu);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flag = 1;
                myposition = position;

                if(position==0){

                    return;
                }
                peopleNamee = listItems.get(position).getPeopleName();
               // ((TextView) view).setTextColor(Color.BLUE);

                 if(peopleNamee==null) {
                    validateMenuItem.setVisibility(View.GONE);
                }
                peopleid= listItems.get(position).getPeopleId();
                occupied= String.valueOf(listItems.get(position).getOccupied());
                if(selectedName.equalsIgnoreCase("0")||occupied.equalsIgnoreCase("0")) {

                    selectedName = String.valueOf(1);
                    addname.setVisibility(View.GONE);

                } else if(occupied.equalsIgnoreCase("1"))
                   {
                       addname.setVisibility(View.VISIBLE);
                   }


                System.out.println("peopleNamee===="+peopleNamee);
                System.out.println("peopleid===="+peopleid);
                System.out.println("occupied===="+occupied);
                // }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean checkToValidation() {
        Pattern psNumber = Pattern.compile("^[A-Z]+$");
        Matcher msNumber = psNumber.matcher(submit_btn.getText().toString());
        boolean bsPhoneNumber = msNumber.matches();

        if(email.getText().toString().isEmpty()) {
            // Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
        }else {
            if (email.getText().toString().trim().matches(emailPattern)) {
                //   Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
            } else {
                //  Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
            }
        }

        return true;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(LoginConfirmActivity.this, MainActivity.class));
        finish();
    }
    public void senddata(){
      //  peopleNamee = addname.getText().toString();
        //System.out.println("abc===="+peopleNamee);
      //  str_entrpin = entrPin_editTxt.getText().toString();
        try {
            JSONObject data = new JSONObject();
            data.put("tenantId",tenentid);
            data.put("peopleName",peopleNamee);
            data.put("peopleId",peopleid);
            data.put("travelerEmail","rajes@gmail.com");
            data.put("pkgId",Packageid);
            Log.e("mainObj=",data.toString());
            new LoginConfimTask().execute(data);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
    }
    public void senddataWithName(){
        getname_et = addname.getText().toString();
        try {
            JSONObject data = new JSONObject();
            data.put("tenantId",tenentid);
            data.put("peopleName",getname_et);
            data.put("travelerEmail","rajes@gmail.com");
            data.put("pkgId",Packageid);
            Log.e("mainObj=",data.toString());
            new LoginConfimTask().execute(data);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        System.out.println("item==="+item);
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        System.out.println("------------------ddd");
    }
    public class LoginConfimTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(LoginConfirmActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return ServiceProviderClass.getLoginConfim(param[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss--------"+result);
            progressDialog.dismiss();
            parseResultAsPerOrgStandards(result);
        }
}
    private void parseResultAsPerOrgStandards(String result) {
        if (result != null) {
                JSONObject jsonObject = null;
                try{
                jsonObject = new JSONObject(result);
                String error = String.valueOf(jsonObject.getBoolean("error"));
                 message = jsonObject.getString("message");
              if(error.equalsIgnoreCase("true"))
              {
                /*  AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginConfirmActivity.this);
                  a_builder.setMessage(message)
                          .setCancelable(false)
                          .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  //  passcode.setText(null);
                                  dialog.dismiss();
                              }
                          });
                  AlertDialog alert = a_builder.create();
                  alert.setTitle("Error");
                  alert.show();
                  alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                  alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);*/
              } else{
                  String packageId = jsonObject.getString("packageId");
                  String tenantId = jsonObject.getString("tenantId");
                  JSONObject jsonObject1 = jsonObject.getJSONObject("travellers");
                  String travelerName = jsonObject1.getString("travelerName");
                  travelerId = String.valueOf(jsonObject1.getInt("travelerId"));
                  shareprefs.setTravelerId(travelerId);
                 String tokens = jsonObject1.getString("token");
                  System.out.println("to--"+tokens);
                  shareprefs.setToken(tokens);
                //  sharedPrefs.setToken(tokens);
              }
                 if(error.equalsIgnoreCase("false"))
                {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginConfirmActivity.this);
                    a_builder.setMessage(message)
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   // passcode.setText(null);
                                    dialog.dismiss();
                                    validateMenuItem.setVisibility(View.GONE);
                                    peopleNamee ="Select Name";

                                    addname.setText("");
                                    addName = "";
                                    Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Success");
                    alert.show();
                    alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                }
                 else{
                     peopleNamee ="Select Name";
                     addName = "";
                 }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
public void checkValidation()
{
    if (spinner.getSelectedItem().toString().trim().equals("Select Name")) {
        // Toast.makeText(CallWs.this, "Error", Toast.LENGTH_SHORT).show();
        validateMenuItem.setVisibility(View.GONE);

    }else {
        validateMenuItem.setVisibility(View.VISIBLE);
       // senddataWithName();
    }
    /*else {
        try {
            validateMenuItem.setVisibility(View.GONE);
           String addNames= addname.getText().toString();

            System.out.println("add-------"+addNames);
            JSONObject datas = new JSONObject();
            datas.put("tenantId",tenentid);
            datas.put("peopleName",addNames);
            datas.put("travelerEmail","rajes@gmail.com");
            datas.put("pkgId",Packageid);
            Log.e("mainObj=",datas.toString());
            new LoginConfimTask().execute(datas);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
    }
*/

}
    @Override
    public void onBackPressed() {
    }
}
