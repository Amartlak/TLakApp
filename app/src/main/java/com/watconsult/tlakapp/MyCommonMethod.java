package com.watconsult.tlakapp;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyCommonMethod {
    private static final String MyPREFERENCES = null;
    private static SharedPreferences.Editor editor;

    /** Called for setting the value based on key in Prefs */

    public static void setPrefsData(Context context, String prefsKey,
                                    String prefValue) {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putString(prefsKey, prefValue);
        editor.commit();
    }

    /** Called for getting the value based on key from Prefs */
    public static String getPrefsData(Context context, String prefsKey,
                                      String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String prefsValue = sharedPreferences.getString(prefsKey, defaultValue);
        return prefsValue;
    }

    /** Called for Showing Alert in Application */
    public static void showAlert(String message, Activity context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        try {
            AlertDialog alert = builder.create();
            alert.show();
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** Called for checking Email Validation */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /** Called for checking Internet connection */
    public static boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo;
        try {
            netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
    public static boolean copyStream(InputStream input, OutputStream output)
        throws IOException {
        boolean isCopied = false;
        byte[] buffer = new byte[1024];
        int bytesRead;
        try {
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            isCopied = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isCopied = false;
        }
        return isCopied;
    }
    public static void closeKeyboard(final Context context, final View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void log(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", "Elena");
        editor.putInt("idName", 12);
        editor.commit();
    }

    private ContextWrapper getApplicationContext() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void showAlert(Animation shakeAnimation) {
    }
}
