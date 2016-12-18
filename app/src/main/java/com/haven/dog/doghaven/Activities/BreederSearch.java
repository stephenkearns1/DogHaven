package com.haven.dog.doghaven.Activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class BreederSearch extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback,View.OnClickListener,GoogleMap.InfoWindowAdapter,GoogleMap.OnInfoWindowClickListener {

    private UserSessionManagment userSessionManag;
    private TextView usernameTV, useremailTV;
    GoogleMap mMap;
    Button getLoc;
    EditText address;
    MarkerOptions shanPark,cabPark,stAnnes,marlay,corkp,fitzp,killney,dondeafp,
            glenP,killy,orm,bel,lagan,comber,glenpark,lough,conn,coole,derryw,
            glenqp;
    LatLng ireland,sp,cp,sa,mp,cor,fp,kh,forp,gp,kfp,op,bf,lv,cg,glen,lnf,cnp,cool,dw,
            gqp;
    Marker userMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getLoc= (Button) findViewById(R.id.button2);
        getLoc.setOnClickListener((View.OnClickListener) this);
        address= (EditText) findViewById(R.id.searchTF);
        ireland = new LatLng(53.476735, -7.727532);
        sp= new LatLng(54.879123, -6.283367);
        cp= new LatLng(54.992416, -7.290486);
        sa= new LatLng(54.947891, -8.355100);
        mp= new LatLng(53.126358, -6.068780);
        cor= new LatLng(53.187215, -6.109611);
        fp= new LatLng(53.135418, -6.084521);
        kh= new LatLng(53.060104, -6.699498);
        forp= new LatLng(52.949632, -9.065299);
        gp= new LatLng(52.669830, -7.815896);
        kfp= new LatLng(52.952830, -7.801130);
        op= new LatLng(53.215978, -6.655961);
        bf= new LatLng(51.643132, -8.764603);
        lv= new LatLng(53.394458, -9.921418);
        cg= new LatLng(53.627507, -8.193836);
        glen= new LatLng(54.573626, -5.893578);
        lnf= new LatLng(53.393741, -6.372186);
        cnp= new LatLng(53.265692, -6.144170);
        cool= new LatLng(52.676931, -6.273516);
        dw= new LatLng(52.506275, -6.579335);
        gqp= new LatLng(51.909715, -8.171779);

        shanPark = (new MarkerOptions().position(sp) .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Husky").snippet("087264511"));
        cabPark = (new MarkerOptions().position(cp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Akita").snippet("0863589478"));
        stAnnes = (new MarkerOptions().position(sa).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Bulldog").snippet("0872646114"));
        marlay = (new MarkerOptions().position(mp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Labrador").snippet("0838693059"));
        corkp = (new MarkerOptions().position(cor).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Malamute").snippet("08938749294"));
        fitzp = (new MarkerOptions().position(fp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Dog du Bordeux").snippet("0874536326"));
        killney = (new MarkerOptions().position(kh).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Rottweiler").snippet("087273040"));
        dondeafp = (new MarkerOptions().position(forp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Springer").snippet("80964785"));
        glenP = (new MarkerOptions().position(gp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("French Bulldog").snippet("089677493"));
        killy = (new MarkerOptions().position(kfp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Doberman").snippet("086977573"));
        orm = (new MarkerOptions().position(op).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Pitbull").snippet("089775584"));
        bel = (new MarkerOptions().position(bf).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Beagle").snippet("0867464638"));
        lagan = (new MarkerOptions().position(lv).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Boxer").snippet("083757584"));
        comber = (new MarkerOptions().position(cg).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Wolfhound").snippet("0875667337"));
        glenpark = (new MarkerOptions().position(glen).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Pug").snippet("0864747839"));
        lough = (new MarkerOptions().position(lnf).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Jack Russell").snippet("0897747483"));
        conn = (new MarkerOptions().position(cnp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Greyhound").snippet("0869646475"));
        coole = (new MarkerOptions().position(cool).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("German Shepard").snippet("68945904"));
        derryw = (new MarkerOptions().position(dw).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Collie").snippet("08766614"));
        glenqp = (new MarkerOptions().position(gqp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Terrier").snippet("0863587478"));

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
            Intent intent = new Intent(this, StartActivtiy.class);
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


        }  else if (id == R.id.nav_manage) {
            Intent info= new Intent(this,DogparkLocatorActivity.class);
            startActivity(info);
        }else if (id == R.id.nav_questions) {
            Intent info= new Intent(this,Questions.class);
            startActivity(info);
        }else if (id==R.id.nav_profile){
            Intent info = new Intent(this,UserProfile.class);
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
        mMap.addMarker(dondeafp);
        mMap.addMarker(killy);
        mMap.addMarker(orm);
        mMap.addMarker(bel);
        mMap.addMarker(lagan);
        mMap.addMarker(comber);
        mMap.addMarker(glenpark);
        mMap.addMarker(lough);
        mMap.addMarker(conn);
        mMap.addMarker(coole);
        mMap.addMarker(derryw);
        mMap.addMarker(glenqp);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ireland, 6));
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
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
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

        shit.setText("Breed: "+marker.getTitle());
        wank.setText("Phone: "+marker.getSnippet());
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
