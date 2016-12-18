package com.haven.dog.doghaven.Activities;

import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Button;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.haven.dog.doghaven.Helpers.MyNetworkingSingletonVolley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.HashMap;

import com.haven.dog.doghaven.Helpers.UserSessionManagment;
import com.haven.dog.doghaven.R;

/**
 * Created by Marken Teder on 10/11/2016.
 */

public class AddDogActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameinsertET, ageinsertET, sexinsertET, breedinsertET, companyinsertET, colorinsertET, illcurrentinsertET, illpastinsertET, vacinsertET, vacmissinginsertET;
    Button AddDogBtn, GalleryBtn, UploadBtn;
    Spinner ddsize, ddfur, ddbody, ddtolerance, ddneutered, ddenergy, ddexercise, ddintelligence, ddplayful, ddinstinct, ddpeople, ddfamily, dddogs, ddemotion, ddsociability;
    private String dog_name, dog_breed, dog_sex, dog_age, dog_company, dog_color, dillcurr, dillpast, dvac, dvacmiss, size, fur, body, tolerance, neutered, energy, exercise, intelligence, playful, instinct, people, family, dogs, emotion, sociability, image;
    private ProgressDialog pDialog;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";
    private UserSessionManagment userSessionManag;
    private ImageView PictureIV;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog_page);

        nameinsertET = (EditText) findViewById(R.id.NameInsertET);
        ageinsertET = (EditText) findViewById(R.id.AgeInsertET);
        sexinsertET = (EditText) findViewById(R.id.SexInsertET);
        breedinsertET = (EditText) findViewById(R.id.BreedInsertET);
        companyinsertET = (EditText) findViewById(R.id.CompanyInsertET);
        colorinsertET = (EditText) findViewById(R.id.ColorInsertET);
        illcurrentinsertET = (EditText) findViewById(R.id.IllnessCurrentInsertET);
        illpastinsertET = (EditText) findViewById(R.id.IllnessPastInsertET);
        vacinsertET = (EditText) findViewById(R.id.VaccinationInsertET);
        vacmissinginsertET = (EditText) findViewById(R.id.VaccinationsMissingInsertET);
        ddsize = (Spinner) findViewById(R.id.ddsize);
        ddfur = (Spinner) findViewById(R.id.ddfur);
        ddbody = (Spinner) findViewById(R.id.ddbody);
        ddtolerance = (Spinner) findViewById(R.id.ddtolerance);
        ddneutered = (Spinner) findViewById(R.id.ddneutered);
        ddenergy = (Spinner) findViewById(R.id.ddenergy);
        ddexercise = (Spinner) findViewById(R.id.ddexercise);
        ddintelligence = (Spinner) findViewById(R.id.ddintelligence);
        ddplayful = (Spinner) findViewById(R.id.ddplayful);
        ddinstinct = (Spinner) findViewById(R.id.ddinstinct);
        ddpeople = (Spinner) findViewById(R.id.ddpeople);
        ddfamily = (Spinner) findViewById(R.id.ddfamily);
        dddogs = (Spinner) findViewById(R.id.dddogs);
        ddemotion = (Spinner) findViewById(R.id.ddemotion);
        ddsociability = (Spinner) findViewById(R.id.ddsociability);

        AddDogBtn = (Button) findViewById(R.id.AddDogBtn);
        GalleryBtn = (Button) findViewById(R.id.GalleryBtn);
        UploadBtn = (Button) findViewById(R.id.UploadBtn);

        PictureIV = (ImageView) findViewById(R.id.PictureIV);

        AddDogBtn.setOnClickListener(this);
        GalleryBtn.setOnClickListener(this);
        UploadBtn.setOnClickListener(this);


        //instantiates objects for reference
        userSessionManag = new UserSessionManagment(this);


    }

    protected void onStart() {
        super.onStart();
        //checks to see if the user is authenticated if not it requests the user to login.
        if (authenticate() == true) {
            //display logged in or start main activity
            //displayUserDetails();
        } else {
            //starts loginIn activity
            Intent intent = new Intent(this, StartActivtiy.class);
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.AddDogBtn) {

            dog_name = nameinsertET.getText().toString();
            dog_age = ageinsertET.getText().toString();
            dog_sex = sexinsertET.getText().toString();
            dog_breed = breedinsertET.getText().toString();
            dog_company = companyinsertET.getText().toString();
            dog_color = colorinsertET.getText().toString();
            dillcurr = illcurrentinsertET.getText().toString();
            dillpast = illpastinsertET.getText().toString();
            dvac = vacinsertET.getText().toString();
            dvacmiss = vacmissinginsertET.getText().toString();
            size = ddsize.getSelectedItem().toString();
            fur = ddfur.getSelectedItem().toString();
            body = ddbody.getSelectedItem().toString();
            tolerance = ddtolerance.getSelectedItem().toString();
            neutered = ddneutered.getSelectedItem().toString();
            energy = ddenergy.getSelectedItem().toString();
            exercise = ddexercise.getSelectedItem().toString();
            intelligence = ddintelligence.getSelectedItem().toString();
            playful = ddplayful.getSelectedItem().toString();
            instinct = ddinstinct.getSelectedItem().toString();
            people = ddpeople.getSelectedItem().toString();
            family = ddfamily.getSelectedItem().toString();
            dogs = dddogs.getSelectedItem().toString();
            emotion = ddemotion.getSelectedItem().toString();
            sociability = ddsociability.getSelectedItem().toString();


            addDog();


        } else {
            if (v.getId() == R.id.GalleryBtn) {
                onGalleryClicker();
            } else {
                if (v.getId() == R.id.UploadBtn) {

                    image = getStringImage(bitmap);

                    uploadImage();
                }
            }
        }
    }

    public void onGalleryClicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                PictureIV.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmap) {


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
            byte[] imageBytes = outputStream.toByteArray();

            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;

    }




    public void uploadImage(){
        pDialog = new ProgressDialog(AddDogActivity.this);
        pDialog.setMessage("...Processing...");
        pDialog.setTitle("Uploading Image");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
        StringRequest ImageRequest = new StringRequest(Request.Method.POST, doghavenAPI_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        Log.i("Returned data:R01", response);
                        if(response.equalsIgnoreCase("success")){

                        }else{

                        }
                    }

                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                Toast.makeText(AddDogActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new Hashtable<String, String>();

                params.put("uploadimage", "uploadimage");
                params.put("image", image);

                return params;
            }
        };

        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(ImageRequest);
    }

    public void addDog(){

        pDialog = new ProgressDialog(AddDogActivity.this);
        pDialog.setMessage("...Processing...");
        pDialog.setTitle("Adding New Dog");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
        StringRequest AddDogRequest  = new StringRequest(Request.Method.POST,doghavenAPI_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        pDialog.hide();
                        Log.i("Returned data:R01", response);
                        if(response.equalsIgnoreCase("success")){

                        }else{

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
                params.put("adddog", "");
                params.put("dog_name", dog_name);
                params.put("dog_age", dog_age);
                params.put("dog_sex", dog_sex);
                params.put("dog_breed", dog_breed);
                params.put("dog_company", dog_company);
                params.put("dog_color", dog_color);
                params.put("dillcurr", dillcurr);
                params.put("dillpast", dillpast);
                params.put("dvac", dvac);
                params.put("dvacmiss", dvacmiss);
                params.put("size", size);
                params.put("fur", fur);
                params.put("body", body);
                params.put("tolerance", tolerance);
                params.put("neutered", neutered);
                params.put("energy", energy);
                params.put("exercise", exercise);
                params.put("intelligence", intelligence);
                params.put("playful", playful);
                params.put("instinct", instinct);
                params.put("people", people);
                params.put("family", family);
                params.put("dogs", dogs);
                params.put("emotion", emotion);
                params.put("sociability", sociability);

                return params;
            }
        };

        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(this).addReuestToQueue(AddDogRequest);
    }

    private boolean authenticate() {
        Log.i("getLoggedIn value", "" + userSessionManag.getBreederLoggedIn());
        return userSessionManag.getBreederLoggedIn();
    }


    public Uri StoreInMemory(){

        String filename = "myfile";
        String string = "Hello world!";
        FileOutputStream outputStream;


            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("imageDir", getApplication().MODE_PRIVATE);
            File file = new File(directory,System.currentTimeMillis() + ".png");
            Uri imgUri = Uri.fromFile(file);
            this.imgPath = file.getAbsolutePath();
            return imgUri;
        }





}
