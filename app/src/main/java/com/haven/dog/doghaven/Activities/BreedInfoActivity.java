package com.haven.dog.doghaven.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;

import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.Models.User;
import com.haven.dog.doghaven.R;

public class BreedInfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private ImageView holder;
    private ImageView stats;
    private Spinner dropdown;
    private ScrollView info;
    private TextView breedInfo;
    private UserSessionManagment userSessionManag;

    private TextView usernameTV, useremailTV;

    private Button physical,medical,social,exercise;

    RatingBar rb1,rb2,rb3,rb4,mrb1,mrb2,mrb3,mrb4,srb1,srb2,srb3,srb4,erb1,erb2,erb3,erb4;



    private String breed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        holder=(ImageView) findViewById(R.id.picHolder);
        breedInfo = (TextView)findViewById(R.id.breedName);
        //stats=(ImageView) findViewById((R.id.infoImage));
        dropdown=(Spinner) findViewById(R.id.ddsize);
       // info= (ScrollView) findViewById(R.id.scroll);
        physical = (Button) findViewById(R.id.physicalBT) ;
        medical = (Button) findViewById(R.id.medicalBT) ;
        social = (Button) findViewById(R.id.socialBT) ;
        exercise = (Button) findViewById(R.id.exerciseBT) ;
        physical.setOnClickListener(this);
        medical.setOnClickListener(this);
        social.setOnClickListener(this);
        exercise.setOnClickListener(this);

        rb2=(RatingBar) findViewById(R.id.ratingBar2);
        rb3=(RatingBar) findViewById(R.id.ratingBar3);
        rb4=(RatingBar) findViewById(R.id.ratingBar4);
        mrb1=(RatingBar) findViewById(R.id.medicalRB1);
        mrb2=(RatingBar) findViewById(R.id.medicalRB2);
        mrb3=(RatingBar) findViewById(R.id.medicalRB3);
        mrb4=(RatingBar) findViewById(R.id.medicalRB4);
        srb1=(RatingBar) findViewById(R.id.socialRB1);
        srb2=(RatingBar) findViewById(R.id.socialRB2);
        srb3=(RatingBar) findViewById(R.id.socialRB3);
        srb4=(RatingBar) findViewById(R.id.socialRB4);
        erb1=(RatingBar) findViewById(R.id.exerciseRB1);
        erb2=(RatingBar) findViewById(R.id.exerciseRB2);
        erb3=(RatingBar) findViewById(R.id.exerciseRB3);
        erb4=(RatingBar) findViewById(R.id.exerciseRB4);

        breed="";


        addListenerOnDropDown();


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
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this,BreederSearch.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent info= new Intent(this,DogparkLocatorActivity.class);
            startActivity(info);
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



    public void addListenerOnDropDown() {
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if(dropdown.getItemAtPosition(i).toString().equals("Akita")){
                        holder.setImageResource(R.drawable.akita);
                        //stats.setImageResource(R.drawable.breedinfo4);
                        breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                        breed = "Akita";

                        //dropdown.setSelection(0);
                }else if (dropdown.getItemAtPosition(i).toString().equals("Husky")){
                    holder.setImageResource(R.drawable.husky);
                    //stats.setImageResource(R.drawable.zhusky_info);
                  // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed = "Husky";
                }else if (dropdown.getItemAtPosition(i).toString().equals("")){

                    //stats.setImageResource(R.drawable.zhusky_info);
                  //  dropdown.setSelection(0);

                    breed = "";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Labrador")){
                    holder.setImageResource(R.drawable.lab);
                    //stats.setImageResource(R.drawable.zlab_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Labrador";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Springer Spaniel")){
                    holder.setImageResource(R.drawable.springer);
                    //stats.setImageResource(R.drawable.zcocker_info);
                   // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Springer Spaniel";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Rottweiler")){
                    holder.setImageResource(R.drawable.rotty);
                   // stats.setImageResource(R.drawable.zrott_info);
                   // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Rottweiler";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Basset Hound")){
                    holder.setImageResource(R.drawable.zbassette);
                   // stats.setImageResource(R.drawable.zbassette_info);
                   //dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Basset Hound";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Beagle")){
                    holder.setImageResource(R.drawable.zbeagle);
                   // stats.setImageResource(R.drawable.zbeagle_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Beagle";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Bernese Mountain Dog")){
                    holder.setImageResource(R.drawable.zbernese);
                   // stats.setImageResource(R.drawable.zbernese_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Bernese Mountain Dog";
                }else if (dropdown.getItemAtPosition(i).toString().equals("BloodHound")){
                    holder.setImageResource(R.drawable.zbloodhound);
                   // stats.setImageResource(R.drawable.zbloodhound_info);
                   // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="BloodHound";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Boxer")){
                    holder.setImageResource(R.drawable.zboxer);
                   // stats.setImageResource(R.drawable.zboxer_info);
                   // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Boxer";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Bulldog")){
                    holder.setImageResource(R.drawable.zbulldog);
                   // stats.setImageResource(R.drawable.zbulldog_info);
                   // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Bulldog";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Bullmastiff")){
                    holder.setImageResource(R.drawable.zbullmastiff);
                   // stats.setImageResource(R.drawable.zbullmastiff_info);
                   // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Bullmastiff";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Cane corso")){
                    holder.setImageResource(R.drawable.zcano);
                   // stats.setImageResource(R.drawable.zcano_info);
                   // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Cane corso";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Chihuahua")){
                    holder.setImageResource(R.drawable.zchihuahua);
                   // stats.setImageResource(R.drawable.zchihuahua_info);
                   // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Chihuahua";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Chow Chow")){
                    holder.setImageResource(R.drawable.zchow);
                    //stats.setImageResource(R.drawable.zchow_info);
                  // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Chow Chow";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Cocker spaniel")){
                    holder.setImageResource(R.drawable.zcocker);
                    //stats.setImageResource(R.drawable.zcocker_info);
                  // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Cocker spaniel";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Collie")){
                    holder.setImageResource(R.drawable.zcollie);
                    //stats.setImageResource(R.drawable.zcollie_info);
                   // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Collie";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Dalmatian")){
                    holder.setImageResource(R.drawable.zdalmatian);
                    //stats.setImageResource(R.drawable.zdalmatian_info);
                   // dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Dalmatian";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Dachshund")){
                    holder.setImageResource(R.drawable.zdashund);
                   // stats.setImageResource(R.drawable.zdashshund_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Dachshund";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Doberman")){
                    holder.setImageResource(R.drawable.zdoberman);
                    //stats.setImageResource(R.drawable.zdoberman_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Doberman";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Dog du Bordeux")){
                    holder.setImageResource(R.drawable.zdogdu);
                    //stats.setImageResource(R.drawable.zdogbor_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Dog du Bordeux";
                }else if (dropdown.getItemAtPosition(i).toString().equals("French Bulldog")){
                    holder.setImageResource(R.drawable.zfrenchbully);
                    //stats.setImageResource(R.drawable.zfrenchbully_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="French Bulldog";
                }else if (dropdown.getItemAtPosition(i).toString().equals("German Shepard")){
                    holder.setImageResource(R.drawable.zgerman);
                    //stats.setImageResource(R.drawable.zgermanshep_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="German Shepard";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Great Dane")){
                    holder.setImageResource(R.drawable.zdane);
                    //stats.setImageResource(R.drawable.zdane_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Great Dane";
                }else if (dropdown.getItemAtPosition(i).toString().equals("GreyHound")){
                    holder.setImageResource(R.drawable.zgreyhound);
                    //stats.setImageResource(R.drawable.zgreyhound_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="GreyHound";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Jack Russell")){
                    holder.setImageResource(R.drawable.zjackrussel);
                    //stats.setImageResource(R.drawable.zjackrussel_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Jack Russell";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Malamute")){
                    holder.setImageResource(R.drawable.zmalamute);
                   // stats.setImageResource(R.drawable.zmalamute_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Malamute";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Pitbull")){
                    holder.setImageResource(R.drawable.zpitbull);
                    //stats.setImageResource(R.drawable.zpitbull_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Pitbull";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Pug")){
                    holder.setImageResource(R.drawable.zpug);
                    //stats.setImageResource(R.drawable.zpug_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Pug";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Retriever")){
                    holder.setImageResource(R.drawable.zretriever);
                    //stats.setImageResource(R.drawable.zretriever_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Retriever";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Setter")){
                    holder.setImageResource(R.drawable.zredsetter);
                    //stats.setImageResource(R.drawable.zsetter_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Setter";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Yorkshire Terrier")){
                    holder.setImageResource(R.drawable.zterrier);
                    //stats.setImageResource(R.drawable.zterrier_info);
                 //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Yorkshire Terrier";
                }else if (dropdown.getItemAtPosition(i).toString().equals("Wolfhound")){
                    holder.setImageResource(R.drawable.zwolfhound);
                    //stats.setImageResource(R.drawable.zwolfhound_info);
                  //  dropdown.setSelection(0);
                    breedInfo.setText(dropdown.getItemAtPosition(i).toString());
                    breed ="Wolfhound";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.physicalBT:
                if(breed.equals("Akita")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup);
                    dialog.setTitle("Physical Attributes");
                    rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
                    rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
                    rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
                    rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

                    rb1.setRating(4);
                    rb2.setRating(4);
                    rb3.setRating(3);
                    rb4.setRating(3);

                    dialog.show();
                    break;

           }else if(breed.equals("Basset Hound")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(3);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Beagle")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Bernese Mountain Dog")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("BloodHound")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Boxer")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Bulldog")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Bullmastiff")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Cane corso")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Chihuahua")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Chow Chow")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Cocker spaniel")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Collie")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Dalmatian")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Dachshund")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Doberman")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Dod du Bordeux")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("French Bulldog")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("German Shepard")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Great Dane")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("GreyHound")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Husky")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Jack Russell")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Labrador")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(3);
               rb4.setRating(3);

               dialog.show();
                    break;
           }else if(breed.equals("Malamute")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

               rb1.setRating(2);
               rb2.setRating(2);
               rb3.setRating(2);
               rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Pitbull")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

                    rb1.setRating(2);
                    rb2.setRating(2);
                    rb3.setRating(2);
                    rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Pug")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

                    rb1.setRating(2);
                    rb2.setRating(2);
                    rb3.setRating(2);
                    rb4.setRating(2);
               dialog.show();
                    break;
           }else if(breed.equals("Retriever")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

                    rb1.setRating(2);
                    rb2.setRating(2);
                    rb3.setRating(2);
                    rb4.setRating(2);
               dialog.show();
                    break;
           }else if(breed.equals("Rottweiler")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

                    rb1.setRating(2);
                    rb2.setRating(2);
                    rb3.setRating(2);
                    rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Setter")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

                    rb1.setRating(2);
                    rb2.setRating(2);
                    rb3.setRating(2);
                    rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Springer Spaniel")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

                    rb1.setRating(2);
                    rb2.setRating(2);
                    rb3.setRating(2);
                    rb4.setRating(2);

               dialog.show();
                    break;
           }else if(breed.equals("Yorkshire Terrier")){
               final Dialog dialog = new Dialog(BreedInfoActivity.this);

               dialog.setContentView(R.layout.breedinfo_popup);
               dialog.setTitle("Physical Attributes");
               rb1= (RatingBar) dialog.findViewById(R.id.ratingBar1);
               rb2=(RatingBar) dialog.findViewById(R.id.ratingBar2);
               rb3=(RatingBar) dialog.findViewById(R.id.ratingBar3);
               rb4=(RatingBar) dialog.findViewById(R.id.ratingBar4);

                    rb1.setRating(2);
                    rb2.setRating(2);
                    rb3.setRating(2);
                    rb4.setRating(2);

               dialog.show();
           }else {

                }
                break;
