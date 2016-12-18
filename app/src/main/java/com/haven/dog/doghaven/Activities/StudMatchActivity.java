package com.haven.dog.doghaven.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.MatchDogsAdapter;
import com.haven.dog.doghaven.Helpers.MatchingAlogrithm;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;
import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Models.Breeder;
import com.haven.dog.doghaven.Models.Dog;
import com.haven.dog.doghaven.Models.UserPrefs;
import com.haven.dog.doghaven.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
  * Created by Marken Teder on 11/11/2016.
 */

public class StudMatchActivity extends AppCompatActivity {

    ImageView matches;
    Spinner dropdown;
    ScrollView matchlist;
    private ArrayList<Dog> dogsList;
    private int dogId;
    private String name, breed, companyName, age, color;
    private  String size, fur, body, tolerance, neutered;
    private String energy, exercise, intelligence, playful,instinct;
    private String people, family, dogs, emotion, sociability;
    private String dillcur, dillpast, dvac, dvacmiss;
    private String TAG_dogname = "dog_name", TAG_age = "dog_age", TAG_breed = "dog_breed", TAG_company = "dog_company", TAG_color= "dog_color";
    private String TAG_size = "size", TAG_fur = "fur", TAG_body = "body", TAG_tolerance = "tolerance", TAG_neutered ="neutered";
    private String TAG_energy = "energy", TAG_exercise ="exercise", TAG_intelligence= "intelligence", TAG_playful ="playful", TAG_instinct = "instinct";
    private String TAG_people="people", TAG_family="family", TAG_dogs="dogs", TAG_emotion="emotion", TAG_sociality="sociability";
    private String TAG_dillcur ="dillcurr", TAG_dillpast = "dillpast", TAG_dvac = "dvac", TAG_dvacmiss = "dvacmiss";
    private ProgressDialog progressDialog;
    private UserSessionManagment userSessionManag;
    private MatchingAlogrithm match;
    private RecyclerView mRecyclerView;
    private MatchDogsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public String searchBreed;
    private final String doghavenAPI_GetStuds_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php?GetStuds=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stud_match_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //get a reference to the reycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.matchDogs_recyclerView);

        //sets the layout mangaer to use a linear layout for displaying views
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //sets up the recyler view to use the customer adapter for dogs
        mAdapter = new MatchDogsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        //instantiates objects for reference
        match = new MatchingAlogrithm();
        userSessionManag = new UserSessionManagment(this);






    }
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            //display logged in or start main activity
            displayUserDetails();
        } else {
            //starts loginIn activity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        //retrive data from stud match activity

        /*
         Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String value = extras.getString("key");
                //The key argument here must match that used in the other activity
            }
         */

    }

    public void GetStuds(){
        JsonArrayRequest getStudListRequest = new JsonArrayRequest(doghavenAPI_GetStuds_URL+searchBreed,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //check the response from the server
                        Log.i("New Response", response.toString());

                        if (!(dogsList == null)) {
                            dogsList.clear();

                        }

                        mAdapter.ClearAll();
                        try {


                            for (int i = 0; i < response.length()-1; i++) {
                                JSONObject dogObj = (JSONObject) response.get(i);
                                dogId= Integer.parseInt(dogObj.getString("dog_id"));
                                name =  dogObj.getString(TAG_dogname);
                                age = dogObj.getString(TAG_age);
                                breed = dogObj.getString(TAG_breed);
                                companyName = dogObj.getString(TAG_company);
                                color = dogObj.getString(TAG_color);
                                size = dogObj.getString(TAG_size);
                                fur = dogObj.getString(TAG_fur);
                                body = dogObj.getString(TAG_body);
                                tolerance = dogObj.getString(TAG_tolerance);
                                neutered = dogObj.getString(TAG_neutered);
                                energy = dogObj.getString(TAG_energy);
                                exercise = dogObj.getString(TAG_exercise);
                                intelligence = dogObj.getString(TAG_intelligence);
                                playful = dogObj.getString(TAG_playful);
                                instinct = dogObj.getString(TAG_instinct);
                                people = dogObj.getString(TAG_people);
                                family = dogObj.getString(TAG_family);
                                dogs = dogObj.getString(TAG_dogs);
                                emotion = dogObj.getString(TAG_emotion);
                                sociability = dogObj.getString(TAG_sociality);
                                dillcur = dogObj.getString(TAG_dillcur);
                                dillpast = dogObj.getString(TAG_dillpast);
                                dvac = dogObj.getString(TAG_dvac);
                                dvacmiss = dogObj.getString(TAG_dvacmiss);

                                Dog dog = new Dog(dogId, name, age, breed, companyName, color, size, fur, body
                                        ,tolerance, neutered, energy,exercise, intelligence, playful, instinct, people
                                        ,family,dogs,emotion, sociability, dillcur, dillpast, dvac, dvacmiss
                                );

                                dogsList.add(dog);
                                /*
                                int id = Integer.parseInt(shopObj.getString(tagId));

                                String eventCat = shopObj.getString(tagCatagory);
                                String eventTitle = shopObj.getString(tagtitle);
                                String eventLocation = shopObj.getString(tagLocation);
                                String eventTime =shopObj.getString(tagpTime);
                                String eventDate  = shopObj.getString(tagDate);
                                Double eventLat = Double.parseDouble(shopObj.getString(tagLat));
                                Double eventLong = Double.parseDouble(shopObj.getString(tagLong));





                                EventsModel event = new EventsModel(id, eventCat, eventTitle, eventLocation,eventTime,eventDate,eventLat,eventLong);
                                listOfEvents.add(event);
                               */

                                //when user prefs have been received, find the dog matches for the user

                            }

                            getMatches();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("JSONError", e.toString());
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(getStudListRequest);

    }


    public void getMatches(){
        //set the data in matchig algorthim to generate matches
        match = new MatchingAlogrithm();
        match.setDogList(dogsList);
        //match.setuserPrefs();
        match.AddDogWeightings();
        match.MostSuitedDogs();
        ArrayList<Dog> dogsToShow = match.getDogsToShow();
        mAdapter.ClearAll();
        if(dogsToShow.size() == 0){

        }
        mAdapter.AddAllDogs(dogsToShow);

    }



    private void displayUserDetails() {
        Breeder breeder = userSessionManag.BreederLoggedIn();

        //set text views
        // View header = navigationView.

        //displayUsernameTV.setText(user.getUserName());
        //displayUseremailTV.setText(user.getEmail())
        //;
        //usernameTV.setText(user.getUsername());
        //useremailTV.setText(user.getEmail());



        //Log.i("user Loggedin", user.getUsername() + user.getEmail());


    }



    private boolean authenticate() {
        Log.i("getLoggedIn value", "" + userSessionManag.getBreederLoggedIn());
        return userSessionManag.getBreederLoggedIn();
    }

}

