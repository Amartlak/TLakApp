
package com.watconsult.tlakapp.ui.map;

import java.util.List;

public class PoiMap {

    private Integer locationId;
    private String locacionName;
    private List<Pous> poi = null;

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

}
