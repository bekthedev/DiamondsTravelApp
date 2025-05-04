package edu.bekthedev.diamondstravel.model;


public class Flight {
    private String departureDate;
    private String origin;
    private String destination;
    private String price;

    public Flight() {}

    public Flight(String departureDate, String origin, String destination, String price) {
        this.departureDate = departureDate;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}