package com.haven.dog.doghaven.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.AdapterView;

import com.haven.dog.doghaven.R;

public class BreedInfoActivity extends AppCompatActivity {
    ImageView holder;
    ImageView stats;
    Spinner dropdown;
    ScrollView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        holder=(ImageView) findViewById(R.id.picHolder);
        stats=(ImageView) findViewById((R.id.infoImage));
        dropdown=(Spinner) findViewById(R.id.dropDown);
        info= (ScrollView) findViewById(R.id.scroll);
        addListenerOnDropDown();




    }

    private void addListenerOnDropDown() {
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(dropdown.getItemAtPosition(i).toString().equals("Akita")){
                        holder.setImageResource(R.drawable.akita);
                        info.setVisibility(View.VISIBLE);
                        stats.setImageResource(R.drawable.breedinfo4);

                }else if (dropdown.getItemAtPosition(i).toString().equals("Husky")){
                    holder.setImageResource(R.drawable.husky);
                    info.setVisibility(View.VISIBLE);
                    stats.setImageResource(R.drawable.breedinfo4);
                }else if (dropdown.getItemAtPosition(i).toString().equals("Labrador")){
                    holder.setImageResource(R.drawable.lab);
                    info.setVisibility(View.VISIBLE);
                    stats.setImageResource(R.drawable.breedinfo4);
                }else if (dropdown.getItemAtPosition(i).toString().equals("Springer Spaniel")){
                    holder.setImageResource(R.drawable.springer);
                    info.setVisibility(View.VISIBLE);
                    stats.setImageResource(R.drawable.breedinfo4);
                }else if (dropdown.getItemAtPosition(i).toString().equals("Rottweiler")){
                    holder.setImageResource(R.drawable.rotty);
                    info.setVisibility(View.VISIBLE);
                    stats.setImageResource(R.drawable.breedinfo4);
                }else{
                    holder.setImageResource(R.drawable.logo);
                    info.setVisibility(View.INVISIBLE);
                    stats.setImageResource(R.drawable.breedinfo4);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
