package com.haven.dog.doghaven.Models;

/**
 * Created by Stephen Kearns on 10/12/2016.
 */

public class Dog {
    private String name, breed, age, color;
    //physical attributes
    private String size, fur, body, tolerance, neutered;

    public Dog(String name, String breed, String age, String color, String size, String fur, String body, String tolerance, String neutered){
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.size = size;
        this.fur = fur;
        this.body = body;
        this.tolerance = tolerance;
        this.neutered = neutered;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setNeutered(String neutered) {
        this.neutered = neutered;
    }

    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }

    public void setFur(String fur) {
        this.fur = fur;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getAge() {
        return age;
    }

    public String getBody() {
        return body;
    }

    public String getFur() {
        return fur;
    }

    public String getColor() {
        return color;
    }

    public String getNeutered() {
        return neutered;
    }

    public String getSize() {
        return size;
    }

    public String getTolerance() {
        return tolerance;
    }
}
