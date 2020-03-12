
package com.watconsult.tlakapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pous {

    @SerializedName("poiId")
    @Expose
    private Integer poiId;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("poiName")
    @Expose
    private String poiName;
    @SerializedName("poiDescription")
    @Expose
    private String poiDescription;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("poiImage")
    @Expose
    private String poiImage;
    @SerializedName("poiType")
    @Expose
    private String poiType;
    @SerializedName("typeIcon")
    @Expose
    private String typeIcon;

    public Integer getPoiId() {
        return poiId;
    }

    public void setPoiId(Integer poiId) {
        this.poiId = poiId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiDescription() {
        return poiDescription;
    }

    public void setPoiDescription(String poiDescription) {
        this.poiDescription = poiDescription;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPoiImage() {
        return poiImage;
    }

    public void setPoiImage(String poiImage) {
        this.poiImage = poiImage;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

}
