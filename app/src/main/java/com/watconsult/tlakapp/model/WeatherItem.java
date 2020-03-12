package com.watconsult.tlakapp.model;

public class WeatherItem {
    private boolean error;
    private String locationName;
    private String day;
    private String date;
    private String celsiusTempMin;
    private String fahrenheitTemMin;
    private String celsiusTempMax;
    private String fahrenheitTemMax;
    private String wind;
    private String icon;
    private String iconType;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

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

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCelsiusTempMin() {
        return celsiusTempMin;
    }

    public void setCelsiusTempMin(String celsiusTempMin) {
        this.celsiusTempMin = celsiusTempMin;
    }

    public String getFahrenheitTemMin() {
        return fahrenheitTemMin;
    }

    public void setFahrenheitTemMin(String fahrenheitTemMin) {
        this.fahrenheitTemMin = fahrenheitTemMin;
    }
    public String getCelsiusTempMax() {
        return celsiusTempMax;
    }

    public void setCelsiusTempMax(String celsiusTempMax) {
        this.celsiusTempMax = celsiusTempMax;
    }

    public String getFahrenheitTemMax() {
        return fahrenheitTemMax;
    }

    public void setFahrenheitTemMax(String fahrenheitTemMax) {
        this.fahrenheitTemMax = fahrenheitTemMax;
    }
    @Override
    public String toString() {
        return locationName;
    }
}
