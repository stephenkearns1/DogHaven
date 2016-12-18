package com.haven.dog.doghaven.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Models.Breeder;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

public class BreederMainScreen extends AppCompatActivity implements View.OnClickListener {
    Button profile,stud,add, companydogsBtn;
    private UserSessionManagment userSessionManag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        profile=(Button) findViewById(R.id.CompanyProfileBtn);
        stud= (Button) findViewById(R.id.studMatch);
        add=(Button) findViewById(R.id.addDog);
        companydogsBtn = (Button) findViewById(R.id.CompanyDogsBtn);
        profile.setOnClickListener(this);
        stud.setOnClickListener(this);
        add.setOnClickListener(this);
        companydogsBtn.setOnClickListener(this);


        //Inflates the nav_header layout as the header and then access the elements in the nav_header to populate with user details in the drawer
      /*  View navHeader = navigationView.getHeaderView(0);
        usernameTV = (TextView) navHeader.findViewById(R.id.usernameHeader_TV);
        useremailTV = (TextView) navHeader.findViewById(R.id.useremailHeader_TV);
       */
        //instantiates objects for reference
        userSessionManag = new UserSessionManagment(this);



    }


    @Override
    protected void onStart() {
        super.onStart();
        //checks to see if the user is authenticated if not it requests the user to login.
        if (authenticate() == true) {
            //display logged in or start main activity
            displayUserDetails();
        } else {
            //starts loginIn activity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.CompanyProfileBtn:
               Intent pb = new Intent(this,BreederProfile.class);
                startActivity(pb);
                break;
            case R.id.studMatch:
                Intent info= new Intent(this,StudMatchActivity.class);
                startActivity(info);
                break;

            case R.id.addDog:
                Intent ad= new Intent(this,AddDogActivity.class);
                startActivity(ad);

                break;
            case R.id.CompanyDogsBtn:
                Intent compDogs = new Intent(this,CompanyDogActivity.class);
                startActivity(compDogs);

                break;



        }
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
