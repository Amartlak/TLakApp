
package com.watconsult.tlakapp.ui.poi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewOnePOIMain {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("typeImagePath")
    @Expose
    private String typeImagePath;
    @SerializedName("poiImagePath")
    @Expose
    private String poiImagePath;
    @SerializedName("traveler")
    @Expose
    private Traveler traveler;
    @SerializedName("optionalPoi")
    @Expose
    private List<OptionalPous> optionalPoi = null;
    @SerializedName("locationPoi")
    @Expose
    private List<LocationPous> locationPoi = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeImagePath() {
        return typeImagePath;
    }

    public void setTypeImagePath(String typeImagePath) {
        this.typeImagePath = typeImagePath;
    }

    public String getPoiImagePath() {
        return poiImagePath;
    }

    public void setPoiImagePath(String poiImagePath) {
        this.poiImagePath = poiImagePath;
    }

    public Traveler getTraveler() {
        return traveler;
    }

    public void setTraveler(Traveler traveler) {
        this.traveler = traveler;
    }

    public List<OptionalPous> getOptionalPoi() {
        return optionalPoi;
    }

    public void setOptionalPoi(List<OptionalPous> optionalPoi) {
        this.optionalPoi = optionalPoi;
    }

    public List<LocationPous> getLocationPoi() {
        return locationPoi;
    }

    public void setLocationPoi(List<LocationPous> locationPoi) {
        this.locationPoi = locationPoi;
    }

}
