package com.haven.dog.doghaven.Activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

import java.io.IOException;
import java.util.List;

public class DogparkLocatorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback,View.OnClickListener,GoogleMap.InfoWindowAdapter,GoogleMap.OnInfoWindowClickListener {

    private UserSessionManagment userSessionManag;
    private TextView usernameTV, useremailTV;
    GoogleMap mMap;
    Button getLoc;
    EditText address;
    MarkerOptions shanPark,cabPark,stAnnes,marlay,corkp,fitzp,killney;
    LatLng ireland,sp,cp,sa,mp,cor,fp,kh;
    Marker userMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogpark_locator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getLoc= (Button) findViewById(R.id.button2);
        getLoc.setOnClickListener((View.OnClickListener) this);
        address= (EditText) findViewById(R.id.searchTF);
        ireland = new LatLng(53.339945, -6.237016);
        sp= new LatLng(53.226694, -6.115031);
        cp= new LatLng(53.261942, -6.157082);
        sa= new LatLng(53.372913, -6.173364);
        mp= new LatLng(53.273069, -6.268421);
        cor= new LatLng(53.309904, -6.414791);
        fp= new LatLng(51.895969, -8.496162);
        kh= new LatLng(53.268692, -6.109663);
        shanPark = (new MarkerOptions().position(sp).title("Marker In Dublin").snippet("Cork Little, Co. Dublin"));
        cabPark = (new MarkerOptions().position(cp).title("Cabinteely Park").snippet("Cabinteely, Dublin"));
        stAnnes = (new MarkerOptions().position(sa).title("St.Annes Dog Park").snippet("Clontarf East, Dublin"));
        marlay = (new MarkerOptions().position(mp).title("Marlay Park").snippet("6 Grange Rd, Rathfarnham, Dublin 16"));
        corkp = (new MarkerOptions().position(cor).title("Corkagh Park").snippet("St John's Cres, Ushers, Dublin 8"));
        fitzp = (new MarkerOptions().position(fp).title("Fitzgerald's Park").snippet("Mardyke, Cork"));
        killney = (new MarkerOptions().position(kh).title("Killiney Hill").snippet("Scalpwilliam, Dublin"));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.worldMap);
        mapFragment.getMapAsync(this);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Inflates the nav_header layout as the header and then access the elements in the nav_header to populate with user details in the drawer
        View navHeader = navigationView.getHeaderView(0);
        usernameTV = (TextView) navHeader.findViewById(R.id.usernameHeader_TV);
        useremailTV = (TextView) navHeader.findViewById(R.id.useremailHeader_TV);

        //instantiates objects for reference
        userSessionManag = new UserSessionManagment(this);

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
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }


    private boolean authenticate() {
        Log.i("getLoggedIn value", "" + userSessionManag.getLoggedIn());
        return userSessionManag.getLoggedIn();
    }

    private void displayUserDetails() {
        User user = userSessionManag.UserLoggedIn();

        //set text views
        // View header = navigationView.

        //displayUsernameTV.setText(user.getUserName());
        //displayUseremailTV.setText(user.getEmail())
        //;
        usernameTV.setText(user.getUsername());
        useremailTV.setText(user.getEmail());



        Log.i("user Loggedin", user.getUsername() + user.getEmail());


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
        getMenuInflater().inflate(R.menu.dogpark_locator, menu);
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
            Intent info= new Intent(this,BreedInfoActivity.class);
            startActivity(info);


        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this,BreederSearch.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent info= new Intent(this,DogparkLocatorActivity.class);
            startActivity(info);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        mMap.addMarker(shanPark);
        mMap.addMarker(cabPark);
        mMap.addMarker(stAnnes);
        mMap.addMarker(marlay);
        mMap.addMarker(corkp);
        mMap.addMarker(fitzp);
        mMap.addMarker(killney);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ireland, 10));
        mMap.setInfoWindowAdapter(this);
        mMap.setOnInfoWindowClickListener( this);

    }





    @Override
    public void onClick(View view) {

        if(userMarker != null){
            userMarker.remove();
        }
        if(view.getId() == R.id.button2){
            String location = address.getText().toString();
            List<Address> addressList = null;
            if(location != null || !location.equals("")){
                Geocoder geocoder = new Geocoder(this);
                try{
                    addressList = geocoder.getFromLocationName(location, 1);
                    if(addressList.size() == 0){

                        address.setError("Incorrect Address");
                    }else{
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        userMarker= mMap.addMarker(new MarkerOptions().position(latLng)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dogpark))
                                .title("Marker in Sydney"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    }

                }catch(IOException e){

                    e.printStackTrace();
                }
            }

        }
    }


    @Override
    public View getInfoWindow(Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.info_window,null);
        TextView shit = (TextView) view.findViewById(R.id.textView4);
        TextView wank = (TextView) view.findViewById(R.id.textView2);

        shit.setText("Park: "+marker.getTitle());
        wank.setText("Address: "+marker.getSnippet());
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        marker.hideInfoWindow();
    }
}
