package com.example.jeffrey_zhang.thepuppyapp;

public class Dog {

    private String name;
    private String breed;
    private int age;
    private String temperament;
    private String ownerName;
    private String userID;

    Dog(){}

    public Dog(String name, String breed, int age, String temperament, String ownerName, String userID) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.temperament = temperament;
        this.ownerName = ownerName;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
