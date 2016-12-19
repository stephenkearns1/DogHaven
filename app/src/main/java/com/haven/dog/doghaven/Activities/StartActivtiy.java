package com.haven.dog.doghaven.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.haven.dog.doghaven.R;

public class StartActivtiy extends AppCompatActivity implements View.OnClickListener {
    Button userloginBtn, breederLoginBtn;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activtiy);

        userloginBtn = (Button) findViewById(R.id.gotoUserLoginBtn);
        breederLoginBtn = (Button) findViewById(R.id.gotoBreederLoginBtn);
        userloginBtn.setOnClickListener(this);
        breederLoginBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gotoUserLoginBtn:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.gotoBreederLoginBtn:
                intent = new Intent(this,BreederLoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
