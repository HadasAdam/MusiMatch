package com.example.musimatch.models;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Transaction;

import com.example.musimatch.models.enums.UserType;
import com.example.musimatch.services.GeneralUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
public class User implements MusimatchEntityInterface {
    public final static String TABLE_NAME = "Users";
    private static final String ID_COLUMN_NAME = "ID";
    private static final String USERNAME_COLUMN_NAME = "USERNAME";
    private static final String IS_ADMIN_COLUMN_NAME = "IS_ADMIN";
    private static final String USER_TYPE_COLUMN_NAME = "USER_TYPE";
    private static final String FIRST_NAME_COLUMN_NAME = "FIRST_NAME";
    private static final String LAST_NAME_COLUMN_NAME = "LAST_NAME";
    private static final String AVERAGE_RATE_COLUMN_NAME = "AVERAGE_RATE";
    private static final String SPOTIFY_URL_COLUMN_NAME = "SPOTIFY_URL";
    private static final String CREATION_DATE_COLUMN_NAME = "CREATION_DATE";

    @PrimaryKey
    @NonNull
    private Long id;
    private String username;
    private boolean isAdmin;
    private String email; //TODO: need to be added to database
    private UserType userType = UserType.NONE;
    private String firstName = "";
    private String lastName = "";
    private Double averageRate = 0d; //TODO: unused in the meanwhile
    private String spotifyUrl = ""; //TODO: unused in the meanwhile
    private LocalDate creationDate;

    public User() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public User(Long id, String username, String email, UserType userType,
                String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isAdmin = false;
        this.creationDate = LocalDate.now();
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageRate = 0D;
        this.spotifyUrl = "";

    }

    public User(Long id, String username, String email, boolean isAdmin, LocalDate creationDate, UserType userType,
                String firstName, String lastName, Double averageRate, String spotifyUrl) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
        this.creationDate = creationDate;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageRate = averageRate;
        this.spotifyUrl = spotifyUrl;
    }

    //TODO: ADD "EMAIL" TO THIS CONSTRUCTOR AND TO THE DATABASE
    public User(Long id, String username, boolean isAdmin, LocalDate creationDate, UserType userType,
                 String firstName, String lastName, Double averageRate, String spotifyUrl) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
        this.creationDate = creationDate;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageRate = averageRate;
        this.spotifyUrl = spotifyUrl;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(ID_COLUMN_NAME, id);
        json.put(USERNAME_COLUMN_NAME, username);
        json.put(IS_ADMIN_COLUMN_NAME, isAdmin);
        json.put(USER_TYPE_COLUMN_NAME, userType); //enum
        json.put(FIRST_NAME_COLUMN_NAME, firstName);
        json.put(LAST_NAME_COLUMN_NAME, lastName);
        json.put(AVERAGE_RATE_COLUMN_NAME, averageRate); //.toJson()
        json.put(SPOTIFY_URL_COLUMN_NAME, spotifyUrl);
        json.put(CREATION_DATE_COLUMN_NAME, creationDate);
        return json;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static User create(Map<String, Object> json) {
        try {
            Long id = Long.parseLong((String) json.get(ID_COLUMN_NAME));
            String username = (String) json.get(USERNAME_COLUMN_NAME);
            Boolean isAdmin = GeneralUtils.StringToBoolean((String) json.get(IS_ADMIN_COLUMN_NAME));
            UserType userType = UserType.getByName((String) json.get(USER_TYPE_COLUMN_NAME));
            String firstName = (String) json.get(FIRST_NAME_COLUMN_NAME);
            String lastName = (String) json.get(LAST_NAME_COLUMN_NAME);
            Double averageRate = Double.parseDouble((String) json.get(AVERAGE_RATE_COLUMN_NAME));
            String spotifyURL = (String) json.get(SPOTIFY_URL_COLUMN_NAME);
            LocalDate creationDate = LocalDate.parse((String) json.get(CREATION_DATE_COLUMN_NAME));

            return new User(id,username,isAdmin,creationDate,userType,firstName,lastName,
                    averageRate,spotifyURL);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTableName() {
        return TABLE_NAME;
    }
}
