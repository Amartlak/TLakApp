package com.watconsult.tlakapp.login;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.watconsult.tlakapp.LoginConfirmActivity;
import com.watconsult.tlakapp.MainActivity;
import com.watconsult.tlakapp.MyCommonMethod;
import com.watconsult.tlakapp.People;
import com.watconsult.tlakapp.PrefManager;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SplashActivity;
import com.watconsult.tlakapp.WIFIInternetConnectionDetector;
import com.watconsult.tlakapp.model.LoginItem;
import com.watconsult.tlakapp.model.Peoples;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;

public class LoginActivity extends Activity {
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    Button loginBtn;
    String passcod;
    private PrefManager prefManager;
    WIFIInternetConnectionDetector cd;
    private LoginViewModel loginViewModel;
    Boolean isConnectionExist = false;
    LoginItem loginItem;
    JSONObject object = null;
    String people_name, people_id, occupied;
    //private ActivityMainBinding mainBinding;
    private ArrayList<LoginItem> dataItems = new ArrayList<LoginItem>();
    EditText passcode;
    String tenantid;
    String packageid;
    String selectedName;
    People peoplemodel;
    ArrayList<Peoples> mUsers;
    public static JSONArray jsonArray;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private View view;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        setContentView(R.layout.new_login_layout);

        if (!checkPermission()) {
            requestPermission();
        } else {
            Toast.makeText(this,"Permission already granted,",Toast.LENGTH_SHORT).show();

        }

        passcode = (EditText) findViewById(R.id.passcode);
        passcode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd = new WIFIInternetConnectionDetector(getApplicationContext());
                isConnectionExist = cd.checkMobileInternetConn();
                if (checkToValidation()) {
                    passcod = passcode.getText().toString();
                }
                if (isConnectionExist) {
                    object = new JSONObject();
                    try {
                        System.out.println("pass========" + passcod);
                        object.put("passcode", passcod);
                        Log.e("obJE=", object.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("error", e.toString());
                    }
                    new LoginTask().execute(object);
               /* Intent intent = new Intent(getApplicationContext(), LoginConfirmActivity.class);
                startActivity(intent);*/
                } else {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginActivity.this);
                    a_builder.setMessage(R.string.NoInternet)
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    passcode.setText(null);
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Error");
                    alert.show();
                    alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);

                }
            }
        });

       /* if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }*/

    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private boolean checkToValidation() {
        Pattern psNumber = Pattern.compile("^[0-9 ]+$");
        Matcher msNumber = psNumber.matcher(loginBtn.getText().toString());
        boolean bsPhoneNumber = msNumber.matches();

       /* if(passcode.getText().toString().trim().length() == 0){
            MyCommonMethod.showAlert("Enter Passcode", LoginActivity.this);
            passcode.requestFocus();
            return false;
        } else if(passcode.getText().toString().trim().length()<11||passcode.getText().toString().trim().length()>11){
            MyCommonMethod.showAlert("Please enter a valid passcode.\n Passcode should be 11 character.", LoginActivity.this);
            passcode.requestFocus();
            return false;
        }*/

        return true;
    }

    @Override
    public void onBackPressed() {

    }

    public class LoginTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(LoginActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(JSONObject... param) {
            return ServiceProviderClass.getLoginService(param[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultddd--------" + result);
            progressDialog.dismiss();
            parseResultAsPerOrgStandards(result);
        }

        private void parseResultAsPerOrgStandards(String result) {

            System.out.println("result--------" + result);
            if (result != null) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    String error = String.valueOf(jsonObject.getBoolean("error"));
                    System.out.println("error======" + error);
                    String message = jsonObject.getString("message");
                    if (error.equalsIgnoreCase("false")) {
                        JSONObject jsonpackage = jsonObject.getJSONObject("tourpackage");
                        System.out.println("jsonpackage====" + jsonpackage);
                        if (jsonpackage != null) {
                            String packagename = jsonpackage.getString("pkgName");
                            packageid = String.valueOf(jsonpackage.getInt("pkgId"));
                            tenantid = jsonpackage.getString("tenantId");
                            String passcode = jsonpackage.getString("passcode");
                            //  String userid = jsonpackage.getString("userId");
                            selectedName = jsonpackage.getString("selectedName");
                        }
                        jsonArray = jsonpackage.getJSONArray("peoples");
                        final int numberofitems = jsonArray.length();
                        for (int i = 0; i < numberofitems; i++) {
                            LoginItem item = new LoginItem();
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            System.out.println("json---" + jsonObject1);
                            item.setPeopleName(jsonObject1.getString("peopleName"));
                            item.setPeopleId(jsonObject1.getString("peopleId"));
                            item.setOccupied(Integer.parseInt(jsonObject1.getString("occupied")));
                            dataItems.add(item);
                            people_name = jsonObject1.getString("peopleName");
                            people_id = jsonObject1.getString("peopleId");
                            occupied = jsonObject1.getString("occupied");
                        }
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginActivity.this);
                        a_builder.setMessage(message)
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        passcode.setText(null);
                                        Intent cartIntent = new Intent(LoginActivity.this, LoginConfirmActivity.class);
                                        cartIntent.putExtra("packageid", packageid);
                                        cartIntent.putExtra("tenantid", tenantid);
                                        cartIntent.putExtra("selectedName", selectedName);
                                        // cartIntent.putParcelableArrayListExtra("UniqueKey", (ArrayList<? extends Parcelable>) dataItems);
                                        startActivity(cartIntent);
                                        dialog.dismiss();
                                        finish();
                                    }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Success");
                        alert.show();
                        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                    } else if (error.equalsIgnoreCase("true")) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginActivity.this);
                        a_builder.setMessage(message)
                                .setCancelable(false)
                                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        passcode.setText(null);
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Error");
                        alert.show();
                        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                    } else if (error.equalsIgnoreCase("true") || passcod.isEmpty()) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginActivity.this);
                        a_builder.setMessage(message)
                                .setCancelable(false)
                                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        passcode.setText(null);
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Error");
                        alert.show();
                        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
         if (requestCode == MY_CAMERA_REQUEST_CODE) {
             if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 Toast.makeText(this, "camera permission granted", Toast.LENGTH_SHORT).show();
             } else {
                 Toast.makeText(this, "camera permission denied", Toast.LENGTH_SHORT).show();
             }
         }
     }*/
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && cameraAccepted)
                        Toast.makeText(this, "Permission Granted, Now you can access location data and camera.", Toast.LENGTH_SHORT);
                    else {
                        Toast.makeText(this, "Permission Denied, You cannot access location data and camera.", Toast.LENGTH_SHORT);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }

    private void showMessageOKCancel(String s, DialogInterface.OnClickListener onClickListener) {
       new AlertDialog.Builder(LoginActivity.this)
               .setMessage(s)
               .setPositiveButton("OK", onClickListener)
               .setNegativeButton("Cancel", null)
               .create()
               .show();
        {
           /* AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginActivity.this);
            a_builder.setMessage(s)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.show();
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
*/
            //alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        }
    }
}
