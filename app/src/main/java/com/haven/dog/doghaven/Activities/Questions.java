package com.haven.dog.doghaven.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.haven.dog.doghaven.R;

public class Questions extends AppCompatActivity {
    Spinner q1sp,q2sp,q3sp,q3_1sp,q4sp,q5sp,q6sp,q7sp,q8sp,
            q9sp,q10sp, q11sp, q12sp, q13sp,q13_1sp,q14sp,q15sp;


    ImageView q1iv, q2iv, q3iv, q3_1iv, q4iv, q5iv, q6iv, q7iv,q8iv,
              q9iv, q10iv, q11iv, q12iv, q13iv, q13_1iv, q14iv, q15iv;

    String size,fur,body,tolerance,fixed,energy,exercise,intelligence,temp,
            instinct,people,family,dogs,emotion,social;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        q1sp = (Spinner) findViewById(R.id.q1SP);
        q2sp = (Spinner) findViewById(R.id.q2SP);
        q3sp = (Spinner) findViewById(R.id.q3SP);
        q3_1sp = (Spinner) findViewById(R.id.q3_1SP);
        q4sp = (Spinner) findViewById(R.id.q4SP);
        q5sp = (Spinner) findViewById(R.id.q5SP);
        q6sp = (Spinner) findViewById(R.id.q6SP);
        q7sp = (Spinner) findViewById(R.id.q7SP);
        q8sp = (Spinner) findViewById(R.id.q8SP);
        q9sp = (Spinner) findViewById(R.id.q9SP);
        q10sp = (Spinner) findViewById(R.id.q10SP);
        q11sp = (Spinner) findViewById(R.id.q11SP);
        q12sp = (Spinner) findViewById(R.id.q12SP);
        q13sp = (Spinner) findViewById(R.id.q13SP);
        q13_1sp = (Spinner) findViewById(R.id.q13_1SP);
        q14sp = (Spinner) findViewById(R.id.q14SP);
        q15sp = (Spinner) findViewById(R.id.q15SP);



        q1iv = (ImageView) findViewById(R.id.q1IV);
        q2iv = (ImageView) findViewById(R.id.q2IV);
        q3iv = (ImageView) findViewById(R.id.q3IV);
        q3_1iv = (ImageView) findViewById(R.id.q3_1IV);
        q4iv = (ImageView) findViewById(R.id.q4IV);
        q5iv = (ImageView) findViewById(R.id.q5IV);
        q6iv = (ImageView) findViewById(R.id.q6IV);
        q7iv = (ImageView) findViewById(R.id.q7IV);
        q8iv = (ImageView) findViewById(R.id.q8IV);
        q9iv = (ImageView) findViewById(R.id.q9IV);
        q10iv = (ImageView) findViewById(R.id.q10IV);
        q11iv = (ImageView) findViewById(R.id.q11IV);
        q12iv = (ImageView) findViewById(R.id.q12IV);
        q13iv = (ImageView) findViewById(R.id.q13IV);
        q13_1iv = (ImageView) findViewById(R.id.q13_1IV);
        q14iv = (ImageView) findViewById(R.id.q14IV);
        q15iv = (ImageView) findViewById(R.id.q15IV);



    }

}
