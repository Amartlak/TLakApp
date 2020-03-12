
package com.watconsult.tlakapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("dayWiseweather")
    @Expose
    private List<DayWiseweather> dayWiseweather = null;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<DayWiseweather> getDayWiseweather() {
        return dayWiseweather;
    }

    public void setDayWiseweather(List<DayWiseweather> dayWiseweather) {
        this.dayWiseweather = dayWiseweather;
    }

}
