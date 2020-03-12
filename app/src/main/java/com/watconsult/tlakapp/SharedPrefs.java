package com.watconsult.tlakapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    String encryptionDecryptionKey = "12345678saurabhm";
    String ivs = "12345678";
    private final String DBNAME = "Mobi";
    private static SharedPreferences sharedPreferences;
    private Context context;
    private static SharedPreferences.Editor spEditor;
    public String CustmDevicId = "cust_mdeviceid";
    public String deviceId = "deviceId";
    public String token = "token";
    public String pkgName = "pkgName";
    public String tenantId = "tenantId";
    public String travelerId = "travelerId";
    public  String imgagepath = "imagepath";
    public String Lat = "lat";
    public  String Longi = "longi";
    //----------
    public SharedPrefs(Context contex) {
        this.context = contex;
        sharedPreferences = this.context.getSharedPreferences(DBNAME,
                Context.MODE_PRIVATE);
    }
    public void setDeviceId(String did) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(deviceId, did);
        spEditor.commit();
    }

    public String getDeviceId() {
        return sharedPreferences.getString(deviceId, null);
    }


    public void setToken(String tok) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(token, tok);
        spEditor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(token, null);
    }


    public void setTenantId(String tanent) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(tenantId, tanent);
        spEditor.commit();
    }
    public String getTenantId() {
        return sharedPreferences.getString(tenantId, null);
    }
    public void setPkgName(String pkg) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(pkgName, pkg);
        spEditor.commit();
    }

    public String getPkgName() {
        return sharedPreferences.getString(pkgName, null);
    }

    public void setLat(String lat) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(String.valueOf(Lat), lat);
        spEditor.commit();
    }
    public String getLat() {
        return sharedPreferences.getString(String.valueOf(Lat), null);
    }

    public void setLongi(String longi) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(String.valueOf(Longi), longi);
        spEditor.commit();
    }
    public String getLongi() {
        return sharedPreferences.getString(String.valueOf(Longi), null);
    }

    public void setTravelerId(String travel) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(travelerId, travel);
        spEditor.commit();
    }
    public String getTravelerId() {
        return sharedPreferences.getString(travelerId, null);
    }


    public void setImgagepath(String images) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(token, images);
        spEditor.commit();
    }

    public String getImgagepath() {
        return sharedPreferences.getString(imgagepath, null);
    }
 /*   public String getMynavTnt() {
        return sharedPreferences.getString(mynavTnt, null);
    }
*/
  /*  public void setMynavTnt(String mynavtnt) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(mynavTnt, mynavtnt);
        spEditor.commit();
    }

    //---------------------------clor
    public void settabBarIconSelColor(String tabbr) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(tabBarIconSelColor, tabbr);
        spEditor.commit();
    }*/

  /*  public String gettabBarIconSelColor() {
        return sharedPreferences.getString(tabBarIconSelColor, null);
    }
    public void setnavTextColor_L(String navTextC_L) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(navTextColor_L, navTextC_L);
        spEditor.commit();
    }

    public String getnavTextColor_L() {
        return sharedPreferences.getString(navTextColor_L, null);
    }
    public void setnavBarColor_L(String navBarC_L) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(navBarColor_L, navBarC_L);
        spEditor.commit();
    }

    public String getnavBarColor_L() {
        return sharedPreferences.getString(navBarColor_L, null);
    }
    public void settabBarIconColor(String tabBarIconC) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(tabBarIconColor, tabBarIconC);
        spEditor.commit();
    }*/

