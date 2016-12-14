package com.haven.dog.doghaven.Models;

/**
 * Created by kearn on 14/12/2016.
 */

public class DogWeightedScore {

    private int indexOfDogInList, score;

    public DogWeightedScore(int indexOfDogInList, int score){
        this.indexOfDogInList = indexOfDogInList;
        this.score = score;
    }

    public void setIndexOfDogInList(int indexOfDogInList) {
        this.indexOfDogInList = indexOfDogInList;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIndexOfDogInList() {
        return indexOfDogInList;
    }

    public int getScore() {
        return score;
    }
}
