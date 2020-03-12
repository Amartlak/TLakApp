
package com.watconsult.tlakapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Traveler {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("tour_package_id")
    @Expose
    private Integer tourPackageId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTourPackageId() {
        return tourPackageId;
    }

    public void setTourPackageId(Integer tourPackageId) {
        this.tourPackageId = tourPackageId;
    }

}
