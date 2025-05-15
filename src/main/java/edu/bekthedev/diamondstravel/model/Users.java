package edu.bekthedev.diamondstravel.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
public class Users {
    private List<User> users = new ArrayList<>();

    @XmlElement(name = "user")
    public List<User> getUsers() {

        return users;
    }

    public void setUsers(List<User> users) {

        this.users = users;
    }
}