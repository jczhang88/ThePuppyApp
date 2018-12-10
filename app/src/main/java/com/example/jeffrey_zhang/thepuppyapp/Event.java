package com.example.jeffrey_zhang.thepuppyapp;

public class Event {
    String eventName;
    String eventLocation;
    String eventDate;
    String eventDescription;
    String maxCapacity;
    Integer numAttendees;

    Event () {

    }

    public Event(String eventName, String eventLocation, String eventDate, String eventDescription,
                  String maxCapacity, Integer numAttendees) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.maxCapacity = maxCapacity;
        this.numAttendees = numAttendees;
    }
}
