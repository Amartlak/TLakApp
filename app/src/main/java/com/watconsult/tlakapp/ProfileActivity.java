package com.watconsult.tlakapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends Activity {
    private static final String TAG = ProfileActivity.class.getSimpleName();
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int CAMERA_PIC_REQUEST = 2;
    ImageView back_btnx_txt;
    ImageView photoclick;
    Button edit;
   // ImageButton user_profile_photo;
    CircleImageView user_profile_photo;
    AlertDialog dialogmydig;
    File file;

    String filename;
    Bitmap mphoto = null;
    Uri selectedImageBrows;
    byte[] data = null;
    double megabytes;
    //  private int GALLERY = 1, CAMERA = 2;
    private Uri fileUri;
    public static final int REQUEST_IMAGE = 100;
    static final int RESULT_LOAD_IMAGE = 1, CAMERA = 2, BROWSEIMG = 3;
    ProfileDetail profileDetail;
    TextView name, place, number, tour_text, dest_number, dest_text,
            explore_number, explre_text, bio, user_name, biodata, email, phone, dob, address,adreees;
    Profile profile;
    ImageView img_preview_farmer_photo;
    String dataa;
    ArrayList<String> arrAllowedDocTypes;
    TextView names;

    SharedPrefs sharedPrefs;
    JSONObject objects;
    private PrefManager prefManager;
    TextView logout,emails,dobs;
    String message,travelerName,travelerEmail,travelerPhone,travelerDOB,travelerAddress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        ButterKnife.bind(this);
        prefManager = new PrefManager(this);
        sharedPrefs=new SharedPrefs(this);
        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);
        edit = (Button) findViewById(R.id.edit);
        logout = (TextView) findViewById(R.id.logout);
        user_profile_photo = (CircleImageView) findViewById(R.id.user_profile_photo);
        back_btnx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photoclick = (ImageView) findViewById(R.id.photoclick);
        dobs = (TextView) findViewById(R.id.dobs);
        adreees = (TextView) findViewById(R.id.adreees);
        names = (TextView) findViewById(R.id.names);
        name = (TextView) findViewById(R.id.user_profile_name);
        emails = (TextView) findViewById(R.id.emails);
        Typeface custom_font11 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        name.setTypeface(custom_font11);
        place = (TextView) findViewById(R.id.place);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        place.setTypeface(custom_font2);
        number = (TextView) findViewById(R.id.tour_number);
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        number.setTypeface(custom_font3);
        tour_text = (TextView) findViewById(R.id.tour);
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        tour_text.setTypeface(custom_font4);
        dest_number = (TextView) findViewById(R.id.dest_number);
        Typeface custom_font5 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        dest_number.setTypeface(custom_font5);
        dest_text = (TextView) findViewById(R.id.dest_text);
        Typeface custom_font6 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        dest_text.setTypeface(custom_font6);
        explore_number = (TextView) findViewById(R.id.explre_number);
        Typeface custom_font7 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        dest_text.setTypeface(custom_font7);
        explre_text = (TextView) findViewById(R.id.explre_text);
        Typeface custom_font8 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        explre_text.setTypeface(custom_font8);
        bio = (TextView) findViewById(R.id.bio);
        Typeface custom_font9 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        bio.setTypeface(custom_font9);
        biodata = (TextView) findViewById(R.id.biodata);
        Typeface custom_font10 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        biodata.setTypeface(custom_font10);
        user_name = (TextView) findViewById(R.id.name);
        Typeface custom_font12 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        user_name.setTypeface(custom_font12);
        email = (TextView) findViewById(R.id.email);
        Typeface custom_font13 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        email.setTypeface(custom_font13);
        phone = (TextView) findViewById(R.id.phone);
        Typeface custom_font14 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        phone.setTypeface(custom_font14);
        dob = (TextView) findViewById(R.id.dob);
        Typeface custom_font15 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        dob.setTypeface(custom_font15);

        address = (TextView) findViewById(R.id.adress);
        Typeface custom_font16 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        address.setTypeface(custom_font16);
     //   loadProfileDefault();

        JSONObject object = new JSONObject();
        try {
            System.out.println("tokens==s==="+sharedPrefs.getToken());
            object.put("token",sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new ProfileTask().execute(object);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(ProfileActivity.this);
                a_builder.setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                objects = new JSONObject();
                                try {
                                    System.out.println("travelerId========="+sharedPrefs.getTravelerId());
                                    objects.put("travelerId",sharedPrefs.getTravelerId());
                                    Log.e("obJE=",objects.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("error",e.toString());
                                }
                                new LogoutTask().execute(objects);
                                dialog.dismiss();
                            }
                        });

                a_builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                AlertDialog alert = a_builder.create();
                alert.setTitle("Logout");
                alert.show();
                alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);


            }
        });
    }
    private void loadProfile(String url) {
        Log.d(TAG, "Image cache path: " + url);

        Glide.with(this).load(url).into(user_profile_photo);
        user_profile_photo.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }

  /*  private void loadProfileDefault() {
        Glide.with(this).load(R.drawable.profile_circular_border_imageview)
                .into(user_profile_photo);
        user_profile_photo.setColorFilter(ContextCompat.getColor(this, R.color.profile_default_tint));
    }*/

    @OnClick({R.id.photoclick, R.id.user_profile_photo})
    void onProfileImageClick() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }
            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }
    private void launchCameraIntent() {
        Intent intent = new Intent(ProfileActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);
        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(ProfileActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                    // loading profile image from local cache
                    loadProfile(uri.toString());
                    sharedPrefs.setImgagepath(uri.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 }
    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                ProfileActivity.this.openSettings();
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
    public class ProfileTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ProfileActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return ServiceProviderClass.getProfile(param[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss---dds-----"+result);
            progressDialog.dismiss();
            parseResultAsPerOrgStandardss(result);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        JSONObject objectt = new JSONObject();
        try {
            objectt.put("token",sharedPrefs.getToken());
            Log.e("obJE=",objectt.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new ProfileTask().execute(objectt);
    }

    private void parseResultAsPerOrgStandardss(String result) {
        if (result != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(result);
                String error = String.valueOf(jsonObject.getBoolean("error"));
                String picture = jsonObject.getString("profilePicture");
                Picasso.with(this).load(picture).into(user_profile_photo);

                String totalDeparture = jsonObject.getString("totalDeparture");
                number.setText(totalDeparture);
                JSONObject jObj = jsonObject.getJSONObject("traveler");
                String travelerName = jObj.getString("travelerName");
                name.setText(travelerName);
                names.setText(travelerName);
                String travelerId = jObj.getString("travelerId");
                String travelerEmail = jObj.getString("travelerEmail");
                emails.setText(travelerEmail);
                String travelerPhone = jObj.getString("travelerPhone");
                phone.setText(travelerPhone);
                String travelerDOB = jObj.getString("travelerDOB");
                dobs.setText(travelerDOB);
                String travelerAddress = jObj.getString("travelerAddress");
                adreees.setText(travelerAddress);
                Log.e("profilePicture",jsonObject.getString("profilePicture"));
                Log.e("travelerName",jObj.getString("travelerName"));
                Log.e("travelerPhone",jObj.getString("travelerPhone"));
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ProfileActivity.this, ProfileDetailActivity.class);
                        intent.putExtra("totalDeparture",totalDeparture);
                        intent.putExtra("travelerName",travelerName);
                        intent.putExtra("travelerEmail",travelerEmail);
                        intent.putExtra("travelerPhone",travelerPhone);
                        intent.putExtra("travelerDOB",travelerDOB);
                        intent.putExtra("travelerAddress",travelerAddress);
                        intent.putExtra("travelerId",travelerId);
                        startActivity(intent);
                    }
                });

               /* if(message.equalsIgnoreCase("true")){
                    androidx.appcompat.app.AlertDialog.Builder a_builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
                    a_builder.setMessage(message)
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    androidx.appcompat.app.AlertDialog alert = a_builder.create();
                    alert.setTitle("Error");
                    alert.show();
                    alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                }*/
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
         //   flightAdapter = new FlightAdapter(getActivity(), R.layout.flight_layout, flightListItems);
          //  flightList.setAdapter(flightAdapter);
        }
    }

    public class LogoutTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ProfileActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return ServiceProviderClass.getLogout(param[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss---dd-----"+result);
            progressDialog.dismiss();
            parseResultAsPerOrgStandards(result);
        }
    }

    private void parseResultAsPerOrgStandards(String result) {
        try {
            JSONObject jsonObject =  new JSONObject(result);
            String error = jsonObject.getString("error");
             message = jsonObject.getString("message");
            if(error.equalsIgnoreCase("false")){
                AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
                a_builder.setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                prefManager.setFirstTimeLaunch(true);
                                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                AlertDialog alert = a_builder.create();

                alert.show();
                alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}