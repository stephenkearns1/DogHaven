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
    Spinner ddphys1, ddphys2, ddphys3, ddphys4, ddphys5, ddbeha1, ddbeha2, ddbeha3, ddbeha4, ddbeha5, ddsoc1, ddsoc2, ddsoc3, ddsoc4, ddsoc5;
    private String dog_name, dog_breed, dog_age,dog_company, dog_color, dillcurr, dillpast, dvac, dvacmiss, phys1, phys2, phys3, phys4, phys5, beha1, beha2, beha3, beha4, beha5, soc1, soc2, soc3, soc4, soc5;
    private  ProgressDialog pDialog;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private UserSessionManagment userSessionManag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog_page);
        //addListenerOnDropDown();

        nameinsertET = (EditText) findViewById(R.id.NameInsertET);
        ageinsertET = (EditText) findViewById(R.id.AgeInsertET);
        breedinsertET = (EditText) findViewById(R.id.BreedInsertET);
        companyinsertET = (EditText) findViewById(R.id.CompanyInsertET);
        colorinsertET = (EditText) findViewById(R.id.ColorInsertET);
        illcurrentinsertET = (EditText) findViewById(R.id.IllnessCurrentInsertET);
        illpastinsertET = (EditText) findViewById(R.id.IllnessPastInsertET);
        vacinsertET = (EditText) findViewById(R.id.VaccinationInsertET);
        vacmissinginsertET = (EditText) findViewById(R.id.VaccinationsMissingInsertET);
        ddphys1=(Spinner) findViewById(R.id.ddphys1);
        ddphys2 = (Spinner) findViewById(R.id.ddphys2);
        ddphys3=(Spinner) findViewById(R.id.ddphys3);
        ddphys4 = (Spinner) findViewById(R.id.ddphys4);
        ddphys5=(Spinner) findViewById(R.id.ddphys5);
        ddbeha1 = (Spinner) findViewById(R.id.ddbeha1);
        ddbeha2=(Spinner) findViewById(R.id.ddbeha2);
        ddbeha3 = (Spinner) findViewById(R.id.ddbeha3);
        ddbeha4=(Spinner) findViewById(R.id.ddbeha4);
        ddbeha5 = (Spinner) findViewById(R.id.ddbeha5);
        ddsoc1 = (Spinner) findViewById(R.id.ddsoc1);
        ddsoc2=(Spinner) findViewById(R.id.ddsoc2);
        ddsoc3 = (Spinner) findViewById(R.id.ddsoc3);
        ddsoc4=(Spinner) findViewById(R.id.ddsoc4);
        ddsoc5 = (Spinner) findViewById(R.id.ddsoc5);

        AddDogBtn = (Button) findViewById(R.id.AddDogBtn);

        AddDogBtn.setOnClickListener(this);

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
                phys1 = ddphys1.getSelectedItem().toString();
                phys2 = ddphys2.getSelectedItem().toString();
                phys3 = ddphys3.getSelectedItem().toString();
                phys4 = ddphys4.getSelectedItem().toString();
                phys5 = ddphys5.getSelectedItem().toString();
                beha1 = ddbeha1.getSelectedItem().toString();
                beha2 = ddbeha2.getSelectedItem().toString();
                beha3 = ddbeha3.getSelectedItem().toString();
                beha4 = ddbeha4.getSelectedItem().toString();
                beha5 = ddbeha5.getSelectedItem().toString();
                soc1 = ddsoc1.getSelectedItem().toString();
                soc2 = ddsoc2.getSelectedItem().toString();
                soc3 = ddsoc3.getSelectedItem().toString();
                soc4 = ddsoc4.getSelectedItem().toString();
                soc5 = ddsoc5.getSelectedItem().toString();


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
                            //display message account has been created

                            //lanuch the login activity

                        }else{
                            //display errr message
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
                params.put("phys1", phys1);
                params.put("phys2", phys2);
                params.put("phys3", phys3);
                params.put("phys4", phys4);
                params.put("phys5", phys5);
                params.put("beha1", beha1);
                params.put("beha2", beha2);
                params.put("beha3", beha3);
                params.put("beha4", beha4);
                params.put("beha5", beha5);
                params.put("soc1", soc1);
                params.put("soc2", soc2);
                params.put("soc3", soc3);
                params.put("soc4", soc4);
                params.put("soc5", soc5);

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

   /* private void addListenerOnDropDown() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }*/

    private boolean authenticate() {
        Log.i("getLoggedIn value", "" + userSessionManag.getBreederLoggedIn());
        return userSessionManag.getBreederLoggedIn();
    }


}
