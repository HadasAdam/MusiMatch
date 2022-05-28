package com.example.musimatch.models;

import androidx.room.Entity;

import com.example.musimatch.models.enums.UserType;

@Entity
public class User {
    private String id;
    private String username;
    private boolean isAdmin;
    private Double lastUpdated;
    private UserType userType = UserType.NONE;
    private String firstName = "";
    private String lastName = "";
    private Double averageRate = 0d;
    private String spotifyUrl = "";

    public User() {

    }

    public User(String id, String username, boolean isAdmin, Double lastUpdated, UserType userType,
                 String firstName, String lastName, Double averageRate, String spotifyUrl) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
        this.lastUpdated = lastUpdated;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageRate = averageRate;
        this.spotifyUrl = spotifyUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Double getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Double lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }

    public String getSpotifyUrl() {
        return spotifyUrl;
    }

    public void setSpotifyUrl(String spotifyUrl) {
        this.spotifyUrl = spotifyUrl;
    }
}
