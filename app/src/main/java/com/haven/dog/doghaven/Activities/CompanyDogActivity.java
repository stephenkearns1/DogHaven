package com.haven.dog.doghaven.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.CompanyDogsAdapter;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;
import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Models.Breeder;
import com.haven.dog.doghaven.Models.Dog;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompanyDogActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private CompanyDogsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Dog> dogList;
    private  UserSessionManagment userSessionManag;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private final String doghavenAPI_GetDogs_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php?companydogs=";
    private String name, breed, companyName, age, color;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_dog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //get a reference to the reycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.companyDogs_recyclerView);

        //sets the layout mangaer to use a linear layout for displaying views
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //sets up the recyler view to use the customer adapter for dogs
        mAdapter = new CompanyDogsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        //instantiates objects for reference
        userSessionManag = new UserSessionManagment(this);

        dogList = new ArrayList<>();



    }

    @Override
    protected void onStart() {
        super.onStart();
        Breeder breeder = userSessionManag.BreederLoggedIn();
        companyName = breeder.getCompanyname();
        Log.i("companyname", companyName);
        //CheckDogsBeenAdded();
        CheckDogsBeenAdded();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.company_dog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }


    public void CheckDogsBeenAdded(){

        StringRequest checkDogBeenAddedRequest  = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {



                    @Override
                    public void onResponse(String response) {
                        //check the response from the server
                        Log.i("New Response", response.toString());


                        if(response.equals("true")){

                          RetriveDogListForCompany();

                        }else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(CompanyDogActivity.this);
                            builder.setMessage("No dogs have been addded")
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(CompanyDogActivity.this);
                builder.setMessage("Error: " + error.toString())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });

                builder.show();
            }
        }) {

            @Override
            protected Map<String, String> getParams ()throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //sending login signals to server that it is a login request and should handle accordingly
                params.put("CompanyDogsExist", "");
                params.put("companyname", "stescats");
                return params;
            }
        };




        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(checkDogBeenAddedRequest);
    }


    public void RetriveDogListForCompany(){
      /*  JsonArrayRequest companyDogListRequest = new JsonArrayRequest(doghavenAPI_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //check the response from the server
                        Log.i("New Response", response.toString());

                        if (!(dogList == null)) {
                            dogList.clear();

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

                                dogList.add(dog);
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


                                //when user prefs have been received, find the dog matches for the user

                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("JSONError", e.toString());
                        }
                        }

                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(CompanyDogActivity.this);
                            builder.setMessage("Error: " + error.toString())
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                        }
                                    });

                            builder.show();

                            error.printStackTrace();
                        }
                    })

                    {
                        @Override
                        protected Map<String, String> getParams ()throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        //sending login signals to server that it is a login request and should handle accordingly
                        params.put("GetCompanyDogs", "");
                        params.put("companyname", companyName);
                        return params;
                    }
                    };

                    */

                    // Adding the request to the queue
                   // MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(companyDogListRequest);

        JsonArrayRequest companyDogListRequest = new JsonArrayRequest(doghavenAPI_GetDogs_URL+companyName,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //check the response from the server
                        Log.i("New Response", response.toString());
                        Log.i("New Response", response.toString());

                        if (!(dogList == null)) {
                            dogList.clear();

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

                                dogList.add(dog);
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




                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("JSONError", e.toString());
                        }

                         mAdapter.AddAllDogs(dogList);
                        }






                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(companyDogListRequest);
    }



    public void DeleteDog(int dogID){
        Log.i("DogID from adapter", " " + dogID);
    }
}
