package com.haven.dog.doghaven.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;
import com.haven.dog.doghaven.Helpers.Validation;
import com.haven.dog.doghaven.R;

import java.util.HashMap;
import java.util.Map;

public class BreederRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText  emailET, companyNameET, companyVatET, addressET, countyET, passwordET;
    Button registerBtn;
    private  String  companyname, companyvatnum, email, addr, county, password;
    private  ProgressDialog progressDialog;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private Validation validation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_register);

        validation = new Validation();
        //This is where the merge issue will happen
        companyNameET = (EditText) findViewById(R.id.breederCompanyNameET);
        companyVatET = (EditText) findViewById(R.id.breederCompanyVatET);
        emailET = (EditText) findViewById(R.id.breederemailET);
        addressET = (EditText) findViewById(R.id.breederAddrET);
        countyET = (EditText) findViewById(R.id.breedercountyET);
        passwordET = (EditText) findViewById(R.id.breederpasswordET);


        registerBtn = (Button) findViewById(R.id.RegisterBreederBtn);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.RegisterBreederBtn){

            companyname = companyNameET.getText().toString();
            companyvatnum = companyVatET.getText().toString();
            email = emailET.getText().toString();
            password = passwordET.getText().toString();
            addr = addressET.getText().toString();
            county = countyET.getText().toString();


            if(companyname.isEmpty() || companyvatnum.isEmpty() || email.isEmpty() || password.isEmpty() || email.isEmpty() || addr.isEmpty() || county.isEmpty()){

                Toast.makeText(getApplicationContext(),"Please Fill in missing information", Toast.LENGTH_SHORT);

            }else if(validation.IsVaildEmail(email) == false && validation.IsVaildPassword(password) == false ){
                emailET.setError("Invaild email");
                passwordET.setError("Invalid password");
            }else if(validation.IsVaildEmail(email) == false) {
                Log.i("email error function", "made it ");
            }else {
                //make call to register methods
                Register();
            }
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
                            Intent intent = new Intent(BreederRegisterActivity.this,BreederLoginActivity.class);
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
                params.put("breederregister", "breederregister");
                params.put("companyname", companyname);
                params.put("companyvatnum", companyvatnum);
                params.put("email", email);
                params.put("password", password);
                params.put("address", addr);
                params.put("county", county);

                return params;
            }
        };

        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(strRequestReg);
    }


    public boolean CheckIfUserExists(){

        StringRequest CheckIfUserExistRequest = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        Log.i("Response Checkusername", response);
                        Log.i("Response length", "" + response.length());

                        if(response.equals("exists")){
                            //exists = true;
                            Log.i("Made it to", "response user name exist");
                            companyNameET.setError("Already exists");
                        }else{
                            Register();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String,String> getParams()  throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                //sending login signals to server that it is a login request and should handle accordingly
                params.put("checkifbreederexists", "checkifuserexists");
                params.put("username", companyname);
                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(CheckIfUserExistRequest);

    }



}

