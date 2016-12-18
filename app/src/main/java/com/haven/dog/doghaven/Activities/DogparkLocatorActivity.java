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
        implements NavigationView.OnNavigationItemSelectedListener,
                   OnMapReadyCallback,View.OnClickListener,GoogleMap.InfoWindowAdapter,
                    GoogleMap.OnInfoWindowClickListener {

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
        setContentView(R.layout.activity_dogpark_locator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getLoc= (Button) findViewById(R.id.button2);
        getLoc.setOnClickListener((View.OnClickListener) this);
        address= (EditText) findViewById(R.id.searchTF);
        ireland = new LatLng(53.476735, -7.727532);
        sp= new LatLng(53.226694, -6.115031);
        cp= new LatLng(53.261942, -6.157082);
        sa= new LatLng(53.372913, -6.173364);
        mp= new LatLng(53.273069, -6.268421);
        cor= new LatLng(53.309904, -6.414791);
        fp= new LatLng(51.895969, -8.496162);
        kh= new LatLng(53.268692, -6.109663);
        forp= new LatLng(53.338343, -6.743944);
        gp= new LatLng(51.801990, -9.660644);
        kfp= new LatLng(54.006684, -7.468258);
        op= new LatLng(54.585026, -5.914765);
        bf= new LatLng(54.557552, -5.928181);
        lv= new LatLng(54.554434, -5.949042);
        cg= new LatLng(54.590054, -5.820545);
        glen= new LatLng(55.008198, -7.986551);
        lnf= new LatLng(54.440743, -7.886711);
        cnp= new LatLng(53.538260, -9.887505);
        cool= new LatLng(53.079970, -8.855419);
        dw= new LatLng(51.971996, -9.593193);
        gqp= new LatLng(51.801963, -9.660600);

        shanPark = (new MarkerOptions().position(sp) .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Shanganagh Park").snippet("Cork Little, Co. Dublin"));
        cabPark = (new MarkerOptions().position(cp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Cabinteely Park").snippet("Cabinteely, Dublin"));
        stAnnes = (new MarkerOptions().position(sa).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("St.Annes Dog Park").snippet("Clontarf East, Dublin"));
        marlay = (new MarkerOptions().position(mp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Marlay Park").snippet("6 Grange Rd, Rathfarnham, Dublin 16"));
        corkp = (new MarkerOptions().position(cor).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Corkagh Park").snippet("St John's Cres, Ushers, Dublin 8"));
        fitzp = (new MarkerOptions().position(fp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Fitzgerald's Park").snippet("Mardyke, Cork"));
        killney = (new MarkerOptions().position(kh).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Killiney Hill").snippet("Scalpwilliam, Dublin"));
        dondeafp = (new MarkerOptions().position(forp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Donadea Forest Park").snippet("Donadea, Celbridge, Co. Kildare"));
        glenP = (new MarkerOptions().position(gp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Donadea Forest Park").snippet("Gleninchaquin, Kenmare, Co. Kerry"));
        killy = (new MarkerOptions().position(kfp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Killykeen Forest Park").snippet("Derinish Beg, Co. Cavan"));
        orm = (new MarkerOptions().position(op).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Ormeau Park").snippet("Ormeau Rd,Belfast,UK"));
        bel = (new MarkerOptions().position(bf).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Belvoir Park Forest").snippet("Belfast BT8 7QT, UK"));
        lagan = (new MarkerOptions().position(lv).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Lagan Valley Regional Park").snippet("3 Lock Keepers Ln, Belfast, UK"));
        comber = (new MarkerOptions().position(cg).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Comber Greenway").snippet("Belfast Rd, Comber, UK"));
        glenpark = (new MarkerOptions().position(glen).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Glenveagh National Park").snippet("Claggan Mountain South, Churchill, Letterkenny"));
        lough = (new MarkerOptions().position(lnf).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Lough Navar Forest").snippet("Glennasheevar Rd, Derrygonnelly, Enniskillen, UK"));
        conn = (new MarkerOptions().position(cnp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Connemara National Park").snippet("Mweelin, Letterfrack, Co. Galway"));
        coole = (new MarkerOptions().position(cool).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Coole Park").snippet("Coole Nature Reserve, Gort, Co. Galway"));
        derryw = (new MarkerOptions().position(dw).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Derrycunihy Wood").snippet("Derrycunihy, Co. Kerry"));
        glenqp = (new MarkerOptions().position(gqp).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Gleninchiquin National Park").snippet("Gleninchaquin, Kenmare, Co. Kerry"));

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
            //sign the user out of the application
            userSessionManag.clearUserData();
            Intent intent = new Intent(this,UserRegisterActivity.class);
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
