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
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText fnameET, snameET, usernameET, emailET, addrET, countyET, dobET, passwordET;
    Button registerBtn;
    private  String  fname, sname, username, email, addr, county, dob, password;
    private final String doghavenAPI_URL = "https://backend-doghaven-app-stephenkearns1.c9users.io/index.php";
    private static final String tagFName= "sname";
    private static final String tagSName = "fname";
    private User user;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        fnameET = (EditText) findViewById(R.id.fnameET);
        snameET = (EditText) findViewById(R.id.snameET);
        usernameET = (EditText) findViewById(R.id.usernameET);
        emailET = (EditText) findViewById(R.id.emailET);
        addrET = (EditText) findViewById(R.id.addrET);
        countyET = (EditText) findViewById(R.id.countyET);
        dobET = (EditText) findViewById(R.id.dobET);
        passwordET = (EditText) findViewById(R.id.userpasswordET);

        registerBtn = (Button) findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.registerBtn:
                fname = fnameET.getText().toString();
                sname = snameET.getText().toString();
                username = usernameET.getText().toString();
                email = emailET.getText().toString();
                addr = addrET.getText().toString();
                county = countyET.getText().toString();
                dob = dobET.getText().toString();
                password = passwordET.getText().toString();

                register();
                break;
        }
    }


    public void register(){
        progressDialog = new ProgressDialog(UserRegisterActivity.this);
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
                            Intent intent = new Intent(UserRegisterActivity.this,LoginActivity.class);
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
                params.put("userregister", "");
                params.put("fname", fname);
                params.put("sname", sname);
                params.put("username", username);
                params.put("email", email);
                params.put("address", addr);
                params.put("county", county);
                params.put("dob", dob);
                params.put("password", password);
                params.put("usertype", "user");
                return params;
            }
        };

        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(strRequestReg);
    }
}
