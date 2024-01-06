package com.example.mobilefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
public class ListActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String[] data = {"Günlük Kalori Belirleme", "Günlük Egzersiz Belirleme", "Diyet Listesi Belirleme"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.grid_item, R.id.textViewTitle, new ArrayList<>(Arrays.asList(data)));

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = (String) parent.getItemAtPosition(position);
                Log.d("ListActivity", "Seçilen Öğe: " + selectedItem);
                if(selectedItem == "Günlük Kalori Belirleme"){
                    Intent intent = new Intent(getApplicationContext(), KaloriActivity.class);
                    startActivity(intent);
                }else if (selectedItem == "Günlük Egzersiz Belirleme"){
                    Intent intent = new Intent(getApplicationContext(), SporActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(), DiyetActivity.class);
                    startActivity(intent);
                }


            }
        });
    }
}