/*
    public String gettabBarIconColor() {
        return sharedPreferences.getString(tabBarIconColor, null);
    }
    public void settabBarColor(String tabBarC) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(tabBarColor, tabBarC);
        spEditor.commit();
    }

    public String gettabBarColor() {
        return sharedPreferences.getString(tabBarColor, null);
    }

    public void setnavTintColor_L(String navTintC) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(navTintColor_L, navTintC);
        spEditor.commit();
    }

    public String getnavTintColor_L() {
        return sharedPreferences.getString(navTintColor_L, null);
    }
    //close


    //-------------------------- Biometric_aurtho
    public void setBiometric_aurtho(boolean bio) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(Biometric_aurtho, String.valueOf(bio));
        spEditor.commit();
    }

    public String getBiometric_aurtho() {
        return sharedPreferences.getString(Biometric_aurtho, null);
    }


    //------------
    //---------------finger-----------
    public void setFingerstateNotNull(boolean status) {
        spEditor = sharedPreferences.edit();
        spEditor.putBoolean(FingerstateNotNull, status);
        spEditor.commit();
    }

    public boolean getFingerstateNotNull() {
        return sharedPreferences.getBoolean(FingerstateNotNull, false);
    }

    //-------------close  clintName

    public void setclintNames(String clintn) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(clintNames, clintn);
        spEditor.commit();
    }

    public String getclintNames() {
        return sharedPreferences.getString(clintNames, null);
    }


    public void setwtsdc(String wtsdcs) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(wtsdc, wtsdcs);
        spEditor.commit();
    }

    public String getwtsdc() {
        return sharedPreferences.getString(wtsdc, null);
    }
    public void setclintcookies(String clintcookiess) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(clintcookies, clintcookiess);
        spEditor.commit();
    }

    public String getclintcookies() {
        return sharedPreferences.getString(clintcookies, null);
    }


    public void setlogontype(String logontypes) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(logontype, logontypes);
        spEditor.commit();
    }

    public String getlogontype() {
        return sharedPreferences.getString(logontype, null);
    }



    public void setCDomainname(String cdomnm) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(CDomainname, cdomnm);
        spEditor.commit();
    }

    public String getCDomainname() {
        return sharedPreferences.getString(CDomainname, null);
    }




    public void setDomainname(String domnm) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(Domainname, domnm);
        spEditor.commit();
    }

    public String getDomainname() {
        return sharedPreferences.getString(Domainname, null);
    }




    public void setdevkey(String devky) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(devkey, devky);
        spEditor.commit();
    }

    public String getdevkey() {
        return sharedPreferences.getString(devkey, null);
    }


//clintid-----
    public void setclientId(String cid) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(clientId, cid);
        spEditor.commit();
    }

    public String getclientId() {
        return sharedPreferences.getString(clientId, null);
    }
//devid
    public void setdevid(String did) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(devid, did);
        spEditor.commit();
    }

    public String getdevid() {
        return sharedPreferences.getString(devid, null);
    }

    //close---
    public void setcustMobID(String mb) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(CustmDevicId, mb);
        spEditor.commit();
    }

    public String getcustMobID() {
        return sharedPreferences.getString(CustmDevicId, null);
    }
//---------------
public void setCryptValues(String crypt) {
    spEditor = sharedPreferences.edit();
    spEditor.putString(CryptValues, crypt);
    spEditor.commit();
}

    public String getCryptValues() {
        return sharedPreferences.getString(CryptValues, null);
    }




    public void setCustomName(String cusnam) {
    spEditor = sharedPreferences.edit();
    spEditor.putString(CustomName, cusnam);
    spEditor.commit();
}

    public String getCustomName() {
        return sharedPreferences.getString(CustomName, null);
    }

    public void setloadweburl(String load) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(loadweburl, load);
        spEditor.commit();
    }

    public String getloadweburl() {
        return sharedPreferences.getString(loadweburl, null);
    }



   // pdfurl
   public void setpdfurl(String pdfurl) {
       spEditor = sharedPreferences.edit();
       spEditor.putString(pdfurl, pdfurl);
       spEditor.commit();
   }

    public String getpdfurl() {
        return sharedPreferences.getString(pdfurl, null);
    }


    //------clintclolor code----
    public void setcheckClintColorcode(String clntcolor) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(checkClintColorcode, clntcolor);
        spEditor.commit();
    }
    public String getcheckClintColorcode() {
        return sharedPreferences.getString(checkClintColorcode, null);
    }
    //---------close----------checkClintColorcode
    public void setjwttoken(String mb) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(JwtTokenss, mb);
        spEditor.commit();
    }
    public String getjwttoken() {
        return sharedPreferences.getString(JwtTokenss, null);
    }
//------------------smsession
    public void setSmsession(String sm) {
      AesEncryption aes = new AesEncryption();
        String encryptedName = aes.encrypt(sm.getBytes(), encryptionDecryptionKey.getBytes(),
                ivs.getBytes());
        spEditor = sharedPreferences.edit();
        spEditor.putString(FirstSmsession, encryptedName);
        spEditor.commit();
    }
    public String getSmsession() {
        String data = sharedPreferences.getString(FirstSmsession, null);
        AesEncryption aes = new AesEncryption();
        String decryptedData = aes.decrypt(data, encryptionDecryptionKey.getBytes(), ivs.getBytes());

        return decryptedData;
    }
//-------------session----------
    public void setSession(String sis) {

        spEditor = sharedPreferences.edit();
        spEditor.putString(FirstSession, sis);
        spEditor.commit();
    }
    public String getSession() {
        return sharedPreferences.getString(FirstSession, null);
    }*/
}

