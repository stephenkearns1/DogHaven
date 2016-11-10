package com.haven.dog.doghaven.Activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.haven.dog.doghaven.R;

/**
 * Created by Marken Teder on 10/11/2016.
 */

public class AddDogActivity extends AppCompatActivity {

    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog_page);
        dropdown=(Spinner) findViewById(R.id.dropDown);
        addListenerOnDropDown();




    }

    private void addListenerOnDropDown() {
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