////////////////////////////////////////////////Medical Button
            case R.id.medicalBT:
                if(breed.equals("Akita")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Basset Hound")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Beagle")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);
                    dialog.show();
                }else if(breed.equals("Bernese Mountain Dog")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("BloodHound")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Boxer")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Bulldog")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Bullmastiff")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Cane corso")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Chihuahua")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Chow Chow")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Cocker spaniel")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);
                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Collie")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Dalmatian")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Dachshund")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Doberman")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Dod du Bordeux")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("French Bulldog")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("German Shepard")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Great Dane")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("GreyHound")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Husky")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Jack Russell")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Labrador")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(3);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(1);

                    dialog.show();
                }else if(breed.equals("Malamute")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Pitbull")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Pug")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Retriever")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Rottweiler")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Setter")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Springer Spaniel")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Yorkshire Terrier")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    mrb1= (RatingBar) dialog.findViewById(R.id.medicalRB1);
                    mrb2=(RatingBar) dialog.findViewById(R.id.medicalRB2);
                    mrb3=(RatingBar) dialog.findViewById(R.id.medicalRB3);
                    mrb4=(RatingBar) dialog.findViewById(R.id.medicalRB4);

                    mrb1.setRating(2);
                    mrb2.setRating(2);
                    mrb3.setRating(2);
                    mrb4.setRating(2);

                    dialog.show();
                }else{

                }


                break;
