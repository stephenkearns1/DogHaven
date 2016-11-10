package com.haven.dog.doghaven.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.haven.dog.doghaven.R;

public class BreederRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText fnameET, snameET, usernameET, emailET, companyNameET, companyVatET, addressET, countyET, dobET, passwordET;
    Button registerBtn;
    private  String  fname, sname, username,companyname, companyvatnum, email, addr, county, dob, password;
    private final String doghavenAPI_URL = "https://backend-doghaven-app-stephenkearns1.c9users.io/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_register);

        //This is where the merge issue will happen
        fnameET = (EditText) findViewById(R.id.fnameET);
        snameET = (EditText) findViewById(R.id.snameET);
        usernameET = (EditText) findViewById(R.id.usernameET);
        emailET = (EditText) findViewById(R.id.emailET);
        companyNameET = (EditText) findViewById(R.id.companyNameET);
        companyVatET = (EditText) findViewById(R.id.companyVatET);
        addressET = (EditText) findViewById(R.id.addrET);
        countyET = (EditText) findViewById(R.id.countyET);
        dobET = (EditText) findViewById(R.id.dobET);
        passwordET = (EditText) findViewById(R.id.userpasswordET);


        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.registerBtn){
            fname = fnameET.getText().toString();
            sname = snameET.getText().toString();
            username = usernameET.getText().toString();
            companyname = companyNameET.getText().toString();
            companyvatnum = companyVatET.getText().toString();
            email = emailET.getText().toString();
            addr = addressET.getText().toString();
            county = countyET.getText().toString();
            dob = dobET.getText().toString();
            password = passwordET.getText().toString();



            //make call to register methods

        }
    }

    public void Register(){


    }
}
