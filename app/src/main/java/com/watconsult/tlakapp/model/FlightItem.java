package com.watconsult.tlakapp.model;

public class FlightItem {
    private boolean error;
    private String message;
    private int travelerId;
    private int tour_package_id;
    private String tenantId;
    private String token;
    private int peopleId;
    private int flightNumber;
    private String airlinCode;
    private String departureDate;
    private String arrivalDate;
    private String departureTime;
    private String arrivalTime;
    private String totalTime;
    private String departureIata;
    private String arrivalIata;
    private String departureTerminal;
    private String flightId;
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

    public int getTravelerId() {
        return travelerId;
    }

    public void setTravelerId(int travelerId) {
        this.travelerId = travelerId;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirlinCode() {
        return airlinCode;
    }

    public void setAirlinCode(String airlinCode) {
        this.airlinCode = airlinCode;
    }
    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureIata() {
        return departureIata;
    }

    public void setDepartureIata(String departureIata) {
        this.departureIata = departureIata;
    }

    public String getArrivalIata() {
        return arrivalIata;
    }

    public void setArrivalIata(String arrivalIata) {
        this.arrivalIata = arrivalIata;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
}
