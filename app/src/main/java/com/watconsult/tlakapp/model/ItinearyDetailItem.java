package com.watconsult.tlakapp.model;

public class ItinearyDetailItem {
    private boolean error;
    private String pkgName;
    private String itinearyImagePath;
    private String token;
    private int tour_package_id;
    private int totalDays;
    private int totalNight;
    private int itinearyId;
    private String dayNumber ;
    private String description;
    private String inclusions;
    private String itinearyImage;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getItinearyImagePath() {
        return itinearyImagePath;
    }

    public void setItinearyImagePath(String itinearyImagePath) {
        this.itinearyImagePath = itinearyImagePath;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTour_package_id() {
        return tour_package_id;
    }

    public void setTour_package_id(int tour_package_id) {
        this.tour_package_id = tour_package_id;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getTotalNight() {
        return totalNight;
    }

    public void setTotalNight(int totalNight) {
        this.totalNight = totalNight;
    }

    public int getItinearyId() {
        return itinearyId;
    }

    public void setItinearyId(int itinearyId) {
        this.itinearyId = itinearyId;
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInclusions() {
        return inclusions;
    }

    public void setInclusions(String inclusions) {
        this.inclusions = inclusions;
    }

    public String getItinearyImage() {
        return itinearyImage;
    }

    public void setItinearyImage(String itinearyImage) {
        this.itinearyImage = itinearyImage;
    }
}
