package com.watconsult.tlakapp.model;

public class LoginItem {
    private boolean error;
    private String message;
   private String pkgName;
    private int pkgId;
    private String tenantId;
    private String passcode;
    private int userId;
    private String peopleName ;
    private String peopleId;
    private int occupied;
    private int selectedName;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setQuestion(String message) {
        this.message = message;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
    public Integer getPkgId() {
        return pkgId;
    }

    public void setPkgId(int pkgId) {
        this.pkgId = pkgId;
    }
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }
    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }
    public Integer getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public int getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(int selectedName) {
        this.selectedName = selectedName;
    }
}