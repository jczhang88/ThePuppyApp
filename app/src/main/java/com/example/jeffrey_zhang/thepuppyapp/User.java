package com.example.jeffrey_zhang.thepuppyapp;

import java.util.Vector;

public class User {

    private String displayName;
    private String emailAddress;
    private String userID;

    User(){}

    public User(String displayName, String emailAddress, String userID, String nickname) {
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.userID = userID;
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


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
