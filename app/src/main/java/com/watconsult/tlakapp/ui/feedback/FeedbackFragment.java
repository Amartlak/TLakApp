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
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.watconsult.tlakapp.MyCommonMethod;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SharedPrefs;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackFragment extends Fragment {
    FeedbackViewModel feedbackViewModell;
    EditText name,email,phone,message;
    Button submit;
    SharedPrefs sharedPrefs;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        feedbackViewModell =
        ViewModelProviders.of(this).get(FeedbackViewModel.class);
        View root = inflater.inflate(R.layout.feedback_layout, container, false);
        sharedPrefs = new SharedPrefs(getActivity());
        name = (EditText) root.findViewById(R.id.EditTextName);
        email = (EditText) root.findViewById(R.id.EditTextEmail);
        phone = (EditText) root.findViewById(R.id.phone);
        message = (EditText) root.findViewById(R.id.EditTextFeedbackBody);
        submit = (Button) root.findViewById(R.id.ButtonSendFeedback);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkToValidation();
               /* if(name.getText().toString().trim().length() == 0) {
                    MyCommonMethod.showAlert("Enter Name", getActivity());
                    name.requestFocus();
                }
                if(email.getText().toString().isEmpty()) {
                    MyCommonMethod.showAlert("Enter Name", getActivity());
                }else if (email.getText().toString().trim().matches(emailPattern)) {
                        MyCommonMethod.showAlert("Enter Valid Email ID", getActivity());
                } else  if(phone.getText().toString().trim().length() == 0) {
                    MyCommonMethod.showAlert("Enter Contact Number", getActivity());
                    phone.requestFocus();
                } else  if(message.getText().toString().trim().length() == 0) {
                    MyCommonMethod.showAlert("Enter Contact Number", getActivity());
                    message.requestFocus();
                }*/
                String names = name.getText().toString();
                name.setText("");
                String emails = email.getText().toString();
                email.setText("");
                String phones = phone.getText().toString();
                phone.setText("");
                String msg = message.getText().toString();
                message.setText("");
                JSONObject object = new JSONObject();
                try {
                    object.put("travelerName",names);
                    object.put("travelerPhone",phones);
                    object.put("travelerEmail",emails);
                    object.put("pkgName",sharedPrefs.getPkgName());
                    object.put("message",msg);
                    object.put("tenantId",sharedPrefs.getTenantId());
                    Log.e("obJE=",object.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error",e.toString());
                }
                new FeedbackTask().execute(object);
            }
        });
        return root;
    }
    public class FeedbackTask extends AsyncTask<JSONObject, Void, String> {
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
            return ServiceProviderClass.getFeedback(param[0]);
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
            JSONObject jsonObject = new JSONObject(result);
            String error = jsonObject.getString("error");
            System.out.println("error-------"+error);
            String messagess = jsonObject.getString("message");
            String travelerName = jsonObject.getString("error");
            String pkgName = jsonObject.getString("pkgName");

          if(error.equalsIgnoreCase("true")){
                AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
                a_builder.setMessage(messagess)
                        .setCancelable(false)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Success");
                alert.show();
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
            if(error.equalsIgnoreCase("false"))
            {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
                a_builder.setMessage(messagess)
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Success");
                alert.show();
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private boolean checkToValidation(){


        if(name.getText().toString().trim().length() == 0){
            MyCommonMethod.showAlert("Please Enter Name", getActivity());
            name.requestFocus();
            return false;
        } else if(name.getText().toString().trim().length()<25){
            MyCommonMethod.showAlert("Name Should Be 25 Digit", getActivity());
            name.requestFocus();
            return false;
        } if(phone.getText().toString().trim().length() == 0){
            MyCommonMethod.showAlert("Please Enter Phone Number", getActivity());
            phone.requestFocus();
            return false;
        } else if(phone.getText().toString().trim().length()<10||phone.getText().toString().trim().length()>10){
            MyCommonMethod.showAlert("Phone Number Should Be 8 Digit", getActivity());
            phone.requestFocus();
            return false;
        }
        return true;
    }
}