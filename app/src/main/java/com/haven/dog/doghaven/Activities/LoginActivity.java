package com.haven.dog.doghaven.Activities;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables

    EditText userNameET, userPasswordET;
    Button login;
    private String username,userPassword;
    private final String loign_URL = "https://backend-doghaven-app-stephenkearns1.c9users.io/scripts/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Assigns views to variables
        userNameET = (EditText) findViewById(R.id.usernameET);
        userPasswordET = (EditText) findViewById(R.id.userpasswordET);
        login = (Button) findViewById(R.id.loginbtn);

        login.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.loginbtn:
                //mite not have to store username, password for checking only on return store details
                username = userNameET.getText().toString();
                 userPassword = userPasswordET.getText().toString();

                User user = new User(username,userPassword);

                //call login method
                authenticate(user);
                break;
        }
    }

    /*
       Authenticates the user cred
       Makes a http request to the server, which returns data in json format
     */
    public void authenticate(User user){

        StringRequest strRequest = new StringRequest(Request.Method.POST,loign_URL,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsArray = new JSONArray(response);
                            Log.i("Returned data json:L01", jsArray.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                            Log.i("Returned data:L01", response);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String,String> getParams()  throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("username", username);
                params.put("userpassword", userPassword);
                return params;
            }
        };

        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(strRequest);
    }

    public void LogUserIn(User user){
        if(user != null){
            //store user data in sharedPerferances
            //check user type and display screen for user
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
