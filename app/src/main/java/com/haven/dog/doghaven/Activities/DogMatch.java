package com.haven.dog.doghaven.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.CompanyDogsAdapter;
import com.haven.dog.doghaven.Helpers.MatchingAlogrithm;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;
import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Models.Dog;
import com.haven.dog.doghaven.Models.DogWeightedScore;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.Models.UserPrefs;
import com.haven.dog.doghaven.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DogMatch extends AppCompatActivity {

    private ArrayList<Dog> dogsList;
    private ArrayList<UserPrefs> userPrefsList;
    private int user_id;
    private final String doghavenAPI_GetDogs_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php?GetAllDogs";
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private String name, breed, companyName, age, color;
    private RecyclerView mRecyclerView;
    private CompanyDogsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private  UserSessionManagment userSessionManag;
    private User user;
    private MatchingAlogrithm match;
    // attributes
    private int dogId;
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
    private List<Dog> dogstest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_match);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        */

        //get a reference to the reycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.matchDogs_recyclerView);

        //sets the layout mangaer to use a linear layout for displaying views
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //sets up the recyler view to use the customer adapter for dogs
        mAdapter = new CompanyDogsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        //instantiates objects for reference
        match = new MatchingAlogrithm();
        userSessionManag = new UserSessionManagment(this);
        user = userSessionManag.UserLoggedIn();







        dogsList = new ArrayList<>();
        userPrefsList = new ArrayList<>();






    }

    @Override
    protected void onStart() {
        super.onStart();
        //checks to see if the user is authenticated if not it requests the user to login.
        if (authenticate()) {
            //display logged in or start main activity
            displayUserDetails();
        } else {
            //starts loginIn activity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }


       //get the logged in users ID to make a request to get the user perferances
        user_id = user.getUserID();
        GetUserPrefs();





    }
    private void displayUserDetails() {
        User user = userSessionManag.UserLoggedIn();

        //set text views
        // View header = navigationView.

        //displayUsernameTV.setText(user.getUserName());
        //displayUseremailTV.setText(user.getEmail())
        //;

        //set drawer with user name and email
        //usernameTV.setText(user.getUsername());
       // useremailTV.setText(user.getEmail());



        Log.i("user Loggedin", user.getUsername() + user.getEmail());


    }



    private boolean authenticate() {
        Log.i("getLoggedIn value", "" + userSessionManag.getLoggedIn());
        return userSessionManag.getLoggedIn();
    }


    private void RetriveDogList() {
        JsonArrayRequest companyDogListRequest = new JsonArrayRequest(doghavenAPI_GetDogs_URL,
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
        })

        {
            @Override
            protected Map<String, String> getParams ()throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //sending login signals to server that it is a login request and should handle accordingly
                params.put("checkifuserexists", "checkifuserexists");
                params.put("username", "");
                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(companyDogListRequest);
    }


    private void GetUserPrefs(){
        progressDialog = new ProgressDialog(DogMatch.this);
        progressDialog.setMessage("Finding matches..");
        progressDialog.setTitle("Matching");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest userPrefsRequest = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {



                    @Override
                    public void onResponse(String nResponse) {


                        Log.i("Response1", nResponse);
                        Log.i("Response length", "" + nResponse.length());
                        if (!(nResponse.equals("failed"))){

                            if (!(userPrefsList == null)) {
                                userPrefsList.clear();

                            }

                            try {

                                //JSONArray jsArray = new JSONArray(response);
                                JSONObject jsUserObj= new JSONObject(nResponse);

                                //JSONObject jsUserObj = (JSONObject) jsArray.get(0);
                                //she have a unique id

                                   /*
                                         Attributes user search is based on and order
                                         Marken  Teder: Physical - Size, Fur, Body, Tolerance, Neutered
                                         Marken  Teder: Behaviour - Energy, Exercise, Intelligence, Playful, Instinct
                                         Marken  Teder: Social - People, Family, Dogs, Emotion, Sociability
                                     */

                                UserPrefs userPrefs = new UserPrefs(
                                        jsUserObj.getString(TAG_size),jsUserObj.getString(TAG_fur),
                                        jsUserObj.getString(TAG_body), jsUserObj.getString(TAG_tolerance),
                                        jsUserObj.getString(TAG_neutered), jsUserObj.getString(TAG_energy),
                                        jsUserObj.getString(TAG_exercise), jsUserObj.getString(TAG_intelligence),
                                        jsUserObj.getString(TAG_playful), jsUserObj.getString(TAG_instinct),
                                        jsUserObj.getString(TAG_people), jsUserObj.getString(TAG_family),
                                        jsUserObj.getString(TAG_dogs), jsUserObj.getString(TAG_emotion),
                                        jsUserObj.getString(TAG_sociality)
                                );


                                userPrefsList.add(userPrefs);


                                progressDialog.hide();


                                RetriveDogList();

                                Log.i("Returned data json:L01", nResponse);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Log.i("Returned data:L01", nResponse);
                        }else{
                            //error message saying incorrect details
                            progressDialog.hide();
                            final AlertDialog.Builder builder = new AlertDialog.Builder(DogMatch.this);
                            builder.setMessage("User Preferances could not be found")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                        }
                                    });

                            builder.show();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String,String> getParams()  throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                //sending login signals to server that it is a login request and should handle accordingly
                Log.i("user ID", "getting sent" + user_id);
                params.put("GetUserPrefs", "GetUserPrefs");
                params.put("user_id", String.valueOf(user_id));
                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(userPrefsRequest);
    }

    public void getMatches(){
        //set the data in matchig algorthim to generate matches
        match = new MatchingAlogrithm();
        match.setDogList(dogsList);
        match.setuserPrefs(userPrefsList);
        match.AddDogWeightings();
        match.MostSuitedDogs();
        ArrayList<Dog> dogsToShow = match.getDogsToShow();
        mAdapter.ClearAll();
        if(dogsToShow.size() == 0){

        }
        mAdapter.AddAllDogs(dogsToShow);
    }
}
