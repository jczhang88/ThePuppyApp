package com.example.jeffrey_zhang.thepuppyapp;

public class User {

    private String displayName;
    private String emailAddress;
    private String profilePicUrl;
    private String userID;
    private String bio;

    User(){}

    public User(String displayName, String emailAddress, String profilePicUrl, String userID,
                String bio) {
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.profilePicUrl = profilePicUrl;
        this.userID = userID;
        this.bio = bio;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
}