/////////////////////////////////////////////////////////////////SocialButton
            case R.id.socialBT:
                if(breed.equals("Akita")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Basset Hound")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Beagle")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Bernese Mountain Dog")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("BloodHound")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Boxer")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Bulldog")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Bullmastiff")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Cane corso")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Chihuahua")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Chow Chow")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Cocker spaniel")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);
                    dialog.setContentView(R.layout.breedinfo_popup2);
                    dialog.setTitle("Physical Attributes");
                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);
                    dialog.show();
                }else if(breed.equals("Collie")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);
                    dialog.show();
                }else if(breed.equals("Dalmatian")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);
                    dialog.show();
                }else if(breed.equals("Dachshund")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);
                    dialog.show();
                }else if(breed.equals("Doberman")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Dod du Bordeux")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("French Bulldog")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("German Shepard")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Great Dane")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("GreyHound")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Husky")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Jack Russell")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Labrador")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(4);
                    srb2.setRating(4);
                    srb3.setRating(3);
                    srb4.setRating(3);
                    dialog.show();
                }else if(breed.equals("Malamute")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Pitbull")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);
                    dialog.show();
                }else if(breed.equals("Pug")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);
                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Retriever")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Rottweiler")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Setter")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);
                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Springer Spaniel")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Yorkshire Terrier")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup3);
                    dialog.setTitle("Physical Attributes");
                    srb1= (RatingBar) dialog.findViewById(R.id.socialRB1);
                    srb2=(RatingBar) dialog.findViewById(R.id.socialRB2);
                    srb3=(RatingBar) dialog.findViewById(R.id.socialRB3);
                    srb4=(RatingBar) dialog.findViewById(R.id.socialRB4);

                    srb1.setRating(2);
                    srb2.setRating(2);
                    srb3.setRating(2);
                    srb4.setRating(2);

                    dialog.show();
                }else{

                }break;
            ///////////////////////////////////////////////////ExerciseButton
            case R.id.exerciseBT:
                if(breed.equals("Akita")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Basset Hound")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Beagle")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Bernese Mountain Dog")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("BloodHound")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Boxer")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);
                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Bulldog")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Bullmastiff")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Cane corso")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Chihuahua")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Chow Chow")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Cocker spaniel")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);
                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Collie")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Dalmatian")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Dachshund")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Doberman")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Dod du Bordeux")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("French Bulldog")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("German Shepard")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Great Dane")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("GreyHound")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Husky")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Jack Russell")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Labrador")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(3);
                    erb2.setRating(2);
                    erb3.setRating(3);
                    erb4.setRating(4);

                    dialog.show();
                }else if(breed.equals("Malamute")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Pitbull")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Pug")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);
                    dialog.show();
                }else if(breed.equals("Retriever")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Rottweiler")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Setter")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(2);

                    dialog.show();
                }else if(breed.equals("Springer Spaniel")){
                    final Dialog dialog = new Dialog(BreedInfoActivity.this);

                    dialog.setContentView(R.layout.breedinfo_popup4);
                    dialog.setTitle("Physical Attributes");
                    erb1= (RatingBar) dialog.findViewById(R.id.exerciseRB1);
                    erb2=(RatingBar) dialog.findViewById(R.id.exerciseRB2);
                    erb3=(RatingBar) dialog.findViewById(R.id.exerciseRB3);
                    erb4=(RatingBar) dialog.findViewById(R.id.exerciseRB4);

                    erb1.setRating(2);
                    erb2.setRating(2);
                    erb3.setRating(2);
                    erb4.setRating(3);

                    dialog.show();
                }else {

                }


            }


        }








}
