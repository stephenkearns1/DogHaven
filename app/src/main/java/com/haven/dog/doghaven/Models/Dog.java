package com.haven.dog.doghaven.Models;

/**
 * Created by Stephen Kearns on 10/12/2016.
 */

public class Dog {
    private String name, breed, companyName, age, color;
    //physical attributes
    private int dogId;
    private  String size, fur, body, tolerance, neutered;
    private String energy, exercise, intelligence, playful,instinct;
    private String people, family, dogs, emotion, sociality;
    private String dillcur, dillpast, dvac, dvacmiss;


    public Dog(int dogId, String name, String breed,String companyName, String age, String color,
               String size, String fur, String body, String tolerance, String neutered,
               String energy, String exercise,  String intelligence,  String playful, String instinct,
               String people, String family, String dogs, String emotion, String sociality,
               String dillcur, String dillpast,String dvac,String dvacmiss
    ){
        this.dogId = dogId;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.size = size;
        this.fur = fur;
        this.body = body;
        this.tolerance = tolerance;
        this.neutered = neutered;
        this.energy = energy;
        this.exercise = exercise;
        this.intelligence = intelligence;
        this.playful = playful;
        this.instinct = instinct;
        this.people = people;
        this.family = family;
        this.dogs = dogs;
        this.emotion = emotion;
        this.sociality = sociality;
        this.dillcur = dillcur;
        this.dillpast = dillpast;
        this.dvac = dvac;
        this.dvacmiss = dvacmiss;

    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSociality(String sociality) {
        this.sociality = sociality;
    }

    public void setPlayful(String playful) {
        this.playful = playful;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public void setInstinct(String instinct) {
        this.instinct = instinct;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public void setDvacmiss(String dvacmiss) {
        this.dvacmiss = dvacmiss;
    }

    public void setDvac(String dvac) {
        this.dvac = dvac;
    }

    public void setDogs(String dogs) {
        this.dogs = dogs;
    }

    public void setDillpast(String dillpast) {
        this.dillpast = dillpast;
    }

    public void setDillcur(String dillcur) {
        this.dillcur = dillcur;
    }

    public int getDogId() {
        return dogId;
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

    public String getCompanyName() {
        return companyName;
    }

    public String getTolerance() {
        return tolerance;
    }

    public String getSociality() {
        return sociality;
    }

    public String getPlayful() {
        return playful;
    }

    public String getPeople() {
        return people;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public String getInstinct() {
        return instinct;
    }

    public String getFamily() {
        return family;
    }

    public String getExercise() {
        return exercise;
    }

    public String getEnergy() {
        return energy;
    }

    public String getEmotion() {
        return emotion;
    }

    public String getDvacmiss() {
        return dvacmiss;
    }

    public String getDvac() {
        return dvac;
    }

    public String getDogs() {
        return dogs;
    }

    public String getDillpast() {
        return dillpast;
    }

    public String getDillcur() {
        return dillcur;
    }
}
