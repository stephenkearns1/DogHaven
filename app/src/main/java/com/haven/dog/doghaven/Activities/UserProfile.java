package com.haven.dog.doghaven.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import com.haven.dog.doghaven.Helpers.Validation;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private EditText profile_username_et, profile_email_et, profile_password_et, profile_password_confirm_et;
    private Button profileupdate;
    private UserSessionManagment userSessionManag;
    private ProgressDialog progressDialog;
    private String originalUsername, updatedUsername, newEmail, newPassword, confirmPassword;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private Validation vailadate;
    private TextView usernameTV, useremailTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeader = navigationView.getHeaderView(0);
        usernameTV = (TextView) navHeader.findViewById(R.id.usernameHeader_TV);
        useremailTV = (TextView) navHeader.findViewById(R.id.useremailHeader_TV);

        profile_username_et = (EditText) findViewById(R.id.profile_username);
        profile_email_et = (EditText) findViewById(R.id.user_profile_email_ET);
        profile_password_et = (EditText) findViewById(R.id.profile_password);
        profile_password_confirm_et = (EditText) findViewById(R.id.profile_password_confirm);
        profileupdate = (Button) findViewById(R.id.profile_update);
        profileupdate.setOnClickListener(this);

        //instantiates objects for reference
        userSessionManag = new UserSessionManagment(this);
        vailadate = new Validation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //checks to see if the user is authenticated if not it requests the user to login.
        if (authenticate() == true) {
            //display logged in or start main activity
            displayUserDetails();
        } else {
            //starts loginIn activity
            Intent intent = new Intent(this, StartActivtiy.class);
            startActivity(intent);
        }

        User user = userSessionManag.UserLoggedIn();
        originalUsername = user.getUsername();
        profile_username_et.setText(user.getUsername());
        profile_email_et.setText(user.getEmail());



    }

    public void UpdateUserDetails(){
        progressDialog = new ProgressDialog(UserProfile.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Updating details please wait");
        progressDialog.setTitle("Updating");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        StringRequest updateUserDetailsRequest = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {



                    @Override
                    public void onResponse(String response) {


                        if (!(response.equals("failed"))){

                                progressDialog.hide();

                                //Log the user in
                                //LogUserIn(mUser);



                                userSessionManag.EditUserDetails(updatedUsername, confirmPassword);


                        }else{
                            //error message saying incorrect details
                            progressDialog.hide();
                            final AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
                            builder.setMessage("Unable to update details")
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
            protected Map<String,String> getParams()  throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //sending login tag to server that it is a update request and should handle accordingly
                params.put("updateUser", "updateUser");
                params.put("username", originalUsername);
                params.put("newusername", updatedUsername);
                params.put("newpassword", confirmPassword);

                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(updateUserDetailsRequest);


}

    public void CheckIfUserExists(){

        StringRequest CheckIfUserExistRequest = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {



                        if(response.equals("exists")){
                            //exists = true;

                            profile_username_et.setError("Already exists");
                        }else{
                            UpdateUserDetails();
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
                params.put("username", updatedUsername);
                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(CheckIfUserExistRequest);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //sign the user out of the application
            userSessionManag.clearUserData();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent info= new Intent(this,DogMatch.class);
            startActivity(info);
        } else if (id == R.id.nav_gallery) {
            Intent info= new Intent(this,BreedInfoActivity.class);
            startActivity(info);


        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this,BreederSearch.class);
            startActivity(intent);
        } else if (id == R.id.nav_questions) {
            Intent info= new Intent(this,Questions.class);
            startActivity(info);
        }else if (id == R.id.nav_manage) {
            Intent info= new Intent(this,DogparkLocatorActivity.class);
            startActivity(info);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        updatedUsername = profile_username_et.getText().toString();
        newEmail = profile_email_et.getText().toString();
        newPassword = profile_password_et.getText().toString();
        confirmPassword = profile_password_confirm_et.getText().toString();
        if(vailadate.IsVaildEmail(newEmail) == false){
            profile_email_et.setError("Invaild email");
        }else if(!(newPassword.equals(confirmPassword))){
            final AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
            builder.setMessage("Passwords do not match")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

            builder.show();
        } else if(!(updatedUsername.equals(originalUsername))){
            CheckIfUserExists();
        }else{
            UpdateUserDetails();
        }


    }


    private void displayUserDetails() {
        User user = userSessionManag.UserLoggedIn();

        //set text views

       // usernameTV.setText(user.getUsername());
        //useremailTV.setText(user.getEmail());






    }



    private boolean authenticate() {

        return userSessionManag.getLoggedIn();
    }
}
