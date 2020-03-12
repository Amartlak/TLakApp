
package com.watconsult.tlakapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayWiseweather {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("celsiusTempMin")
    @Expose
    private Double celsiusTempMin;
    @SerializedName("fahrenheitTemMin")
    @Expose
    private Double fahrenheitTemMin;
    @SerializedName("celsiusTempMax")
    @Expose
    private Double celsiusTempMax;
    @SerializedName("fahrenheitTemMax")
    @Expose
    private Double fahrenheitTemMax;
    @SerializedName("wind")
    @Expose
    private String wind;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("iconType")
    @Expose
    private String iconType;
    @SerializedName("icon")
    @Expose
    private String icon;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getCelsiusTempMin() {
        return celsiusTempMin;
    }

    public void setCelsiusTempMin(Double celsiusTempMin) {
        this.celsiusTempMin = celsiusTempMin;
    }

    public Double getFahrenheitTemMin() {
        return fahrenheitTemMin;
    }

    public void setFahrenheitTemMin(Double fahrenheitTemMin) {
        this.fahrenheitTemMin = fahrenheitTemMin;
    }

    public Double getCelsiusTempMax() {
        return celsiusTempMax;
    }

    public void setCelsiusTempMax(Double celsiusTempMax) {
        this.celsiusTempMax = celsiusTempMax;
    }

    public Double getFahrenheitTemMax() {
        return fahrenheitTemMax;
    }

    public void setFahrenheitTemMax(Double fahrenheitTemMax) {
        this.fahrenheitTemMax = fahrenheitTemMax;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
