package com.haven.dog.doghaven.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.haven.dog.doghaven.R;

/**
  * Created by Marken Teder on 11/11/2016.
 */

public class StudMatchActivity extends AppCompatActivity {

    ImageView matches;
    Spinner dropdown;
    ScrollView matchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stud_match_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        matches=(ImageView) findViewById((R.id.MatchesImgV));
        dropdown=(Spinner) findViewById(R.id.BreedSpinner);
        matchlist= (ScrollView) findViewById(R.id.MatchesScrollV);
        addListenerOnDropDown();




    }

    private void addListenerOnDropDown() {
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(dropdown.getItemAtPosition(i).toString().equals("Akita")){
                    matchlist.setVisibility(View.VISIBLE);
                    matches.setImageResource(R.drawable.matches);

                }else{
                    matchlist.setVisibility(View.INVISIBLE);
                    matches.setImageResource(R.drawable.matches);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}

