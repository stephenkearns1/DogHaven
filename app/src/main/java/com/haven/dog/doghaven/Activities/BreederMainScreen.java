package com.haven.dog.doghaven.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.haven.dog.doghaven.R;

public class BreederMainScreen extends AppCompatActivity implements View.OnClickListener {
    Button profile,stud,add, companydogsBtn;

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

}
