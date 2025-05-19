package edu.bekthedev.diamondstravel.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User {
    private String username;
    private String password;

    public User() {
    }

    //constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @XmlElement
    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
}