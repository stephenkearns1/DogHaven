package com.haven.dog.doghaven.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;
import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class Questions extends AppCompatActivity implements View.OnClickListener{
   private Spinner q1sp,q2sp,q3sp,q3_1sp,q4sp,q5sp,q6sp,q7sp,q8sp,
            q9sp,q10sp, q11sp, q12sp, q13sp,q13_1sp,q14sp,q15sp;


   private ImageView q1iv, q2iv, q3iv, q3_1iv, q4iv, q5iv, q6iv, q7iv,q8iv,
              q9iv, q10iv, q11iv, q12iv, q13iv, q13_1iv, q14iv, q15iv;

   private String size,fur,body,tolerance,fixed,energy,exercise,intelligence,temp,
            instinct,people,family,dogs,emotion,social,user_id;
   private int userId;

   private Button addPref;

    private LinearLayout q3_1LL,q13_1LL;

    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";

    private UserSessionManagment userSessionManag;

    private  ProgressDialog pDialog;

    private User user;

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
        q3iv =(ImageView) findViewById(R.id.q3IV);
        q3_1iv =(ImageView) findViewById(R.id.q3_1IV);
        q4iv =(ImageView) findViewById(R.id.q4IV);
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

        q3_1LL= (LinearLayout) findViewById(R.id.q3_1);
        q13_1LL= (LinearLayout) findViewById(R.id.q13_1);

        addPref = (Button) findViewById(R.id.addPref);
        addPref.setOnClickListener(this);

        userSessionManag = new UserSessionManagment(this);
        user = userSessionManag.UserLoggedIn();
        userId= user.getUserID();

        addListenerOnDropDown();




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
        q1sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
               if(q1sp.getItemAtPosition(i).toString().equals("Large")){
                        size="Large";
                }else if(q1sp.getItemAtPosition(i).toString().equals("Medium")){
                    size="Medium";
                }else{
                    size="Small";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q2sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                    if(q2sp.getItemAtPosition(i).toString().equals("Yes I mind")){
                        fur="Non-Shedding";
                    }else {
                        fur="Shedding";
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q3sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q3sp.getItemAtPosition(i).toString().equals("Working")){
                    body="Strong";
                    q3_1LL.setVisibility(View.GONE);
                }else if(q3sp.getItemAtPosition(i).toString().equals("Show/Agility")){
                    body="Agile";
                    q3_1LL.setVisibility(View.GONE);
                }else {
                    q3_1LL.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q3_1sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q3_1sp.getItemAtPosition(i).toString().equals("Yes I mind")){
                    body="Healthy";

                }else {
                    body="Disease-prone";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        q4sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q4sp.getItemAtPosition(i).toString().equals("Hot")){
                    tolerance="Tolerates Warmth";

                }else if(q4sp.getItemAtPosition(i).toString().equals("Cold")){
                    tolerance="Tolerates Cold";
                }else{
                   tolerance=" Tolerates Both";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q5sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q5sp.getItemAtPosition(i).toString().equals("Yes")){
                    fixed="Fixed Yes";

                }else{
                    fixed="Fixed No";
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

        q7sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q7sp.getItemAtPosition(i).toString().equals("Very Active")){
                    exercise="High exercise";

                }else if(q7sp.getItemAtPosition(i).toString().equals("Active")){
                    exercise="Medium exercise";
                }else{
                    exercise="Low exercise";
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

        q12sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q12sp.getItemAtPosition(i).toString().equals("Yes")){
                    family="Child-friendly Yes";

                }else{
                    family="Child-friendly No";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        q13sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q13sp.getItemAtPosition(i).toString().equals("Yes")){
                    dogs="Sociable";
                    q13_1LL.setVisibility(View.GONE);
                }else{
                    q13_1LL.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q13_1sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q13sp.getItemAtPosition(i).toString().equals("Yes")){
                    dogs="Sociable";

                }else{
                    dogs="Unsocial";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        q14sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q14sp.getItemAtPosition(i).toString().equals("Yes")){
                    emotion="Tolerates being Alone";

                }else{
                    emotion="Hard to be Alone";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        q15sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(q15sp.getItemAtPosition(i).toString().equals("Yes")){
                    social="Low Barking";

                }else{
                    social="High Barking";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addPref(){

        pDialog = new ProgressDialog(Questions.this);
        pDialog.setMessage("...Processing...");
        pDialog.setTitle("Adding User Preferences");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
        StringRequest AddPrefsRequest  = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        pDialog.hide();
                        Log.i("Returned data:R01", response);
                        if(response.equalsIgnoreCase("success")){

                        }else{

                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String,String> getParams()  throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("addpref", "addpref");
                params.put("user_id",String.valueOf(userId));
                params.put("size", size);
                params.put("fur", fur);
                params.put("body", body);
                params.put("tolerance", tolerance);
                params.put("neutered", fixed);
                params.put("energy", energy);
                params.put("exercise", exercise);
                params.put("intelligence", intelligence);
                params.put("playful", temp);
                params.put("instinct", instinct);
                params.put("people", people);
                params.put("family", family);
                params.put("dogs", dogs);
                params.put("emotion", emotion);
                params.put("sociability", social);

                return params;
            }
        };

        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(AddPrefsRequest);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addPref) {
            addPref();
        }
    }
}
