package com.example.jeffrey_zhang.thepuppyapp;

import java.util.Vector;

public class User {

    private String displayName;
    private String emailAddress;
    public String userID;
    private int num_dogs;

    User(){}

    public User(String displayName, String emailAddress, String userID) {
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.userID = userID;
        this.num_dogs = 0;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getNum_dogs() {
        return num_dogs;

    }

    public void setNum_dogs(int num_dogs) {
        this.num_dogs = num_dogs;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
