
package com.watconsult.tlakapp.ui.map;


import com.google.gson.annotations.Expose;

public class Pous {

    private Integer poiId;
    private String poiName;
    private String latitude;
    private String longitude;
    private String poiAddress;
    private String typeIcon;
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getPoiId() {
        return poiId;
    }

    public void setPoiId(Integer poiId) {
        this.poiId = poiId;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
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

    public String getPoiAddress() {
        return poiAddress;
    }

    public void setPoiAddress(String poiAddress) {
        this.poiAddress = poiAddress;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

}
