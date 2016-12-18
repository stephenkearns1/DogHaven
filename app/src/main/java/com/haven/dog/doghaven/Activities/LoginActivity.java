package com.haven.dog.doghaven.Activities;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;
import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables

    EditText userNameET, userPasswordET;
    Button login;
    TextView registerBtn;
    private String username,userPassword;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private static final String tagUserID = "user_id";
    private static final String tagFName= "sname";
    private static final String tagSName = "fname";
    private static final String tagUsername = "username";
    private static final String tagEmail = "email";
    private static final String tagPassword = "password";

    private User user;
    private ProgressDialog progressDialog;
    private UserSessionManagment userSessionManag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Assigns views to variables
        userNameET = (EditText) findViewById(R.id.usernameET);
        userPasswordET = (EditText) findViewById(R.id.userpasswordET);
        login = (Button) findViewById(R.id.loginbtn);
        registerBtn = (TextView) findViewById(R.id.link_signup);

        login.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        //instantiates objects for reference
        userSessionManag = new UserSessionManagment(this);


    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.loginbtn:

                if (userNameET.getText().toString().equals("") || userPasswordET.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter a valid username and password",Toast.LENGTH_LONG);
                }else{
                    //mite not have to store username, password for checking only on return store details
                    username = userNameET.getText().toString();
                    userPassword = userPasswordET.getText().toString();

                    user = new User(username, userPassword);

                    //call login method
                    authenticate(user);
                }
                break;
            case R.id.link_signup:
                Log.i("onClickReg", "onclick working register");
                Intent intent = new Intent(this,UserRegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    /*
       Authenticates the user cred
       Makes a http request to the server, which returns data in json format
     */

    public void authenticate(User user){

        progressDialog = new ProgressDialog(LoginActivity.this);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        /*
        JsonArrayRequest jsArryRequest = new JsonArrayRequest(Request.Method.POST, doghavenAPI_URL,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                     Log.i("Response Login", "Made it to response");
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley error: login", error);
            }){
                @Override
                protected Map<String,String> getParams()  throws AuthFailureError{
                    Map<String,String> params = new HashMap<>();
                    //sending login signals to server that it is a login request and should handle accordingly
                    params.put("login", "login");
                    params.put("username", username);
                    params.put("userpassword", userPassword);
                    return params;
                }
            };
            */




        /*


        JsonArrayRequest jsArryRequest = new JsonArrayRequest(Request.Method.POST, doghavenAPI_URL,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {


                        Log.i("Made it to response", "Yeahhhh");
                        Log.i("Respomse", response.toString());

                        try {
                           // JSONArray jsArray = new JSONArray(response);
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject jsUserObj = (JSONObject) response.get(i);
                                //she have a unique id
                                User mUser = new User();
                                mUser.setfName(jsUserObj.getString(tagFName));
                                mUser.setsName(jsUserObj.getString(tagSName));

                                progressDialog.hide();

                                //Log the user in
                                LogUserIn(mUser);

                            }
                            Log.i("Returned data json:L01", response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                     /*   try {
                            JSONArray jsArray = new JSONArray(response);

                            JSONObject jsUserObj = (JSONObject) jsArray.get(0);
                            //she have a unique id
                            User mUser = new User();
                            mUser.setfName(jsUserObj.getString(tagFName));
                            mUser.setsName(jsUserObj.getString(tagSName));

                            progressDialog.hide();

                            //Log the user in
                            LogUserIn(mUser);
                            Log.i("Returned data json:L01", jsArray.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("ERROR", "Chceck console for error and stack trace");
                                error.printStackTrace();
                            }



                    }){
                        @Override
                        protected Map<String,String> getParams()  throws AuthFailureError{
                            Map<String,String> params = new HashMap<>();
                            //sending login signals to server that it is a login request and should handle accordingly
                            params.put("login", "login");
                            params.put("username", username);
                            params.put("userpassword", userPassword);
                            return params;
                        }
                    };


        */





        StringRequest jsArryRequest = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {



                    @Override
                    public void onResponse(String response) {

                        Log.i("Response1", response);
                        Log.i("Response length", "" + response.length());
                        if (!(response.equals("failed"))){


                            try {

                                //JSONArray jsArray = new JSONArray(response);
                                JSONObject jsUserObj= new JSONObject(response);

                                //JSONObject jsUserObj = (JSONObject) jsArray.get(0);
                                //she have a unique id

                                User mUser = new User(Integer.parseInt(jsUserObj.getString(tagUserID)),jsUserObj.getString(tagFName), jsUserObj.getString(tagSName), jsUserObj.getString(tagUsername),
                                                        jsUserObj.getString(tagEmail), jsUserObj.getString(tagPassword));



                                progressDialog.hide();

                                //Log the user in
                                LogUserIn(mUser);
                                Log.i("Returned data json:L01", response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Log.i("Returned data:L01", response);
                        }else{
                            //error message saying incorrect details
                            progressDialog.hide();
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("User does not exist")
                                    .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            });

                            builder.show();
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
                params.put("login", "login");
                params.put("username", username);
                params.put("userpassword", userPassword);
                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(jsArryRequest);
    }

    public void LogUserIn(User user){
        if(user != null){
            //store user data in sharedPerferances
            //check user type and display screen for user
            userSessionManag.SessionUserMangement(user);
            User user2 = userSessionManag.UserLoggedIn();
            Log.i("user_id", " " + user2.getUserID());
            userSessionManag.setUserLoggedIn(true);
            Intent intent = new Intent(this,UserMainScreenActivity.class);
            startActivity(intent);
            Log.i("user data Login", user.getfName() + user.getsName());
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
