
package com.watconsult.tlakapp.ui.map;

import java.util.List;

public class MainPOI {

    private Boolean error;
    private String message;
    private String typeImage;
    private Traveler traveler;
    private List<PoiMap> poiMap = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

    public Traveler getTraveler() {
        return traveler;
    }

    public void setTraveler(Traveler traveler) {
        this.traveler = traveler;
    }

    public List<PoiMap> getPoiMap() {
        return poiMap;
    }

    public void setPoiMap(List<PoiMap> poiMap) {
        this.poiMap = poiMap;
    }

}
