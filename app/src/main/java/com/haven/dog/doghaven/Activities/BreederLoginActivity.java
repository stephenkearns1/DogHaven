package com.haven.dog.doghaven.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;
import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Models.Breeder;
import com.haven.dog.doghaven.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BreederLoginActivity extends AppCompatActivity implements View.OnClickListener   {

    private EditText companynameET, passwordET;
    private Button loginBtn, registerBtn;
    private String companyname, password;
    private Breeder breeder;
    private ProgressDialog progressDialog, errorDialog;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private static final String tagCompanyname= "companyname";
    private static final String tagCompanyVat= "companyvat";
    private static final String tagCompanyEmail= "email";
    private static final String tagCompanyAddr= "address";
    private static final String tagCompanyCounty= "county";
    private static final String tagCompanyPassword= "password";
    TextView breederSignup;

    private PopupWindow popupWindow;
    private LinearLayout linearLayout;
    private UserSessionManagment userSessionManag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_login);

        companynameET = (EditText) findViewById(R.id.breederCompanynameET);
        passwordET = (EditText) findViewById(R.id.breederpasswordET);

        loginBtn = (Button) findViewById(R.id.breederloginActivitybtn);
        //registerBtn = (Button) findViewById(R.id.breederregisterBtnLogin);
        breederSignup = (TextView) findViewById(R.id.breeder_signup);
        loginBtn.setOnClickListener(this);
        breederSignup.setOnClickListener(this);

        popupWindow = new PopupWindow(this);
        linearLayout = new LinearLayout(this);

        //instantiates objects for reference
        userSessionManag = new UserSessionManagment(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.breederloginActivitybtn){



            if (companynameET.getText().toString().equals("") || passwordET.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Please enter a vaild username and password",Toast.LENGTH_LONG);
                companynameET.setError("Please enter a vail companyname");
            }else {

                //mite not have to store username, password for checking only on return store details
                companyname = companynameET.getText().toString();
                password = passwordET.getText().toString();

                breeder = new Breeder(companyname, password);

                //call login method
                authenticate(breeder);
            }
        }else if(v.getId() == R.id.breeder_signup){
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
        errorDialog = new ProgressDialog(BreederLoginActivity.this);
        errorDialog.setMessage("User does not exist, checked details or sign up");
        errorDialog.setTitle("Failed ");


        StringRequest jsArryRequest = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.i("Response1", response);
                        Log.i("Response length", "" + response.length());
                        if (response.equals("failed")) {
                            //The user did not exist, hide the progress dialog and display a message to the user
                            progressDialog.hide();
                            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(BreederLoginActivity.this);
                            builder.setMessage("Company does not exist")
                                    .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                        }
                                    });

                            builder.show();
                        }else {

                            try {



                                JSONObject jsUserObj = new JSONObject(response);


                                Breeder breeder = new Breeder(
                                        jsUserObj.getString(tagCompanyname),
                                        jsUserObj.getString(tagCompanyVat),
                                        jsUserObj.getString(tagCompanyEmail),
                                        jsUserObj.getString(tagCompanyAddr),
                                        jsUserObj.getString(tagCompanyCounty),
                                        jsUserObj.getString(tagCompanyPassword)
                                );





                                progressDialog.hide();

                                //Log the user in
                                LogBreederIn(breeder);
                                Log.i("Returned data json:L01", response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Log.i("Returned data:L01", response);
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
                params.put("companyname", companyname);
                params.put("password", password);
                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(jsArryRequest);
    }

    public void LogBreederIn(Breeder breeder){
        if(breeder != null){
            //Creates a session for the user through the use of shared preferences
            userSessionManag.BreederSessionManagment(breeder);
            userSessionManag.setBreederLoggedIn(true);
            Intent intent = new Intent(this,BreederMainScreen.class);
            startActivity(intent);

        }else{
            //display error message saying invaild cred
        }
    }

}
