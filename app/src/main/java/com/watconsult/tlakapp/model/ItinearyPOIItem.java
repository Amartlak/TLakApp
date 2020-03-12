package com.watconsult.tlakapp.model;

public class ItinearyPOIItem {
    private String dayNumber;
    private String locationName;
    private String poiName;
    private String poiImage;


    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
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

    public String getPoiImage() {
        return poiImage;
    }

    public void setPoiImage(String poiImage) {
        this.poiImage = poiImage;
    }
}
