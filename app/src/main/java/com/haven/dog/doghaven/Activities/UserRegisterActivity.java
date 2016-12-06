package com.haven.dog.doghaven.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.haven.dog.doghaven.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText fnameET, snameET, usernameET, emailET, addrET, countyET, dobET, passwordET;
    Button registerBtn;
    private  String  fname, sname, username, email, addr, county, dob, password;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private static final String tagFName= "sname";
    private static final String tagSName = "fname";
    private User user;
    ProgressDialog progressDialog;
    boolean exists;
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
        if(v.getId() == R.id.registerBtn){

        }
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

                Log.i("username", username);

                emailET.setError("Invaild email");

                /*
                   validates the users details, and tells the user which of the information does not pass validation.
                 */

                if(fname.isEmpty() || sname.isEmpty() || username.isEmpty() || username.isEmpty() || email.isEmpty() || addr.isEmpty() || county.isEmpty() || dob.isEmpty() || password.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Please Fill in missing information", Toast.LENGTH_SHORT);

                }else if(IsVaildEmail(email) == false){
                    Log.i("email error function", "made it ");
                   /* Drawable icon =
                            getResources().getDrawable(R.drawable.ic_report_problem_black_24dp);
                    if (icon != null) {
                        icon.setBounds(0, 0,
                                icon.getIntrinsicWidth(),
                                icon.getIntrinsicHeight());
                    } */
                    //emailET.setFocusable(true);
                    emailET.setError("Invaild email");
                   // emailET.requestFocus();
                    //emailET.setError("Invaild email");

                } else if(IsVaildPassword(password) == false){
                    passwordET.setError("Invalid password");

                }else{
                    CheckIfUserExists();
                }


                //if(exists) {

                //check if the username exists
                  /*  usernameET.setError("User exists");
                    Log.i("Username", "The username exists");

                }else if(exists == false) {

                    //register();
                }

                */
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
                params.put("userregister", "userregister");
                params.put("fname", fname);
                params.put("sname", sname);
                params.put("username", username);
                params.put("email", email);
                params.put("addr", addr);
                params.put("county", county);
                params.put("dob", dob);
                params.put("password", password);
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
                            usernameET.setError("Already exists");
                        }else{
                            register();
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
                params.put("checkifuserexists", "checkifuserexists");
                params.put("username", username);
                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(CheckIfUserExistRequest);
        return exists;
    }


    public boolean IsVaildEmail(String email){

        String emailFormat = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]$";
        Pattern pattern = Pattern.compile(emailFormat, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        boolean matches = matcher.matches();
        Log.i("email matching", ":" + matches);
        Pattern pattern2 = Patterns.EMAIL_ADDRESS;
        return pattern2.matcher(email).matches();

        //return !TextUtils.isEmpty(password) && android.util.Patterns.EMAIL_ADDRESS.matcher(password).matches();
        /* OR


               return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
}


         */

       // return matcher.matches();
    }

    public boolean IsVaildPassword (String password){

        String passwordFormat = "((?=.*\\d)(?=.*[a-z]).{6,30})";
        Pattern pattern = Pattern.compile(passwordFormat);
        Matcher matcher = pattern.matcher(password);
        boolean matches = matcher.matches();
        Log.i("Password matching", ":" + matches);
        return matcher.matches();
    }

}
