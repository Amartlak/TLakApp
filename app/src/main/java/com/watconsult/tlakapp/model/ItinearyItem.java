package com.watconsult.tlakapp.model;

public class ItinearyItem {
    private boolean error;
    private int travelerId;
    private int peopleId;
    private int tour_package_id;
    private String tenantId;
    private int pkgId;
    private String pkgName;
    private String startDate ;
    private String endDate;
    private int totalDays;
    private int totalNight;
    private String startTime ;
    private String timezone;
  //  private String itinearyImagePath;
    private int itinearyId;
    private String dayNumber ;
    private String dayHeading;
    private String screenView ;
    private String description;
    private String inclusions;
    private String itinearyImage;
    private static int value;
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getTravelerId() {
        return travelerId;
    }

    public void setTravelerId(int travelerId) {
        this.travelerId = travelerId;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public int getTour_package_id() {
        return tour_package_id;
    }

    public void setTour_package_id(int tour_package_id) {
        this.tour_package_id = tour_package_id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public int getPkgId() {
        return pkgId;
    }

    public void setPkgId(int pkgId) {
        this.pkgId = pkgId;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

   /* public String getItinearyImagePath() {
        return itinearyImagePath;
    }

    public void setItinearyImagePath(String itinearyImagePath) {
        this.itinearyImagePath = itinearyImagePath;
    }*/

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

    public String getDayHeading() {
        return dayHeading;
    }

    public void setDayHeading(String dayHeading) {
        this.dayHeading = dayHeading;
    }

    public String getScreenView() {
        return screenView;
    }

    public void setScreenView(String screenView) {
        this.screenView = screenView;
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

    public void setValue(int value) {
        this.itinearyId = itinearyId;
    }
    public int getValue() {
        return value;
    }
}
