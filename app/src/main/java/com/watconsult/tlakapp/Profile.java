package com.watconsult.tlakapp;


/**
 * Created by gaurav.garg on 18-07-2017.
 */

public class Profile {



    private String profilePic;


    private String profilePicname;

    private String about;

    /**
     * No args constructor for use in serialization
     */
    public Profile() {
    }



    public Profile(String profilePic, String profilePicname) {

        this.profilePic = profilePic;
        this.profilePicname = profilePicname;

    }





    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePicname() {
        return profilePicname;
    }

    public void setProfilePicname(String profilePicname) {
        this.profilePicname = profilePicname;
    }






}
