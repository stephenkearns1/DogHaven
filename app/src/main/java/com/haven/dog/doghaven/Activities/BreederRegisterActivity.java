package com.haven.dog.doghaven.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;
import com.haven.dog.doghaven.R;

import java.util.HashMap;
import java.util.Map;

public class BreederRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText fnameET, snameET, usernameET, emailET, companyNameET, companyVatET, addressET, countyET, dobET, passwordET;
    Button registerBtn;
    private  String  fname, sname, username,companyname, companyvatnum, email, addr, county, dob, password;
    private  ProgressDialog progressDialog;
    private final String doghavenAPI_URL = "https://backend-doghaven-app-stephenkearns1.c9users.io/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_register);

        //This is where the merge issue will happen
        fnameET = (EditText) findViewById(R.id.breederfnameET);
        snameET = (EditText) findViewById(R.id.breedersnameET);
        usernameET = (EditText) findViewById(R.id.breederfnameET);
        emailET = (EditText) findViewById(R.id.breederemailET);
        companyNameET = (EditText) findViewById(R.id.companyNameET);
        companyVatET = (EditText) findViewById(R.id.companyVatET);
        addressET = (EditText) findViewById(R.id.breederAddrET);
        countyET = (EditText) findViewById(R.id.breedercountyET);
        dobET = (EditText) findViewById(R.id.breederdobET);
        passwordET = (EditText) findViewById(R.id.breederpasswordET);


        registerBtn = (Button) findViewById(R.id.RegisterBreederBtn);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.RegisterBreederBtn){
            fname = fnameET.getText().toString();
            sname = snameET.getText().toString();
            username = usernameET.getText().toString();
            companyname = companyNameET.getText().toString();
            companyvatnum = companyVatET.getText().toString();
            email = emailET.getText().toString();
            addr = addressET.getText().toString();
            county = countyET.getText().toString();
            dob = dobET.getText().toString();
            password = passwordET.getText().toString();



            //make call to register methods
            Register();

        }
    }

    public void Register(){

        progressDialog = new ProgressDialog(BreederRegisterActivity.this);
        progressDialog.setMessage("Registering account");
        progressDialog.setTitle("processing..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        StringRequest strRequestReg = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {



                    @Override
                    public void onResponse(String response) {

                        progressDialog.hide();
                        Log.i("Returned data:R01", response);
                        if(response.equalsIgnoreCase("success")){
                            //display message account has been created

                            //lanuch the login activity
                            Intent intent = new Intent(BreederRegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }else{
                            //display error message
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
                params.put("breederregister", "");
                params.put("fname", fname);
                params.put("sname", sname);
                params.put("username", username);
                params.put("companyname", companyname);
                params.put("companyvatnum", companyvatnum);
                params.put("email", email);
                params.put("address", addr);
                params.put("county", county);
                params.put("dob", dob);
                params.put("password", password);
                return params;
            }
        };

        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(strRequestReg);
    }
}

