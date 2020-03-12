
package com.watconsult.tlakapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationPous {

    @SerializedName("itinearyId")
    @Expose
    private Integer itinearyId;
    @SerializedName("dayNumber")
    @Expose
    private Integer dayNumber;
    @SerializedName("locationId")
    @Expose
    private Integer locationId;
    @SerializedName("locacionName")
    @Expose
    private String locacionName;
    @SerializedName("poi")
    @Expose
    private List<Pous> poi = null;

    public Integer getItinearyId() {
        return itinearyId;
    }

    public void setItinearyId(Integer itinearyId) {
        this.itinearyId = itinearyId;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocacionName() {
        return locacionName;
    }

    public void setLocacionName(String locacionName) {
        this.locacionName = locacionName;
    }

    public List<Pous> getPoi() {
        return poi;
    }

    public void setPoi(List<Pous> poi) {
        this.poi = poi;
    }


    public boolean equals(Object obj)
    {
        if (obj instanceof LocationPous)
        {
            LocationPous other = (LocationPous)obj;
            if (other.getLocacionName().equals(this.getLocacionName()) && other.getLocationId().equals(this.getLocationId()))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public int hashCode()
    {
        return this.getLocacionName().hashCode() + this.getLocationId().hashCode();
    }
}
