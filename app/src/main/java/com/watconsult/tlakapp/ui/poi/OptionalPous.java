
package com.watconsult.tlakapp.ui.poi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptionalPous {

    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("poiName")
    @Expose
    private String poiName;
    @SerializedName("optionPoiImage")
    @Expose
    private String optionPoiImage;
    @SerializedName("optionTypeImage")
    @Expose
    private String optionTypeImage;

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

    public String getOptionPoiImage() {
        return optionPoiImage;
    }

    public void setOptionPoiImage(String optionPoiImage) {
        this.optionPoiImage = optionPoiImage;
    }

    public String getOptionTypeImage() {
        return optionTypeImage;
    }

    public void setOptionTypeImage(String optionTypeImage) {
        this.optionTypeImage = optionTypeImage;
    }

}
