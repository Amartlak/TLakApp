package com.watconsult.tlakapp;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ServiceProviderClass {

    private static String sResponse = null;
    private static String METHOD_NAME;
    public static Context context;

   // SharedPrefs shareprefs=new SharedPrefs(this);
     //Login service--------------------
    public  static String getLoginService(JSONObject jsonObject) {

        //METHOD_NAME = "fsomodule/fsoUpdateSubSampleDetail/";

        try {
            //URL url = new URL(ROOT + METHOD_NAME);
            URL url = new URL("https://account.tlakapp.com/tlak/api/login");

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
    public  static String getLoginConfim(JSONObject jsonObject) {

        //METHOD_NAME = "fsomodule/fsoUpdateSubSampleDetail/";

        try {
            //URL url = new URL(ROOT + METHOD_NAME);
            URL url = new URL("https://account.tlakapp.com/tlak/api/traveler");

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
    public  static String getPOIData(JSONObject jsonObject) {

        try {
            //URL url = new URL(ROOT + METHOD_NAME);
            URL url = new URL("https://account.tlakapp.com/tlak/api/poi");

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
    public  static String getItinearyData(JSONObject jsonObject) {

        //METHOD_NAME = "fsomodule/fsoUpdateSubSampleDetail/";
        try {
            //URL url = new URL(ROOT + METHOD_NAME);
            URL url = new URL("https://account.tlakapp.com/tlak/api/itineary");

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
    public  static String getItinearyDetail(JSONObject jsonObject) {
        try {
            URL url = new URL("http://52.25.193.143/tlak/public/api/detailitineary/197");

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
    public  static String getFlight(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/flight");

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
    public  static String getHotel(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/hotel");

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

    public  static String getPlaCard(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/placard");

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
    public  static String getHotelDetails(JSONObject jsonObject) {
        try {
            URL url = new URL("http://account.tlakapp.com/tlak/api/hotel");

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
    public  static String getDocument(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/travel-document");

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
    public  static String getLogout(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/logout");
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
    public  static String getProfile(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/profile");

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

    public  static String getContactSupport(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/support");

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
    public  static String getOptionalDeparture(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/optional-departure");

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
    public  static String getWeatherDetail(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/weather");
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
    public  static String getPoIMapDetail(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/poiOnMap");
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
    public  static String getOptionalMapDetail(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/optionalPoi");
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

    public  static String getFeedback(JSONObject jsonObject) {
        try {
            URL url = new URL("https://account.tlakapp.com/tlak/api/feedback");
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
  /*  public static String getDocListService() {
        String DResponse = null;
        try {
            //URL url = new URL(ROOT + METHOD_NAME);
            URL url = new URL("https://qa6.lifeatworkportal.com/msrvcs/xerox/osb/rks/docupload/imageQuery/getDocumentList.htm");
            System.out.println("smtesttttttttttttt" +NewFinalSMsession);
            System.out.println("sssstesttttttttttttt" +NewFinalSEssion);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true); // this will tell that you will read "something"
            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestMethod("POST"); // http verb
            //urlConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            //urlConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            urlConnection.setRequestProperty("Cookie", cookieString +","+NewFinalSEssion);

//            urlConnection.setRequestProperty("Cookie",NewFinalSMsession);
//            urlConnection.setRequestProperty("Cookie", NewFinalSEssion);
            OutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            InputStream in = urlConnection.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            DResponse = total.toString();
            wr.close();
            in.close();
            urlConnection.disconnect();
        } catch (Exception e) {
           //return e.getMessage();
            Log.e("PostComment Exception", e.toString());
        }
         System.out.println("Drespns" +DResponse);
        return DResponse;
    }*/
   /* public static String getCategoryResponse() {
        String result = "";
        String cookieString = "SMSESSION" + "=" + ValuSmsesion + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
        String cookieStringSession = "SESSION" + "=" + Login.targetValue + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
        System.out.println("smsesionsssss" + cookieString);
        System.out.println("Sessionnnnn" + cookieStringSession);

        //   String cookiessss = "SMSESSION" + "=" + "EmEnTMX5z00c4FtsPAGlHmfLFjtIT7XrPndzvVUEM1S1LiPNswXKENyN9NB4+iAbP9GyFmnOCQg0DvHYWrxpAA3qhRGlAFDiEn4X5Gnu05DD9RAtJsoIqB2xVx+7BKYl29Ueb1pjes+pKnw4Y51equba6LVaspCSUcN7TkCfVc5U9zkK7W3Ej0FHaKZCHywB6XATRg7v1GdZq6+jj0gqR+EaPTE+sFJUvYEGysnPaxtlMn0J5n9qGJx9LQ7Tfhd3b6uwpIeqZ3uXbppZThr+ALF3ulvhV+q99lP5wzIaTJlo9zbveA143sgFPT2h10vJVsPYY76PHIex6CEywCqqCu8Ro0K08AxqKH+9n3A7citbtAO+VYd3zVzDtTU2Ugw4H5Q6M9B/Y6jJ9Uh1jfQMshQAWnvA5Ye4F7nrGhUD8Z1v2uTZj3Y51MTk4mONQZoq8G934n4UwNRmgen2cRoF97dMyLZPfVMLTf0BmypDpHaao8eDFaK3ZczZd+yL4HPtnA7hGp3QHSWLt3qrQ7IBQjT2FtxHunf39FcG27I+1AYL0XB72qWADxMMiikbutVeUYfEIEQaq1h4dwm7T72epp0c/vadTHkha9HsfDUgWyMulCWtrK3jiTM7cde6kz1AjzGEFR7HomBNpf5fmreQFTCFgT99WyMhrSFDG6QxYO5OX9k6kgeLHuyOHiJu3i/haRi2IYkYA0tf7q4LiVwYtW42VWQoknSrRacWHohzqyCfkU3+CceDlYH+ACd6iRLUAlQ5o/98KgpUnSwFqrE4P78z40ebtDAMxIeGysFQQZYBec566lA+LAr0ibjex5Nr7mC+OrwnuRCjSmdkMIN8528+IEp1FXc4WE6Q6wBjjllHoaXl5BK8j6y+uGC/yioHx6nqCAUhQ/ufwzY22wi0Noa4tRohZ2+Gi4+qS20f9tUXf5HEJW3bqxwCoCB2eis2oMw/KQ83buUvglgeayLD7jOT7DnVM9a1pDozpVS0723wP35CkhJJ3Tzo6pWAajgf" + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
        //  String cookieStr = "SESSION" + "=" + "c74b8e5d-628e-4a9d-8909-5f00b25f43d0" + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";

        try {
            URL url = new URL("https://qa6.lifeatworkportal.com/msrvcs/xerox/rks/contactus/categories.htm");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true); // this will tell that you will read "something"
            //  urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestMethod("POST");

            urlConnection.setRequestProperty("Content-Type", "text/plain");
            // urlConnection.setRequestProperty("Cookie", cookieString + cookieStringSession);
            urlConnection.setRequestProperty("Cookie", cookieString + "," + NewFinalSEssion);
            urlConnection.connect();
            int aaaa = urlConnection.getResponseCode();
            System.out.println("aaa==================" + aaaa);
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;
            in.close();
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;
    }*/
//category for docupload---------
/*
public static String getCategoryResponseForDocupload() {
    String result = "";
    String cookieString = "SMSESSION" + "=" + ValuSmsesion + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
    String cookieStringSession = "SESSION" + "=" + Login.targetValue + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
    System.out.println("smsesionsssss" + cookieString);
    System.out.println("Sessionnnnn" + cookieStringSession);

    //   String cookiessss = "SMSESSION" + "=" + "EmEnTMX5z00c4FtsPAGlHmfLFjtIT7XrPndzvVUEM1S1LiPNswXKENyN9NB4+iAbP9GyFmnOCQg0DvHYWrxpAA3qhRGlAFDiEn4X5Gnu05DD9RAtJsoIqB2xVx+7BKYl29Ueb1pjes+pKnw4Y51equba6LVaspCSUcN7TkCfVc5U9zkK7W3Ej0FHaKZCHywB6XATRg7v1GdZq6+jj0gqR+EaPTE+sFJUvYEGysnPaxtlMn0J5n9qGJx9LQ7Tfhd3b6uwpIeqZ3uXbppZThr+ALF3ulvhV+q99lP5wzIaTJlo9zbveA143sgFPT2h10vJVsPYY76PHIex6CEywCqqCu8Ro0K08AxqKH+9n3A7citbtAO+VYd3zVzDtTU2Ugw4H5Q6M9B/Y6jJ9Uh1jfQMshQAWnvA5Ye4F7nrGhUD8Z1v2uTZj3Y51MTk4mONQZoq8G934n4UwNRmgen2cRoF97dMyLZPfVMLTf0BmypDpHaao8eDFaK3ZczZd+yL4HPtnA7hGp3QHSWLt3qrQ7IBQjT2FtxHunf39FcG27I+1AYL0XB72qWADxMMiikbutVeUYfEIEQaq1h4dwm7T72epp0c/vadTHkha9HsfDUgWyMulCWtrK3jiTM7cde6kz1AjzGEFR7HomBNpf5fmreQFTCFgT99WyMhrSFDG6QxYO5OX9k6kgeLHuyOHiJu3i/haRi2IYkYA0tf7q4LiVwYtW42VWQoknSrRacWHohzqyCfkU3+CceDlYH+ACd6iRLUAlQ5o/98KgpUnSwFqrE4P78z40ebtDAMxIeGysFQQZYBec566lA+LAr0ibjex5Nr7mC+OrwnuRCjSmdkMIN8528+IEp1FXc4WE6Q6wBjjllHoaXl5BK8j6y+uGC/yioHx6nqCAUhQ/ufwzY22wi0Noa4tRohZ2+Gi4+qS20f9tUXf5HEJW3bqxwCoCB2eis2oMw/KQ83buUvglgeayLD7jOT7DnVM9a1pDozpVS0723wP35CkhJJ3Tzo6pWAajgf" + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
    //  String cookieStr = "SESSION" + "=" + "c74b8e5d-628e-4a9d-8909-5f00b25f43d0" + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";

    try {
        URL url = new URL("https://qa6.lifeatworkportal.com/msrvcs/xerox/rks/mdocUpload/DOC_CATEGORY/refData.htm");

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true); // this will tell that you will read "something"
        //  urlConnection.setInstanceFollowRedirects(false);
        urlConnection.setRequestMethod("POST");

        urlConnection.setRequestProperty("Content-Type","application/json");
        // urlConnection.setRequestProperty("Cookie", cookieString + cookieStringSession);
        urlConnection.setRequestProperty("Cookie", cookieString + "," + NewFinalSEssion);
        urlConnection.connect();
        int aaaa = urlConnection.getResponseCode();
        System.out.println("aaa==================" + aaaa);
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;
        in.close();
        urlConnection.disconnect();

    } catch (Exception e) {
        e.printStackTrace();

    }

    return result;
}
*/


    //-----------------------------docuploadServicesss
 /*   public static String getDocUploadServicesss(JSONObject jsonObject) {
        String result = "";
        String cookieString = "SMSESSION" + "=" + ValuSmsesion + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
        String cookieStringSession = "SESSION" + "=" + Login.targetValue + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
        System.out.println("smsesionsssss" + cookieString);
        System.out.println("Sessionnnnn" + cookieStringSession);

        //   String cookiessss = "SMSESSION" + "=" + "EmEnTMX5z00c4FtsPAGlHmfLFjtIT7XrPndzvVUEM1S1LiPNswXKENyN9NB4+iAbP9GyFmnOCQg0DvHYWrxpAA3qhRGlAFDiEn4X5Gnu05DD9RAtJsoIqB2xVx+7BKYl29Ueb1pjes+pKnw4Y51equba6LVaspCSUcN7TkCfVc5U9zkK7W3Ej0FHaKZCHywB6XATRg7v1GdZq6+jj0gqR+EaPTE+sFJUvYEGysnPaxtlMn0J5n9qGJx9LQ7Tfhd3b6uwpIeqZ3uXbppZThr+ALF3ulvhV+q99lP5wzIaTJlo9zbveA143sgFPT2h10vJVsPYY76PHIex6CEywCqqCu8Ro0K08AxqKH+9n3A7citbtAO+VYd3zVzDtTU2Ugw4H5Q6M9B/Y6jJ9Uh1jfQMshQAWnvA5Ye4F7nrGhUD8Z1v2uTZj3Y51MTk4mONQZoq8G934n4UwNRmgen2cRoF97dMyLZPfVMLTf0BmypDpHaao8eDFaK3ZczZd+yL4HPtnA7hGp3QHSWLt3qrQ7IBQjT2FtxHunf39FcG27I+1AYL0XB72qWADxMMiikbutVeUYfEIEQaq1h4dwm7T72epp0c/vadTHkha9HsfDUgWyMulCWtrK3jiTM7cde6kz1AjzGEFR7HomBNpf5fmreQFTCFgT99WyMhrSFDG6QxYO5OX9k6kgeLHuyOHiJu3i/haRi2IYkYA0tf7q4LiVwYtW42VWQoknSrRacWHohzqyCfkU3+CceDlYH+ACd6iRLUAlQ5o/98KgpUnSwFqrE4P78z40ebtDAMxIeGysFQQZYBec566lA+LAr0ibjex5Nr7mC+OrwnuRCjSmdkMIN8528+IEp1FXc4WE6Q6wBjjllHoaXl5BK8j6y+uGC/yioHx6nqCAUhQ/ufwzY22wi0Noa4tRohZ2+Gi4+qS20f9tUXf5HEJW3bqxwCoCB2eis2oMw/KQ83buUvglgeayLD7jOT7DnVM9a1pDozpVS0723wP35CkhJJ3Tzo6pWAajgf" + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
        //  String cookieStr = "SESSION" + "=" + "c74b8e5d-628e-4a9d-8909-5f00b25f43d0" + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";

        try {
            URL url = new URL("https://qa6.lifeatworkportal.com/msrvcs/xerox/osb/rks/docUpload/uploadDocument.htm");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true); // this will tell that you will read "something"
            //  urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestMethod("POST");

            urlConnection.setRequestProperty("Content-Type", "multipart/form-data");
            // urlConnection.setRequestProperty("Cookie", cookieString + cookieStringSession);
            urlConnection.setRequestProperty("Cookie", cookieString + "," + NewFinalSEssion);
            urlConnection.connect();
            int aaaa = urlConnection.getResponseCode();
            System.out.println("aaa==================" + aaaa);
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;
            in.close();
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;
    }*/

   /* public static String getTopicResponseForDocupload() {
        String result = "";
        String cookieString = "SMSESSION" + "=" + ValuSmsesion + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
        String cookieStringSession = "SESSION" + "=" + Login.targetValue + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
        System.out.println("smsesionsssss" + cookieString);
        System.out.println("Sessionnnnn" + cookieStringSession);

        //   String cookiessss = "SMSESSION" + "=" + "EmEnTMX5z00c4FtsPAGlHmfLFjtIT7XrPndzvVUEM1S1LiPNswXKENyN9NB4+iAbP9GyFmnOCQg0DvHYWrxpAA3qhRGlAFDiEn4X5Gnu05DD9RAtJsoIqB2xVx+7BKYl29Ueb1pjes+pKnw4Y51equba6LVaspCSUcN7TkCfVc5U9zkK7W3Ej0FHaKZCHywB6XATRg7v1GdZq6+jj0gqR+EaPTE+sFJUvYEGysnPaxtlMn0J5n9qGJx9LQ7Tfhd3b6uwpIeqZ3uXbppZThr+ALF3ulvhV+q99lP5wzIaTJlo9zbveA143sgFPT2h10vJVsPYY76PHIex6CEywCqqCu8Ro0K08AxqKH+9n3A7citbtAO+VYd3zVzDtTU2Ugw4H5Q6M9B/Y6jJ9Uh1jfQMshQAWnvA5Ye4F7nrGhUD8Z1v2uTZj3Y51MTk4mONQZoq8G934n4UwNRmgen2cRoF97dMyLZPfVMLTf0BmypDpHaao8eDFaK3ZczZd+yL4HPtnA7hGp3QHSWLt3qrQ7IBQjT2FtxHunf39FcG27I+1AYL0XB72qWADxMMiikbutVeUYfEIEQaq1h4dwm7T72epp0c/vadTHkha9HsfDUgWyMulCWtrK3jiTM7cde6kz1AjzGEFR7HomBNpf5fmreQFTCFgT99WyMhrSFDG6QxYO5OX9k6kgeLHuyOHiJu3i/haRi2IYkYA0tf7q4LiVwYtW42VWQoknSrRacWHohzqyCfkU3+CceDlYH+ACd6iRLUAlQ5o/98KgpUnSwFqrE4P78z40ebtDAMxIeGysFQQZYBec566lA+LAr0ibjex5Nr7mC+OrwnuRCjSmdkMIN8528+IEp1FXc4WE6Q6wBjjllHoaXl5BK8j6y+uGC/yioHx6nqCAUhQ/ufwzY22wi0Noa4tRohZ2+Gi4+qS20f9tUXf5HEJW3bqxwCoCB2eis2oMw/KQ83buUvglgeayLD7jOT7DnVM9a1pDozpVS0723wP35CkhJJ3Tzo6pWAajgf" + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOnly)";
        //  String cookieStr = "SESSION" + "=" + "c74b8e5d-628e-4a9d-8909-5f00b25f43d0" + "; Domain=" + "qa6.lifeatworkportal.com" + "; Path=/" + "; Send for=Secure connections only" + "; Accessible to script=No (HttpOn
        try {
            System.out.println("value========="+ActivitySec_Doc.cardStatusString);
            // URL url = new URL("https://qa6.lifeatworkportal.com/msrvcs/xerox/rks/mdocUpload/Health&Welfare/refData.htm");
            URL url = new URL("https://qa6.lifeatworkportal.com/msrvcs/xerox/rks/mdocUpload/"+ActivitySec_Doc.cardStatusString+"/refData.htm");
            System.out.println("url--"+url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true); // this will tell that you will read "something"
            //  urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestMethod("POST");

            urlConnection.setRequestProperty("Content-Type","application/json");
            // urlConnection.setRequestProperty("Cookie", cookieString + cookieStringSession);
            urlConnection.setRequestProperty("Cookie", cookieString + "," + NewFinalSEssion);
            urlConnection.connect();
            int aaaa = urlConnection.getResponseCode();
            System.out.println("aaa==================" + aaaa);
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;
            in.close();
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;
    }*/


}
