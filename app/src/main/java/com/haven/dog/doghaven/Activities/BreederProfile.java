package com.haven.dog.doghaven.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;
import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Helpers.Validation;
import com.haven.dog.doghaven.Models.Breeder;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

import java.util.HashMap;
import java.util.Map;

public class BreederProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private EditText companynameET, companyEmailET, companyAddrET, companyPasswordET, newCompanyPasswordET;
    private String originalCompanyname, newCompanyname, newEmail, newPassword, confirmPassword, newAddr;
    private UserSessionManagment userSessionManag;
    private ProgressDialog progressDialog;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private Validation vailadate;
    private Button updateBtn;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_profile);
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




        companynameET = (EditText) findViewById(R.id.profile_breeder_companynameET);
        companyEmailET = (EditText) findViewById(R.id.breeder_profile_email_ET);
        companyAddrET = (EditText) findViewById(R.id.breeder_profile_address_ET);
        companyPasswordET = (EditText) findViewById(R.id.breeder_profile_passwordET);
        newCompanyPasswordET = (EditText) findViewById(R.id.breeder_profile_password_confirmET);
        updateBtn = (Button) findViewById(R.id.breeder_profile_updateBtn);

        updateBtn.setOnClickListener(this);
        //instantiates objects for reference
        userSessionManag = new UserSessionManagment(this);
        vailadate = new Validation();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Breeder company  = userSessionManag.BreederLoggedIn();
        originalCompanyname = company.getCompanyname();
        companynameET.setText(company.getCompanyname());
        companyEmailET.setText(company.getEmail());
        companyAddrET.setText(company.getAddr());



    }

    public void UpdateCompanyDetails(){
        progressDialog = new ProgressDialog(BreederProfile.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Updating details please wait");
        progressDialog.setTitle("Updating");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        StringRequest updateCompanyDetailsRequest = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {



                    @Override
                    public void onResponse(String response) {

                        Log.i("Response1", response);
                        Log.i("Response length", "" + response.length());
                        if (!(response.equals("failed"))){

                            progressDialog.hide();


                            Log.i("Returned data json:L01", response);

                            userSessionManag.EditBreederDetails(newCompanyname, newEmail, newAddr, newPassword);

                            Log.i("Returned data:L01", response);
                        }else{
                            //error message saying incorrect details
                            progressDialog.hide();
                            final AlertDialog.Builder builder = new AlertDialog.Builder(BreederProfile.this);
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
                params.put("updateBreeder", "updateBreeder");
                params.put("companyname", originalCompanyname);
                params.put("newcompanyname", newCompanyname);
                params.put("newemail", newEmail);
                params.put("newaddr", newAddr);
                params.put("newpassword", confirmPassword);

                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(updateCompanyDetailsRequest);


    }

    public void CheckIfBreederExists(){

        StringRequest CheckIfBreederExistRequest = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        Log.i("Response Checkusername", response);
                        Log.i("Response length", "" + response.length());

                        if(response.equals("exists")){
                            //exists = true;
                            Log.i("Made it to", "response user name exist");
                            companynameET.setError("Already exists");
                        }else{
                            UpdateCompanyDetails();
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
                params.put("companyname", newCompanyname);
                return params;
            }
        };



        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(CheckIfBreederExistRequest);

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
        getMenuInflater().inflate(R.menu.breeder_profile, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        newCompanyname = companynameET.getText().toString();
        newEmail = companyEmailET.getText().toString();
        newAddr = companyAddrET.getText().toString();
        newPassword = companyPasswordET.getText().toString();
        confirmPassword = newCompanyPasswordET.getText().toString();
        if(!(vailadate.IsVaildEmail(newEmail))){
            companyEmailET.setError("Invalid email");
        }
        else if(!(newPassword.equals(confirmPassword))){
            final AlertDialog.Builder builder = new AlertDialog.Builder(BreederProfile.this);
            builder.setMessage("Passwords do not match")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

            builder.show();
        }
        else if(vailadate.IsVaildPassword(confirmPassword) == false){
            final AlertDialog.Builder builder = new AlertDialog.Builder(BreederProfile.this);
            builder.setMessage("Invaild password")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

            builder.show();
        }else if(!(originalCompanyname.equals(newCompanyname))){
            CheckIfBreederExists();
        }else{
            UpdateCompanyDetails();
        }

    }
}
