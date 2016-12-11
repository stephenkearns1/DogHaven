package com.haven.dog.doghaven.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.app.ProgressDialog;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;

import java.util.Map;
import java.util.HashMap;

import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.R;

/**
 * Created by Marken Teder on 10/11/2016.
 */

public class AddDogActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameinsertET, ageinsertET, breedinsertET, companyinsertET, colorinsertET, illcurrentinsertET, illpastinsertET, vacinsertET, vacmissinginsertET;
    Button AddDogBtn;
    Spinner ddsize, ddfur, ddbody, ddtolerance, ddneutered, ddenergy, ddexercise, ddintelligence, ddplayful, ddinstinct, ddpeople, ddfamily, dddogs, ddemotion, ddsociability;
    private String dog_name, dog_breed, dog_age,dog_company, dog_color, dillcurr, dillpast, dvac, dvacmiss, size, fur, body, tolerance, neutered, energy, exercise, intelligence, playful, instinct, people, family, dogs, emotion, sociability;
    private  ProgressDialog pDialog;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private UserSessionManagment userSessionManag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog_page);

        nameinsertET = (EditText) findViewById(R.id.NameInsertET);
        ageinsertET = (EditText) findViewById(R.id.AgeInsertET);
        breedinsertET = (EditText) findViewById(R.id.BreedInsertET);
        companyinsertET = (EditText) findViewById(R.id.CompanyInsertET);
        colorinsertET = (EditText) findViewById(R.id.ColorInsertET);
        illcurrentinsertET = (EditText) findViewById(R.id.IllnessCurrentInsertET);
        illpastinsertET = (EditText) findViewById(R.id.IllnessPastInsertET);
        vacinsertET = (EditText) findViewById(R.id.VaccinationInsertET);
        vacmissinginsertET = (EditText) findViewById(R.id.VaccinationsMissingInsertET);
        ddsize=(Spinner) findViewById(R.id.ddsize);
        ddfur = (Spinner) findViewById(R.id.ddfur);
        ddbody=(Spinner) findViewById(R.id.ddbody);
        ddtolerance = (Spinner) findViewById(R.id.ddtolerance);
        ddneutered=(Spinner) findViewById(R.id.ddneutered);
        ddenergy = (Spinner) findViewById(R.id.ddenergy);
        ddexercise =(Spinner) findViewById(R.id.ddexercise);
        ddintelligence = (Spinner) findViewById(R.id.ddintelligence);
        ddplayful=(Spinner) findViewById(R.id.ddplayful);
        ddinstinct = (Spinner) findViewById(R.id.ddinstinct);
        ddpeople = (Spinner) findViewById(R.id.ddpeople);
        ddfamily=(Spinner) findViewById(R.id.ddfamily);
        dddogs = (Spinner) findViewById(R.id.dddogs);
        ddemotion=(Spinner) findViewById(R.id.ddemotion);
        ddsociability = (Spinner) findViewById(R.id.ddsociability);

        AddDogBtn = (Button) findViewById(R.id.AddDogBtn);

        AddDogBtn.setOnClickListener(this);
        addListenerOnDropDown();

        //instantiates objects for reference
        userSessionManag = new UserSessionManagment(this);


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

    public void onClick(View v) {
        addListenerOnDropDown();
        if(v.getId() == R.id.AddDogBtn){

                    dog_name = nameinsertET.getText().toString();
                    dog_age = ageinsertET.getText().toString();
                    dog_breed = breedinsertET.getText().toString();
                    dog_company = companyinsertET.getText().toString();
                    dog_color = colorinsertET.getText().toString();
                    dillcurr = illcurrentinsertET.getText().toString();
                    dillpast = illpastinsertET.getText().toString();
                    dvac = vacinsertET.getText().toString();
                    dvacmiss = vacmissinginsertET.getText().toString();
                    size = ddsize.getSelectedItem().toString();
                    fur = ddfur.getSelectedItem().toString();
                    body = ddbody.getSelectedItem().toString();
                    tolerance = ddtolerance.getSelectedItem().toString();
                    neutered = ddneutered.getSelectedItem().toString();
                    energy = ddenergy.getSelectedItem().toString();
                    exercise = ddexercise.getSelectedItem().toString();
                    intelligence = ddintelligence.getSelectedItem().toString();
                    playful = ddplayful.getSelectedItem().toString();
                    instinct = ddinstinct.getSelectedItem().toString();
                    people = ddpeople.getSelectedItem().toString();
                    family = ddfamily.getSelectedItem().toString();
                    dogs = dddogs.getSelectedItem().toString();
                    emotion = ddemotion.getSelectedItem().toString();
                    sociability = ddsociability.getSelectedItem().toString();

                    Log.i("Color is: ", dog_color);
                    Log.i("Size is:", size);
                    Log.i("fur is :", fur);

                    addDog();
        }
    }



    public void addDog(){

        pDialog = new ProgressDialog(AddDogActivity.this);
        pDialog.setMessage("...Processing...");
        pDialog.setTitle("Adding New Dog");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
        StringRequest AddDogRequest  = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {



                    @Override
                    public void onResponse(String response) {

                        pDialog.hide();
                        Log.i("Returned data:R01", response);
                        if(response.equalsIgnoreCase("success")){


                        }else{

                        }

                        ;
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String,String> getParams()  throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("adddog", "adddog");
                params.put("dog_name", dog_name);
                params.put("dog_age", dog_age);
                params.put("dog_breed", dog_breed);
                params.put("dog_company", dog_company);
                params.put("dog_color", dog_color);
                params.put("dillcurr", dillcurr);
                params.put("dillpast", dillpast);
                params.put("dvac", dvac);
                params.put("dvacmiss", dvacmiss);
                params.put("size", size);
                params.put("fur", fur);
                params.put("body", body);
                params.put("tolerance", tolerance);
                params.put("neutered", neutered);
                params.put("energy", energy);
                params.put("exercise", exercise);
                params.put("intelligence", intelligence);
                params.put("playful", playful);
                params.put("instinct", instinct);
                params.put("people", people);
                params.put("family", family);
                params.put("dogs", dogs);
                params.put("emotion", emotion);
                params.put("sociability", sociability);

                return params;
            }
        };

        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(AddDogRequest);
    }

      /*  StringRequest AddDogRequest = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {



                    @Override
                    public void onResponse(String response) {

                        pDialog.hide();
                        Log.i("Returned data:R02", response);
                        if(response.equalsIgnoreCase("success")){

                            Intent intent = new Intent(AddDogActivity.this,AddDogActivity.class);
                            startActivity(intent);

                        }else{

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Log.i("volley error", error.toString());
            }
        }) {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("adddog", "adddog");
                params.put("dname", dname);
                params.put("dage", dage);
                params.put("dbreed", dbreed);

                return params;
            }

        };

        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(AddDogRequest);

    }

    */

    private void addListenerOnDropDown() {
        ddsize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean authenticate() {
        Log.i("getLoggedIn value", "" + userSessionManag.getBreederLoggedIn());
        return userSessionManag.getBreederLoggedIn();
    }


}
