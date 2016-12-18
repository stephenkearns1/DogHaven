package com.haven.dog.doghaven.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

import static android.view.View.VISIBLE;

/**
 * Created by Sean on 18/12/2016.
 */

public class StudPreferences extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    private String body,energy,intelligence,temp,
            instinct,people,breed;

    private Spinner q3sp,q6sp,q8sp,
            q9sp,q10sp, q11sp;

    private Button addPref;

    private UserSessionManagment userSessionManag;

    private User user;

    private EditText breedname;

    private TextView usernameTV, useremailTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_prefences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        q3sp = (Spinner) findViewById(R.id.q3SP);
        q6sp = (Spinner) findViewById(R.id.q6SP);
        q8sp = (Spinner) findViewById(R.id.q8SP);
        q9sp = (Spinner) findViewById(R.id.q9SP);
        q10sp = (Spinner) findViewById(R.id.q10SP);
        q11sp = (Spinner) findViewById(R.id.q11SP);
        breedname= (EditText) findViewById(R.id.breedNametf);
        breed = breedname.getText().toString();
        addListenerOnDropDown();

        addPref = (Button) findViewById(R.id.addPref);
        addPref.setOnClickListener(this);

        userSessionManag = new UserSessionManagment(this);
        user = userSessionManag.UserLoggedIn();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Inflates the nav_header layout as the header and then access the elements in the nav_header to populate with user details in the drawer
        View navHeader = navigationView.getHeaderView(0);
        usernameTV = (TextView) navHeader.findViewById(R.id.usernameHeader_TV);
        useremailTV = (TextView) navHeader.findViewById(R.id.useremailHeader_TV);




    }

    private boolean authenticate() {
        Log.i("getLoggedIn value", "" + userSessionManag.getLoggedIn());
        return userSessionManag.getLoggedIn();
    }

    protected void onStart() {
        super.onStart();
        //checks to see if the user is authenticated if not it requests the user to login.
        if (authenticate() == true) {
            //display logged in or start main activity
            //displayUserDetails();
        } else {
            //starts loginIn activity
            Intent intent = new Intent(this, StartActivtiy.class);
            startActivity(intent);
        }



    }

    private void displayUserDetails() {
        User user = userSessionManag.UserLoggedIn();

        //set text views
        // View header = navigationView.

        //displayUsernameTV.setText(user.getUserName());
        //displayUseremailTV.setText(user.getEmail())
        //;
        usernameTV.setText(user.getUsername());
        useremailTV.setText(user.getEmail());



        Log.i("user Loggedin", user.getUsername() + user.getEmail());


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
        getMenuInflater().inflate(R.menu.dogpark_locator, menu);
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
            //sign the user out of the application
            userSessionManag.clearUserData();
            Intent intent = new Intent(this,UserRegisterActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_slideshow) {
            Intent info= new Intent(this,AddDogActivity.class);
            startActivity(info);
        }  else if (id == R.id.nav_manage) {
            Intent info= new Intent(this,CompanyDogActivity.class);
            startActivity(info);
        } else if (id == R.id.nav_gallery) {
            Intent info= new Intent(this,StudPreferences.class);
            startActivity(info);
        } if (id == R.id.nav_camera) {
            Intent info= new Intent(this,BreederProfile.class);
            startActivity(info);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addListenerOnDropDown() {
        q3sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q3sp.getItemAtPosition(i).toString().equals("Working")){
                    body="Strong";

                }else {
                    body = "Agile";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q6sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q6sp.getItemAtPosition(i).toString().equals("Very Energetic")){
                    energy="High-energy";

                } else if(q6sp.getItemAtPosition(i).toString().equals("Energetic")){
                    energy="Medium-energy";
                }else{
                    energy="Low-energy";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q8sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q8sp.getItemAtPosition(i).toString().equals("Yes I would have patience")){
                    intelligence="Stubborn";

                }else{
                    intelligence="Stubborn";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q9sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q9sp.getItemAtPosition(i).toString().equals("Dominate")){
                    temp="Dominate";

                }else{
                    temp="Submissive";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q10sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q10sp.getItemAtPosition(i).toString().equals("Yes")){
                    instinct="High Prey Drive";

                }else{
                    instinct="Low Prey Drive";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q11sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q11sp.getItemAtPosition(i).toString().equals("Yes")){
                    people="Protective";

                }else{
                    people="Stranger friendly";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        //Create the bundle
        Intent i = new Intent(this,StudMatchActivity.class);

        Bundle bundle = new Bundle();
//Add your data from getFactualResults method to bundle
        bundle.putString("breed", breed);
        bundle.putString("body", body);
        bundle.putString("energy", energy);
        bundle.putString("intelligence", intelligence);
        bundle.putString("playful", temp);
        bundle.putString("instinct", instinct);
        bundle.putString("people", people);
//Add the bundle to the intent
        i.putExtras(bundle);
        startActivity(i);


    }
}
