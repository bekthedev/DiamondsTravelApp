package edu.bekthedev.diamondstravel.model;

import jakarta.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String origin;
    private String destination;
    private String date;


    public Booking(String username, String origin, String destination, String date, String departureTime) {
        this.username = username;
        this.origin = origin;
        this.destination = destination;
        this.date = date;

    }

    //  Required no-arg constructor for JPA
    public Booking() {
    }

    public Booking(Long id, String username, String origin, String destination, String date, String departureTime) {
        this.id = id;
        this.username = username;
        this.origin = origin;
        this.destination = destination;
        this.date = date;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}