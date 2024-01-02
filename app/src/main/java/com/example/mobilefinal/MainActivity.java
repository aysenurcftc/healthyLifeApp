package com.example.mobilefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //#FF0E5711
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changePage(View view){
        Intent intent = new Intent(MainActivity.this, InformationActivity.class);
        startActivity(intent);
    }



}