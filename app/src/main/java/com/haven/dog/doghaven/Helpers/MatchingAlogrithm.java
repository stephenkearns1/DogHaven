package com.haven.dog.doghaven.Helpers;

import com.haven.dog.doghaven.Models.Dog;
import com.haven.dog.doghaven.Models.DogWeightedScore;
import com.haven.dog.doghaven.Models.UserPrefs;

import java.util.ArrayList;

/**
 * Created by kearn on 13/12/2016.
 */

public class MatchingAlogrithm {


    private ArrayList<Dog> dogList;
    private ArrayList<DogWeightedScore> dogScoreList;
    private ArrayList<UserPrefs> userPrefs;
    private ArrayList<Dog> dogsToShow;

    public MatchingAlogrithm(){

        /*
             Attributes user search is based on and order
             Marken  Teder: Physical - Size, Fur, Body, Tolerance, Neutered
             Marken  Teder: Behaviour - Energy, Exercise, Intelligence, Playful, Instinct
             Marken  Teder: Social - People, Family, Dogs, Emotion, Sociability
         */

        dogList = new ArrayList<>();
        dogScoreList = new ArrayList<>();
        userPrefs = new ArrayList<>();
        dogsToShow = new ArrayList<>();

    }



    public void AddDogWeightings(){

        for(int i = 0; i < dogList.size(); i++){

            Dog dog = dogList.get(i);
            //reset the score for next dogs score to be calculated
            int score = 0;
           // for(int j = 0; j < userPrefs.size(); j++){
        /*
             Attributes user search is based on and order
             Physical - Size, Fur, Body, Tolerance, Neutered
             Behaviour - Energy, Exercise, Intelligence, Playful, Instinct
             Social - People, Family, Dogs, Emotion, Sociability
         */
                //add weighting methods here
                if(userPrefs.get(0).getSize().equalsIgnoreCase(dog.getSize()))
                    score++;
                else if(userPrefs.get(0).getFur().equalsIgnoreCase(dog.getFur()))
                    score++;
                else if(userPrefs.get(0).getBody().equalsIgnoreCase(dog.getBody()))
                    score++;
                else if(userPrefs.get(0).getTolerance().equalsIgnoreCase(dog.getTolerance()))
                    score++;
                else if(userPrefs.get(0).getNeutered().equalsIgnoreCase(dog.getNeutered()))
                    score++;
                else if(userPrefs.get(0).getEnergy().equalsIgnoreCase(dog.getEnergy()))
                    score++;
                else if(userPrefs.get(0).getExercise().equalsIgnoreCase(dog.getExercise()))
                    score++;
                else if(userPrefs.get(0).getIntelligence().equalsIgnoreCase(dog.getIntelligence()))
                    score++;
                else if(userPrefs.get(0).getPlayful().equalsIgnoreCase(dog.getPlayful()))
                    score++;
                else if(userPrefs.get(0).getInstinct().equalsIgnoreCase(dog.getInstinct()))
                    score++;
                else if(userPrefs.get(0).getPeople().equalsIgnoreCase(dog.getPeople()))
                    score++;
                else if(userPrefs.get(0).getFamily().equalsIgnoreCase(dog.getFamily()))
                    score++;
                else if(userPrefs.get(0).getDogs().equalsIgnoreCase(dog.getDogs()))
                    score++;
                else if(userPrefs.get(0).getEmotion().equalsIgnoreCase(dog.getEmotion()))
                    score++;
                else if(userPrefs.get(0).getSociability().equalsIgnoreCase(dog.getSociality()))
                    score++;

            //}

            DogWeightedScore weightedDogScore = new DogWeightedScore(i, score);
            dogScoreList.add(weightedDogScore);
        }
    }


    public ArrayList MostSuitedDogs(){

        QuickSort(0, dogScoreList.size()-1);
        /*
        int starting_point = (0 + dogScoreList.size()-1) / 2;

        for(int i = dogScoreList.size()-1; i >=  starting_point; i--){

            int index = dogScoreList.get(i).getIndexOfDogInList();
            dogsToShow.add((Dog) dogList.get(index));
        }

        */

        for(int i = 0; i < 3; i++){

            int index = dogScoreList.get(i).getIndexOfDogInList();
            dogsToShow.add((Dog) dogList.get(index));
        }

        return dogsToShow;
    }

    public void QuickSort(int start, int end){
        int parition_index;
        if(start < end){

            parition_index = Parition(start, end);

            QuickSort(start,parition_index-1);
            QuickSort(parition_index+1, end);

        }else
            return;
    }


    public int Parition(int start, int end){


        DogWeightedScore pivot = dogScoreList.get(start);
        int up = start+1, down = end;

        while(up < down){

            while(start < end && ((Comparable)dogScoreList.get(up).getScore()).compareTo((Comparable)pivot.getScore()) > 0){
                up = up +1;
            }

            while(down > start && (((Comparable)dogScoreList.get(down).getScore()).compareTo((Comparable)pivot.getScore()) < 0 || ((Comparable)dogScoreList.get(down).getScore()).compareTo((Comparable)pivot.getScore())== 0)){
                down = down - 1;
            }

            if(up < down){

                DogWeightedScore temp = dogScoreList.get(up);
                dogScoreList.set(up, dogScoreList.get(down));
                dogScoreList.set(down, temp);

            }
        }

        //up and down have crossed switch down with the pivot

        dogScoreList.set(start,dogScoreList.get(down));
        dogScoreList.set(down, pivot);
        //return the pivot index
        return down;
    }


    public void setDogList(ArrayList<Dog> dogList) {
        this.dogList = dogList;
    }


    public void setDogsToShow(ArrayList<Dog> dogsToShow) {
        this.dogsToShow = dogsToShow;
    }

    public void setuserPrefs(ArrayList<UserPrefs> userPrefs) {
        this.userPrefs = userPrefs;
    }
}




