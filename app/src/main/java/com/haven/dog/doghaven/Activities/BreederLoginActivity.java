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
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.Models.Breeder;
import com.haven.dog.doghaven.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BreederLoginActivity extends AppCompatActivity implements View.OnClickListener   {
    private EditText usernameET, passwordET;
    private Button loginBtn, registerBtn;
    private String username, password;
    private Breeder breeder;
    private ProgressDialog progressDialog;
    private final String doghavenAPI_URL = "https://backend-doghaven-app-stephenkearns1.c9users.io/index.php";
    private static final String tagFName= "sname";
    private static final String tagSName = "fname";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_login);

        usernameET = (EditText) findViewById(R.id.breederusernameET);
        passwordET = (EditText) findViewById(R.id.breederpasswordET);

        loginBtn = (Button) findViewById(R.id.breederloginActivitybtn);
        registerBtn = (Button) findViewById(R.id.breederregisterBtnLogin);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.breederloginActivitybtn){
            if (usernameET.getText().toString().equals("") || passwordET.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Please enter a vaild username and password",Toast.LENGTH_LONG);
            }else {

                //mite not have to store username, password for checking only on return store details
                username = usernameET.getText().toString();
                 password = passwordET.getText().toString();

                breeder = new Breeder(username, password);

                //call login method
                authenticate(breeder);
            }
        }else if(v.getId() == R.id.breederregisterBtnLogin){
            Intent intent = new Intent(this,BreederRegisterActivity.class);
            startActivity(intent);
        }
    }

    public void authenticate(Breeder breeder){

        progressDialog = new ProgressDialog(BreederLoginActivity.this);
        progressDialog.setMessage("Logging in");
        progressDialog.setTitle("Authenticating");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest jsArryRequest = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.i("Response1", response);
                        Log.i("Response length", "" + response.length());
                        if (response.length() > 2) {


                            try {

                                JSONArray jsArray = new JSONArray(response);

                                JSONObject jsUserObj = (JSONObject) jsArray.get(0);
                                //she have a unique id
                                Breeder breeder = new Breeder();
                                breeder.setfName(jsUserObj.getString(tagFName));
                                breeder.setsName(jsUserObj.getString(tagSName));

                                progressDialog.hide();

                                //Log the user in
                               LogBreederIn(breeder);
                               Log.i("Returned data json:L01", response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Log.i("Returned data:L01", response);
                        }else{
                            //error message saying incorrect details
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
                //sending login signals to server that it is a login request and should handle accordingly
                params.put("breederlogin", "breederlogin");
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(jsArryRequest);
    }

    public void LogBreederIn(Breeder breeder){
        if(breeder != null){
            //store user data in sharedPerferances
            //check user type and display screen for user
            Intent intent = new Intent(this,UserMainScreenActivity.class);
            startActivity(intent);
            Log.i("user data Login", breeder.getfName() + breeder.getsName());
              /*
                  if(user.getType == "user")
                        start usermainscreen
                  else
                        start breederscreen
               */
        }else{
            //display error message saying invaild cred
        }
    }

}
