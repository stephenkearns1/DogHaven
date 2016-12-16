package com.haven.dog.doghaven.Models;

/**
 * Created by Stephen Kearns on 16/12/2016.
 */

public class StudSearchPrefs {

    private String size, fur, body, tolerance, neutered , energy, exercise, intelligence, playful, instinct, people, family, dogs, emotion, sociability;


    public StudSearchPrefs(String size,String fur, String body,String tolerance, String neutered , String energy,
                     String exercise,String intelligence,String playful,String instinct,String people,String family,String dogs,String emotion, String sociability ){
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
        this.sociability = sociability;
    }


    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }

    public void setSociability(String sociability) {
        this.sociability = sociability;
    }

    public void setSize(String size) {
        this.size = size;
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

    public void setNeutered(String neutered) {
        this.neutered = neutered;
    }

    public void setInstinct(String instinct) {
        this.instinct = instinct;
    }

    public void setFur(String fur) {
        this.fur = fur;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public void setDogs(String dogs) {
        this.dogs = dogs;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getDogs() {
        return dogs;
    }

    public String getBody() {
        return body;
    }

    public String getEnergy() {
        return energy;
    }

    public String getEmotion() {
        return emotion;
    }

    public String getFamily() {
        return family;
    }

    public String getExercise() {
        return exercise;
    }

    public String getInstinct() {
        return instinct;
    }

    public String getFur() {
        return fur;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public String getNeutered() {
        return neutered;
    }

    public String getPeople() {
        return people;
    }

    public String getPlayful() {
        return playful;
    }

    public String getSize() {
        return size;
    }

    public String getSociability() {
        return sociability;
    }

    public String getTolerance() {
        return tolerance;
    }

}


