package com.haven.dog.doghaven.Models;

/**
 * Created by kearn on 18/12/2016.
 */

public class StudPrefs {
    private String body , energy , intelligence ,playful , instinct,people;

    public StudPrefs(String body, String energy, String instinct, String intelligence, String people, String playful) {
        this.body = body;
        this.energy = energy;
        this.instinct = instinct;
        this.intelligence = intelligence;
        this.people = people;
        this.playful = playful;
    }


    public void setBody(String body) {
        this.body = body;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public void setInstinct(String instinct) {
        this.instinct = instinct;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public void setPlayful(String playful) {
        this.playful = playful;
    }

    public String getBody() {
        return body;
    }

    public String getInstinct() {
        return instinct;
    }

    public String getEnergy() {
        return energy;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public String getPeople() {
        return people;
    }

    public String getPlayful() {
        return playful;
    }
}
