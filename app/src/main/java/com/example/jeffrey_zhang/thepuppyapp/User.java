package com.example.jeffrey_zhang.thepuppyapp;


public class User {

    private String displayName;
    private String emailAddress;
    private String userID;
    private String Bio;
    private String Location;
    private int num_dogs;

    User(){}

    public User(String displayName, String emailAddress, String userID) {
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.userID = userID;
        this.num_dogs = 0;
        this.Bio = "";
        this.Location = "";
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

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public void setNum_dogs(int num_dogs) {
        this.num_dogs = num_dogs;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
