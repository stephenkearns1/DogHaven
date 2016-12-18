package com.haven.dog.doghaven.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class StudPreferences extends AppCompatActivity implements View.OnClickListener {
    private String body,energy,intelligence,temp,
            instinct,people;

    private Spinner q1sp,q2sp,q3sp,q3_1sp,q4sp,q5sp,q6sp,q7sp,q8sp,
            q9sp,q10sp, q11sp, q12sp, q13sp,q13_1sp,q14sp,q15sp;

    private Button addPref;

    private UserSessionManagment userSessionManag;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_stud_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        q3sp = (Spinner) findViewById(R.id.q3SP);
        q6sp = (Spinner) findViewById(R.id.q6SP);
        q8sp = (Spinner) findViewById(R.id.q8SP);
        q9sp = (Spinner) findViewById(R.id.q9SP);
        q10sp = (Spinner) findViewById(R.id.q10SP);
        q11sp = (Spinner) findViewById(R.id.q11SP);
        addListenerOnDropDown();

        addPref = (Button) findViewById(R.id.addPref);
        addPref.setOnClickListener(this);

        userSessionManag = new UserSessionManagment(this);
        user = userSessionManag.UserLoggedIn();


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
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }



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
