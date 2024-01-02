package com.example.mobilefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }


    public void changeActivity(View view){
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);

    }
}