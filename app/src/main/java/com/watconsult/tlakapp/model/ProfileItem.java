package com.watconsult.tlakapp.model;

public class ProfileItem {
    private boolean error;
    private String token;
    private String tenantId;
    private String travelerId;
    private String travelerName;
    private String travelerEmail;
    private String travelerPhone;
    private String travelerDOB;
    private String travelerAddress;
    private String totalDeparture;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTravelerId() {
        return travelerId;
    }

    public void setTravelerId(String travelerId) {
        this.travelerId = travelerId;
    }

    public String getTravelerName() {
        return travelerName;
    }

    public void setTravelerName(String travelerName) {
        this.travelerName = travelerName;
    }

    public String getTravelerEmail() {
        return travelerEmail;
    }

    public void setTravelerEmail(String travelerEmail) {
        this.travelerEmail = travelerEmail;
    }

    public String getTravelerPhone() {
        return travelerPhone;
    }

    public void setTravelerPhone(String travelerPhone) {
        this.travelerPhone = travelerPhone;
    }

    public String getTravelerDOB() {
        return travelerDOB;
    }

    public void setTravelerDOB(String travelerDOB) {
        this.travelerDOB = travelerDOB;
    }

    public String getTravelerAddress() {
        return travelerAddress;
    }

    public void setTravelerAddress(String travelerAddress) {
        this.travelerAddress = travelerAddress;
    }

    public String getTotalDeparture() {
        return totalDeparture;
    }

    public void setTotalDeparture(String totalDeparture) {
        this.totalDeparture = totalDeparture;
    }
}
