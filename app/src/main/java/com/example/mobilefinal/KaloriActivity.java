package com.example.mobilefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KaloriActivity extends AppCompatActivity {

    private EditText editTextKalori;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori);

        editTextKalori = findViewById(R.id.editTextKalori);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kaloriValue = getKaloriValue();
                saveToDatabase(kaloriValue);
                editTextKalori.setText("");
                showToast("Kalori alındı!");
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Kaloriler", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS kaloriler (id INTEGER PRIMARY KEY, kalori VARCHAR)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Function to get the Kalori value from the EditText
    private String getKaloriValue() {
        return editTextKalori.getText().toString();
    }



    // Function to save Kalori value to the database
    private void saveToDatabase(String kaloriValue) {
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Kaloriler", MODE_PRIVATE, null);
            database.execSQL("INSERT INTO kaloriler (kalori) VALUES  ('" + kaloriValue + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }





}