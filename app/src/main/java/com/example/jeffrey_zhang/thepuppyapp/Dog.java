package com.example.jeffrey_zhang.thepuppyapp;

public class Dog {

    private String name;
    private String breed;
    private int age;
    private String profilePicUrl;
    private String temperament;
    private String ownerName;

    Dog(){}

    public Dog(String name, String breed, int age, String profilePicUrl, String temperament, String ownerName) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.profilePicUrl = profilePicUrl;
        this.temperament = temperament;
        this.ownerName = ownerName;
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

    public String getProfilePicUrl() {
        return profilePicUrl;
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

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
