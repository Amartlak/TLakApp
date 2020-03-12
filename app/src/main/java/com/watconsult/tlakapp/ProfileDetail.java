package com.watconsult.tlakapp;


/**
 * Created by GAURAV on 7/16/2017.
 */

public class ProfileDetail {


    private String picture;

    private String picturename;

    public ProfileDetail() {
    }


    public ProfileDetail(String picture, String picturename) {

        this.picture = picture;
        this.picturename = picturename;

    }



    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicturename() {
        return picturename;
    }

    public void setPicturename(String picturename) {
        this.picturename = picturename;
    }

}
