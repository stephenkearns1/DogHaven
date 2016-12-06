package com.haven.dog.doghaven.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;

import java.util.Map;
import java.util.HashMap;
import com.haven.dog.doghaven.R;

/**
 * Created by Marken Teder on 10/11/2016.
 */

public class AddDogActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameinsertET, ageinsertET, breedinsertET;
    Button AddDogBtn;
    Spinner dropdown;
    private String dname, dbreed, dage;
    private  ProgressDialog pDialog;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog_page);
        dropdown=(Spinner) findViewById(R.id.dropDown);
        addListenerOnDropDown();

        nameinsertET = (EditText) findViewById(R.id.NameInsertET);
        ageinsertET = (EditText) findViewById(R.id.AgeInsertET);
        breedinsertET = (EditText) findViewById(R.id.BreedInsertET);

        AddDogBtn = (Button) findViewById(R.id.AddDogBtn);

        AddDogBtn.setOnClickListener(this);


    }

    public void onClick(View v) {
        if(v.getId() == R.id.AddDogBtn){

                dname = nameinsertET.getText().toString();
                dage = ageinsertET.getText().toString();
                dbreed = breedinsertET.getText().toString();

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
                params.put("dname", dname);
                params.put("dage", dage);
                params.put("dbreed", dbreed);

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
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
