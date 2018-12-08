package com.example.jeffrey_zhang.thepuppyapp;

public class Events {
    String eventName;
    String eventLocation;
    String eventDate;
    String eventDescription;

    Events () {

    }

    public Events(String eventName, String eventLocation, String eventDate, String eventDescription) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
    }
}
