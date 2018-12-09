package com.example.jeffrey_zhang.thepuppyapp;

public class Events {
    String eventName;
    String eventLocation;
    String eventDate;
    String eventDescription;
    Integer maxCapacity;

    Events () {

    }

    public Events(String eventName, String eventLocation, String eventDate, String eventDescription,
                  Integer maxCapacity) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.maxCapacity = maxCapacity;
    }
}
