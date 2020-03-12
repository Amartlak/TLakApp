package com.watconsult.tlakapp.model;

public class DocListitem {
    private boolean error;
    private String message;
    private String pkgId;
    private String docType;
    private String tenantId;
    private String token;
    private String travelDocId;
    private String travelDocName;
    private String typeIcon;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPkgId() {
        return pkgId;
    }

    public void setPkgId(String pkgId) {
        this.pkgId = pkgId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTravelDocId() {
        return travelDocId;
    }

    public void setTravelDocId(String travelDocId) {
        this.travelDocId = travelDocId;
    }

    public String getTravelDocName() {
        return travelDocName;
    }

    public void setTravelDocName(String travelDocName) {
        this.travelDocName = travelDocName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }
}
